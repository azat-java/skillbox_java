import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.RecursiveTask;

import static java.util.stream.Collectors.toList;

public class SiteParser extends RecursiveTask<ConcurrentSkipListSet<String>> {
    private final String url;
    private final String root;
    private final ConcurrentSkipListSet<String> siteMap;

    public SiteParser(String url, String root, ConcurrentSkipListSet<String> siteMap) {
        this.url = url;
        this.root = root;
        this.siteMap = siteMap;
    }

    @Override
    protected ConcurrentSkipListSet<String> compute() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> linksArray = getLinksFromPage(url);
        List<SiteParser> tasks = new ArrayList<>();

        for (String curUrl : linksArray) {
            if (siteMap.contains(curUrl))
                continue;
            siteMap.add(curUrl);
            SiteParser task = new SiteParser(curUrl, root, siteMap);
            tasks.add(task);
            task.fork();
        }
        tasks.forEach(RecursiveTask::join);
        return siteMap;
    }

    protected List<String> getLinksFromPage(String url) {
        try {
            Document doc = Jsoup.parse(Jsoup.connect(url).userAgent("Mozilla").get().toString());
            Elements links = doc.select("a[href]");
            return links.stream()
                    .map(element -> element.attr("href"))
                    .filter(link -> !link.isEmpty() && !link.contains("#")
                            && !link.contains("?")
                            && !link.contains(".pdf")
                            && (link.startsWith("/") || link.startsWith(root))
                            && !link.equals("/"))
                    .map(link -> getFullLink(link, root)).collect(toList());
        } catch (HttpStatusException | UnsupportedMimeTypeException e){
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private String getFullLink(String link, String root) {
        return link.startsWith(root) ? link : root + link;
    }
}

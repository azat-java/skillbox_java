import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinTask;
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

        for (String curUrl : linksArray) {
            if (!siteMap.contains(curUrl)) {
                siteMap.add(curUrl);
                new Thread(() -> ForkJoinTask.invokeAll(new SiteParser(curUrl, root, siteMap))).start();
            }
        }
        return siteMap;
    }

    protected List<String> getLinksFromPage(String url) {
        try {
            Document doc = Jsoup.parse(Jsoup.connect(url).userAgent("Mozilla").get().toString());
            Elements links = doc.select("a[href]");
            return links.stream()
                    .filter(link -> !link.attr("href").isEmpty() && !link.attr("href").contains("#")
                            && !link.attr("href").contains("?")
                            && !link.attr("href").contains(".pdf")
                            && (link.attr("href").startsWith("/") || link.attr("href").startsWith(root))
                            && !link.attr("href").equals("/"))
                    .map(link -> getFullLink(link.attr("href"), root)).collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private String getFullLink(String link, String root) {
        return link.startsWith(root) ? link : root + link;
    }
}

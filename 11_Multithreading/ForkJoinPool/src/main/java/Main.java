import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final String URL = "https://skillbox.ru";
    private static final String FILE_NAME = "./sitemap.txt";

    public static void main(String[] args) {
        ConcurrentSkipListSet<String> siteMap = new ConcurrentSkipListSet<>();
        System.out.printf("Программа создания карты сайта %s\n\n", URL);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new SiteParser(URL, URL, siteMap));
        writeToFile(FILE_NAME, siteMap);
        System.out.printf("Карта сайта %s создана", URL);
    }

    public static void writeToFile(String fileName, ConcurrentSkipListSet<String> siteMap) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            PrintWriter printWriter = new PrintWriter(fileWriter);
            int spaces = 1;
            String previousUrl = null;
            Iterator<String> urlIterator = siteMap.iterator();
            Stack<String> parents = new Stack<>();
            while (urlIterator.hasNext()) {
                String curUrl = urlIterator.next();
                if (previousUrl != null) {
                    if (curUrl.contains(previousUrl)) {
                        spaces += 4;
                        parents.push(previousUrl);
                    } else {
                        while (!parents.isEmpty() && !curUrl.contains(parents.peek())) {
                            parents.pop();
                            spaces -= 4;
                        }
                    }
                }
                printWriter.printf("%" + spaces + "s%s\n", "", curUrl);
                previousUrl = curUrl;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

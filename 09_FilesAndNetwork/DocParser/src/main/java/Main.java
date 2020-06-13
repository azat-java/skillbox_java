import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {
    private static final String IMAGES_FOLDER = "./images/";
    private static final String URL = "https://lenta.ru/";

    public static void main(String[] args) {
        try {
            System.out.printf("Программа скачивания картинок с сайта %s\n\n", URL);
            System.out.println("Имена сохраненных файлов:");
            Document doc = Jsoup.parse(Jsoup.connect(URL).get().toString());
            Elements elements = doc.select("img");
            elements.forEach(img -> {
                try {
                    saveImage(img.attr("abs:src"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveImage(String src) throws IOException {
        if (src.isBlank()) {
            return;
        }
        String name = src.substring(src.lastIndexOf("/"));
        System.out.println(name.substring(1));

        URL url = new URL(src);
        try(InputStream in = url.openStream()) {
            Files.copy(in, Path.of(IMAGES_FOLDER, name), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
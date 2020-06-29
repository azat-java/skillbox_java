import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "C:/src";
        String dstFolder = "C:/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        Queue<File> fileQueue = new ConcurrentLinkedQueue<>(Arrays.asList(Objects.requireNonNull(srcDir.listFiles())));

        int processorsAmount = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < processorsAmount; i++) {
            ImageResizer resizer = new ImageResizer(fileQueue, newWidth, dstFolder, start);
            new Thread(resizer).start();
        }
    }
}

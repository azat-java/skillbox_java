import java.io.File;

public class Main {
    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "C:/src";
        String dstFolder = "C:/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int processorsAmount = Runtime.getRuntime().availableProcessors();
        int filesPerThreadAmount = files.length / processorsAmount;
        int filesForLastThread = files.length;
        int arrayLength = filesPerThreadAmount;
        for (int i = 0; i < processorsAmount; i++) {
            if (i == (processorsAmount - 1))
                arrayLength = filesForLastThread;
            filesForLastThread -= filesPerThreadAmount;
            File[] filesPerThread = new File[arrayLength];
            System.arraycopy(files, i * filesPerThreadAmount, filesPerThread, 0, filesPerThread.length);
            ImageResizer resizer = new ImageResizer(filesPerThread, newWidth, dstFolder, start);
            new Thread(resizer).start();
        }
    }
}

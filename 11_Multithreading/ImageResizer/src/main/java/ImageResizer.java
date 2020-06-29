import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Queue;

public class ImageResizer implements Runnable {
    private Queue<File> fileQueue;
    private int newWidth;
    private String dstFolder;
    private long start;

    public ImageResizer(Queue<File> fileQueue, int newWidth, String dstFolder, long start) {
        this.fileQueue = fileQueue;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        int resizedImages = 0;
        try {
            while (fileQueue.size() > 0) {
                File imageFile = fileQueue.poll();
                if (imageFile == null) {
                    return;
                }
                BufferedImage image = ImageIO.read(imageFile);

                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );

                BufferedImage newImage = Scalr.resize(image, newWidth, newHeight);

                File newFile = new File(dstFolder + "/" + imageFile.getName());
                ImageIO.write(newImage, "jpg", newFile);

                resizedImages++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Finished after start: " + (System.currentTimeMillis() - start) + " ms. Images amount for thread: " + resizedImages);
    }
}

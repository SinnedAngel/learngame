package arm.man.testsnake;

import android.graphics.Paint;

/**
 * Created by ARM on 01/08/18.
 */

public interface Graphics {
    public static enum ImageFormat{
        ARGB8888, ARGB4444, RGB565
    }

    Image newImage(String filename, ImageFormat imageFormat);

    void clearScreen(int color);

    void drawLine(int x, int y, int x2, int y2, int color);

    void drawRect(int x, int y, int width, int height, int color);

    void drawImage(Image image, int x, int y, int srcX, int srcY, int scrWidth, int scrHeight);

    void drawImage(Image image, int x, int y);

    void drawString(String text, int x, int y, Paint paint);

    int getWidth();

    int getHeight();

    void drawARGB(int a, int r, int g, int b);
}

package arm.man.testsnake.implementation;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;

import arm.man.testsnake.framework.Graphics;
import arm.man.testsnake.framework.Image;

/**
 * Created by ARM on 03/08/18.
 */

public class AndroidGraphics implements Graphics {
    AssetManager asset;
    Bitmap framebuffer;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    public AndroidGraphics(AssetManager asset, Bitmap framebuffer) {
        this.asset = asset;
        this.framebuffer = framebuffer;
        this.canvas = new Canvas(framebuffer);
        this.paint = new Paint();
    }

    @Override
    public Image newImage(String filename, ImageFormat format) {
        Bitmap.Config config = null;
        if (format == ImageFormat.RGB565)
            config = Bitmap.Config.RGB_565;
        else if (format == ImageFormat.ARGB4444)
            config = Bitmap.Config.ARGB_4444;
        else
            config = Bitmap.Config.ARGB_8888;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = asset.open(filename);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '" + filename + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '" + filename + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
        }

        if (bitmap.getConfig() == Bitmap.Config.RGB_565)
            format = ImageFormat.RGB565;
        else if (bitmap.getConfig() == Bitmap.Config.ARGB_4444)
            format = ImageFormat.ARGB4444;
        else
            format = ImageFormat.ARGB8888;

        return new AndroidImage(bitmap, format);
    }

    @Override
    public void clearScreen(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
    }

    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }

    @Override
    public void drawImage(Image image, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth;
        dstRect.bottom = y + srcHeight;

        canvas.drawBitmap(((AndroidImage) image).bitmap, srcRect, dstRect, null);
    }

    @Override
    public void drawImage(Image image, int x, int y) {
        canvas.drawBitmap(((AndroidImage) image).bitmap, x, y, null);
    }

    @Override
    public void drawString(String text, int x, int y, Paint paint) {
        canvas.drawText(text, x, y, paint);
    }

    @Override
    public int getWidth() {
        return framebuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return framebuffer.getHeight();
    }

    @Override
    public void drawARGB(int a, int r, int g, int b) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawARGB(a, r, g, b);
    }

    public void drawScaledImage(Image image, int x, int y, int width, int height, int srcX, int srcY, int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + width;
        dstRect.bottom = y + height;
        canvas.drawBitmap(((AndroidImage) image).bitmap, srcRect, dstRect, null);
    }
}

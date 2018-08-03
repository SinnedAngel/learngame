package arm.man.testsnake.framework;

/**
 * Created by ARM on 01/08/18.
 */

public interface Image {
    int getWidth();
    int getHeight();
    Graphics.ImageFormat getFormat();
    void dispose();
}

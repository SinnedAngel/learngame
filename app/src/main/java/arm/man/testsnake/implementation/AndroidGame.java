package arm.man.testsnake.implementation;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PowerManager.WakeLock;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import arm.man.testsnake.framework.Audio;
import arm.man.testsnake.framework.FileIO;
import arm.man.testsnake.framework.Game;
import arm.man.testsnake.framework.Graphics;
import arm.man.testsnake.framework.Input;
import arm.man.testsnake.framework.Screen;

/**
 * Created by ARM on 03/08/18.
 */

public class AndroidGame extends Activity implements Game {
    AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    WakeLock wakeLock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        int frameBufferWidth = isPortrait ? 800 : 1280;
        int frameBufferHeight = isPortrait ? 1280 : 800;
        Bitmap framebuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Bitmap.Config.RGB_565);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        float scaleX = (float) frameBufferWidth / size.x;
        float scaleY = (float) frameBufferHeight / size.y;

        renderView = new AndroidFastRenderView(this, framebuffer);
    }

    @Override
    public Audio getAudio() {
        return null;
    }

    @Override
    public Input getInput() {
        return null;
    }

    @Override
    public FileIO getFileIO() {
        return null;
    }

    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public void setScreen(Screen screen) {

    }

    @Override
    public Screen getCurrentScreen() {
        return null;
    }

    @Override
    public Screen getInitScreen() {
        return null;
    }
}

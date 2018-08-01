package arm.man.testsnake;

/**
 * Created by ARM on 01/08/18.
 */

public interface Game {
    Audio getAudio();
    Input getInput();
    FileIO getFileIO();
    Graphics getGraphics();
    void setScreen(Screen screen);
    Screen getCurrentScreen();
    Screen getInitScreen();
}

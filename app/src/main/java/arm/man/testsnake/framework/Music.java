package arm.man.testsnake.framework;

/**
 * Created by ARM on 01/08/18.
 */

public interface Music {
    void play();
    void stop();
    void pause();
    void setLooping(boolean looping);
    void setVolume(float volume);
    boolean isPlaying();
    boolean isStopped();
    boolean isLooping();
    void dispose();
    void seekBegin();
}

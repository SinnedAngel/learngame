package arm.man.testsnake.framework;

/**
 * Created by ARM on 01/08/18.
 */

public abstract class Screen {
    protected final Game game;

    public Screen(Game game){
        this.game = game;
    }

    public abstract void update(float deltaTime);
    public abstract void paint(float deltaTime);
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
    public abstract void backButton();
}

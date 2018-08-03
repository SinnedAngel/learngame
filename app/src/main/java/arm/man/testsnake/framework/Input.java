package arm.man.testsnake;

import java.util.List;

/**
 * Created by ARM on 01/08/18.
 */

public interface Input {
    static class TouchEvent {
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;
        public static final int TOUCH_HOLD = 3;

        public int type;
        public int x, y;
        public int pointer;
    }

    boolean isTouchDown(int pointer);
    int getTouchX(int pointer);
    int getTouchY(int pointer);
    List<TouchEvent> getTouchEvents();
}
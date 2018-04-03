package phdev.com.br.ritmando.cmp.models;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by ks_le on 01/04/2018.
 */

public interface Component {

    void update();
    void draw(Canvas canvas);
    boolean onTouchEvent(MotionEvent motionEvent);

}

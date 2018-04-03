package phdev.com.br.ritmando.cmp.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import phdev.com.br.ritmando.cmp.models.Component;
import phdev.com.br.ritmando.cmp.models.Entity;

/**
 * Created by ks_le on 01/04/2018.
 */

public class Note extends Entity implements Component {

    private Note(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();



        canvas.restoreToCount(savedState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}

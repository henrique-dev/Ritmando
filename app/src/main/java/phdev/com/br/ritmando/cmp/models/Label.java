package phdev.com.br.ritmando.cmp.models;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 03/04/2018.
 */

public class Label extends WindowEntity {

    public Label(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Label(Rect area) {
        super(area);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}

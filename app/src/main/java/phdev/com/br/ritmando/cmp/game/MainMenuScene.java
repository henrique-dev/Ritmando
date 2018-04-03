package phdev.com.br.ritmando.cmp.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import phdev.com.br.ritmando.cmp.environment.Scene;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
 */

public class MainMenuScene extends Scene {

    protected MainMenuScene(int x, int y, int width, int height) {
        super(x, y, width, height);
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

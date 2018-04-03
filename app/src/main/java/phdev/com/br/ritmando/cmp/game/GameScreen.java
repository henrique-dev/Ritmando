package phdev.com.br.ritmando.cmp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import phdev.com.br.ritmando.cmp.models.Screen;
import phdev.com.br.ritmando.cmp.models.Text;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
 */

public class GameScreen extends Screen {

    private Text myText;

    public GameScreen(int x, int y, int width, int height) {
        super(x, y, width, height);

        super.defaultPaint.setColor(Color.BLACK);

        this.myText = new Text(super.area, "Paulo Henrique");
        this.myText.setTextSize(0);

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();

        canvas.drawRect(super.area, super.defaultPaint);
        this.myText.draw(canvas);

        canvas.restoreToCount(savedState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}

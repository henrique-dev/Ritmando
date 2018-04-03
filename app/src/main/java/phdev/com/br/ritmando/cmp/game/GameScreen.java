package phdev.com.br.ritmando.cmp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import phdev.com.br.ritmando.cmp.models.Button;
import phdev.com.br.ritmando.cmp.models.Component;
import phdev.com.br.ritmando.cmp.models.Screen;
import phdev.com.br.ritmando.cmp.models.Text;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
 */

public class GameScreen extends Screen {

    private Text myText;
    Component cmp, cmp2;

    public GameScreen(int x, int y, int width, int height) {
        super(x, y, width, height);

        super.defaultPaint.setColor(Color.BLUE);

        //this.myText = new Text(super.area, "Paulo Henrique");
        //this.myText.setStroke(Color.BLUE, 2);

        cmp = new Button(0,0,700,250);
        cmp2 = new Button(0, 300, 700, 550);


    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();

        canvas.drawRect(super.area, super.defaultPaint);
        //this.myText.draw(canvas);
        cmp.draw(canvas);

        canvas.restoreToCount(savedState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        cmp.onTouchEvent(motionEvent);
        return false;
    }
}

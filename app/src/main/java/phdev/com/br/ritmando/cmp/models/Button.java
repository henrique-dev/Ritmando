package phdev.com.br.ritmando.cmp.models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import phdev.com.br.ritmando.cmp.effect.Effect;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
 */

public class Button extends Entity {

    private ArrayList<Effect> effects;
    private Listener listener;
    private Text buttonText;

    public Button(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Button(Rect area) {
        super(area);
    }

    public Button(Rect area, String buttonText) {
        super(area);
        this.buttonText = new Text(area, buttonText);
    }

    public Button(Rect area, Text buttonText) {
        super(area);
        this.buttonText = buttonText;
    }

    @Override
    public void update() {
        for (Effect eff : effects)
            eff.update();
    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();

        canvas.drawRect(super.area, super.defaultPaint);
        if (this.buttonText != null) {
            this.buttonText.draw(canvas);
        }

        canvas.restoreToCount(savedState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        int action = motionEvent.getActionMasked();
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        if (haveCollision(x, y, super.area)) {
            Random rand = new Random();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    //this.buttonText.setColor(Color.rgb(rand.nextInt(254), rand.nextInt(254), rand.nextInt(254)));
                    super.defaultPaint.setColor(Color.rgb(rand.nextInt(254), rand.nextInt(254), rand.nextInt(254)));
                    break;
            }
        }

        return false;
    }
}

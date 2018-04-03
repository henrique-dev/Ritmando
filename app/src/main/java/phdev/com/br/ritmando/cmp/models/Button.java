package phdev.com.br.ritmando.cmp.models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import phdev.com.br.ritmando.GameLog;
import phdev.com.br.ritmando.cmp.effect.Effect;
import phdev.com.br.ritmando.cmp.effect.Fade;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
 */

public class Button extends Entity {

    private ArrayList<Effect> effects;
    private Listener listener;
    private Text buttonText;
    private boolean active;

    public Button(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.active = true;
        effects = new ArrayList<>();
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

    public void addListener(Listener listener) {
        this.listener = listener;
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

        if (haveCollision(x, y, super.area) && active) {
            //Random rand = new Random();
            GameLog.debug(this, "EXECUTOU");
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    //super.defaultPaint.setColor(Color.rgb(rand.nextInt(254), rand.nextInt(254), rand.nextInt(254)));
                    active = false;
                    Fade fade = new Fade(this, Fade.FADEOUT);
                    fade.addListener(new Listener() {
                        @Override
                        public void execute() {
                            if (listener != null)
                                listener.execute();
                            //active = true;
                        }
                    });
                    effects.add(fade);

                    break;
            }
        }

        return false;
    }
}

package phdev.com.br.ritmando.cmp.models;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import phdev.com.br.ritmando.GameLog;
import phdev.com.br.ritmando.cmp.effect.Effect;
import phdev.com.br.ritmando.cmp.effect.Fade;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
 */

public class Button extends WindowEntity {

    public Button(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Button(Rect area) {
        super(area);
    }

    public Button(Rect area, String buttonText) {
        super(area);
        super.entityText = new Text(area, buttonText);
    }

    public Button(Rect area, Text buttonText) {
        super(area);
        super.entityText = buttonText;
    }

    private void fire() {
        final Fade fade = new Fade(this, Fade.FADEOUT);
        fade.addListener(new ActionListener() {
            @Override
            public void actionPerformed(Event evt) {
                ((ActionListener)listener).actionPerformed(evt);
                effects.remove(fade);
            }
        });
        fade.start();
        super.effects.add(fade);
    }

    public void addActionListener(ActionListener listener) {
        super.addListener(listener);
    }

    public void addClickListener(ClickListener listener) {
        super.addListener(listener);
    }

    @Override
    public void update() {
        for (Effect eff : super.effects)
            eff.update();
        GameLog.debug(this, super.effects.size() + "");
    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();

        canvas.drawRect(super.area, super.defaultPaint);
        if (super.entityText != null) {
            super.entityText.draw(canvas);
        }

        canvas.restoreToCount(savedState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!super.active)
            return false;

        int action = motionEvent.getActionMasked();
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        if (haveCollision(x, y, super.area)) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    fire();
                    break;
            }
        }
        return false;
    }
}

package phdev.com.br.ritmando.cmp.window;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

import phdev.com.br.ritmando.GameLog;
import phdev.com.br.ritmando.cmp.effect.ClickEffect;
import phdev.com.br.ritmando.cmp.effect.Effect;
import phdev.com.br.ritmando.cmp.effect.Fade;
import phdev.com.br.ritmando.cmp.effect.Flash;
import phdev.com.br.ritmando.cmp.listeners.ActionListener;
import phdev.com.br.ritmando.cmp.listeners.ClickListener;
import phdev.com.br.ritmando.cmp.listeners.events.Event;
import phdev.com.br.ritmando.cmp.window.utils.Text;
import phdev.com.br.ritmando.cmp.models.WindowEntity;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
 */

public class Button extends WindowEntity {

    public static final int EFFECT_CLICK = 0;

    public Button(int x, int y, int width, int height) {
        super(new Rect(x, y, x + width, y + height));
        super.effects = new ArrayList<>(2);
        this.changeActionEffect(ClickEffect.FLASHING);
    }

    public Button(Rect area) {
        super(area);
        super.effects = new ArrayList<>(2);
        this.changeActionEffect(ClickEffect.FADE_IN_OUT);
    }

    public Button(Rect area, String buttonText) {
        super(area);
        super.entityText = new Text(area, buttonText);
        super.effects = new ArrayList<>(2);
        this.changeActionEffect(ClickEffect.FADE_IN_OUT);
    }

    public Button(Rect area, Text buttonText) {
        super(area);
        super.entityText = buttonText;

    }

    private void changeActionEffect(int typeEffect) {
        if (typeEffect == ClickEffect.FADE_IN_OUT) {
            super.effects.add(EFFECT_CLICK,  new Fade(this, Fade.FADEOUT, new ActionListener() {
                @Override
                public void actionPerformed(Event evt) {
                    fire(evt);
                }
            }));
        } else if (typeEffect == ClickEffect.FLASHING) {
            super.effects.add(EFFECT_CLICK,  new Flash(this, new ActionListener() {
                @Override
                public void actionPerformed(Event evt) {
                    fire(evt);
                }
            }));
        }
    }

    private void fire(Event evt) {
        ((ActionListener)listener).actionPerformed(evt);
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
                    ((ClickEffect)super.effects.get(0)).start();
                    break;
            }
        }
        return false;
    }
}
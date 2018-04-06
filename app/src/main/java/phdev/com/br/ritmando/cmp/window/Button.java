package phdev.com.br.ritmando.cmp.window;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

import phdev.com.br.ritmando.cmp.effect.ClickEffect;
import phdev.com.br.ritmando.cmp.effect.Effect;
import phdev.com.br.ritmando.cmp.effect.FadeEffect;
import phdev.com.br.ritmando.cmp.effect.FlashEffect;
import phdev.com.br.ritmando.cmp.listeners.ActionListener;
import phdev.com.br.ritmando.cmp.listeners.ClickListener;
import phdev.com.br.ritmando.cmp.listeners.events.Event;
import phdev.com.br.ritmando.cmp.utils.Text;
import phdev.com.br.ritmando.cmp.models.WindowEntity;

/*
 * Copyright (C) 2018 Paulo Henrique Gonçalves Bacelar
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class Button extends WindowEntity {

    public static final int EFFECT_CLICK = 0;
    private int DEFAULT_CLICK_EFFECT = ClickEffect.FLASHING;

    private boolean clicked = false;

    public Button(int x, int y, int width, int height) {
        super(new Rect(x, y, x + width, y + height));
        super.effects = new ArrayList<>(2);
        this.changeActionEffect(DEFAULT_CLICK_EFFECT);
    }

    public Button(Rect area) {
        super(area);
        super.effects = new ArrayList<>(2);
        this.changeActionEffect(DEFAULT_CLICK_EFFECT);
    }

    public Button(Rect area, String buttonText) {
        super(area);
        super.entityText = new Text(area, buttonText);
        super.effects = new ArrayList<>(2);
        this.changeActionEffect(DEFAULT_CLICK_EFFECT);
    }

    public Button(Rect area, Text buttonText) {
        super(area);
        super.entityText = buttonText;
        super.effects = new ArrayList<>(2);
        this.changeActionEffect(DEFAULT_CLICK_EFFECT);
    }

    public Button(String textButton) {
        super(new Rect());
        super.entityText = new Text(new Rect(), textButton);
        super.effects = new ArrayList<>(2);
        this.changeActionEffect(DEFAULT_CLICK_EFFECT);
    }

    @Override
    public void setArea(Rect area) {
        super.setArea(area);
        if (super.entityText != null)
            super.entityText.setArea(new Rect(area));
    }

    public void setText(String text) {
        super.entityText.setText(text);
    }

    public String getText() {
        return super.entityText.getText();
    }

    public void setTextSize(float size) {
        super.entityText.setTextSize(size);
    }

    public void setColor(int color) {
        super.defaultPaint.setColor(color);
    }

    public int getColor() {
        return super.defaultPaint.getColor();
    }

    public void changeActionEffect(int typeEffect) {
        DEFAULT_CLICK_EFFECT = typeEffect;
        if (typeEffect == ClickEffect.FADE_IN_OUT) {
            super.effects.add(EFFECT_CLICK,  new FadeEffect(this, FadeEffect.FADEOUT, new ActionListener() {
                @Override
                public void actionPerformed(Event evt) {
                    fire(evt);
                    Button.this.clicked = false;
                }
            }));
        } else if (typeEffect == ClickEffect.FLASHING) {
            super.effects.add(EFFECT_CLICK,  new FlashEffect(this, new ActionListener() {
                @Override
                public void actionPerformed(Event evt) {
                    fire(evt);
                    Button.this.clicked = false;
                }
            }));
        }
    }

    private void fire(Event evt) {
        if (super.listener != null)
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
        if (this.clicked)
            return false;

        int action = motionEvent.getActionMasked();
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        if (haveCollision(x, y, super.area)) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    this.clicked = true;
                    ((ClickEffect)super.effects.get(0)).start();
                    break;
            }
        }
        return false;
    }
}

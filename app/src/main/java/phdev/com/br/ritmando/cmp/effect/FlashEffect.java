package phdev.com.br.ritmando.cmp.effect;

import phdev.com.br.ritmando.cmp.listeners.ActionListener;
import phdev.com.br.ritmando.cmp.models.Entity;

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

public class FlashEffect extends ClickEffect {

    private int flashCounter;
    private int maxFlashs;
    private int speed;
    private boolean flashin;
    private boolean flashout;

    public FlashEffect(Entity entity, ActionListener actionListener) {
        super(entity, actionListener);
        this.flashout = true;
        this.maxFlashs = 1;
        this.speed = 35;
    }

    public void setSpeed(int speed) {
        if (speed <= 0)
            this.speed = 1;
        else if (speed > 255)
            this.speed = 255;
        else
            this.speed = speed;
    }

    public void setMaxFlashs(int maxFlashs) {
        if (maxFlashs <= 0)
            this.maxFlashs = 1;
        else
            this.maxFlashs = maxFlashs;

    }

    public void addActionListener(ActionListener actionListener) {
        super.actionListener = actionListener;
    }

    @Override
    public void update() {
        if (super.running) {
            if (this.flashin) {
                int alpha = super.entity.getDefaultPaint().getAlpha();
                alpha += this.speed;
                if (alpha > 255) {
                    alpha = 255;
                    super.entity.getDefaultPaint().setAlpha(alpha);

                    if (flashCounter >= maxFlashs) {
                        this.flashin = false;
                        this.flashout = false;
                        this.running = false;
                        super.actionListener.actionPerformed(null);
                        reset();
                        return;
                    }

                    this.flashin = false;
                    this.flashout = true;
                } else
                    super.entity.getDefaultPaint().setAlpha(alpha);
            } else if (this.flashout) {
                int alpha = super.entity.getDefaultPaint().getAlpha();
                alpha -= this.speed;
                if (alpha < 0) {
                    alpha = 0;
                    super.entity.getDefaultPaint().setAlpha(alpha);
                    this.flashCounter++;
                    this.flashout = false;
                    this.flashin = true;
                } else
                    super.entity.getDefaultPaint().setAlpha(alpha);
            }
        }
    }

    @Override
    protected void reset() {
        this.flashCounter = 0;
        this.flashout = true;
    }

}

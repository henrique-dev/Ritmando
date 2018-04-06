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
package phdev.com.br.ritmando.cmp.effect;

import phdev.com.br.ritmando.cmp.listeners.ActionListener;
import phdev.com.br.ritmando.cmp.models.Entity;

/**
 * Classe para criação de efeitos do tipo flash.
 * @version 1.0
 */
public class FlashEffect extends Effect {

    /**
     * Quantidade atual de flashs executados.
     */
    private int flashCounter;

    /**
     * Quantidade necessaria de flashs para terminar o efeito.
     */
    private int maxFlash;

    /**
     * Taxa de velocidade dos flashs.
     */
    private int speed;

    /**
     * Estado atual do efeito.
     */
    private boolean flashIn, flashOut;

    private int originalAlpha;

    /**
     * Cria o efeito do tipo flash.
     */
    public FlashEffect() {
        super(null, null);
        this.flashOut = true;
        this.maxFlash = 1;
        this.speed = 63;
        this.flashCounter = 0;
    }

    /**
     * Cria o efeito do tipo flash.
     *
     * @param entity entidade para ser aplicado o efeito.
     * @param actionListener escuta para o evento para ser executado apos o efeito.
     */
    public FlashEffect(Entity entity, ActionListener actionListener) {
        super(entity, actionListener);
        this.originalAlpha = entity.getDefaultPaint().getAlpha();
        this.flashOut = true;
        this.maxFlash = 1;
        this.speed = 35;
        this.flashCounter = 0;
    }

    /**
     * Cria o efeito do tipo flash.
     *
     * @param entity entidade para ser aplicado o efeito.
     * @param actionListener escuta para o evento para ser executado apos o efeito.
     * @param speed velocidade do efeito.
     * @param maxFlash quantidade necessaria de flashs para terminar o efeito.
     */
    public FlashEffect(Entity entity, ActionListener actionListener, int speed, int maxFlash) {
        super(entity, actionListener);
        this.flashOut = true;
        this.maxFlash = maxFlash;
        this.speed = speed;
    }

    @Override
    public void setEntity(Entity entity) {
        super.setEntity(entity);
        this.originalAlpha = entity.getDefaultPaint().getAlpha();
    }

    public void setSpeed(int speed) {
        if (speed <= 0)
            this.speed = 1;
        else if (speed > 255)
            this.speed = 255;
        else
            this.speed = speed;
    }

    public void setMaxFlash(int maxFlash) {
        if (maxFlash <= 0)
            this.maxFlash = 1;
        else
            this.maxFlash = maxFlash;

    }

    public void addActionListener(ActionListener actionListener) {
        super.actionListener = actionListener;
    }

    @Override
    public void update() {
        if (super.running) {
            if (this.flashIn) {
                int alpha = super.entity.getDefaultPaint().getAlpha();
                alpha += this.speed;
                if (alpha > 255) {
                    alpha = 255;
                    super.entity.getDefaultPaint().setAlpha(alpha);
                    if (flashCounter >= maxFlash) {
                        this.stop();
                        super.actionListener.actionPerformed(null);
                    } else {
                        this.flashIn = false;
                        this.flashOut = true;
                    }
                } else
                    super.entity.getDefaultPaint().setAlpha(alpha);
            } else if (this.flashOut) {
                int alpha = super.entity.getDefaultPaint().getAlpha();
                alpha -= this.speed;
                if (alpha < 0) {
                    alpha = 0;
                    super.entity.getDefaultPaint().setAlpha(alpha);
                    this.flashCounter++;
                    this.flashOut = false;
                    this.flashIn = true;
                } else
                    super.entity.getDefaultPaint().setAlpha(alpha);
            }
        }
    }

    @Override
    public void stop() {
        this.flashOut = true;
        this.flashOut = false;
        super.entity.getDefaultPaint().setAlpha(this.originalAlpha);
        this.running = false;
        this.flashCounter = 0;
    }

}

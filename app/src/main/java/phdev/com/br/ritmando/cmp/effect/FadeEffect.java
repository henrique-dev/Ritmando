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
 * Classe para criação de efeitos do tipo fade-in / fade-out.
 * @version 1.0
 */
public class FadeEffect extends ClickEffect {

    public static final int FADEIN = 1;
    public static final int FADEOUT = 2;

    /**
     * Estado que define o tipo do efeito.
     */
    private boolean fadein;

    /**
     * Estado que define o tipo do efeito.
     */
    private boolean fadeout;

    /**
     * Taxa de variação do atributo de transparencia.
     */
    private int alphaDiv;

    @Deprecated
    private int originalAlpha;

    /**
     * Cria o efeito do tipo fade, podendo ser de saida ou de entrada.
     *
     * @param entity entidade para ser aplicado o efeito.
     * @param fadeType subtipo de efeito.
     * @param actionListener escuta para o evento para ser executado apos o efeito.
     */
    public FadeEffect(Entity entity, int fadeType, ActionListener actionListener) {
        super(entity, actionListener);
        this.originalAlpha = entity.getDefaultPaint().getAlpha();
        this.alphaDiv = 20;
        if (fadeType == FADEIN)
            fadein = true;
        else if (fadeType == FADEOUT)
            fadeout = true;
    }

    /**
     * Redefine a escuta para o evento a ser executado apos o efeito.
     *
     * @param listener escuta contendo o evento.
     */
    public void addActionListener(ActionListener listener) {
        super.actionListener = listener;
    }

    @Override
    public void update() {
        if (super.running) {
            if (this.fadein) {
                int alpha = super.entity.getDefaultPaint().getAlpha();
                alpha += this.alphaDiv;
                if (alpha > 255) {
                    alpha = 255;
                    super.entity.getDefaultPaint().setAlpha(alpha);
                    super.actionListener.actionPerformed(null);
                    this.fadein = false;
                } else
                    super.entity.getDefaultPaint().setAlpha(alpha);
            } else if (this.fadeout) {
                int alpha = super.entity.getDefaultPaint().getAlpha();
                alpha -= this.alphaDiv;
                if (alpha < 0) {
                    alpha = 0;
                    super.entity.getDefaultPaint().setAlpha(alpha);
                    super.actionListener.actionPerformed(null);
                    this.fadeout = false;
                } else
                    super.entity.getDefaultPaint().setAlpha(alpha);
            }
        }
    }

    @Override
    public void reset() {

    }

}

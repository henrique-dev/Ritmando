package phdev.com.br.ritmando.cmp.effect;

import phdev.com.br.ritmando.cmp.listeners.ActionListener;
import phdev.com.br.ritmando.cmp.models.Entity;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 03/04/2018.
 */

public class Fade extends ClickEffect {

    public static final int FADEIN = 1;
    public static final int FADEOUT = 2;

    private boolean fadein;
    private boolean fadeout;

    private int alphaDiv;

    private int originalAlpha;

    public Fade(Entity entity, int fadeType, ActionListener actionListener) {
        super(entity, actionListener);
        this.originalAlpha = entity.getDefaultPaint().getAlpha();
        this.alphaDiv = 20;
        if (fadeType == FADEIN)
            fadein = true;
        else if (fadeType == FADEOUT)
            fadeout = true;
    }

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

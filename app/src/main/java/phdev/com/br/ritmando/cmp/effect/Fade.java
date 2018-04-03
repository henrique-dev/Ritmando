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
    private boolean blinking;

    private int alpha;

    public Fade(Entity entity, int fadeType, ActionListener actionListener) {
        super(entity, actionListener);
        this.alpha = entity.getDefaultPaint().getAlpha();
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
                this.alpha += 10;
                super.entity.getDefaultPaint().setAlpha(alpha);
                if (this.alpha > 255) {
                    this.alpha = 255;
                    super.entity.getDefaultPaint().setAlpha(alpha);
                    super.actionListener.actionPerformed(null);
                    this.fadein = false;
                }
            }
            if (this.fadeout) {
                this.alpha -= 10;
                super.entity.getDefaultPaint().setAlpha(alpha);
                if (this.alpha < 0) {
                    this.alpha = 0;
                    super.entity.getDefaultPaint().setAlpha(alpha);
                    super.actionListener.actionPerformed(null);
                    this.fadeout = false;
                }
            }
        }
    }

}

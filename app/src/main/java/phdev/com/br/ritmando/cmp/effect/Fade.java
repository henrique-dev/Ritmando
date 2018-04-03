package phdev.com.br.ritmando.cmp.effect;

import phdev.com.br.ritmando.cmp.models.ActionListener;
import phdev.com.br.ritmando.cmp.models.Entity;
import phdev.com.br.ritmando.cmp.models.Event;
import phdev.com.br.ritmando.cmp.models.Listener;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 03/04/2018.
 */

public class Fade implements ClickEffect {

    public static final int FADEIN = 1;
    public static final int FADEOUT = 2;

    private ActionListener listener;
    private Entity entity;

    private boolean fadein;
    private boolean fadeout;
    private boolean blinking;

    private int alpha;

    private boolean running;

    public Fade(Entity entity, int fadeType) {
        this.entity = entity;
        this.alpha = entity.getDefaultPaint().getAlpha();
        if (fadeType == FADEIN)
            fadein = true;
        else if (fadeType == FADEOUT)
            fadeout = true;
    }

    public void addListener(ActionListener listener) {
        this.listener = listener;
    }

    @Override
    public void start() {
        this.running = true;
    }

    @Override
    public void stop() {
        this.running = false;
    }

    @Override
    public void update() {
        if (this.running) {
            if (this.fadein) {
                this.alpha += 10;
                this.entity.getDefaultPaint().setAlpha(alpha);
                if (this.alpha > 255) {
                    this.alpha = 255;
                    this.entity.getDefaultPaint().setAlpha(alpha);
                    this.listener.actionPerformed(null);
                    this.fadein = false;
                }
            }
            if (this.fadeout) {
                this.alpha -= 10;
                this.entity.getDefaultPaint().setAlpha(alpha);
                if (this.alpha < 0) {
                    this.alpha = 0;
                    this.entity.getDefaultPaint().setAlpha(alpha);
                    this.listener.actionPerformed(null);
                    this.fadeout = false;
                }
            }
        }
    }

}

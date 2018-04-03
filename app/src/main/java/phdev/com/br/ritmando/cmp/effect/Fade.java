package phdev.com.br.ritmando.cmp.effect;

import phdev.com.br.ritmando.cmp.models.Entity;
import phdev.com.br.ritmando.cmp.models.Listener;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 03/04/2018.
 */

public class Fade implements Effect {

    public static final int BLINKING = 0;
    public static final int FADEIN = 1;
    public static final int FADEOUT = 2;

    private Listener listener;
    private Entity entity;

    private boolean fadein;
    private boolean fadeout;
    private boolean blinking;

    private int alpha;

    public Fade(Entity entity, int fadeType) {

    }

    @Override
    public void update() {
        if (fadein) {
            alpha += 5;
            if (alpha > 255) {
                if (!blinking)
                    listener.execute();
                fadein = false;
            }
        }
        if (fadeout) {
            alpha -= 5;
            if (alpha < 0) {
                if (!blinking)
                    listener.execute();
                fadeout = false;
            }
        }
    }

}

package phdev.com.br.ritmando.cmp.effect;

import phdev.com.br.ritmando.cmp.listeners.ActionListener;
import phdev.com.br.ritmando.cmp.models.Entity;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 03/04/2018.
 */

public class Flash extends ClickEffect {

    private int flashCounter;
    private boolean fadein;
    private boolean fadeout;

    protected Flash(Entity entity, ActionListener actionListener) {
        super(entity, actionListener);
    }

    @Override
    public void update() {

    }

}

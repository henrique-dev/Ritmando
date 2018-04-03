package phdev.com.br.ritmando.cmp.effect;

import phdev.com.br.ritmando.cmp.listeners.ActionListener;
import phdev.com.br.ritmando.cmp.models.Entity;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 03/04/2018.
 */

public abstract class ClickEffect implements Effect {

    protected ActionListener actionListener;
    protected Entity entity;
    protected boolean running;

    protected ClickEffect(Entity entity, ActionListener actionListener) {
        this.entity = entity;
        this.actionListener = actionListener;
    }

    public final Effect start() {
        this.running = true;
        return this;
    }

    public final Effect stop() {
        this.running = false;
        return this;
    }

}

package phdev.com.br.ritmando.cmp.models;

import android.graphics.Rect;

import java.util.ArrayList;

import phdev.com.br.ritmando.cmp.effect.Effect;
import phdev.com.br.ritmando.cmp.listeners.Listener;
import phdev.com.br.ritmando.cmp.window.utils.Text;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 03/04/2018.
 */

public abstract class WindowEntity extends Entity {

    protected ArrayList<Effect> effects;
    protected Listener listener;
    protected Text entityText;

    protected WindowEntity(Rect area) {
        super(area);
        this.effects = new ArrayList<>();
        super.active = true;
    }

    protected void addListener(Listener listener) {
        this.listener = listener;
    }
}

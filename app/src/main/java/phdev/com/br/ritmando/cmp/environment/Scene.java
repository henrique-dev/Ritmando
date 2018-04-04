package phdev.com.br.ritmando.cmp.environment;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

import phdev.com.br.ritmando.cmp.models.Component;
import phdev.com.br.ritmando.cmp.models.Entity;

/**
 * Created by ks_le on 01/04/2018.
 */

public abstract class Scene extends Entity implements Component {

    private ArrayList<Entity> entities = new ArrayList<>();

    protected Scene(int x, int y, int width, int height) {
        super(new Rect(x, y, x + width, y + height));
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public Entity getEntity(int index) {
        return this.entities.get(index);
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    @Override
    public void update() {
        for (Entity ent : this.entities)
            if (ent.isActive())
                ent.update();
    }

    @Override
    public void draw(Canvas canvas) {
        for (Entity ent : this.entities)
            if (ent.isVisible())
                ent.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        for (Entity ent : this.entities)
            if (ent.isActive())
                ent.onTouchEvent(motionEvent);
        return false;
    }


}

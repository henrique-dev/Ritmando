package phdev.com.br.ritmando.cmp.window;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

import phdev.com.br.ritmando.cmp.models.Component;
import phdev.com.br.ritmando.cmp.models.Entity;
import phdev.com.br.ritmando.cmp.models.WindowEntity;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 03/04/2018.
 */

public class Window extends WindowEntity {

    private ArrayList<Entity> entities = new ArrayList<>();
    private Button closeButton;

    private Layout layout;

    public Window() {
        super();
        this.layout = new ListLayout(ListLayout.VERTICAL_ALINGMENT);
    }

    public Window(int x, int y, int width, int height) {
        super(new Rect(x, y, x+ width, y + height));
        this.layout = new ListLayout(ListLayout.VERTICAL_ALINGMENT);
    }

    public Window(Rect area, Layout layout) {
        super(area);
        this.layout = layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
        this.layout.set(this);
    }

    public Layout getLayout() {
        return this.layout;
    }

    public void add(Entity entity) {
        this.entities.add(entity);
        this.layout.format();
    }

    public void remove(Entity entity) {
        this.entities.remove(entity);
    }

    public Entity get(int index) {
        return entities.get(index);
    }

    public ArrayList<Entity> get() {
        return this.entities;
    }

    @Override
    public void update() {
        for (Entity ent : entities)
            if (ent.isActive())
                ent.update();
    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();
        canvas.drawRect(super.area, super.defaultPaint);
        for (Entity ent : entities)
            if (ent.isVisible())
                ent.draw(canvas);
        canvas.restoreToCount(savedState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        for (Entity ent : entities) {
            if (ent.isActive())
                ent.onTouchEvent(motionEvent);
        }
        return false;
    }

}

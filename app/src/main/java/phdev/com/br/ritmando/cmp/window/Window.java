package phdev.com.br.ritmando.cmp.window;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

import phdev.com.br.ritmando.cmp.models.Component;
import phdev.com.br.ritmando.cmp.models.WindowEntity;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 03/04/2018.
 */

public class Window extends WindowEntity {

    private ArrayList<Component> components = new ArrayList<>();
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

    public void addComponent(Component component) {
        this.components.add(component);
        this.layout.format();
    }

    public void removeComponent(Component component) {
        this.components.remove(component);
    }

    public Component getComponent(int index) {
        return components.get(index);
    }

    public ArrayList<Component> getComponents() {
        return this.components;
    }

    @Override
    public void update() {
        for (Component cmp : components)
            cmp.update();
    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();
        canvas.drawRect(super.area, super.defaultPaint);
        for (Component cmp : components)
            cmp.draw(canvas);
        canvas.restoreToCount(savedState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        for (Component cmp : components) {
            cmp.onTouchEvent(motionEvent);
        }
        return false;
    }

}

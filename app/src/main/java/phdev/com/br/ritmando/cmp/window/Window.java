package phdev.com.br.ritmando.cmp.window;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

import phdev.com.br.ritmando.GameLog;
import phdev.com.br.ritmando.cmp.models.Component;
import phdev.com.br.ritmando.cmp.models.WindowEntity;
import phdev.com.br.ritmando.cmp.window.utils.Layout;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 03/04/2018.
 */

public class Window extends WindowEntity {

    private ArrayList<Component> components;
    private Button closeButton;

    private Layout layout;

    public Window(int x, int y, int width, int height) {
        super(new Rect(x, y, x+ width, y + height));
        this.components = new ArrayList<>();
    }

    public Window(Rect area, Layout layout) {
        super(area);
        this.components = new ArrayList<>();
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
        this.layout.set(components);
    }

    public Layout getLayout() {
        return this.layout;
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public void removeComponent(Component component) {
        this.components.remove(component);
    }

    public Component getComponent(int index) {
        return components.get(index);
    }

    @Override
    public void update() {
        for (Component cmp : components)
            cmp.update();
    }

    @Override
    public void draw(Canvas canvas) {
        for (Component cmp : components)
            cmp.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        for (Component cmp : components) {
            cmp.onTouchEvent(motionEvent);
        }
        return false;
    }

}

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

public abstract class Screen extends Entity implements Component {

    private ArrayList<Scene> scenes;

    protected Screen(int x, int y, int width, int height) {
        super(new Rect(x, y, x + width, y + height));
        this.scenes = new ArrayList<>();
    }

    protected void addScene(Scene scene) {
        this.scenes.add(scene);
    }

    protected void removeScene(Scene scene) {
        this.scenes.remove(scene);
    }

    protected Scene getScene(int index) {
        return this.scenes.get(index);
    }

    protected ArrayList<Scene> getScenes() {
        return this.scenes;
    }

    @Override
    public void update() {
        for (Scene sc : this.scenes)
            if (sc.isActive())
                sc.update();
    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();
        canvas.drawRect(super.area, super.defaultPaint);
        for (Scene sc : this.scenes) {
            if (sc.isVisible())
                sc.draw(canvas);
        }
        canvas.restoreToCount(savedState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        for (Scene sc : this.scenes)
            if (sc.isActive())
                sc.onTouchEvent(motionEvent);
        return false;
    }
}

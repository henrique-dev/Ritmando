package phdev.com.br.ritmando.cmp.models;

import android.graphics.Paint;
import android.graphics.Rect;

import phdev.com.br.ritmando.cmp.models.Component;

/**
 * Created by ks_le on 01/04/2018.
 */

public abstract class Entity implements Component {

    protected Rect area;
    protected Paint defaultPaint;
    protected boolean active = true;
    protected boolean visible = true;

    protected Entity() {
        this.area = new Rect();
        this.defaultPaint = new Paint();
    }

    protected Entity(Rect area) {
        this.area = new Rect(area);
        this.defaultPaint = new Paint();
    }

    protected static boolean haveCollision(float x, float y, Rect area) {
        return (x > area.left && x < area.right && y > area.top && y < area.bottom);
    }

    public Rect getArea() {
        return this.area;
    }

    public void setArea(Rect area) {
        this.area = area;
    }

    public Paint getDefaultPaint() {
        return this.defaultPaint;
    }

    public void setDefaultPaint(Paint defaultPaint) {
        this.defaultPaint = defaultPaint;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}

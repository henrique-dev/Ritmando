package phdev.com.br.ritmando.cmp.models;

import android.graphics.Paint;
import android.graphics.Rect;

import phdev.com.br.ritmando.cmp.models.Component;

/*
 * Copyright (C) 2018 Paulo Henrique Gonçalves Bacelar
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

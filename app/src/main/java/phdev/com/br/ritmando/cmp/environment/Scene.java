package phdev.com.br.ritmando.cmp.environment;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

import phdev.com.br.ritmando.cmp.models.Component;
import phdev.com.br.ritmando.cmp.models.Entity;

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

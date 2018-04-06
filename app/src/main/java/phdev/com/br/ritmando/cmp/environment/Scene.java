package phdev.com.br.ritmando.cmp.environment;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

import phdev.com.br.ritmando.cmp.models.Component;
import phdev.com.br.ritmando.cmp.models.Entity;
import phdev.com.br.ritmando.cmp.models.GameEntity;
import phdev.com.br.ritmando.cmp.models.WindowEntity;

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

    private ArrayList<WindowEntity> windowEntities;
    private ArrayList<GameEntity> gameEntities;

    protected Scene(int x, int y, int width, int height) {
        super(new Rect(x, y, x + width, y + height));
        this.windowEntities = new ArrayList<>();
        this.gameEntities = new ArrayList<>();
    }

    public void add(WindowEntity windowEntity) {
        this.windowEntities.add(windowEntity);
    }

    public void add(GameEntity gameEntity) {
        this.gameEntities.add(gameEntity);
    }

    @Override
    public void update() {
        for (Entity ent : this.gameEntities)
            if (ent.isActive())
                ent.update();
        for (Entity ent : this.windowEntities)
            if (ent.isActive())
                ent.update();
    }

    @Override
    public void draw(Canvas canvas) {
        for (Entity ent : this.gameEntities)
            if (ent.isVisible())
                ent.draw(canvas);
        for (Entity ent : this.windowEntities)
            if (ent.isVisible())
                ent.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        for (Entity ent : this.windowEntities)
            if (ent.isActive())
                ent.onTouchEvent(motionEvent);
        return false;
    }


}

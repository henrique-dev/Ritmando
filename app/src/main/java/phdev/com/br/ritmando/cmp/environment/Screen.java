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

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
package phdev.com.br.ritmando.cmp.window;

import android.graphics.Rect;

import java.util.ArrayList;

import phdev.com.br.ritmando.cmp.models.Entity;

public class ListLayout implements Layout{

    public static final int HORIZONTAK_ALINGMENT = 0;
    public static final int VERTICAL_ALINGMENT = 1;

    private int alignment;
    private int spaceH;
    private int spaceW;

    private Entity entity;
    //private ArrayList<Component> components;

    public ListLayout() {
        this.alignment = VERTICAL_ALINGMENT;
        this.spaceW = 0;
        this.spaceH = 0;
    }

    public ListLayout(int alignment) {
        this.alignment = alignment;
        this.spaceW = 0;
        this.spaceH = 0;
    }

    public ListLayout(int alignment, int spaceW, int spaceH) {
        this.alignment = alignment;
        this.spaceH = spaceH;
        this.spaceW = spaceW;
    }

    @Override
    public void set(Entity entity) {
        this.entity = entity;
        this.format();
    }

    @Override
    public void format() {
        ArrayList<Entity> tmpEntity = ((Window)this.entity).get();
        if (!(tmpEntity.size() > 0))
            return;

        int x = this.entity.getArea().left;
        int y = this.entity.getArea().top;
        if (this.alignment == HORIZONTAK_ALINGMENT) {
            int height = this.entity.getArea().height() - this.spaceH * 2;
            int cmpWidth = (this.entity.getArea().width() - this.spaceW * (tmpEntity.size()+1)) / tmpEntity.size();
            for (int i=0; i<tmpEntity.size(); i++) {
                Entity tmpEnt = tmpEntity.get(i);
                tmpEnt.setArea(new Rect(
                        (this.spaceW + this.spaceW*i) + x + (i * cmpWidth),
                        this.spaceH + y,
                        (this.spaceW + this.spaceW*i) + x + ((i+1) * cmpWidth),
                        this.spaceH + y + height));
            }
        } else if (this.alignment == VERTICAL_ALINGMENT) {
            int width = this.entity.getArea().width() - this.spaceW * 2;
            int cmpHeight = (this.entity.getArea().height() - this.spaceH * (tmpEntity.size()+1)) / tmpEntity.size();
            for (int i=0; i<tmpEntity.size(); i++) {
                Entity tmpEnt = tmpEntity.get(i);
                tmpEnt.setArea(new Rect(
                        this.spaceW + x,
                        (this.spaceH + this.spaceH*i) + y + (i * cmpHeight),
                        this.spaceW + x + width,
                        (this.spaceH + this.spaceH*i) + y + ((i+1) * cmpHeight)));
            }
        }
    }


}

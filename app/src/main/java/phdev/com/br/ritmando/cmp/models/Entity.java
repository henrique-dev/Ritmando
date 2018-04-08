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
package phdev.com.br.ritmando.cmp.models;

import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.CallSuper;

import phdev.com.br.ritmando.cmp.models.Component;

/**
 * Classe base para todas as entidades.
 * @version 1.0
 */
public abstract class Entity implements Component {

    /**
     * Area da entidade.
     */
    protected Rect area;

    /**
     * {@link Paint} relacionado geral da entidade.
     */
    protected Paint defaultPaint;

    /**
     * Estado ativo da entidade. Afeta updateable e controllable.
     */
    protected boolean active = true;

    /**
     * Estado visivel da entidade. Afeta drawable.
     */
    protected boolean visible = true;

    /**
     * Cria uma entidade.
     */
    protected Entity() {
        this.area = new Rect();
        this.defaultPaint = new Paint();
    }

    /**
     * Cria uma entidade.
     *
     * @param area area relacionada a entidade.
     */
    protected Entity(Rect area) {
        this.area = new Rect(area);
        this.defaultPaint = new Paint();
    }

    /**
     * Retorna a area da entoidade.
     *
     * @return area da entidade.
     */
    public Rect getArea() {
        return this.area;
    }

    /**
     * Redefine a area da entidade.
     *
     * @param area area da entidade.
     */
    public void setArea(Rect area) {
        this.area = area;
    }

    protected void setX(int x) {
        int width = this.area.width();
        this.area.right = width + (this.area.left = x);
    }

    protected int getX() {
        return this.area.left;
    }

    protected void setY(int y) {
        int height = this.area.height();
        this.area.top = y;
        this.area.bottom = y + height;
        //this.area.bottom = height + (this.area.top = y);
    }

    protected int getY() {
        return this.area.top;
    }

    /**
     * Retorna o {@link Paint} geral relacionado a entidade.
     *
     * @return {@link Paint} geral da entidade.
     */
    public Paint getDefaultPaint() {
        return this.defaultPaint;
    }

    /**
     * Retorna o {@link Paint} geral relacionado a entidade.
     *
     * @param defaultPaint {@link Paint} geral da entidade.
     */
    public void setDefaultPaint(Paint defaultPaint) {
        this.defaultPaint = defaultPaint;
    }

    /**
     * Retorna o estado ativo da entidade.
     *
     * @return estado ativo.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Redefine o estado ativo da entidade.
     *
     * @param active estado ativo.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Retorna o estado visivel da entidade.
     *
     * @return estado visivel.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Redefine o estado visivel da entidade.
     *
     * @param visible estado visivel.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Metodo basico de colisão para as entidades.
     *
     * @param x posição x do teste de colisão.
     * @param y posição y do teste de colisão.
     * @param entity entidade colidida.
     * @return true para colidido e false para não colidido.
     */
    protected static boolean haveCollision(float x, float y, Entity entity) {
        Rect area = entity.getArea();
        return (x > area.left && x < area.right && y > area.top && y < area.bottom);
    }

    protected static void move(Entity entity, float displaceX, float displaceY) {
        entity.setX( entity.getX() + (int)displaceX);
        entity.setY( entity.getY() + (int)displaceY);
    }
}

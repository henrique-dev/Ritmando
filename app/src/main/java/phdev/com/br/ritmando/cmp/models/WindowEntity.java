package phdev.com.br.ritmando.cmp.models;

import android.graphics.Rect;

import java.util.ArrayList;

import phdev.com.br.ritmando.cmp.effect.ClickEffect;
import phdev.com.br.ritmando.cmp.effect.Effect;
import phdev.com.br.ritmando.cmp.listeners.Listener;
import phdev.com.br.ritmando.cmp.utils.Text;

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

public abstract class WindowEntity extends Entity {

    protected Effect clickEffect;
    protected Effect loopEffect;
    protected Listener listener;
    protected Text entityText;

    protected WindowEntity() {
        super();
    }

    protected WindowEntity(Rect area) {
        super(area);
    }

    protected void setClickEffect(Effect effect) {
        this.clickEffect = effect;
    }

    protected void setLoopEffect(Effect effect) {
        this.loopEffect = effect;
    }

    public Text getEntityText() {
        return this.entityText;
    }

    public void setEntityText(Text entityText) {
        this.entityText = entityText;
    }

    protected void addListener(Listener listener) {
        this.listener = listener;
    }
}

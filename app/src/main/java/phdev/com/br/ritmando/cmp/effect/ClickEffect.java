package phdev.com.br.ritmando.cmp.effect;

import phdev.com.br.ritmando.GameLog;
import phdev.com.br.ritmando.cmp.listeners.ActionListener;
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

public abstract class ClickEffect implements Effect {

    public static final int FADE_IN_OUT = 0;
    public static final int FLASHING = 1;

    protected ActionListener actionListener;
    protected Entity entity;
    protected boolean running;

    protected ClickEffect(Entity entity, ActionListener actionListener) {
        this.entity = entity;
        this.actionListener = actionListener;
    }

    public final Effect start() {
        this.running = true;
        return this;
    }

    public final Effect stop() {
        this.running = false;
        return this;
    }

    protected abstract void reset();

}

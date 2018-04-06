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
package phdev.com.br.ritmando.cmp.effect;

import phdev.com.br.ritmando.cmp.listeners.ActionListener;
import phdev.com.br.ritmando.cmp.models.Entity;

/**
 * Classe pai de todas as classes que forneçam efeitos visuais para entidades.
 */
public abstract class ClickEffect implements Effect {

    public static final int FADE_IN_OUT = 0;
    public static final int FLASHING = 1;

    /**
     * Escuta para eventos que ocoreem após o efeito.
     */
    protected ActionListener actionListener;

    /**
     * Entidade que consome o efeito.
     */
    protected Entity entity;

    /**
     * Estado do efeito.
     */
    protected boolean running;

    /**
     * Cria um novo efeito.
     *
     * @param entity entidade que ira consumir o efeito.
     * @param actionListener escuta contendo os eventos que ocorrerão após o efeito.
     */
    protected ClickEffect(Entity entity, ActionListener actionListener) {
        this.entity = entity;
        this.actionListener = actionListener;
    }

    /**
     * Inicia o efeito.
     *
     * @return o próprio efeito.
     */
    public final Effect start() {
        this.running = true;
        return this;
    }

    /**
     * Encerra o efeito.
     *
     * @return o próprio efeito.
     */
    public final Effect stop() {
        this.running = false;
        return this;
    }

    /**
     * Reseta os atributos modificados da entidade para o estado inicial.
     *
     */
    @Deprecated
    protected abstract void reset();

}

package phdev.com.br.ritmando.cmp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;

import phdev.com.br.ritmando.GameParameters;
import phdev.com.br.ritmando.cmp.effect.ClickEffect;
import phdev.com.br.ritmando.cmp.listeners.ActionListener;
import phdev.com.br.ritmando.cmp.window.Button;
import phdev.com.br.ritmando.cmp.listeners.events.Event;
import phdev.com.br.ritmando.cmp.environment.Screen;
import phdev.com.br.ritmando.cmp.window.Window;
import phdev.com.br.ritmando.cmp.window.ListLayout;
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

public class GameScreen extends Screen {

    private MainMenuScene mainMenuScene;

    public GameScreen(int x, int y, int width, int height) {
        super(x, y, width, height);

        super.defaultPaint.setColor(Color.WHITE);

        this.mainMenuScene = new MainMenuScene(0, 0, GameParameters.getInstance().screenSize.width(), GameParameters.getInstance().screenSize.height());
        super.addScene(this.mainMenuScene);

    }
}

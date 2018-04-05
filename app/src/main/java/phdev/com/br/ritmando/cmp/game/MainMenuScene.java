package phdev.com.br.ritmando.cmp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import phdev.com.br.ritmando.GameParameters;
import phdev.com.br.ritmando.cmp.environment.Scene;
import phdev.com.br.ritmando.cmp.graphics.Sprite;
import phdev.com.br.ritmando.cmp.graphics.Texture;
import phdev.com.br.ritmando.cmp.window.Button;
import phdev.com.br.ritmando.cmp.window.ListLayout;
import phdev.com.br.ritmando.cmp.window.Window;

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

public class MainMenuScene extends Scene {

    private MainWindow mainWindow;

    private Texture texture;

    private Rect rects[];

    private int cont = 0;

    Sprite sprites[];

    public MainMenuScene(int x, int y, int width, int height) {
        super(x, y, width, height);
        mainWindow = new MainWindow();
        super.addEntity(mainWindow);

        this.texture = new Texture("sprites01.png");
        //this.texture = new Texture("sprites01.png", 100, 100);
        //this.texture.scaleMe(GameParameters.getInstance().screenSize.width(), GameParameters.getInstance().screenSize.height());

        //this.sprites = Sprite.getSpriteFromTexture(this.texture, 9, 7, 62);
        rects = Sprite.getSpriteFromTexture(this.texture, 9, 7, 62);

    }

    private class MainWindow extends Window {

        private Button startButton;
        private Button optionButton;

        public MainWindow() {
            super();
            int divWidth = (GameParameters.getInstance().screenSize.width()/8);
            int divHeight = (GameParameters.getInstance().screenSize.height()/8);
            int spaceW = 20;
            int spaceH = 100;
            float defaultTextSize = divHeight * 0.9f;

            super.getArea().set(divWidth, divHeight, divWidth + divWidth*6, divHeight + divHeight*6);
            super.setLayout(new ListLayout(ListLayout.VERTICAL_ALINGMENT, spaceW, spaceH));

            this.startButton = new Button("Iniciar");
            this.startButton.setColor(Color.GRAY);
            this.startButton.setTextSize(defaultTextSize);
            super.add(this.startButton);

            this.optionButton = new Button("Opções");
            this.optionButton.setColor(Color.GRAY);
            this.optionButton.setTextSize(defaultTextSize);
            super.add(this.optionButton);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();

        //canvas.drawBitmap(texture.getBitmap(), 0, 0, defaultPaint);
        //canvas.drawBitmap(texture.getBitmap(), new Rect(0,0,1820, 2745), area, defaultPaint);

        //canvas.drawBitmap(sprites[0].getTexture().getBitmap(), 0, 0, defaultPaint);
        //GameLog.debug(this, rects[0] + "");
        canvas.drawBitmap(texture.getBitmap(), rects[cont/5], rects[0], defaultPaint);
        cont++;
        if (cont > 30)
            cont = 0;

        canvas.restoreToCount(savedState);
    }

}

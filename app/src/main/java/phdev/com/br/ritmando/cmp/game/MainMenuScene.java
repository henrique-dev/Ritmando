package phdev.com.br.ritmando.cmp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.io.IOException;

import phdev.com.br.ritmando.GameLog;
import phdev.com.br.ritmando.GameParameters;
import phdev.com.br.ritmando.cmp.effect.FadeEffect;
import phdev.com.br.ritmando.cmp.effect.FlashEffect;
import phdev.com.br.ritmando.cmp.environment.Scene;
import phdev.com.br.ritmando.cmp.graphics.Sprite;
import phdev.com.br.ritmando.cmp.graphics.Texture;
import phdev.com.br.ritmando.cmp.listeners.ActionListener;
import phdev.com.br.ritmando.cmp.listeners.events.Event;
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

    //private Rect rects[];

    private int cont = 0;
    private int spriteAtual = cont;
    private boolean test = true;

    Sprite sprites[];

    TesteEntity heroi;

    public MainMenuScene(int x, int y, int width, int height) {
        super(x, y, width, height);
        mainWindow = new MainWindow();
        super.add(mainWindow);

        heroi = new TesteEntity();
        heroi.setArea(new Rect(0,0,0,0));
        try {
            this.texture = new Texture("sprites01.png");
            //this.texture = new Texture("sprites01.png", 100, 100);
            //this.texture.scaleMe(GameParameters.getInstance().screenSize.width(), GameParameters.getInstance().screenSize.height());
        } catch (IOException ioe) {
            GameLog.error(this, ioe.getMessage());
        }

        //this.sprites = Sprite.getSpriteFromTexture(this.texture, 9, 7, 62);
        this.sprites = Sprite.getSpriteFromTexture(heroi, this.texture, 9, 7, 62);
        //rects = Sprite.getSpriteFromTexture(this.texture, 9, 7, 62);

    }

    private class MainWindow extends Window {

        private Button startButton;
        private Button optionButton;
        private Button exitButton;

        public MainWindow() {
            super();
            int divWidth = (GameParameters.getInstance().screenSize.width()/8);
            int divHeight = (GameParameters.getInstance().screenSize.height()/8);
            int spaceW = 20;
            int spaceH = 20;
            float defaultTextSize = divHeight * 0.9f;

            super.getArea().set(0, MainMenuScene.this.area.bottom - 400, MainMenuScene.this.area.right, MainMenuScene.this.area.bottom);
            super.setLayout(new ListLayout(ListLayout.HORIZONTAK_ALINGMENT, spaceW, spaceH));

            this.startButton = new Button("Cancelar");
            this.startButton.setColor(Color.RED);
            //this.startButton.setTextSize(85);
            this.startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(Event evt) {
                    sprites[0].invertH();
                }
            });
            super.add(this.startButton);

            //this.optionButton = new Button("Paulo\nHenrique\nGoncalves\nBacelar");
            this.optionButton = new Button("Calcular");
            this.optionButton.setColor(Color.GRAY);
            //this.optionButton.setTextSize(defaultTextSize);
            //this.optionButton.setTextSize(150);
            this.optionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(Event evt) {
                    sprites[0].invertV();
                }
            });
            super.add(this.optionButton);

            //this.exitButton = new Button("Paulo Henrique");
            this.exitButton = new Button("OK");
            this.exitButton.setColor(Color.GREEN);
            //this.exitButton.setTextSize(defaultTextSize);
            this.exitButton.setClickEffect(new FadeEffect());
            this.exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(Event evt) {
                    sprites[0].rotate(2);
                }
            });
            super.add(this.exitButton);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();
        super.draw(canvas);

        this.sprites[spriteAtual].draw(canvas);

        canvas.restoreToCount(savedState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return false;
    }

}

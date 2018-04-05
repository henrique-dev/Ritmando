package phdev.com.br.ritmando.cmp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;

import phdev.com.br.ritmando.GameParameters;
import phdev.com.br.ritmando.cmp.environment.Scene;
import phdev.com.br.ritmando.cmp.graphics.Texture;
import phdev.com.br.ritmando.cmp.window.Button;
import phdev.com.br.ritmando.cmp.window.Layout;
import phdev.com.br.ritmando.cmp.window.ListLayout;
import phdev.com.br.ritmando.cmp.window.Window;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
 */

public class MainMenuScene extends Scene {

    private MainWindow mainWindow;

    private Texture texture;

    public MainMenuScene(int x, int y, int width, int height) {
        super(x, y, width, height);
        mainWindow = new MainWindow();
        super.addEntity(mainWindow);


        this.texture = new Texture("image.jpg");
        this.texture.scaleMe(GameParameters.getInstance().screenSize.width(), GameParameters.getInstance().screenSize.height());
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

        canvas.drawBitmap(texture.getBitmap(), 0, 0, defaultPaint);
    }

}

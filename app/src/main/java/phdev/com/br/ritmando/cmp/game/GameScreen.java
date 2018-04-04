package phdev.com.br.ritmando.cmp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;

import phdev.com.br.ritmando.GameParameters;
import phdev.com.br.ritmando.cmp.listeners.ActionListener;
import phdev.com.br.ritmando.cmp.window.Button;
import phdev.com.br.ritmando.cmp.listeners.events.Event;
import phdev.com.br.ritmando.cmp.environment.Screen;
import phdev.com.br.ritmando.cmp.window.Window;
import phdev.com.br.ritmando.cmp.window.ListLayout;
import phdev.com.br.ritmando.cmp.utils.Text;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
 */

public class GameScreen extends Screen {

    private Text myText;
    private Button botao1, botao2, botao3, botao4;
    private Window window;

    public GameScreen(int x, int y, int width, int height) {
        super(x, y, width, height);

        super.defaultPaint.setColor(Color.WHITE);

        //this.myText = new Text(super.area, "Paulo Henrique");
        //this.myText.setStroke(Color.BLUE, 2);

        botao1 = new Button("Botão 1");
        botao1.setColor(Color.RED);

        botao2 = new Button("Botão 2");
        botao2.setColor(Color.GREEN);

        botao3 = new Button("Botão 3");
        botao3.setColor(Color.BLUE);

        botao4 = new Button("Botão 4");
        botao4.setColor(Color.YELLOW);

        window = new Window(0,0, 300, 750);
        window.setLayout(new ListLayout(ListLayout.VERTICAL_ALINGMENT, 20, 20));

        window.addComponent(botao1);
        window.addComponent(botao2);
        window.addComponent(botao3);
        window.addComponent(botao4);


    }

    @Override
    public void update() {
        window.update();
    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();

        canvas.drawRect(super.area, super.defaultPaint);


        window.draw(canvas);

        canvas.restoreToCount(savedState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}

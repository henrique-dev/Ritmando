package phdev.com.br.ritmando.cmp.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import phdev.com.br.ritmando.GameLog;
import phdev.com.br.ritmando.cmp.listeners.ActionListener;
import phdev.com.br.ritmando.cmp.window.Button;
import phdev.com.br.ritmando.cmp.listeners.events.Event;
import phdev.com.br.ritmando.cmp.environment.Screen;
import phdev.com.br.ritmando.cmp.window.Window;
import phdev.com.br.ritmando.cmp.window.utils.ListLayout;
import phdev.com.br.ritmando.cmp.window.utils.Text;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
 */

public class GameScreen extends Screen {

    private Text myText;
    Button cmp, cmp2;
    Window window;

    public GameScreen(int x, int y, int width, int height) {
        super(x, y, width, height);

        super.defaultPaint.setColor(Color.BLUE);

        //this.myText = new Text(super.area, "Paulo Henrique");
        //this.myText.setStroke(Color.BLUE, 2);

        cmp = new Button(0,0,700,250);
        cmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(Event evt) {
                //cmp2 = new Button(0, 300, 700, 550);
                window.getLayout().format();
            }
        });

        window = new Window(0,0,0,0);
        window.setLayout(new ListLayout());


    }

    @Override
    public void update() {
        cmp.update();
        window.update();
    }

    @Override
    public void draw(Canvas canvas) {
        int savedState = canvas.save();

        canvas.drawRect(super.area, super.defaultPaint);
        //this.myText.draw(canvas);
        cmp.draw(canvas);
        if (cmp2 != null)
            cmp2.draw(canvas);

        window.draw(canvas);

        canvas.restoreToCount(savedState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        cmp.onTouchEvent(motionEvent);
        return false;
    }
}

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

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
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

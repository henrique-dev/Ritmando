package phdev.com.br.ritmando;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by ks_le on 01/04/2018.
 */

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setupParameters();
        super.setContentView( new GameEngine(this) );
    }

    private void setupParameters() {

        GameParameters.getInstance().assetManager = getAssets();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        GameParameters.getInstance().screenSize = new Rect(0, 0, size.x, size.y);
    }

}

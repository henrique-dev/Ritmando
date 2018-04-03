package phdev.com.br.ritmando;

import android.graphics.Rect;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
 */

public class GameParameters {

    private static GameParameters instance = new GameParameters();
    public Rect screenSize;

    public static GameParameters getInstance() {
        if (instance == null)
            instance = new GameParameters();
        return instance;
    }

}

package phdev.com.br.ritmando;

import android.util.Log;

/**
 * Created by Paulo Henrique Gonçalves Bacelar on 01/04/2018.
 */

public class GameLog {

    private static int logIndex;

    private GameLog() {
        logIndex = 0;
    }

    public static void error(Object obj, String msg) {
        Log.e("MyApp: " + obj.getClass().getName(), logIndex++ + ": " + msg);
    }

    public static void debug(Object obj, String msg) {
        Log.d("MyApp: " + obj.getClass().getName(), logIndex++ + ": " + msg);
    }

}

package phdev.com.br.ritmando;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import phdev.com.br.ritmando.cmp.game.GameScreen;
import phdev.com.br.ritmando.cmp.environment.Screen;

/**
 * Created by ks_le on 01/04/2018.
 */

public class GameEngine extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread mainThread;

    private Screen screen;

    public GameEngine(Context context) {
        super(context);
        getHolder().addCallback(this);

        if (this.mainThread == null) {
            GameLog.debug(this, "MainThread criada");
            this.mainThread = new MainThread(this);
        }

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        GameLog.debug(this, "Surface criada.");

        initComponents();

        GameLog.debug(this, "MainThread iniciada");
        this.mainThread.start();

        GameLog.debug(this, "Loop da MainThread ativado");
        this.mainThread.setRunning(true);


    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        GameLog.debug(this, "Surface modificada");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        GameLog.debug(this, "Surface destruida");

        boolean retry = true;
        while (retry) {
            if (this.mainThread != null) {

                GameLog.debug(this, "Finalizando componentes");
                finalizeComponents();

                GameLog.debug(this, "Loop da MainThread desativado");
                this.mainThread.setRunning(false);
                try {
                    this.mainThread.join();
                    GameLog.debug(this, "MainThread destruida");
                } catch (InterruptedException ie) {
                    GameLog.error(this, ie.getMessage());
                } finally {
                    retry = false;
                }
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void draw(Canvas canvas) {
        this.screen.draw(canvas);
    }

    public void update() {
        this.screen.update();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.screen.onTouchEvent(motionEvent);
    }

    private void initComponents() {
        screen = new GameScreen(0,0, GameParameters.getInstance().screenSize.right, GameParameters.getInstance().screenSize.bottom);
    }

    private void finalizeComponents() {
        screen = null;
    }

    private class MainThread extends Thread {

        private int FPS = 30;
        private int averageFPS;

        private boolean running;

        private Canvas canvas;

        private final SurfaceHolder surfaceHolder;
        private GameEngine gameEngine;

        MainThread(GameEngine gameEngine) {
            this.surfaceHolder = gameEngine.getHolder();
            this.gameEngine = gameEngine;
            this.running = false;
        }

        @Override
        public void run() {

            long startTime;
            long timeMillis;
            long waitTime;
            long totalTime = 0;
            long frameCount = 0;
            long targetTime = 1000/FPS;

            while (this.running) {
                startTime = System.nanoTime();
                this.canvas = null;

                try {
                    this.canvas = this.surfaceHolder.lockCanvas();

                    synchronized (this.surfaceHolder) {
                        this.gameEngine.update();
                        this.gameEngine.draw(this.canvas);
                    }
                } catch (Exception e) {
                    //Log.e(this.getClass().getName(), e.getMessage());
                    GameLog.error(this, e.getMessage());
                } finally {
                    if (canvas != null) {
                        try {
                            this.surfaceHolder.unlockCanvasAndPost(this.canvas);
                        } catch (Exception e) {
                            //Log.e(this.getClass().getName(), "Unlock-Canvas. " + e.getMessage());
                            GameLog.error(this, e.getMessage());
                        }
                    }
                }

                timeMillis = (System.nanoTime() - startTime) / 1000000;
                waitTime = targetTime - timeMillis;
                try {
                    sleep(waitTime);
                } catch (Exception ie) {
                    //GameLog.e(this.getClass().getName(), ie.getMessage());
                    //ie.printStackTrace();
                }
                totalTime += System.nanoTime() - startTime;
                frameCount++;
                if (frameCount == FPS) {
                    averageFPS = (int)(1000/((totalTime/frameCount)/1000000));
                    frameCount = 0;
                    totalTime = 0;
                }
            }

        }

        protected void setRunning(boolean running) {
            this.running = running;
        }

    }

}

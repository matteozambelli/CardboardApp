package com.example.fabio.cardboardpb.Thread;

/**
 * Created by fabio on 27/01/2015.
 */
import android.graphics.Canvas;
import android.widget.TextView;

public class GameLoopThread extends Thread {
    static final long FPS = 5;
    private GameAnimationView view;
    private boolean running = false;


    public GameLoopThread(GameAnimationView view) {
        this.view = view;

    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        while (running) {
            Canvas c = null;
            startTime = System.currentTimeMillis();
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.onDraw(c);

                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}
        }
    }


}
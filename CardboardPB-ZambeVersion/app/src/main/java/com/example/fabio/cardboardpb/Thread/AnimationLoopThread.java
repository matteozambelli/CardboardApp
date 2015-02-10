package com.example.fabio.cardboardpb.Thread;

/**
 * Created by fabio on 27/01/2015.
 */
import android.graphics.Canvas;
import android.view.SurfaceView;

public class AnimationLoopThread extends Thread {
    static final long FPS = 5;
    private SurfaceView view;
    private boolean running = false;
    private AnimationBackgroundView viewBackground;
    private AnimationExplosionView viewExplosion;

    public AnimationLoopThread(SurfaceView view) {
        this.view = view;
        if(view instanceof AnimationBackgroundView){
            viewBackground = (AnimationBackgroundView) view;
        }
        if(view instanceof AnimationExplosionView){
            viewExplosion = (AnimationExplosionView) view;
        }
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
            Canvas canvasBackground = null;
            Canvas canvasExplosion = null;
            startTime = System.currentTimeMillis();

            if (view instanceof AnimationBackgroundView) {

                try {

                    canvasBackground = viewBackground.getHolder().lockCanvas();
                    synchronized (viewBackground.getHolder()) {

                        viewBackground.onDrawAnimationBackgroundView(canvasBackground);

                    }
                } finally {
                    if (canvasBackground != null) {
                        viewBackground.getHolder().unlockCanvasAndPost(canvasBackground);
                    }
                }
                sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
                try {
                    if (sleepTime > 0)
                        sleep(sleepTime);
                    else
                        sleep(10);
                } catch (Exception e) {

                }
            }

            if (view instanceof AnimationExplosionView) {

                try {
                    canvasExplosion = viewExplosion.getHolder().lockCanvas();

                    synchronized (viewExplosion.getHolder()) {
                        viewExplosion.onDrawAnimationeExplosionView(canvasExplosion);
                        }

                } finally {
                    if (canvasExplosion != null) {
                        viewExplosion.getHolder().unlockCanvasAndPost(canvasExplosion);
                    }
                }
                sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
                try {
                    if (sleepTime > 0)
                        sleep(sleepTime);
                    else
                        sleep(10);
                } catch (Exception e) {

                }
            }
        }
    }
}
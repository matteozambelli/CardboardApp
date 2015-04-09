package com.example.fabio.cardboardpb.Thread;

/**
 * Created by fabio on 27/01/2015.
 */
import android.graphics.Canvas;
import android.view.SurfaceView;

public class AnimationLoopThread extends Thread {
    static final long FPSexplosion = 8;
    static final long FPSbackground = 10;
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
        long ticksPSexplosion = 1000 / FPSexplosion;
        long ticksPSbackground = 1000 / FPSbackground;
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
                        if(canvasBackground!=null)
                            viewBackground.onDrawAnimationBackgroundView(canvasBackground);

                    }
                } finally {
                    if (canvasBackground != null) {
                        viewBackground.getHolder().unlockCanvasAndPost(canvasBackground);
                    }
                }
                sleepTime = ticksPSbackground - (System.currentTimeMillis() - startTime);
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
                        if(canvasExplosion!=null)
                            viewExplosion.onDrawAnimationeExplosionView(canvasExplosion);
                        }

                } finally {
                    if (canvasExplosion != null) {
                        viewExplosion.getHolder().unlockCanvasAndPost(canvasExplosion);
                    }
                }
                sleepTime = ticksPSexplosion - (System.currentTimeMillis() - startTime);
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
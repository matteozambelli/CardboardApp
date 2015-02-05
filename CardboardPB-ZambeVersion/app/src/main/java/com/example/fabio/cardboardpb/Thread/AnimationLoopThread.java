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
    private AnimationBackgroundView view1;
    private AnimationExplosionView view2;



    public AnimationLoopThread(SurfaceView view) {
        this.view = view;
        if(view instanceof AnimationBackgroundView){
            view1= (AnimationBackgroundView) view;
        }
        if(view instanceof AnimationExplosionView){
            view2= (AnimationExplosionView) view;
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
            Canvas c = null;
            startTime = System.currentTimeMillis();

            if (view instanceof AnimationBackgroundView) {

                try {

                    c = view1.getHolder().lockCanvas();
                    synchronized (view1.getHolder()) {

                        view1.onDrawAnimationBackgroundView(c);

                    }
                } finally {
                    if (c != null) {
                        view1.getHolder().unlockCanvasAndPost(c);
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

                    c = view2.getHolder().lockCanvas();
                    synchronized (view2.getHolder()) {

                        view2.onDrawAnimationeExplosionView(c);

                    }
                } finally {
                    if (c != null) {
                        view2.getHolder().unlockCanvasAndPost(c);
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
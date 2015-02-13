package com.example.fabio.cardboardpb.Thread;

/**
 * Created by fabio on 05/02/2015.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.fabio.cardboardpb.R;

public class AnimationExplosionView extends SurfaceView {
    private SurfaceHolder holder;
    private AnimationLoopThread gameLoopThread;
    private Sprite spriteExplosion;

    public AnimationExplosionView(Context context) {
        super(context);

        gameLoopThread = new AnimationLoopThread(this);
        setZOrderOnTop(true);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });

        holder.setFormat(PixelFormat.TRANSPARENT);
        createSprite();
    }


    private void createSprite() {
        Bitmap bmpExplosion = BitmapFactory.decodeResource(getResources(), R.drawable.explosion_anim);
        spriteExplosion = new Sprite(this,bmpExplosion,12);
    }


    public void onDrawAnimationeExplosionView(Canvas canvas) {
        //canvas.drawColor(Color.BLACK);
        if(canvas!=null)
            spriteExplosion.onDrawExplosion(canvas);
    }


}

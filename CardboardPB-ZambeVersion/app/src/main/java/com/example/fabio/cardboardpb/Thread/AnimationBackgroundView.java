package com.example.fabio.cardboardpb.Thread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.fabio.cardboardpb.R;

public class AnimationBackgroundView extends SurfaceView {
    private SurfaceHolder holder;
    private AnimationLoopThread gameLoopThread;
    private Sprite spriteBackground;

    public AnimationBackgroundView(Context context) {
        super(context);

        gameLoopThread = new AnimationLoopThread(this);
        //setZOrderOnTop(true);
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
        Bitmap bmpBackground = BitmapFactory.decodeResource(getResources(), R.drawable.sfondo8);
        spriteBackground = new Sprite(this,bmpBackground,1);
    }


    public void onDrawAnimationBackgroundView(Canvas canvas) {
        //canvas.drawColor(Color.BLACK);
        if(canvas!=null)
            spriteBackground.onDrawBackground(canvas);
    }


}
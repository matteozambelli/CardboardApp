package com.example.fabio.cardboardpb.Thread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.fabio.cardboardpb.R;

public class GameAnimationView extends SurfaceView {
    private Bitmap bmpFire;
    private Bitmap bmpBackground;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private Sprite spriteFire;
    private Sprite spriteBackground;
    private int count=0;

    public GameAnimationView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        //setZOrderOnTop(true);
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
                //holder.setSizeFromLayout();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
        holder.setFormat(PixelFormat.TRANSPARENT);
        //bmpFire = BitmapFactory.decodeResource(getResources(), R.drawable.explosion_anim1);
        bmpBackground = BitmapFactory.decodeResource(getResources(), R.drawable.background_car1_ridotta);
        spriteBackground = new Sprite(this,bmpBackground);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        count++;
        //if(count==12)
         //   gameLoopThread.setRunning(false);
        spriteBackground.onDraw(canvas);
    }
}
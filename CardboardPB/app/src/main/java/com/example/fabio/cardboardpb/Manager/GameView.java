package com.example.fabio.cardboardpb.Manager;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.fabio.cardboardpb.R;

public class GameView extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private Sprite sprite;
    private int count=0;

    public GameView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        setZOrderOnTop(true);
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
                holder.setFixedSize(370,370);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
        holder.setFormat(PixelFormat.TRANSPARENT);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.explosion_anim);
        sprite = new Sprite(this,bmp);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        count++;
        //if(count==12)
         //   gameLoopThread.setRunning(false);
        sprite.onDraw(canvas);
    }
}
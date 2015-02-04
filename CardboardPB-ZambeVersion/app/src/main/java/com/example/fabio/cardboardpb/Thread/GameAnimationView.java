package com.example.fabio.cardboardpb.Thread;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.fabio.cardboardpb.R;

public class GameAnimationView extends SurfaceView {
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private Sprite spriteBackground;
    private Sprite spriteCarFrontLane2;


    public GameAnimationView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
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
        Bitmap bmpCarFrontLane2 = BitmapFactory.decodeResource(getResources(), R.drawable.car_front_sprite);
        Bitmap bmpBackground = BitmapFactory.decodeResource(getResources(), R.drawable.background_car1_ridotta);
        spriteBackground = new Sprite(this,bmpBackground,3);
        spriteCarFrontLane2 = new Sprite(this,bmpCarFrontLane2,9);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.BLACK);
        spriteBackground.onDrawBackground(canvas);
        spriteCarFrontLane2.onDrawCarFrontLane2(canvas);
    }
}
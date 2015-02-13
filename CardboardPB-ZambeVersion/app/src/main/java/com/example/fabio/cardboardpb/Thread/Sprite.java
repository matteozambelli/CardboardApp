package com.example.fabio.cardboardpb.Thread;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.view.SurfaceView;

public class Sprite {

    private static final int BMP_ROWS = 1;
    private AnimationBackgroundView gameView;
    private AnimationExplosionView explosionView;
    private SurfaceView surfaceView;
    private Bitmap bmp;
    private int xExplosion = 40;
    private int yExplosion = 150;
    private int xBackgound = 0;
    private int yBackground = 0;
    private int xSpeed=1;
    private int ySpeed=1;
    private int currentFrame = 0;
    private int width;
    private int height;
    public int nColums;


    public Sprite(SurfaceView surfaceView, Bitmap bmp,int colums) {
        nColums=colums;
        this.width = bmp.getWidth() / nColums;
        this.height = bmp.getHeight() / BMP_ROWS;
        if(surfaceView instanceof AnimationBackgroundView){
            this.gameView = (AnimationBackgroundView) surfaceView;
        }
        if(surfaceView instanceof AnimationExplosionView){
            this.explosionView = (AnimationExplosionView) surfaceView;
        }
        this.bmp = bmp;
    }

    private void updateExplosion() {
        currentFrame = ++currentFrame % nColums;
    }

    private void updateBackground() {
        //y = y + ySpeed;
        currentFrame = ++currentFrame % nColums;
    }

    public void onDrawExplosion(Canvas canvas) {
        updateExplosion();
        if(canvas!=null)
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        int srcX = currentFrame * width;
        int srcY = 0;
        Rect src = new Rect(srcX, 0, srcX + width, srcY + height);
        Rect dst = new Rect(xExplosion, yExplosion, xExplosion + width, yExplosion + height);
        if(canvas!=null)
            canvas.drawBitmap(bmp, src, dst, null);
    }

    public void onDrawBackground(Canvas canvas) {
        updateBackground();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        int srcX = currentFrame * width;
        int srcY = 0;
        Rect src = new Rect(srcX, 0, srcX + width, srcY + height);
        Rect dst = new Rect(xBackgound, yBackground, xBackgound + width, yBackground + height);
        canvas.drawBitmap(bmp, src, dst, null);
    }

}
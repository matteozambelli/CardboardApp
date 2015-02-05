package com.example.fabio.cardboardpb.Thread;

import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.LruCache;

public class Sprite {
    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right
    int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
    private static final int BMP_ROWS = 1;
    private static final int BMP_COLUMNS = 9;
    private static final int MAX_SPEED = 5;
    private GameAnimationView gameView;
    private Bitmap bmp;
    private int xc = 0;
    private int yc = 0;
    private int xb = 0;
    private int yb = 0;
    private int xSpeed=1;
    private int ySpeed=1;
    private int currentFrame = 1;
    private int width;
    private int height;
    public int nColums;



    public Sprite(GameAnimationView gameView, Bitmap bmp,int colums) {
        nColums=colums;
        this.width = bmp.getWidth() / nColums;
        this.height = bmp.getHeight() / BMP_ROWS;
        this.gameView = gameView;
        this.bmp = bmp;

        //Random rnd = new Random();
        //x = rnd.nextInt(gameView.getWidth() - width);
        //y = rnd.nextInt(gameView.getHeight() - height);
        //xSpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
        //ySpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
    }

    private void updateCarFrontLane2() {
        if(xc==0){
            xc=160;
        }
        if(yc==0){
            yc=140;
        }

        if(yc==220){
            yc=140;
        }

        yc+=2;
        //currentFrame = ++currentFrame % BMP_COLUMNS;
    }
    private void updateBackground() {
        //y = y + ySpeed;
        currentFrame = ++currentFrame % nColums;
    }

    public void onDrawCarFrontLane2(Canvas canvas) {
        updateCarFrontLane2();

        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        int srcX = currentFrame * width;
        int srcY = 0;
        Rect src = new Rect(srcX, 0, srcX + width, srcY + height);
        Rect dst = new Rect(xc, yc, xc + width, yc + height);
        Paint paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText(getYcarValue().toString(), 300, 300, paint);
        canvas.drawBitmap(bmp, src, dst, null);
    }

    public void onDrawBackground(Canvas canvas) {
        updateBackground();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        int srcX = currentFrame * width;
        int srcY = 0;
        Rect src = new Rect(srcX, 0, srcX + width, srcY + height);
        Rect dst = new Rect(xb, yb, xb + width, yb + height);
        canvas.drawBitmap(bmp, src, dst, null);
    }

    public Integer getYcarValue() {
        Integer k=new Integer(yc);
        return k;
    }

   /* private int getAnimationRow() {
        double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
        int direction = (int) Math.round(dirDouble) % BMP_ROWS;
        return DIRECTION_TO_ANIMATION_MAP[direction];
    }*/


}
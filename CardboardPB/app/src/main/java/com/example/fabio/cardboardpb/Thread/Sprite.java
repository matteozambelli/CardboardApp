package com.example.fabio.cardboardpb.Thread;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.LruCache;

public class Sprite {
    private static final int BMP_ROWS = 1;
    private static final int BMP_COLUMNS = 9;
    private int x = 160;
    private int y = 110;
    private int xSpeed = 1;
    private GameAnimationView ov;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;
    private int cont=0;
    private int ySpeed = 1;
    private int direction;

    public Sprite(GameAnimationView gameView, Bitmap bmp) {
        this.ov = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
    }

    private void update() {

            //0 = up
            //1 = down
            //2 = left
            //3 = right

            //facing down
           /* if(x > ov.getWidth() - width - xSpeed){
                xSpeed = 0;
                ySpeed = 1;
                direction = 1;
            }

            //going left
            if(y > ov.getHeight() - height - ySpeed){
                // xSpeed = -1;
                ySpeed = 0;
                direction = 2;
            }

            //facing up
            if(x + xSpeed < 0){
                x = 0;
                xSpeed = 0;
                ySpeed = -1;
                direction = 0;
            }

            //facing right
            if(y + ySpeed <0){
                y = 0;
                //xSpeed = 1;
                ySpeed = 0;
                direction = 3;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
*/
            //currentFrame = ++currentFrame %9;

        if(y>=210){
            y=110;
        }
        else {
            y += ySpeed;
        }

        //currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas) {
        update();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        int srcX = currentFrame * width;
        int srcY = 0 ;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText(getYvalue().toString(), 200, 200, paint);
        canvas.drawBitmap(bmp, src, dst, null);
    }

    public Integer getYvalue(){
        Integer k=new Integer(y);
        return k;
    }
}
package com.example.fabio.myapplicationgameprova;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by fabio on 03/02/2015.
 */
public class Sprite {

    public int x,y;
    int xSpeed, ySpeed;
    int height,width;
    Bitmap b;
    SurfaceViewExample.OurView ov;
    int currentFrame = 0;
    int direction = 0;

    public Sprite(SurfaceViewExample.OurView ourView, Bitmap blob) {
        b=blob;
        ov=ourView;
        height=b.getHeight();
        width=b.getWidth()/9;
        x=y=0;
        xSpeed=0;
        ySpeed=1;
    }

    public void onDraw(Canvas canvas) {
        update();
        //int srcY=direction*height;
        int srcX=currentFrame*width;
        Rect src=new Rect(srcX,0, srcX+width, height); //begin to top-left corner of the bitmap
        Rect dst=new Rect(x, y, x+width, y+height); //where bitmap should be

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        canvas.drawText(getYvalue().toString(), 10, 25, paint);
        canvas.drawBitmap(b, src, dst, null);
    }

    public Integer getYvalue(){
        Integer k=new Integer(y);
        return k;
    }
    private void update() {

        //0 = up
        //1 = down
        //2 = left
        //3 = right

        //facing down
        if(x > ov.getWidth() - width - xSpeed){
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

        //currentFrame = ++currentFrame %9;

        x+=xSpeed;
        y+=ySpeed;
    }


}

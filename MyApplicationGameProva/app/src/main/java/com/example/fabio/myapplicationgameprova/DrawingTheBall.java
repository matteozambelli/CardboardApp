package com.example.fabio.myapplicationgameprova;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by fabio on 03/02/2015.
 */
public class DrawingTheBall extends View {

    int x,y;
    Bitmap bball;

    public DrawingTheBall(Context context) {
        super(context);
        bball= BitmapFactory.decodeResource(getResources(), R.drawable.bubble_3);
        x=0;
        y=0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect ourRect=new Rect();
        ourRect.set(0,0,canvas.getWidth(),canvas.getHeight()/2);

        Paint blue=new Paint();
        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.FILL);

        canvas.drawRect(ourRect,blue);

        if(x < canvas.getWidth()){
            x+=10;
        }
        else{
            x=0;
        }

        if(x < canvas.getHeight()){
            y+=10;
        }
        else{
            y=0;
        }

        Paint p=new Paint();
        canvas.drawBitmap(bball,x,y,p);
        invalidate();
    }
}

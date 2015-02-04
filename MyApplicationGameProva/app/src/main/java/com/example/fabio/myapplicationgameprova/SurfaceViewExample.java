package com.example.fabio.myapplicationgameprova;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by fabio on 03/02/2015.
 */
public class SurfaceViewExample extends Activity implements View.OnTouchListener{

    OurView v;
    Bitmap ball,blob;
    float x,y;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v=new OurView(this);
        v.setOnTouchListener(this);
        ball= BitmapFactory.decodeResource(getResources(),R.drawable.bubble_3);
        blob= BitmapFactory.decodeResource(getResources(),R.drawable.car_front_sprite);
        x=y=0;
        setContentView(v);
    }

    @Override
    protected void onPause() {
        super.onPause();
        v.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        v.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent me) {
        try {
            Thread.sleep(50); //ritardo l'effetto del touchEvent,
            // per avere un effetto più fluido
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (me.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = me.getX();
                y = me.getY();
                break;
            case MotionEvent.ACTION_UP:
                x = me.getX();
                y = me.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                x = me.getX();
                y = me.getY();
                break;
        }

        return true;
    }


    public class OurView extends SurfaceView implements Runnable {

        Thread t = null;
        SurfaceHolder holder;
        public Sprite sprite;
        boolean isItOK = false;
        boolean spriteLoaded = false;
        Integer i=new Integer(2);


        public OurView(Context context) {
            super(context);
            holder = getHolder();
        }

        @Override
        public void run() {

            sprite = new Sprite(OurView.this, blob);

            while (isItOK){
                //perform canvas drawing
                if(!holder.getSurface().isValid()){
                    continue;
                }
                if(!spriteLoaded){
                    spriteLoaded=true;
                }
                Canvas c = holder.lockCanvas();
                onDraw(c);
                holder.unlockCanvasAndPost(c);
            }

        }


        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(255, 150, 150, 10);
            canvas.drawBitmap(ball, x - (ball.getWidth() / 2),
                    y - (ball.getHeight() / 2), null); //fisso il centro in posizione (0,0)
            sprite.onDraw(canvas);
        }

        public void pause(){
            isItOK = false;
            while(true){
                try{
                    t.join();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            t = null;
        }

        public void resume(){
            isItOK = true;
            t = new Thread(this);
            t.start();
        }

    }

}

package com.example.fabio.cardboardpb.Thread;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.fabio.cardboardpb.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

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


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void createSprite() {
        Bitmap bmpBackground = BitmapFactory.decodeResource(getResources(), R.drawable.cartoonbackground1);
        //bmpBackground.getConfig();
        //mutableBitmap.reconfigure(400,400, mutableBitmap.getConfig());
        //ByteArrayOutputStream out = new ByteArrayOutputStream();
        //bmpBackground.compress(Bitmap.CompressFormat.PNG,100,out);
        //Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
        spriteBackground = new Sprite(this,bmpBackground,1);
    }


    public void onDrawAnimationBackgroundView(Canvas canvas) {
        //canvas.drawColor(Color.BLACK);
        if(canvas!=null)
            spriteBackground.onDrawBackground(canvas);
    }


}
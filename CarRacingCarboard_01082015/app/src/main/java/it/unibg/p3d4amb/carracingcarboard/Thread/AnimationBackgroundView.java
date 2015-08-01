package it.unibg.p3d4amb.carracingcarboard.Thread;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import it.unibg.p3d4amb.carracingcarboard.R;

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
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable=true;
        options.inScaled=true;
        //this is the file going to use temporally to save the bytes.
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.backgroundfinalecompleto5,options);
        Bitmap rbitmap = getResizedBitmap(bitmap,700,11200);
        spriteBackground = new Sprite(this,rbitmap,16);
    }

    public Bitmap getResizedBitmap(Bitmap bm, long newHeight, long newWidth)
    {
        long width = (long)bm.getWidth();
        long height = (long)bm.getHeight();
        double scaleWidth = ((double) newWidth) / width;
        double scaleHeight = ((double) newHeight) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale((float) scaleWidth, (float) scaleHeight);
        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, (int)width, (int)height, matrix, false);
        return resizedBitmap;
    }


    public void onDrawAnimationBackgroundView(Canvas canvas) {
        //canvas.drawColor(Color.BLACK);
        if(canvas!=null)
            spriteBackground.onDrawBackground(canvas);
    }


}
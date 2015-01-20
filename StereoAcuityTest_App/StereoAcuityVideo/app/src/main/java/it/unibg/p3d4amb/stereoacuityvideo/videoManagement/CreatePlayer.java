package it.unibg.p3d4amb.stereoacuityvideo.videoManagement;

import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Silvia on 16/10/2014.
 */
public class CreatePlayer implements SurfaceHolder.Callback {

    public SurfaceView v;
    public MediaPlayer p;
    public SurfaceHolder h;


    public CreatePlayer(SurfaceView v, MediaPlayer p) {
        this.v = v;
        this.p = p;

    }


    public void getHolderMy() {
        h = v.getHolder();
        h.addCallback(this);
    }


/*    public void stopPlay(){
        if (p.isPlaying()){
            p.stop();
            p.release();
        }
    }*/

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        p.setDisplay(holder);
        p.prepareAsync();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}

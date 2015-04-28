package it.unibg.p3d4amb.carracingcarboard.Thread;

import android.os.AsyncTask;
import android.widget.ImageView;

import it.unibg.p3d4amb.carracingcarboard.Animation.AnimationPanorama;
import it.unibg.p3d4amb.carracingcarboard.Manager.Enum.Eye;
import it.unibg.p3d4amb.carracingcarboard.Manager.GlobalData;
import it.unibg.p3d4amb.carracingcarboard.Manager.PenalizationPanoramaManager;

import java.util.ArrayList;

/**
 * Created by matteo on 15/04/2015.
 */
public class SunAsyncTask extends AsyncTask<Void, Void, Void> {

    private ArrayList<ImageView> panoramaRightSky;
    private ArrayList<ImageView> panoramaLeftSky;

    private AnimationPanorama animationPanorama;
    private PenalizationPanoramaManager penalizationPanoramaManager;
    private int displayWidth;
    private int displayHeight;
    private GlobalData globalData;
    private Eye eye;

    /**
     *
     * @param panoramaRightSky
     * @param panoramaLeftSky
     * @param displayWidth
     * @param displayHeight
     * @param globalData
     * @param eye
     */
    public SunAsyncTask(ArrayList<ImageView> panoramaRightSky, ArrayList<ImageView> panoramaLeftSky, int displayWidth, int displayHeight, GlobalData globalData, Eye eye) {
        this.panoramaRightSky = panoramaRightSky;
        this.panoramaLeftSky = panoramaLeftSky;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.globalData = globalData;
        this.eye = eye;
        animationPanorama= new AnimationPanorama();
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        animationPanorama.animatePanoramaSun(panoramaLeftSky.get(1),panoramaRightSky.get(1),displayWidth,displayHeight);
        animationPanorama = new AnimationPanorama();
    }
}

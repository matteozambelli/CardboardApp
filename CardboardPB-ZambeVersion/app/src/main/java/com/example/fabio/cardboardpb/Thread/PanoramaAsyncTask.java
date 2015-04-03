package com.example.fabio.cardboardpb.Thread;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.fabio.cardboardpb.Animation.AnimationPanorama;

import java.util.ArrayList;

/**
 * Created by matteo on 03/04/2015.
 */
public class  PanoramaAsyncTask extends AsyncTask<Void, Void, Void>{

    private ArrayList<ImageView> panoramaLeft1;
    private ArrayList<ImageView> panoramaLeft2;
    private ArrayList<ImageView> panoramaRight1;
    private ArrayList<ImageView> panoramaRight2;
    private ArrayList<ImageView> panoramaRightSky;
    private ArrayList<ImageView> panoramaLeftSky;

    private Activity activity;
    private AnimationPanorama animationPanorama;

    /**
     *
     * @param panoramaLeft1
     * @param panoramaLeft2
     * @param panoramaRight1
     * @param panoramaRight2
     * @param panoramaLeftSky
     * @param panoramaRightSky
     * @param activity
     */
    public PanoramaAsyncTask(ArrayList<ImageView> panoramaLeft1, ArrayList<ImageView> panoramaLeft2, ArrayList<ImageView> panoramaRight1, ArrayList<ImageView> panoramaRight2,ArrayList<ImageView> panoramaLeftSky,ArrayList<ImageView> panoramaRightSky, Activity activity) {
        this.panoramaLeft1 = panoramaLeft1;
        this.panoramaLeft2 = panoramaLeft2;
        this.panoramaRight1 = panoramaRight1;
        this.panoramaRight2 = panoramaRight2;
        this.panoramaLeftSky=panoramaLeftSky;
        this.panoramaRightSky=panoramaRightSky;
        this.activity = activity;
        animationPanorama=new AnimationPanorama();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(Void... params) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                animationPanorama.animatePanoramaLeftView(panoramaLeft1.get(0),panoramaRight1.get(0));
                animationPanorama.animatePanoramaCloud(panoramaLeftSky.get(0),panoramaRightSky.get(0));
            }
        });

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

    }

}
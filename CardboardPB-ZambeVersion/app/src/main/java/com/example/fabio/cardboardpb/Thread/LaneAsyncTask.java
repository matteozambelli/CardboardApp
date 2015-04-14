package com.example.fabio.cardboardpb.Thread;

/**
 * Created by Fabio on 14/04/2015.
 */

import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.fabio.cardboardpb.Animation.AnimationLane;
import com.example.fabio.cardboardpb.Animation.AnimationPanorama;
import com.example.fabio.cardboardpb.Manager.Enum.Side;
import com.example.fabio.cardboardpb.Manager.PanoramaManager;

import java.util.ArrayList;

/**
 * Created by matteo on 03/04/2015.
 */
public class LaneAsyncTask extends AsyncTask<Void, Void, Void> {


    private ImageView laneLeft1;
    private ImageView laneLeft2;
    private ImageView laneLeft3;
    private ImageView laneRight1;
    private ImageView laneRight2;
    private ImageView laneRight3;
    //private PanoramaManager panoramaManager;
    private AnimationLane animationLane;
    //private int pick=0;
    //private Side side;
    private int displayWidth;
    private int displayHeight;


    public LaneAsyncTask( ImageView laneLeft1, ImageView laneLeft2, ImageView laneLeft3,
                             ImageView laneRight1, ImageView laneRight2,
                             ImageView laneRight3,
                             int width,int height) {

        this.laneLeft1 = laneLeft1;
        this.laneLeft2 = laneLeft2;
        this.laneLeft3 = laneLeft3;

        this.laneRight1 = laneRight1;
        this.laneRight2 = laneRight2;
        this.laneRight3 = laneRight3;


        this.displayHeight=height;
        this.displayWidth=width;

        animationLane = new AnimationLane();
        //panoramaManager = new PanoramaManager();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    public Void doInBackground(Void... params) {
        //panoramaManager.randomPanorama();
        //pick= panoramaManager.getIdSubject();
        //side=panoramaManager.getSelectedSide();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        /*animationLane.hideImage(laneLeft1);
        animationLane.hideImage(laneLeft2);
        animationLane.hideImage(laneLeft3);
        animationLane.hideImage(laneRight1);
        animationLane.hideImage(laneRight2);
        animationLane.hideImage(laneRight3);*/
        animationLane.showImage(laneLeft1);
        animationLane.showImage(laneRight1);
        animationLane.animateLane1(laneLeft1, laneRight1,
                        displayWidth, displayHeight);

        animationLane.showImage(laneLeft2);
        animationLane.showImage(laneRight2);
        animationLane.animateLane2(laneLeft2, laneRight2,
                        displayWidth, displayHeight);

        animationLane.showImage(laneLeft3);
        animationLane.showImage(laneRight3);
        animationLane.animateLane3(laneLeft3, laneRight3,
                        displayWidth, displayHeight);

    }


}
package it.unibg.p3d4amb.carracingcarboard.Thread;

/**
 * Created by Fabio on 14/04/2015.
 */

import android.os.AsyncTask;
import android.widget.ImageView;

import it.unibg.p3d4amb.carracingcarboard.Animation.AnimationLane;

/**
 * Created by matteo on 03/04/2015.
 */
public class LaneAsyncTask extends AsyncTask<Void, Void, Void> {


    private ImageView laneLeft1;
    private ImageView laneLeft3;
    private ImageView laneRight1;
    private ImageView laneRight3;
    private AnimationLane animationLane;
    private int displayWidth;
    private int displayHeight;


    public LaneAsyncTask( ImageView laneLeft1, ImageView laneLeft3,
                             ImageView laneRight1,
                             ImageView laneRight3,
                             int width,int height) {

        this.laneLeft1 = laneLeft1;
        this.laneLeft3 = laneLeft3;
        this.laneRight1 = laneRight1;
        this.laneRight3 = laneRight3;
        this.displayHeight=height;
        this.displayWidth=width;

        animationLane = new AnimationLane();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    public Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        animationLane.showImage(laneLeft1);
        animationLane.showImage(laneRight1);
        animationLane.animateLane1(laneLeft1, laneRight1,
                        displayWidth, displayHeight);

        animationLane.showImage(laneLeft3);
        animationLane.showImage(laneRight3);
        animationLane.animateLane3(laneLeft3, laneRight3,
                        displayWidth, displayHeight);

    }


}
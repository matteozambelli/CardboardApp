package com.example.fabio.cardboardpb.Thread;

import android.app.Activity;
import android.widget.ImageView;

import com.example.fabio.cardboardpb.Manager.PanoramaManager;

import java.util.ArrayList;

/**
 * Created by matteo on 07/04/2015.
 */
public class PanoramaThread extends   Thread{

    private ArrayList<ImageView> panoramaLeft1;
    private ArrayList<ImageView> panoramaLeft2;
    private ArrayList<ImageView> panoramaRight1;
    private ArrayList<ImageView> panoramaRight2;
    private ArrayList<ImageView> panoramaRightSky;
    private ArrayList<ImageView> panoramaLeftSky;

    private Activity activity;
    private PanoramaAsyncTask  panoramaAsyncTask;


    /**
     *
     * @param panoramaLeft1
     * @param panoramaLeft2
     * @param panoramaRight1
     * @param panoramaRight2
     * @param panoramaRightSky
     * @param panoramaLeftSky
     * @param activity
     */
    public PanoramaThread(ArrayList<ImageView> panoramaLeft1, ArrayList<ImageView> panoramaLeft2, ArrayList<ImageView> panoramaRight1, ArrayList<ImageView> panoramaRight2, ArrayList<ImageView> panoramaRightSky, ArrayList<ImageView> panoramaLeftSky, Activity activity) {
            this.panoramaLeft1 = panoramaLeft1;
        this.panoramaLeft2 = panoramaLeft2;
        this.panoramaRight1 = panoramaRight1;
        this.panoramaRight2 = panoramaRight2;
        this.panoramaRightSky = panoramaRightSky;
        this.panoramaLeftSky = panoramaLeftSky;

        this.activity = activity;
    }

    @Override
    public void run() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                panoramaAsyncTask= new PanoramaAsyncTask(panoramaLeft1,panoramaLeft2,panoramaRight1,panoramaRight2,panoramaLeftSky,panoramaRightSky,activity);
                panoramaAsyncTask.doInBackground();
                try {
                    Thread.sleep(1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

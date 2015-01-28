package com.example.fabio.cardboardpb.Manager;

import android.app.Activity;
import android.widget.ImageView;

import com.example.fabio.cardboardpb.Animation.AnimationPanorama;

/**
 * Created by matteo on 28/01/2015.
 */
public class PanoramaThread extends Thread{

    private Activity activity;
    private PanoramaManager panoramaManager;
    private AnimationPanorama animationPanorama;
    private Side panoramaSide;
    private int idPanorama;
    private boolean state;
    //Left eye panorama
    private ImageView panoramaLeftSideLeftId0;
    private ImageView panoramaLeftSideLeftId1;
    private ImageView panoramaLeftSideRightId0;
    private ImageView panoramaLeftSideRightId1;
    //Right eye panorama
    private ImageView panoramaRightSideLeftId0;
    private ImageView panoramaRightSideLeftId1;
    private ImageView panoramaRightSideRightId0;
    private ImageView panoramaRightSideRightId1;


    /**
     *
     * @param activity
     * @param panoramaLeftSideLeftId0
     * @param panoramaLeftSideLeftId1
     * @param panoramaLeftSideRightId0
     * @param panoramaLeftSideRightId1
     * @param panoramaRightSideLeftId0
     * @param panoramaRightSideLeftId1
     * @param panoramaRightSideRightId0
     * @param panoramaRightSideRightId1
     */
    public PanoramaThread(Activity activity, ImageView panoramaLeftSideLeftId0, ImageView panoramaLeftSideLeftId1, ImageView panoramaLeftSideRightId0, ImageView panoramaLeftSideRightId1, ImageView panoramaRightSideLeftId0, ImageView panoramaRightSideLeftId1, ImageView panoramaRightSideRightId0, ImageView panoramaRightSideRightId1) {
        this.activity = activity;
        panoramaManager=new PanoramaManager();
        panoramaManager.randomPanorama();
        animationPanorama=new AnimationPanorama();
        this.panoramaLeftSideLeftId0 = panoramaLeftSideLeftId0;
        this.panoramaLeftSideLeftId1 = panoramaLeftSideLeftId1;
        this.panoramaLeftSideRightId0 = panoramaLeftSideRightId0;
        this.panoramaLeftSideRightId1 = panoramaLeftSideRightId1;
        this.panoramaRightSideLeftId0 = panoramaRightSideLeftId0;
        this.panoramaRightSideLeftId1 = panoramaRightSideLeftId1;
        this.panoramaRightSideRightId0 = panoramaRightSideRightId0;
        this.panoramaRightSideRightId1 = panoramaRightSideRightId1;
        animationPanorama.hideImage(panoramaLeftSideLeftId0);
        animationPanorama.hideImage(panoramaLeftSideLeftId1);
        animationPanorama.hideImage(panoramaLeftSideRightId0);
        animationPanorama.hideImage(panoramaLeftSideRightId1);
        animationPanorama.hideImage(panoramaRightSideLeftId0);
        animationPanorama.hideImage(panoramaRightSideLeftId1);
        animationPanorama.hideImage(panoramaRightSideRightId0);
        animationPanorama.hideImage(panoramaRightSideRightId1);
    }

    @Override
    public void run() {
        while(true){
            if(state){
                // Se è vero fai questo
            }else{
                // Se non è vero fai altro
            }
            state = !state;

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    panoramaSide = panoramaManager.getSelectedSide();
                    idPanorama = panoramaManager.getIdSubject();

                    if (panoramaSide.equals(Side.LEFT) && idPanorama == 1) {
                        animationPanorama.showImage(panoramaLeftSideLeftId0);
                        animationPanorama.showImage(panoramaRightSideLeftId0);
                        // t1.setText("left 0");
                        animationPanorama.animatePanoramaLeftView(panoramaLeftSideLeftId0, panoramaRightSideLeftId0);
                    }

                    if (panoramaSide.equals(Side.LEFT) && idPanorama == 2) {
                        animationPanorama.showImage(panoramaLeftSideLeftId1);
                        animationPanorama.showImage(panoramaRightSideLeftId1);
                        //t1.setText("left 1");
                        animationPanorama.animatePanoramaLeftView(panoramaLeftSideLeftId1, panoramaRightSideLeftId1);
                    }

                    if (panoramaSide.equals(Side.RIGHT) && idPanorama == 1) {
                        animationPanorama.showImage(panoramaLeftSideRightId0);
                        animationPanorama.showImage(panoramaRightSideRightId0);
                        //t1.setText("right 0");
                        animationPanorama.animatePanoramaRightView(panoramaLeftSideRightId0, panoramaRightSideRightId0);
                    }
                    if (panoramaSide.equals(Side.RIGHT) && idPanorama == 2) {
                        animationPanorama.showImage(panoramaLeftSideRightId1);
                        animationPanorama.showImage(panoramaRightSideRightId1);
                        //t1.setText("right 1");
                        animationPanorama.animatePanoramaRightView(panoramaLeftSideRightId1, panoramaRightSideRightId1);
                    }


                }
            });
        }
    }


}

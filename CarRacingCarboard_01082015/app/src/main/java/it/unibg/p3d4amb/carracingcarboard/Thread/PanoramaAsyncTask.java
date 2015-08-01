package it.unibg.p3d4amb.carracingcarboard.Thread;

import android.os.AsyncTask;
import android.widget.ImageView;

import it.unibg.p3d4amb.carracingcarboard.Animation.AnimationPanorama;
import it.unibg.p3d4amb.carracingcarboard.Manager.Enum.Eye;
import it.unibg.p3d4amb.carracingcarboard.Manager.Enum.Side;
import it.unibg.p3d4amb.carracingcarboard.Manager.GlobalData;
import it.unibg.p3d4amb.carracingcarboard.Manager.PanoramaManager;
import it.unibg.p3d4amb.carracingcarboard.Manager.PenalizationPanoramaManager;

import java.util.ArrayList;

/**
 * Created by matteo on 03/04/2015.
 */
public class PanoramaAsyncTask extends AsyncTask<Void, Void, Void> {


    private ArrayList<ImageView> panoramaLeft1;
    private ArrayList<ImageView> panoramaLeft2;
    private ArrayList<ImageView> panoramaRight1;
    private ArrayList<ImageView> panoramaRight2;
    private ArrayList<ImageView> panoramaRightSky;
    private ArrayList<ImageView> panoramaLeftSky;
    private PanoramaManager panoramaManager;
    private AnimationPanorama animationPanorama;
    private PenalizationPanoramaManager penalizationPanoramaManager;
    private int pick=0;
    private Side side;
    private int displayWidth;
    private int displayHeight;
    private GlobalData globalData;
    private Eye eye;


    public PanoramaAsyncTask(ArrayList<ImageView> panoramaLeft1, ArrayList<ImageView> panoramaLeft2,
                             ArrayList<ImageView> panoramaRight1, ArrayList<ImageView> panoramaRight2,
                             ArrayList<ImageView> panoramaLeftSky, ArrayList<ImageView> panoramaRightSky,
                             int width,int height,GlobalData globalData,Eye eye) {

        this.panoramaLeft1 = panoramaLeft1;
        this.panoramaLeft2 = panoramaLeft2;
        this.panoramaRight1 = panoramaRight1;
        this.panoramaRight2 = panoramaRight2;
        this.panoramaLeftSky = panoramaLeftSky;
        this.panoramaRightSky = panoramaRightSky;

        this.displayHeight=height;
        this.displayWidth=width;
        this.globalData=globalData;
        this.eye=eye;
        penalizationPanoramaManager= new PenalizationPanoramaManager(panoramaLeft1,panoramaRight1,panoramaLeft2,panoramaRight2,panoramaLeftSky,panoramaRightSky,globalData);
        animationPanorama = new AnimationPanorama();
        panoramaManager = new PanoramaManager();
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

                    panoramaManager.randomPanorama();
                    pick=panoramaManager.getIdSubject();
                    side=panoramaManager.getSelectedSide();

                    animationPanorama.hideImage(panoramaLeft1.get(0));
                    animationPanorama.hideImage(panoramaLeft1.get(1));
                    animationPanorama.hideImage(panoramaLeft1.get(2));
                    animationPanorama.hideImage(panoramaLeft2.get(0));
                    animationPanorama.hideImage(panoramaLeft2.get(1));
                    animationPanorama.hideImage(panoramaLeft2.get(2));
                    animationPanorama.hideImage(panoramaRight1.get(0));
                    animationPanorama.hideImage(panoramaRight1.get(1));
                    animationPanorama.hideImage(panoramaRight1.get(2));
                    animationPanorama.hideImage(panoramaRight2.get(0));
                    animationPanorama.hideImage(panoramaRight2.get(1));
                    animationPanorama.hideImage(panoramaRight2.get(2));


                    if (side.equals(Side.LEFT)) {
                        if (pick == 1) {
                            animationPanorama.showImage(panoramaLeft1.get(0));
                            animationPanorama.showImage(panoramaRight1.get(0));
                            animationPanorama.animatePanoramaLeftView(panoramaLeft1.get(0), panoramaRight1.get(0),
                                    displayWidth, displayHeight);
                            penalizationPanoramaManager.penalize(eye);
                        }
                        if (pick == 2) {
                            animationPanorama.showImage(panoramaLeft1.get(1));
                            animationPanorama.showImage(panoramaRight1.get(1));
                            animationPanorama.animatePanoramaLeftView(panoramaLeft1.get(1), panoramaRight1.get(1),
                                    displayWidth, displayHeight);
                            penalizationPanoramaManager.penalize(eye);
                        }
                        if (pick == 3) {
                            animationPanorama.showImage(panoramaLeft1.get(2));
                            animationPanorama.showImage(panoramaRight1.get(2));
                            animationPanorama.animatePanoramaLeftView(panoramaLeft1.get(2), panoramaRight1.get(2),
                                    displayWidth, displayHeight);
                            penalizationPanoramaManager.penalize(eye);
                        }
                    } else {
                        if (pick == 1) {
                            animationPanorama.showImage(panoramaLeft2.get(0));
                            animationPanorama.showImage(panoramaRight2.get(0));
                            animationPanorama.animatePanoramaRightView(panoramaLeft2.get(0), panoramaRight2.get(0),
                                    displayWidth, displayHeight);
                            penalizationPanoramaManager.penalize(eye);
                        }
                        if (pick== 2) {
                            animationPanorama.showImage(panoramaLeft2.get(1));
                            animationPanorama.showImage(panoramaRight2.get(1));
                            animationPanorama.animatePanoramaRightView(panoramaLeft2.get(1), panoramaRight2.get(1),
                                    displayWidth, displayHeight);
                            penalizationPanoramaManager.penalize(eye);
                        }
                        if (pick == 3) {
                            animationPanorama.showImage(panoramaLeft2.get(2));
                            animationPanorama.showImage(panoramaRight2.get(2));
                            animationPanorama.animatePanoramaRightView(panoramaLeft2.get(2), panoramaRight2.get(2),
                                    displayWidth, displayHeight);
                            penalizationPanoramaManager.penalize(eye);
                        }
                    }
                    penalizationPanoramaManager.penalize(eye);
                    animationPanorama.animatePanoramaCloud(panoramaLeftSky.get(0), panoramaRightSky.get(0),
                            displayWidth, displayHeight);




                }


}
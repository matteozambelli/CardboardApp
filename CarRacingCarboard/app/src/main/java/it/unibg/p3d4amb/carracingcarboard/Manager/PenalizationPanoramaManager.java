package it.unibg.p3d4amb.carracingcarboard.Manager;

import android.widget.ImageView;

import it.unibg.p3d4amb.carracingcarboard.Manager.Enum.Eye;

import java.util.ArrayList;

/**
 * Created by matteo on 15/04/2015.
 */
public class PenalizationPanoramaManager implements PenalizationPanoramaManagerInterface {

    private ArrayList<ImageView> panoramaLeft1= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaRight1= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaLeft2= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaRight2= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaLeftSky= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaRightSky= new ArrayList<ImageView>();
    private GlobalData globalData;

    /**
     *
     * @param panoramaLeft1
     * @param panoramaRight1
     * @param panoramaLeft2
     * @param panoramaRight2
     * @param panoramaLeftSky
     * @param panoramaRightSky
     * @param globalData
     */
    public PenalizationPanoramaManager(ArrayList<ImageView> panoramaLeft1, ArrayList<ImageView> panoramaRight1, ArrayList<ImageView> panoramaLeft2, ArrayList<ImageView> panoramaRight2, ArrayList<ImageView> panoramaLeftSky, ArrayList<ImageView> panoramaRightSky, GlobalData globalData) {
        this.panoramaLeft1 = panoramaLeft1;
        this.panoramaRight1 = panoramaRight1;
        this.panoramaLeft2 = panoramaLeft2;
        this.panoramaRight2 = panoramaRight2;
        this.panoramaLeftSky = panoramaLeftSky;
        this.panoramaRightSky = panoramaRightSky;
        this.globalData = globalData;
    }


    /**
     * set the penalization to the imageView of the enemy
     * @param eye
     */
    @Override
    public void penalize(Eye eye) {
        switch(eye){
            case LEFT_EYE:{

                panoramaLeft1.get(0).setAlpha(getLevelPenalization());
                panoramaLeft1.get(1).setAlpha(getLevelPenalization());
                panoramaLeft1.get(2).setAlpha(getLevelPenalization());
                panoramaLeft2.get(0).setAlpha(getLevelPenalization());
                panoramaLeft2.get(1).setAlpha(getLevelPenalization());
                panoramaLeft2.get(2).setAlpha(getLevelPenalization());
                panoramaLeftSky.get(0).setAlpha(getLevelPenalization());
                panoramaLeftSky.get(1).setAlpha(getLevelPenalization());

            }break;
            case RIGHT_EYE:{
                panoramaRight1.get(0).setAlpha(getLevelPenalization());
                panoramaRight1.get(1).setAlpha(getLevelPenalization());
                panoramaRight1.get(2).setAlpha(getLevelPenalization());
                panoramaRight2.get(0).setAlpha(getLevelPenalization());
                panoramaRight2.get(1).setAlpha(getLevelPenalization());
                panoramaRight2.get(2).setAlpha(getLevelPenalization());
                panoramaRightSky.get(0).setAlpha(getLevelPenalization());
                panoramaRightSky.get(1).setAlpha(getLevelPenalization());



            }break;
            default:{}break;
        }

    }
    /**
     *
     * @return Alpha value to penalize the image
     */
    private int getLevelPenalization(){

        switch(globalData.getLevel()){

            case 1:{
                return 200;
            }
            case 2:{
                return 180;
            }
            case 3:{
                return 160;
            }
            case 4:{
                return 140;
            }
            case 5:{
                return 120;
            }
            case 6:{
                return 100;
            }
            case 7:{
                return 80;
            }
            case 8:{
                return 50;
            }
            default:{
                return 50;
            }
        }
    }

}

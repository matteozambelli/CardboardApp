package com.example.fabio.cardboardpb.Manager;

import android.widget.ImageView;

import com.example.fabio.cardboardpb.Manager.Enum.Eye;

import java.util.ArrayList;

/**
 * Created by matteo on 05/02/2015.
 */

public class PenalizationManager implements PenalizationManagerInterface {

    private ArrayList<ImageView> enemyLeftLane1;
    private ArrayList<ImageView> enemyLeftLane2;
    private ArrayList<ImageView> enemyLeftLane3;
    private ArrayList<ImageView> enemyRightLane1;
    private ArrayList<ImageView> enemyRightLane2;
    private ArrayList<ImageView> enemyRightLane3;

    private ArrayList<ImageView> panoramaLeft1= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaRight1= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaLeft2= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaRight2= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaLeftSky= new ArrayList<ImageView>();
    private ArrayList<ImageView> panoramaRightSky= new ArrayList<ImageView>();

    private boolean panorama;


    private GlobalData globalData;


    /**
     * constructor for enemy penalization
     * @param enemyLeftLane1
     * @param enemyLeftLane2
     * @param enemyLeftLane3
     * @param enemyRightLane1
     * @param enemyRightLane2
     * @param enemyRightLane3
     * @param globalData
     */
    public PenalizationManager(ArrayList<ImageView> enemyLeftLane1, ArrayList<ImageView> enemyLeftLane2, ArrayList<ImageView> enemyLeftLane3, ArrayList<ImageView> enemyRightLane1, ArrayList<ImageView> enemyRightLane2, ArrayList<ImageView> enemyRightLane3, GlobalData globalData) {
        this.enemyLeftLane1 = enemyLeftLane1;
        this.enemyLeftLane2 = enemyLeftLane2;
        this.enemyLeftLane3 = enemyLeftLane3;
        this.enemyRightLane1 = enemyRightLane1;
        this.enemyRightLane2 = enemyRightLane2;
        this.enemyRightLane3 = enemyRightLane3;
        this.globalData = globalData;
    }

    /**
     * constructor for panorama penalization
     * @param panoramaLeft1
     * @param panoramaRight1
     * @param panoramaLeft2
     * @param panoramaRight2
     * @param panoramaLeftSky
     * @param panoramaRightSky
     * @param panorama
     * @param globalData
     */
    public PenalizationManager(ArrayList<ImageView> panoramaLeft1, ArrayList<ImageView> panoramaRight1, ArrayList<ImageView> panoramaLeft2, ArrayList<ImageView> panoramaRight2, ArrayList<ImageView> panoramaLeftSky, ArrayList<ImageView> panoramaRightSky, GlobalData globalData,boolean panorama) {
        this.panoramaLeft1 = panoramaLeft1;
        this.panoramaRight1 = panoramaRight1;
        this.panoramaLeft2 = panoramaLeft2;
        this.panoramaRight2 = panoramaRight2;
        this.panoramaLeftSky = panoramaLeftSky;
        this.panoramaRightSky = panoramaRightSky;
        this.panorama = panorama;
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
                if(panorama){
                    panoramaLeft1.get(0).setAlpha(getLevelPenalization());
                    panoramaLeft1.get(1).setAlpha(getLevelPenalization());
                    panoramaLeft2.get(0).setAlpha(getLevelPenalization());
                    panoramaLeft2.get(1).setAlpha(getLevelPenalization());
                    panoramaLeftSky.get(0).setAlpha(getLevelPenalization());
                }else{
                enemyLeftLane1.get(0).setAlpha(getLevelPenalization());
                enemyLeftLane2.get(0).setAlpha(getLevelPenalization());
                enemyLeftLane3.get(0).setAlpha(getLevelPenalization());
                enemyLeftLane1.get(1).setAlpha(getLevelPenalization());
                enemyLeftLane2.get(1).setAlpha(getLevelPenalization());
                enemyLeftLane3.get(1).setAlpha(getLevelPenalization());
                enemyLeftLane1.get(2).setAlpha(getLevelPenalization());
                enemyLeftLane2.get(2).setAlpha(getLevelPenalization());
                enemyLeftLane3.get(2).setAlpha(getLevelPenalization());
                enemyLeftLane1.get(3).setAlpha(getLevelPenalization());
                enemyLeftLane2.get(3).setAlpha(getLevelPenalization());
                enemyLeftLane3.get(3).setAlpha(getLevelPenalization());
                }

            }break;
            case RIGHT_EYE:{
                if(panorama){
                    panoramaRight1.get(0).setAlpha(getLevelPenalization());
                    panoramaRight1.get(1).setAlpha(getLevelPenalization());
                    panoramaRight2.get(0).setAlpha(getLevelPenalization());
                    panoramaRight2.get(1).setAlpha(getLevelPenalization());
                    panoramaRightSky.get(0).setAlpha(getLevelPenalization());

                }else {
                    enemyRightLane1.get(0).setAlpha(getLevelPenalization());
                    enemyRightLane2.get(0).setAlpha(getLevelPenalization());
                    enemyRightLane3.get(0).setAlpha(getLevelPenalization());
                    enemyRightLane1.get(1).setAlpha(getLevelPenalization());
                    enemyRightLane2.get(1).setAlpha(getLevelPenalization());
                    enemyRightLane3.get(1).setAlpha(getLevelPenalization());
                    enemyRightLane1.get(2).setAlpha(getLevelPenalization());
                    enemyRightLane2.get(2).setAlpha(getLevelPenalization());
                    enemyRightLane3.get(2).setAlpha(getLevelPenalization());
                    enemyRightLane1.get(3).setAlpha(getLevelPenalization());
                    enemyRightLane2.get(3).setAlpha(getLevelPenalization());
                    enemyRightLane3.get(3).setAlpha(getLevelPenalization());
                }
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

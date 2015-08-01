package it.unibg.p3d4amb.carracingcarboard.Manager;

import android.widget.ImageView;
import it.unibg.p3d4amb.carracingcarboard.Manager.Enum.Eye;
import java.util.ArrayList;

/**
 * Created by matteo on 05/02/2015.
 */

public class PenalizationEnemyManager implements PenalizationEnemyManagerInterface {

    private ArrayList<ImageView> enemyLeftLane1;
    private ArrayList<ImageView> enemyLeftLane2;
    private ArrayList<ImageView> enemyLeftLane3;
    private ArrayList<ImageView> enemyRightLane1;
    private ArrayList<ImageView> enemyRightLane2;
    private ArrayList<ImageView> enemyRightLane3;



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
    public PenalizationEnemyManager(ArrayList<ImageView> enemyLeftLane1, ArrayList<ImageView> enemyLeftLane2, ArrayList<ImageView> enemyLeftLane3, ArrayList<ImageView> enemyRightLane1, ArrayList<ImageView> enemyRightLane2, ArrayList<ImageView> enemyRightLane3, GlobalData globalData) {
        this.enemyLeftLane1 = enemyLeftLane1;
        this.enemyLeftLane2 = enemyLeftLane2;
        this.enemyLeftLane3 = enemyLeftLane3;
        this.enemyRightLane1 = enemyRightLane1;
        this.enemyRightLane2 = enemyRightLane2;
        this.enemyRightLane3 = enemyRightLane3;
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


            }break;
            case RIGHT_EYE:{

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

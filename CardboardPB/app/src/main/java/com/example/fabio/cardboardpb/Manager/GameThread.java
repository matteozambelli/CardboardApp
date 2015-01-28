package com.example.fabio.cardboardpb.Manager;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabio.cardboardpb.Animation.AnimationEnemies;

import org.w3c.dom.Text;

import java.util.Timer;

/**
 * Created by matteo on 27/01/2015.
 */
public class GameThread extends Thread{

    private GameManager gameManager;
    private AnimationEnemies animationEnemies;
    private ImageView enemyLeftLane1Id0;
    private ImageView enemyLeftLane2Id0;
    private ImageView enemyLeftLane3Id0;
    private ImageView enemyRightLane1Id0;
    private ImageView enemyRightLane2Id0;
    private ImageView enemyRightLane3Id0;
    private TextView t1;
    private TextView textLevel;
    private boolean state;
    private Activity activity;
    private int pick;
    private int size;
    private int i;

    /**
     *
     * @param activity
     * @param text1
     * @param textLevel
     * @param i1
     * @param i2
     * @param i3
     * @param i4
     * @param i5
     * @param i6
     */
    public GameThread(Activity activity,TextView text1,TextView textLevel,ImageView i1,ImageView i2, ImageView i3,ImageView i4,ImageView i5, ImageView i6) {
        this.activity=activity;
        gameManager = new GameManager();
        gameManager.generateGameData();
        animationEnemies = new AnimationEnemies();
        t1=text1;
        this.textLevel=textLevel;
        enemyLeftLane1Id0=i1;
        enemyLeftLane2Id0=i2;
        enemyLeftLane3Id0=i3;
        enemyRightLane1Id0=i4;
        enemyRightLane2Id0=i5;
        enemyRightLane3Id0=i6;
        animationEnemies.hideImage(enemyLeftLane1Id0);
        animationEnemies.hideImage(enemyLeftLane2Id0);
        animationEnemies.hideImage(enemyLeftLane3Id0);
        animationEnemies.hideImage(enemyRightLane1Id0);
        animationEnemies.hideImage(enemyRightLane2Id0);
        animationEnemies.hideImage(enemyRightLane3Id0);
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
                    textLevel.setText("LEVEL "+gameManager.getIdLevel());
                    pick = gameManager.getIdEnemy().get(i).getSelectedLane();
                    size = gameManager.getIdEnemy().size();

                    if (pick == 1) {
                        animationEnemies.showImage(enemyLeftLane1Id0);
                        animationEnemies.showImage(enemyRightLane1Id0);
                        animationEnemies.animateFrontCarLane1(enemyLeftLane1Id0, enemyRightLane1Id0);

                        t1.setText("lane 1");
                    }
                    if (pick == 2) {
                        animationEnemies.showImage(enemyLeftLane2Id0);
                        animationEnemies.showImage(enemyRightLane2Id0);
                        animationEnemies.animateFrontCarLane2(enemyLeftLane2Id0, enemyRightLane2Id0);

                        t1.setText("lane 2");
                    }
                    if (pick == 3) {
                        animationEnemies.showImage(enemyLeftLane3Id0);
                        animationEnemies.showImage(enemyRightLane3Id0);
                        animationEnemies.animateFrontCarLane3(enemyLeftLane3Id0, enemyRightLane3Id0);

                        t1.setText("lane 3");
                    }

                }
            });
            try {
                Thread.sleep(gameManager.getInterval());
                i++;
                if(i==size) {
                    gameManager.generateGameData();
                    i=0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
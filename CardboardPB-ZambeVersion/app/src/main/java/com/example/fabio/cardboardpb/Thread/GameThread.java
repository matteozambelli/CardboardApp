package com.example.fabio.cardboardpb.Thread;

import android.app.Activity;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.fabio.cardboardpb.Animation.AnimationEnemies;
import com.example.fabio.cardboardpb.Animation.AnimationTarget;
import com.example.fabio.cardboardpb.Manager.GameManager;
import com.example.fabio.cardboardpb.Manager.GlobalData;
import com.example.fabio.cardboardpb.R;


/**
 * Created by matteo on 27/01/2015.
 */
public class GameThread extends Thread{


    private GameManager gameManager;
    private AnimationEnemies animationEnemies;
    private AnimationTarget animationTarget;
    private ImageView enemyLeftLane1Id0;
    private ImageView enemyLeftLane2Id0;
    private ImageView enemyLeftLane3Id0;
    private ImageView enemyRightLane1Id0;
    private ImageView enemyRightLane2Id0;
    private ImageView enemyRightLane3Id0;
    private TextView t1;
    private TextView t2;
    private ImageView target1;
    private ImageView target2;
    private ImageView target3;
    private TextView textLevel;
    private boolean state;
    private Activity activity;
    private int pick;
    private int size;
    private int i;
    private GlobalData globalData;


    Thread Controllo;
    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    int secs;
    int mins;
    int milliseconds;

    private long startTime = 0L;
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
    public GameThread(Activity activity,TextView text1,TextView text2,TextView textLevel,ImageView i1,ImageView i2, ImageView i3,
                      ImageView i4,ImageView i5,
                      ImageView i6, ImageView target1,ImageView target2,ImageView target3, GlobalData globalData) {
        this.activity=activity;
        gameManager = new GameManager();
        gameManager.generateGameData();
        animationEnemies = new AnimationEnemies();

        t1=text1;
        t2=text2;
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
        this.target1=target1;
        this.target2=target2;
        this.target3=target3;
        this.globalData=globalData;
        animationTarget=new AnimationTarget();

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
                    //collision(animationTarget);
                    //onAnimationTimer();

                    if (pick == 1) {
                        animationEnemies.showImage(enemyLeftLane1Id0);
                        animationEnemies.showImage(enemyRightLane1Id0);
                        onAnimationTimer();
                        animationTarget.animateTarget1(target1);
                        animationEnemies.animateFrontCarLane1(enemyLeftLane1Id0, enemyRightLane1Id0);

                        animationEnemies=new AnimationEnemies();
                    }
                    if (pick == 2) {
                        animationEnemies.showImage(enemyLeftLane2Id0);
                        animationEnemies.showImage(enemyRightLane2Id0);
                        onAnimationTimer();
                        animationTarget.animateTarget2(target2);
                        animationEnemies.animateFrontCarLane2(enemyLeftLane2Id0, enemyRightLane2Id0);

                        animationEnemies=new AnimationEnemies();


                       // t1.setText("lane 2");
                    }
                    if (pick == 3) {
                        animationEnemies.showImage(enemyLeftLane3Id0);
                        animationEnemies.showImage(enemyRightLane3Id0);
                        onAnimationTimer();

                        animationTarget.animateTarget3(target3);

                        animationEnemies.animateFrontCarLane3(enemyLeftLane3Id0, enemyRightLane3Id0);

                        //t1.setText("lane 3");
                    }

                    //animationEnemies=new AnimationEnemies();
                    animationTarget=new AnimationTarget();

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

    public void onAnimationTimer(){


                animationTarget.animationTarget1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        t1.setText("CREATO ANIMAZIONE 1");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        globalData.setEnd1(true);
                        t1.setText("FINITA ANIMAZIONE 1");

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }


                });

        animationEnemies.animationSetLane1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            /*public void onHide(Animation animation) {
                if (globalData.isEnd1() && globalData.getAbsolutePosition() == 1) {
                    animationEnemies.hideImage(enemyLeftLane1Id0);
                    t1.setText("Collision on 1");
                }

            }*/


        });


                animationTarget.animationTarget2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        t1.setText("CREATO ANIMAZIONE 2");


                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        globalData.setEnd2(true);
                        t1.setText("FINITA ANIMAZIONE 2");

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                animationTarget.animationTarget3.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        t1.setText("CREATO ANIMAZIONE 3");


                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        globalData.setEnd3(true);
                        t1.setText("FINITA ANIMAZIONE 3");

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                if(globalData.isEnd1() && globalData.getAbsolutePosition()==1){

                    t2.setText("COLLISIONE SU 1");
                    // animationEnemies.hideImage(enemyLeftLane1Id0);
                }
                if(globalData.isEnd2() && globalData.getAbsolutePosition()==2){
                    t2.setText("COLLISIONE SU 2");
                    //animationEnemies.hideImage(enemyLeftLane2Id0);
                }
                if(globalData.isEnd3() && globalData.getAbsolutePosition()==3){
                    t2.setText("COLLISIONE SU 3");
                    //  animationEnemies.hideImage(enemyLeftLane3Id0);
                }
                if(!(globalData.isEnd1() && globalData.getAbsolutePosition()==1) && !(globalData.isEnd2() && globalData.getAbsolutePosition()==2) && !(globalData.isEnd3() && globalData.getAbsolutePosition()==3)){
                    t2.setText("NO COLLISIONE");
                }


    }


    public void collision(final AnimationTarget animationTarget) {




    }


    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

             secs = (int) (updatedTime / 1000);
             mins = secs / 60;
            secs = secs % 60;
             milliseconds = (int) (updatedTime % 1000);
            //timerValue.setText("" + mins + ":"
             //       + String.format("%02d", secs) + ":"
               //     + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }

    };



}
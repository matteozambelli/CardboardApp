package com.example.fabio.cardboardpb.Thread;

import android.app.Activity;

import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.fabio.cardboardpb.Animation.AnimationEnemies;
import com.example.fabio.cardboardpb.Animation.AnimationTarget;
import com.example.fabio.cardboardpb.Manager.Enum.Eye;
import com.example.fabio.cardboardpb.Manager.GameManager;
import com.example.fabio.cardboardpb.Manager.GlobalData;
import com.example.fabio.cardboardpb.Manager.PenalizationManager;
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
    private ImageView target1;
    private ImageView target2;
    private ImageView target3;
    private TextView t1;
    private TextView t2;
    private TextView textLevelLeft;
    private TextView textLifeLeft;
    private TextView textScoreLeft;
    private TextView textLevelRight;
    private TextView textLifeRight;
    private TextView textScoreRight;
    private TextView textLevelGameOverLeft;
    private TextView textLevelGameOverRight;
    private TextView textScoreGameOverLeft;
    private TextView textScoreGameOverRight;
    private boolean state;
    private Activity activity;
    private int pick;
    private int size;
    private int i;
    private GlobalData globalData;
    private PenalizationManager penalizationManager;
    private Eye eye;
    private RelativeLayout relativeLayoutAnimationLeft;
    private RelativeLayout relativeLayoutAnimationRight;
    private RelativeLayout relativeLayoutLeft;
    private RelativeLayout relativeLayoutRight;
    private FrameLayout frameLayoutGameOverLeft;
    private FrameLayout frameLayoutGameOverRight;
    private AnimationExplosionView animationExplosionViewLeft;
    private AnimationExplosionView animationExplosionViewRight;
    private int displayWidth;
    private int displayHeight;
    //private Toast toastLifeLeft;
    //private Toast toastLifeRight;

    Thread Controllo;
    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    int secs;
    int mins;
    int milliseconds;

    private long startTime = 0L;
    public boolean runnable; //if life==0 runnable turn false
    TextView textLevelDialogLeft;

    /**
     * @param activity
     * @param text1
     * @param i1
     * @param i2
     * @param i3
     * @param i4
     * @param i5
     * @param i6
     * @param RLAnimationLeft
     * @param RLAnimationRight
     * @param width
     * @param height
     */
    public GameThread(Activity activity, TextView text1, TextView text2, TextView tLevelLeft, TextView tLifeLeft, TextView tScoreLeft,
                      TextView tLevelRight, TextView tLifeRight, TextView tScoreRight, ImageView i1, ImageView i2, ImageView i3,
                      ImageView i4, ImageView i5, ImageView i6, ImageView target1, ImageView target2, ImageView target3,
                      GlobalData globalData, Eye eye, RelativeLayout RLAnimationLeft, RelativeLayout RLAnimationRight,
                      int width, int height,boolean running) {
        this.activity=activity;
        relativeLayoutLeft=(RelativeLayout)activity.findViewById(R.id.relativeLayoutLeft);
        relativeLayoutRight=(RelativeLayout)activity.findViewById(R.id.relativeLayoutRight);

        frameLayoutGameOverLeft=(FrameLayout)activity.findViewById(R.id.frameLayoutGameOverLeft);
        frameLayoutGameOverRight=(FrameLayout)activity.findViewById(R.id.frameLayoutGameOverRight);

        relativeLayoutLeft.removeView(frameLayoutGameOverLeft);
        relativeLayoutRight.removeView(frameLayoutGameOverRight);

        gameManager = new GameManager();
        gameManager.generateGameData();
        animationEnemies = new AnimationEnemies();
        relativeLayoutAnimationLeft=RLAnimationLeft;
        relativeLayoutAnimationRight=RLAnimationRight;
        t1 =text1;
        t2=text2;
        textLifeLeft=tLifeLeft;
        textLevelLeft=tLevelLeft;
        textScoreLeft =tScoreLeft;
        textLifeRight=tLifeRight;
        textLevelRight=tLevelRight;
        textScoreRight =tScoreRight;
        displayHeight=height;
        displayWidth=width;
        runnable=running;
        //toastLifeLeft=Toast.makeText(activity.getApplicationContext(),"Oh noo, you lost a Life!!", Toast.LENGTH_LONG);
        //toastLifeRight=Toast.makeText(activity.getApplicationContext(), "Oh noo, you lost a Life!!", Toast.LENGTH_LONG);
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
        penalizationManager=new PenalizationManager(enemyLeftLane1Id0,enemyLeftLane2Id0, enemyLeftLane3Id0,
                enemyRightLane1Id0, enemyRightLane2Id0,  enemyRightLane3Id0, globalData);
        this.eye=eye;
    }

    @Override
    public void run() {
        while(runnable){
            if(state){
                // Se è vero fai questo
            }else{
                // Se non è vero fai altro
            }
            state = !state;

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (globalData.getLife() == 0){
                        runnable=false;
                        //todo PREMI PLAY PER INSERIRE NOME, UP PER RIGIOCARE

                        textLevelGameOverLeft = (TextView) frameLayoutGameOverLeft.findViewById(R.id.textLevelGameOverLeft);
                        textLevelGameOverLeft.setText("LEVEL: "+(globalData.getLevel().toString()));
                        textLevelGameOverRight = (TextView) frameLayoutGameOverRight.findViewById(R.id.textLevelGameOverRight);
                        textLevelGameOverRight.setText("LEVEL: "+(globalData.getLevel().toString()));
                        textScoreGameOverLeft = (TextView) frameLayoutGameOverLeft.findViewById(R.id.textScoreGameOverLeft);
                        textScoreGameOverLeft.setText("SCORE: "+(globalData.getScore().toString()));
                        textScoreGameOverRight = (TextView) frameLayoutGameOverRight.findViewById(R.id.textScoreGameOverRight);
                        textScoreGameOverRight.setText("SCORE: "+(globalData.getScore().toString()));

                        relativeLayoutLeft.addView(frameLayoutGameOverLeft);
                        relativeLayoutRight.addView(frameLayoutGameOverRight);

                    }

                    else { //only if globalData.getLife() >0
                        runnable = true;

                        textLevelLeft.setText("LEVEL: " + gameManager.getIdLevel());
                        textLifeLeft.setText("LIFE: " + globalData.getLife());
                        textScoreLeft.setText("SCORE: " + globalData.getScore().toString());

                        textLevelRight.setText("LEVEL: " + gameManager.getIdLevel());
                        textLifeRight.setText("LIFE: " + globalData.getLife());
                        textScoreRight.setText("SCORE: " + globalData.getScore().toString());

                        pick = gameManager.getIdEnemy().get(i).getSelectedLane();
                        size = gameManager.getIdEnemy().size();
                        penalizationManager.penalize(eye);
                        //collision(animationTarget);
                        //onAnimationTimer();

                        if (pick == 1) {
                            animationEnemies.showImage(enemyLeftLane1Id0);
                            animationEnemies.showImage(enemyRightLane1Id0);
                            onAnimationTimer();
                            animationTarget.animateTarget1(target1, displayWidth, displayHeight);
                            animationEnemies.animateFrontCarLane1(enemyLeftLane1Id0, enemyRightLane1Id0,
                                    displayWidth, displayHeight);
                            animationEnemies = new AnimationEnemies();
                        }
                        if (pick == 2) {
                            animationEnemies.showImage(enemyLeftLane2Id0);
                            animationEnemies.showImage(enemyRightLane2Id0);
                            onAnimationTimer();
                            animationTarget.animateTarget2(target2, displayWidth, displayHeight);
                            animationEnemies.animateFrontCarLane2(enemyLeftLane2Id0, enemyRightLane2Id0,
                                    displayWidth, displayHeight);

                            animationEnemies = new AnimationEnemies();


                            // t1.setText("lane 2");
                        }
                        if (pick == 3) {
                            animationEnemies.showImage(enemyLeftLane3Id0);
                            animationEnemies.showImage(enemyRightLane3Id0);
                            onAnimationTimer();

                            animationTarget.animateTarget3(target3, displayWidth, displayHeight);

                            animationEnemies.animateFrontCarLane3(enemyLeftLane3Id0, enemyRightLane3Id0,
                                    displayWidth, displayHeight);
                            animationEnemies = new AnimationEnemies();

                            //t1.setText("lane 3");
                        }

                        //animationEnemies=new AnimationEnemies();
                        animationTarget = new AnimationTarget();
                    }

                }
            });
          try {
                Thread.sleep(gameManager.getInterval());
                i++;
                if(i==size) {
                    gameManager.generateGameData();
                    globalData.increaseLevel();
                    i=0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void onAnimationTimer(){

        animationEnemies.animationSetLane1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(animationExplosionViewLeft!= null || animationExplosionViewLeft!= null) {
                    relativeLayoutAnimationLeft.removeView(animationExplosionViewLeft);
                    relativeLayoutAnimationRight.removeView(animationExplosionViewRight);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationEnemies.animationSetLane2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(animationExplosionViewLeft!= null || animationExplosionViewLeft!= null) {
                    relativeLayoutAnimationLeft.removeView(animationExplosionViewLeft);
                    relativeLayoutAnimationRight.removeView(animationExplosionViewRight);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationEnemies.animationSetLane3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(animationExplosionViewLeft!= null || animationExplosionViewLeft!= null) {
                    relativeLayoutAnimationLeft.removeView(animationExplosionViewLeft);
                    relativeLayoutAnimationRight.removeView(animationExplosionViewRight);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationTarget.animationTarget1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                globalData.setEnd1(false);
                globalData.setEnd2(false);
                globalData.setEnd3(false);
                t1.setText("CREATO ANIMAZIONE 1");

                if(!(globalData.isEnd1() && globalData.getAbsolutePosition()==1) && !(globalData.isEnd2() && globalData.getAbsolutePosition()==2) && !(globalData.isEnd3() && globalData.getAbsolutePosition()==3)){
                    t2.setText("NO COLLISIONE");
                }

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                globalData.setEnd1(true);
                t1.setText("FINITA ANIMAZIONE 1");
                if(globalData.isEnd1() && globalData.getAbsolutePosition()==1){
                    globalData.decreaseLife();
                    textLifeLeft.setText("LIFE: " + globalData.getLife().toString());
                    textLifeRight.setText("LIFE: " + globalData.getLife().toString());
                    t2.setText("COLLISIONE SU 1");
                    animationExplosionViewLeft = new AnimationExplosionView(activity.getApplicationContext());
                    animationExplosionViewRight = new AnimationExplosionView(activity.getApplicationContext());
                    relativeLayoutAnimationLeft.addView(animationExplosionViewLeft);
                    relativeLayoutAnimationRight.addView(animationExplosionViewRight);
                    animationEnemies.hideImage(enemyLeftLane1Id0);
                    animationEnemies.hideImage(enemyRightLane1Id0);
                }
                else{
                    globalData.increaseScore();
                    textScoreLeft.setText("SCORE: " + globalData.getScore().toString());
                    textScoreRight.setText("SCORE: " + globalData.getScore().toString());
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }


        });

        animationTarget.animationTarget2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                globalData.setEnd1(false);
                globalData.setEnd2(false);
                globalData.setEnd3(false);
                t1.setText("CREATO ANIMAZIONE 2");

                if(!(globalData.isEnd1() && globalData.getAbsolutePosition()==1) && !(globalData.isEnd2() && globalData.getAbsolutePosition()==2) && !(globalData.isEnd3() && globalData.getAbsolutePosition()==3)){
                    t2.setText("NO COLLISIONE");
                }


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                globalData.setEnd2(true);
                t1.setText("FINITA ANIMAZIONE 2");
                if(globalData.isEnd2() && globalData.getAbsolutePosition()==2){
                    globalData.decreaseLife();
                    textLifeLeft.setText("LIFE: " + globalData.getLife().toString());
                    textLifeRight.setText("LIFE: " + globalData.getLife().toString());
                    t2.setText("COLLISIONE SU 2");
                    animationExplosionViewLeft = new AnimationExplosionView(activity.getApplicationContext());
                    animationExplosionViewRight = new AnimationExplosionView(activity.getApplicationContext());
                    animationExplosionViewLeft.setX(100);
                    animationExplosionViewRight.setX(100);
                    relativeLayoutAnimationLeft.addView(animationExplosionViewLeft);
                    relativeLayoutAnimationRight.addView(animationExplosionViewRight);
                    animationEnemies.hideImage(enemyLeftLane2Id0);
                    animationEnemies.hideImage(enemyRightLane2Id0);
                }
                else{
                    globalData.increaseScore();
                    textScoreLeft.setText("SCORE: " + globalData.getScore().toString());
                    textScoreRight.setText("SCORE: " + globalData.getScore().toString());
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationTarget.animationTarget3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                globalData.setEnd1(false);
                globalData.setEnd2(false);
                globalData.setEnd3(false);
                t1.setText("CREATO ANIMAZIONE 3");

                if(!(globalData.isEnd1() && globalData.getAbsolutePosition()==1) && !(globalData.isEnd2() && globalData.getAbsolutePosition()==2) && !(globalData.isEnd3() && globalData.getAbsolutePosition()==3)){
                    t2.setText("NO COLLISIONE");
                }


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                globalData.setEnd3(true);
                t1.setText("FINITA ANIMAZIONE 3");
                if(globalData.isEnd3() && globalData.getAbsolutePosition()==3){
                    globalData.decreaseLife();
                    t2.setText("COLLISIONE SU 3");
                    textLifeLeft.setText("LIFE: " + globalData.getLife().toString());
                    textLifeRight.setText("LIFE: " + globalData.getLife().toString());
                    animationExplosionViewLeft = new AnimationExplosionView(activity.getApplicationContext());
                    animationExplosionViewRight = new AnimationExplosionView(activity.getApplicationContext());
                    animationExplosionViewLeft.setX(200);
                    animationExplosionViewRight.setX(200);
                    relativeLayoutAnimationLeft.addView(animationExplosionViewLeft);
                    relativeLayoutAnimationRight.addView(animationExplosionViewRight);
                    animationEnemies.hideImage(enemyLeftLane3Id0);
                    animationEnemies.hideImage(enemyRightLane3Id0);
                }
                else{
                    globalData.increaseScore();
                    textScoreLeft.setText("SCORE: " + globalData.getScore().toString());
                    textScoreRight.setText("SCORE: " + globalData.getScore().toString());
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
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
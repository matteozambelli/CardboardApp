package com.example.fabio.cardboardpb.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabio.cardboardpb.Animation.AnimationEnemies;
import com.example.fabio.cardboardpb.Animation.AnimationPanorama;


import com.example.fabio.cardboardpb.Manager.GameManager;
import com.example.fabio.cardboardpb.Manager.GameThread;
import com.example.fabio.cardboardpb.Manager.PanoramaManager;
import com.example.fabio.cardboardpb.Manager.Side;
import com.example.fabio.cardboardpb.R;


public class MainActivity extends Activity {


    private int i=0;
    private TextView textLevel;
    private GameManager gameManager=new GameManager();
    private PanoramaManager panoramaManager=new PanoramaManager();
    private AnimationEnemies animationEnemies;
    private AnimationPanorama panoramaAnimation;
    private int pick;
    private int size;
    private Side panoramaSide;
    private int idPanorama;
    //private GameThread g;
    private boolean isEndEnemyLane1 =false;
    private boolean isEndEnemyLane2 = false;
    private boolean isEndEnemyLane3 = false;

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

    //Our car
    private ImageView carLeft;
    private ImageView carRight;

    //Enemies
    private ImageView enemyLeftLane1Id0;
    private ImageView enemyRightLane1Id0;
    private ImageView enemyLeftLane2Id0;
    private ImageView enemyRightLane2Id0;
    private ImageView enemyLeftLane3Id0;
    private ImageView enemyRightLane3Id0;

    private TextView levelCounterLeft;
    private TextView levelCounterRight;
    private TextView t1; //REMOVE THIS

    private int leftCarPosition;
    private int rightCarPosition;
    private int absolutePosition = 2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carLeft = (ImageView) findViewById(R.id.imageViewMyCarLeft);
        carRight = (ImageView) findViewById(R.id.imageViewMyCarRight);

        enemyLeftLane1Id0 = (ImageView) findViewById(R.id.imageViewEnemyLeftLane1Id0);
        enemyRightLane1Id0 = (ImageView) findViewById(R.id.imageViewEnemyRightLane1Id0);

        enemyLeftLane2Id0 = (ImageView) findViewById(R.id.imageViewEnemyLeftLane2Id0);
        enemyRightLane2Id0 = (ImageView) findViewById(R.id.imageViewEnemyRightLane2Id0);

        enemyLeftLane3Id0 = (ImageView) findViewById(R.id.imageViewEnemyLeftLane3Id0);
        enemyRightLane3Id0 = (ImageView) findViewById(R.id.imageViewEnemyRightLane3Id0);

        levelCounterLeft= (TextView) findViewById(R.id.textViewLevelLeft);
        levelCounterRight= (TextView) findViewById(R.id.textViewLevelRight);
        levelCounterLeft.setText("1");
        levelCounterRight.setText("1");
        textLevel= (TextView) findViewById(R.id.textLevel);

        //Left eye panorama
        panoramaLeftSideLeftId0= (ImageView) findViewById(R.id.imageViewLeftSideLeftId0);
        panoramaLeftSideLeftId1= (ImageView) findViewById(R.id.imageViewLeftSideLeftId1);
        panoramaLeftSideRightId0= (ImageView) findViewById(R.id.imageViewLeftSideRightId0);
        panoramaLeftSideRightId1= (ImageView) findViewById(R.id.imageViewLeftSideRightId1);


        //Right eye panorama
        panoramaRightSideLeftId0= (ImageView) findViewById(R.id.imageViewRightSideLeftId0);
        panoramaRightSideLeftId1= (ImageView) findViewById(R.id.imageViewRightSideLeftId1);
        panoramaRightSideRightId0= (ImageView) findViewById(R.id.imageViewRightSideRightId0);
        panoramaRightSideRightId1= (ImageView) findViewById(R.id.imageViewRightSideRightId1);

        t1 = (TextView) findViewById(R.id.textViewProva);

        panoramaAnimation= new AnimationPanorama();

        panoramaAnimation.animatePanoramaRightView(panoramaLeftSideRightId1, panoramaRightSideRightId1);
        panoramaAnimation.hideImage(panoramaLeftSideLeftId0);
        panoramaAnimation.hideImage(panoramaLeftSideLeftId1);
        panoramaAnimation.hideImage(panoramaLeftSideRightId0);
        panoramaAnimation.hideImage(panoramaLeftSideRightId1);
        panoramaAnimation.hideImage(panoramaRightSideLeftId0);
        panoramaAnimation.hideImage(panoramaRightSideLeftId1);
        panoramaAnimation.hideImage(panoramaRightSideRightId0);
        panoramaAnimation.hideImage(panoramaRightSideRightId1);

        animationEnemies=new AnimationEnemies();
        animationEnemies.hideImage(enemyLeftLane1Id0);
        animationEnemies.hideImage(enemyLeftLane2Id0);
        animationEnemies.hideImage(enemyLeftLane3Id0);
        animationEnemies.hideImage(enemyRightLane1Id0);
        animationEnemies.hideImage(enemyRightLane2Id0);
        animationEnemies.hideImage(enemyRightLane3Id0);

        //g=new GameThread(this,enemyLeftLane1Id0,enemyLeftLane2Id0,enemyLeftLane3Id0);

        //g.gioca();
        //getCollision(animationEnemies);

        gameManager.generateGameData();
        panoramaManager.randomPanorama();

        Thread thread = new Thread(new Runnable() {
            boolean state;

            @Override
            public void run() {
                try {
                    while(true){
                        if(state){
                            // Se è vero fai questo
                        }else{
                            // Se non è vero fai altro
                        }
                        state = !state;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                textLevel.setText("LEVEL "+gameManager.getIdLevel());
                                pick=gameManager.getIdEnemy().get(i).getSelectedLane();
                                size=gameManager.getIdEnemy().size();
                                panoramaSide=panoramaManager.getSelectedSide();
                                idPanorama=panoramaManager.getIdSubject();

                                if(panoramaSide.equals(Side.LEFT) && idPanorama==0){
                                    panoramaAnimation.showImage(panoramaLeftSideLeftId0);
                                    panoramaAnimation.showImage(panoramaRightSideLeftId0);
                                   // t1.setText("left 0");
                                    panoramaAnimation.animatePanoramaLeftView(panoramaLeftSideLeftId0, panoramaRightSideLeftId0);
                                }

                                if(panoramaSide.equals(Side.LEFT) && idPanorama==1){
                                    panoramaAnimation.showImage(panoramaLeftSideLeftId1);
                                    panoramaAnimation.showImage(panoramaRightSideLeftId1);
                                    //t1.setText("left 1");
                                    panoramaAnimation.animatePanoramaLeftView(panoramaLeftSideLeftId1, panoramaRightSideLeftId1);
                                }

                                if(panoramaSide.equals(Side.RIGHT) && idPanorama==0){
                                    panoramaAnimation.showImage(panoramaLeftSideRightId0);
                                    panoramaAnimation.showImage(panoramaRightSideRightId0);
                                    //t1.setText("right 0");
                                    panoramaAnimation.animatePanoramaLeftView(panoramaLeftSideRightId0, panoramaRightSideRightId0);
                                }
                                if(panoramaSide.equals(Side.RIGHT) && idPanorama==1){
                                    panoramaAnimation.showImage(panoramaLeftSideRightId1);
                                    panoramaAnimation.showImage(panoramaRightSideRightId1);
                                    //t1.setText("right 1");
                                    panoramaAnimation.animatePanoramaLeftView(panoramaLeftSideRightId1, panoramaRightSideRightId1);
                                }

                                if(pick==1) {
                                    animationEnemies.showImage(enemyLeftLane1Id0);
                                    animationEnemies.showImage(enemyRightLane1Id0);
                                    animationEnemies.animateFrontCarLane1(enemyLeftLane1Id0, enemyRightLane1Id0);
                                   // t1.setText("lane 1");
                                }
                                if(pick==2){
                                    animationEnemies.showImage(enemyLeftLane2Id0);
                                    animationEnemies.showImage(enemyRightLane2Id0);
                                    animationEnemies.animateFrontCarLane2(enemyLeftLane2Id0,enemyRightLane2Id0);
                                    //t1.setText("lane 2");
                                }
                                if(pick==3){
                                    animationEnemies.showImage(enemyLeftLane3Id0);
                                    animationEnemies.showImage(enemyRightLane3Id0);
                                    animationEnemies.animateFrontCarLane3(enemyLeftLane3Id0,enemyRightLane3Id0);
                                    //t1.setText("lane 3");
                                }

                            }
                        });

                        Thread.sleep(gameManager.getInterval());
                        i++;
                        if(i==size) {

                            gameManager.generateGameData();
                            i=0;}
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();

    }



    private void getCollision(AnimationEnemies animationEnemies) {

        animationEnemies.animateFrontCarLane1(enemyLeftLane1Id0, enemyRightLane1Id0)
                .setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        isEndEnemyLane1 = true;
                        detectCollision();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
        animationEnemies.animateFrontCarLane2(enemyLeftLane2Id0, enemyRightLane2Id0)
                .setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        isEndEnemyLane2 = true;
                        detectCollision();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
        animationEnemies.animateFrontCarLane3(enemyLeftLane3Id0, enemyRightLane3Id0)
                .setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        isEndEnemyLane3 = true;
                        detectCollision();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * we want to kill the activity when it go on pause
     */
    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                //on key + press
                if (action == KeyEvent.ACTION_DOWN) {
                    volumeUp();
                }
                return true;
            case KeyEvent.KEYCODE_MEDIA_NEXT:
                //on key + press
                if (action == KeyEvent.ACTION_DOWN) {
                    volumeUp();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    //on key - press
                    volumeDown();
                }
                return true;
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                if (action == KeyEvent.ACTION_DOWN) {
                    //on key - press
                    volumeDown();
                }
                return true;
            case KeyEvent.KEYCODE_HEADSETHOOK:
                if (action == KeyEvent.ACTION_DOWN) {
                    //on key home press
                    playAgain();
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }



    /**
     * handle the key + press event
     */
    private void volumeUp() {

        if (absolutePosition > 1) {
            absolutePosition--;
            leftCarPosition -= 230;
            rightCarPosition -= 230;
            carLeft.setTranslationX(leftCarPosition);
            carRight.setTranslationX(rightCarPosition);
        } else {
            absolutePosition = 1;
        }
        detectCollision();
    }


    /**
     * handle the key - press event
     */
    private void volumeDown() {

        if (absolutePosition < 3) {
            absolutePosition++;
            leftCarPosition += 230;
            rightCarPosition += 230;
            carLeft.setTranslationX(leftCarPosition);
            carRight.setTranslationX(rightCarPosition);
        } else {
            absolutePosition = 3;
        }
        detectCollision();

    }

    private void detectCollision() {

        //TODO far sparire macchine in caso di no collision e animazione in caso di scontro

        if(!(absolutePosition==1 && isEndEnemyLane1) &&
                !(absolutePosition==2 && isEndEnemyLane2) &&
                !(absolutePosition==3 && isEndEnemyLane3)){
            t1.setText("no collision");

        }

        if(absolutePosition==1 && isEndEnemyLane1){
            t1.setText("scontro su 1");
            //TODO Explosion animation on Line 1
        }

        if(absolutePosition==2 && isEndEnemyLane2){
            t1.setText("scontro su 2");
            //TODO Explosion animation on Line 2
        }

        if(absolutePosition==3 && isEndEnemyLane3){
            t1.setText("scontro su 3");
            //TODO Explosion animation on Line 3
        }
    }

    /**
     * handle the home button pression
     */
    private void playAgain() {

        Intent restart = new Intent(MainActivity.this, MainActivity.class);
        startActivity(restart);


    }

}


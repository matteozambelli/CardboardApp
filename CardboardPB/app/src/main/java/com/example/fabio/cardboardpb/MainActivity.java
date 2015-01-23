package com.example.fabio.cardboardpb;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.fabio.cardboardpb.Exception.NoLaneException;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private GameManager gameManager;
    private boolean isEndEnemyLane1 = false;
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

    private ImageView carLeft;
    private ImageView carRight;
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

        gameManager= new GameManager();

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



        //Left eye panorama
        panoramaLeftSideLeftId0= (ImageView) findViewById(R.id.imageViewLeftSideLeftId0);

    //   panoramaLeftSideLeftId1= (ImageView) findViewById(R.id.imageViewLeftSideLeftId1);

      //  panoramaLeftSideRightId0= (ImageView) findViewById(R.id.imageViewLeftSideRightId0);

        panoramaLeftSideRightId1= (ImageView) findViewById(R.id.imageViewLeftSideRightId1);


        //Right eye panorama
        panoramaRightSideLeftId0= (ImageView) findViewById(R.id.imageViewRightSideLeftId0);

       //panoramaRightSideLeftId1= (ImageView) findViewById(R.id.imageViewRightSideLeftId1);
       // panoramaRightSideRightId0= (ImageView) findViewById(R.id.imageViewRightSideRightId0);

        panoramaRightSideRightId1= (ImageView) findViewById(R.id.imageViewRightSideRightId1);



        t1 = (TextView) findViewById(R.id.textViewProva);


        // MOVE THIS
        /*hideEnemy(enemyLeftLane1Id0);
        hideEnemy(enemyLeftLane2Id0);
        hideEnemy(enemyLeftLane3Id0);
        hideEnemy(enemyRightLane1Id0);
        hideEnemy(enemyRightLane2Id0);
        hideEnemy(enemyRightLane3Id0);*/


        MyAnimationPanorama panoramaAnimation= new MyAnimationPanorama();

        panoramaAnimation.animatePanoramaLeftView(panoramaLeftSideLeftId0, panoramaRightSideLeftId0);
        panoramaAnimation.animatePanoramaRightView(panoramaLeftSideRightId1, panoramaRightSideRightId1);

        animateFrontCarLane1(enemyLeftLane1Id0, enemyRightLane1Id0);
        animateFrontCarLane2(enemyLeftLane2Id0, enemyRightLane2Id0);
        animateFrontCarLane3(enemyLeftLane3Id0,enemyRightLane3Id0);







        gameManager.generateGameData();
        /*int pick;
       // for(int i=0;i<temp.size();i++){
           pick=gameManager.getIdEnemy().remove(0).getSelectedLane();

            if(pick==1){
                showEnemy(enemyLeftLane1Id0);

                animateFrontCarLane1(enemyLeftLane1Id0, enemyRightLane1Id0);
            }
            else if(pick==2){
                showEnemy(enemyLeftLane2Id0);
                animateFrontCarLane2(enemyLeftLane2Id0, enemyRightLane2Id0);
            }else if(pick==3){
                showEnemy(enemyLeftLane3Id0);
                animateFrontCarLane3(enemyLeftLane3Id0, enemyRightLane3Id0);
            }else{
                    //TODO catturare eccezione
            }


        /*try {
            wait(gameManager.getIntervall());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pick=gameManager.getIdEnemy().remove(1).getSelectedLane();

        if(pick==1){
            showEnemy(enemyLeftLane1Id0);

            animateFrontCarLane1(enemyLeftLane1Id0, enemyRightLane1Id0);
        }
        else if(pick==2){
            showEnemy(enemyLeftLane2Id0);
            animateFrontCarLane2(enemyLeftLane2Id0, enemyRightLane2Id0);
        }else if(pick==3){
            showEnemy(enemyLeftLane3Id0);
            animateFrontCarLane3(enemyLeftLane3Id0, enemyRightLane3Id0);
        }else{
            //TODO catturare eccezzione
        }
*/

        // }







    }

    private void animateFrontCarLane1(final ImageView ivLeft, final ImageView ivRight) {
        TranslateAnimation TranslateAnimation1 = new TranslateAnimation(0, Animation.RELATIVE_TO_SELF-38, 0, Animation.RELATIVE_TO_SELF + 38);
        ScaleAnimation ScaleAnimation1 = new ScaleAnimation(1, 3f,
                1, 3f,
                Animation.RELATIVE_TO_SELF, 0.9f,
                Animation.RELATIVE_TO_SELF, 0.5f);


        //Create AnimationSet Lane 1
        AnimationSet animationSetLane1 = new AnimationSet(false);
        animationSetLane1.addAnimation(TranslateAnimation1);
        animationSetLane1.addAnimation(ScaleAnimation1);
        animationSetLane1.setDuration(3000);
        animationSetLane1.setFillAfter(true);
        animationSetLane1.setStartOffset(5000);

        ivLeft.startAnimation(animationSetLane1);
        ivRight.startAnimation(animationSetLane1);

        animationSetLane1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
               /* showEnemy(ivLeft);
                showEnemy(ivRight);*/
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
    }

    private void animateFrontCarLane2(final ImageView ivLeft, final ImageView ivRight) {

        TranslateAnimation TranslateAnimation2 = new TranslateAnimation(0, 0, 0, Animation.RELATIVE_TO_SELF + 30);
        ScaleAnimation ScaleAnimation2 = new ScaleAnimation(1, 3.5f,
                1, 3.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        //Create AnimationSet Lane 2
        AnimationSet animationSetLane2 = new AnimationSet(false);
        animationSetLane2.addAnimation(TranslateAnimation2);
        animationSetLane2.addAnimation(ScaleAnimation2);
        animationSetLane2.setDuration(3000);
        animationSetLane2.setFillAfter(true);

        ivLeft.startAnimation(animationSetLane2);
        ivRight.startAnimation(animationSetLane2);

        animationSetLane2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                     /*showEnemy(ivLeft);
                     showEnemy(ivRight);*/
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
    }

    private void animateFrontCarLane3(final ImageView ivLeft, final ImageView ivRight) {
        TranslateAnimation TranslateAnimation3 = new TranslateAnimation(0, Animation.RELATIVE_TO_SELF+38, 0, Animation.RELATIVE_TO_SELF + 38);
        ScaleAnimation ScaleAnimation3 = new ScaleAnimation(1, 3f,
                1, 3f,
                Animation.RELATIVE_TO_SELF, 0.1f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        //Create AnimationSet Lane 3
        AnimationSet animationSetLane3 = new AnimationSet(false);
        animationSetLane3.addAnimation(TranslateAnimation3);
        animationSetLane3.addAnimation(ScaleAnimation3);
        animationSetLane3.setDuration(3000);
        animationSetLane3.setFillAfter(true);
        animationSetLane3.setStartOffset(2000);

        ivLeft.startAnimation(animationSetLane3);
        ivRight.startAnimation(animationSetLane3);

        animationSetLane3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
               /* showEnemy(ivLeft);
                showEnemy(ivRight);*/
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

        absolutePosition--;
        if (absolutePosition >= 1) {
            leftCarPosition -= 230;
            rightCarPosition -= 230;
            carLeft.setTranslationX(leftCarPosition);
            carRight.setTranslationX(rightCarPosition);
        } else {
            absolutePosition = 1;
        }
        /*
        new AlertDialog.Builder(this)
                .setTitle("test mode")
                .setMessage("key + pressed").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
            }
        }).show();
*/
    }


    //this method will be modify soon

    /**
     * handle the key - press event
     */
    private void volumeDown() {

        absolutePosition++;
        if (absolutePosition <= 3) {
            leftCarPosition += 230;
            rightCarPosition += 230;
            carLeft.setTranslationX(leftCarPosition);
            carRight.setTranslationX(rightCarPosition);
        } else {
            absolutePosition = 3;
        }
       /*
        new AlertDialog.Builder(this)
                .setTitle("test mode")
                .setMessage("key - pressed").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
          }
}).show();
*/
    }

    /**
     * handle the home button pression
     */
    private void playAgain() {

        Intent restart = new Intent(MainActivity.this, MainActivity.class);
        startActivity(restart);

   /*new AlertDialog.Builder(this)
            .setTitle("test mode")
            .setMessage("PAUSE").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            // continue with delete
        }
    }).show();
*/
    }

    private void detectCollision() {

        //Detect collision on Line 1
        if (isEndEnemyLane1){
            enemyLeftLane1Id0.setAlpha(0);
            enemyRightLane1Id0.setAlpha(0);
            if(absolutePosition==1){
                t1.setText("scontro su 1");
                //TODO Explosion animation on Line 1
            }
        }

        //Detect collision on Line 2
        if (isEndEnemyLane2){
            enemyLeftLane2Id0.setAlpha(0);
            enemyRightLane2Id0.setAlpha(0);
            if(absolutePosition==2){
                t1.setText("scontro su 2");
                //TODO Explosion animation on Line 2
            }
        }

        //Detect collision on Line 3
        if (isEndEnemyLane3){
            enemyLeftLane3Id0.setAlpha(0);
            enemyRightLane3Id0.setAlpha(0);
            if(absolutePosition==3){
                t1.setText("scontro su 3");
                //TODO Explosion animation on Line 3
            }
        }
    }

    private void hideEnemy(ImageView img){
        img.setAlpha(0f);
    }

    private void showEnemy(ImageView img){
        img.setAlpha(255f);
    }
}


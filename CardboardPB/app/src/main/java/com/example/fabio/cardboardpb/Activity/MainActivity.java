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


import com.example.fabio.cardboardpb.Manager.GameThread;
import com.example.fabio.cardboardpb.R;


public class MainActivity extends Activity {

   // private GameManager gameManager;
    private GameThread g;
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

    private AnimationEnemies animationEnemies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gameManager= new GameManager();

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

        //panoramaLeftSideLeftId1= (ImageView) findViewById(R.id.imageViewLeftSideLeftId1);

        //panoramaLeftSideRightId0= (ImageView) findViewById(R.id.imageViewLeftSideRightId0);

        panoramaLeftSideRightId1= (ImageView) findViewById(R.id.imageViewLeftSideRightId1);


        //Right eye panorama
        panoramaRightSideLeftId0= (ImageView) findViewById(R.id.imageViewRightSideLeftId0);

        //panoramaRightSideLeftId1= (ImageView) findViewById(R.id.imageViewRightSideLeftId1);
        // panoramaRightSideRightId0= (ImageView) findViewById(R.id.imageViewRightSideRightId0);

        panoramaRightSideRightId1= (ImageView) findViewById(R.id.imageViewRightSideRightId1);

        t1 = (TextView) findViewById(R.id.textViewProva);

        
        // MOVE THIS
        hideEnemy(enemyLeftLane1Id0);
        hideEnemy(enemyLeftLane2Id0);
        hideEnemy(enemyLeftLane3Id0);
        hideEnemy(enemyRightLane1Id0);
        hideEnemy(enemyRightLane2Id0);
        hideEnemy(enemyRightLane3Id0);

        g=new GameThread(enemyLeftLane1Id0,enemyLeftLane2Id0,enemyLeftLane3Id0);



        AnimationPanorama panoramaAnimation= new AnimationPanorama();

        panoramaAnimation.animatePanoramaLeftView(panoramaLeftSideLeftId0, panoramaRightSideLeftId0);
        panoramaAnimation.animatePanoramaRightView(panoramaLeftSideRightId1, panoramaRightSideRightId1);

        animationEnemies=new AnimationEnemies();

        //set the animation listener

        g.gioca();
        //getCollision(animationEnemies);


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

   /*new AlertDialog.Builder(this)
            .setTitle("test mode")
            .setMessage("PAUSE").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            // continue with delete
        }
    }).show();
*/
    }



    private void hideEnemy(ImageView img){
        img.setAlpha(0f);
    }

    private void showEnemy(ImageView img){
        img.setAlpha(255f);
    }
}


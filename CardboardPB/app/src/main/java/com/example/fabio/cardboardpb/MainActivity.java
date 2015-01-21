package com.example.fabio.cardboardpb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {
    private ImageView ivCarFrontLeft;
    private ImageView ivCarFrontRight;
    private TextView t1;
    private TextView t2;

    private ImageView carLeft;
    private ImageView carRight;
    private int leftCarPosition;
    private int rightCarPosition;
    private int absolutePosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivCarFrontLeft= (ImageView) findViewById(R.id.imageViewCarFrontLeft);
        ivCarFrontRight= (ImageView) findViewById(R.id.imageViewCarFrontRight);

        animateFrontCar( ivCarFrontLeft, ivCarFrontRight );

        carLeft=(ImageView) findViewById(R.id.imageViewMyCarLeft);
        carRight=(ImageView) findViewById(R.id.imageViewMyCarRight);
        leftCarPosition= carLeft.getWidth();
        rightCarPosition= carRight.getWidth();
    }



private void animateFrontCar(ImageView ivLeft, ImageView ivRight) {
    TranslateAnimation TranslateAnimation = new TranslateAnimation( 0, 0 , 0, Animation.RELATIVE_TO_SELF+30);
    ScaleAnimation ScaleAnimation= new ScaleAnimation(1,3.5f,
            1,3.5f,
            Animation.RELATIVE_TO_SELF,0.5f,
            Animation.RELATIVE_TO_SELF,0.5f
    );

    //Create AnimationSet
    AnimationSet animationSet= new AnimationSet(false);
    animationSet.addAnimation(TranslateAnimation);
    animationSet.addAnimation(ScaleAnimation);
    animationSet.setDuration(3000);
    animationSet.setFillAfter(true);

    ivLeft.startAnimation(animationSet);
    ivRight.startAnimation(animationSet);

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
                if(action== KeyEvent.ACTION_DOWN){
                //on key home press
                home();
            }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }


    /**
     * handle the key + press event
     */
    private void volumeUp(){

        absolutePosition++;
        if(absolutePosition<2) {
            leftCarPosition -= 230;
            rightCarPosition -= 230;
            carLeft.setTranslationX(leftCarPosition);
            carRight.setTranslationX(rightCarPosition);
        }else{
            absolutePosition=1;
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
    private void volumeDown(){

        absolutePosition--;
        if(absolutePosition>-2) {
            leftCarPosition += 230;
            rightCarPosition += 230;
            carLeft.setTranslationX(leftCarPosition);
            carRight.setTranslationX(rightCarPosition);
        }else{
            absolutePosition=-1;
        }
       /*
        new AlertDialog.Builder(this)
                .setTitle("test mode")
                .setMessage("key - pressed").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
          }
}).show();
*/        }

/**
 * handle the home button pression
 */
private void home(){

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

}

package com.example.fabio.cardboardpb.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabio.cardboardpb.Manager.Enum.Language;
import com.example.fabio.cardboardpb.R;

/**
 * Created by fabio on 20/01/2015.
 */
public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private ImageView ivLeft;
    private ImageView ivRight;
    private String id_user;

    private TextView t;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent=getIntent();
        Bundle data = getIntent().getExtras();
        id_user=(String) intent.getSerializableExtra("id_user");

        //t=(TextView) findViewById(R.id.textView);
        //t.setText(id_user);
        ivLeft = (ImageView) findViewById(R.id.imageView1);
        ivRight = (ImageView) findViewById(R.id.imageView2);

        //Create Animation
        Animation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        Animation animation2= new ScaleAnimation(1,0,
                1,0,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f
                );

        //Create AnimationSet
        AnimationSet animationSet= new AnimationSet(false);
        animationSet.addAnimation(animation);
        animationSet.addAnimation(animation2);
        animationSet.setDuration(3000);
        animationSet.setFillAfter(true);

        ivLeft.startAnimation(animationSet);
        ivRight.startAnimation(animationSet);

        boolean b = new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, LanguageActivity.class);
                i.putExtra("id_user", id_user);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }
}



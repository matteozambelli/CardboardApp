package com.example.fabio.cardboardpb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.widget.ImageView;

/**
 * Created by fabio on 20/01/2015.
 */
public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private ImageView iv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                iv=findViewById(R.id.imageView1);
               // iv.setAnimation()



                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}



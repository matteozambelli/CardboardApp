package com.example.fabio.cardboardpb;

import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by matteo on 23/01/2015.
 */
public class MyAnimation {

    //start panorama animation
    public void animatePanoramaLeftLeftView(ImageView ivLeft, ImageView ivRight) {
        TranslateAnimation TranslateAnimation = new TranslateAnimation(0, 0, 0, android.view.animation.Animation.RELATIVE_TO_SELF + 30);
        ScaleAnimation ScaleAnimation = new ScaleAnimation(1, 3.5f,
                1, 3.5f,
                android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f,
                android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f
        );
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(TranslateAnimation);
        animationSet.addAnimation(ScaleAnimation);
        animationSet.setDuration(3000);
        animationSet.setFillAfter(true);

        ivLeft.startAnimation(animationSet);
        ivRight.startAnimation(animationSet);

        animationSet.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {

            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {

            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {

            }
        });
    }

    //end panorama animation
}

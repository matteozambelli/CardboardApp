package com.example.fabio.cardboardpb;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by matteo on 23/01/2015.
 */
public class MyAnimation {

    //start panorama animation
    public void animatePanoramaLeftView(final ImageView ivLeft, final ImageView ivRight) {
        TranslateAnimation TranslateAnimation = new TranslateAnimation(0, Animation.RELATIVE_TO_SELF-28, 0, android.view.animation.Animation.RELATIVE_TO_SELF + 30);
        ScaleAnimation ScaleAnimation = new ScaleAnimation(1, 3.5f,
                1, 3.5f,
                android.view.animation.Animation.RELATIVE_TO_SELF, 1f,
                android.view.animation.Animation.RELATIVE_TO_SELF, 1f
        );
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(TranslateAnimation);
        animationSet.addAnimation(ScaleAnimation);
        animationSet.setDuration(20000);
        animationSet.setFillAfter(true);

        ivLeft.startAnimation(animationSet);
        ivRight.startAnimation(animationSet);

        animationSet.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {

            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                hidePanorama(ivLeft);
                hidePanorama(ivRight);
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {

            }
        });
    }


    public void animatePanoramaRightView(final ImageView ivLeft, final ImageView ivRight) {
        TranslateAnimation TranslateAnimation = new TranslateAnimation(0, Animation.RELATIVE_TO_SELF-28, 0, android.view.animation.Animation.RELATIVE_TO_SELF + 30);
        ScaleAnimation ScaleAnimation = new ScaleAnimation(1, 3.5f,
                1, 3.5f,
                android.view.animation.Animation.RELATIVE_TO_SELF, -1f,
                android.view.animation.Animation.RELATIVE_TO_SELF, 1f
        );
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(TranslateAnimation);
        animationSet.addAnimation(ScaleAnimation);
        animationSet.setDuration(20000);
        animationSet.setFillAfter(true);

        ivLeft.startAnimation(animationSet);
        ivRight.startAnimation(animationSet);

        animationSet.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {

            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                hidePanorama(ivLeft);
                hidePanorama(ivRight);
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {

            }
        });
    }
    //end panorama animation


    public void hidePanorama(ImageView img){
        img.setAlpha(0f);
    }


}

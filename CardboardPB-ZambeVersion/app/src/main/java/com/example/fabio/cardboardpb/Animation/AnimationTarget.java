package com.example.fabio.cardboardpb.Animation;

import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by matteo on 03/02/2015.
 */
public class AnimationTarget {

    public AnimationSet animationTarget1=new AnimationSet(false);
    public AnimationSet animationTarget2=new AnimationSet(false);
    public AnimationSet animationTarget3=new AnimationSet(false);



    public void animateTarget1(final ImageView ivLeft, int displayWidth, int displayHeight) {
        TranslateAnimation TranslateAnimationTarget1 = new TranslateAnimation(0, ((int) (Animation.RELATIVE_TO_SELF - (displayWidth * 0.008))),
                0,((int) (Animation.RELATIVE_TO_SELF + (displayHeight * 0.02))));
        ScaleAnimation ScaleAnimationTarget1 = new ScaleAnimation(1, 5.2f,
                1, 5.2f,
                Animation.RELATIVE_TO_SELF, 0.9f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        animationTarget1.addAnimation(TranslateAnimationTarget1);
        animationTarget1.addAnimation(ScaleAnimationTarget1);
        animationTarget1.setDuration(2000);
        animationTarget1.setFillAfter(true);

        ivLeft.startAnimation(animationTarget1);


    }



    public void animateTarget2(final ImageView ivLeft, int displayWidth, int displayHeight) {

        TranslateAnimation TranslateAnimationTarget2 = new TranslateAnimation(0, 0,
                0, ((int) (Animation.RELATIVE_TO_SELF + (displayHeight * 0.05))));
        ScaleAnimation ScaleAnimationTarget2 = new ScaleAnimation(1, 3.5f,
                1, 3.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        //Create AnimationSet Lane 2
        animationTarget2.addAnimation(TranslateAnimationTarget2);
        animationTarget2.addAnimation(ScaleAnimationTarget2);
        animationTarget2.setDuration(2000);
        animationTarget2.setFillAfter(true);

        ivLeft.startAnimation(animationTarget2);


    }

    public void animateTarget3(final ImageView ivLeft, int displayWidth, int displayHeight) {
        TranslateAnimation TranslateAnimationTarget3 = new TranslateAnimation(0, ((int) (Animation.RELATIVE_TO_SELF + (displayWidth * 0.008))),
                0,((int) (Animation.RELATIVE_TO_SELF + (displayHeight * 0.02))));
        ScaleAnimation ScaleAnimationTarget3 = new ScaleAnimation(1, 5.2f,
                1, 5.2f,
                Animation.RELATIVE_TO_SELF, 0.1f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        //Create AnimationSet Lane 3
        animationTarget3.addAnimation(TranslateAnimationTarget3);
        animationTarget3.addAnimation(ScaleAnimationTarget3);
        animationTarget3.setDuration(2000);
        animationTarget3.setFillAfter(true);


        ivLeft.startAnimation(animationTarget3);

    }
}

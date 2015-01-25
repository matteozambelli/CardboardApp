package com.example.fabio.cardboardpb.Animation;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by fabio on 24/01/2015.
 */
public class AnimationEnemies extends Animation{

    public AnimationSet animateFrontCarLane1(final ImageView ivLeft, final ImageView ivRight) {
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

        return animationSetLane1;
    }



    public AnimationSet animateFrontCarLane2(final ImageView ivLeft, final ImageView ivRight) {

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
        return animationSetLane2;
    }

    public AnimationSet animateFrontCarLane3(final ImageView ivLeft, final ImageView ivRight) {
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

        return animationSetLane3;
    }
}

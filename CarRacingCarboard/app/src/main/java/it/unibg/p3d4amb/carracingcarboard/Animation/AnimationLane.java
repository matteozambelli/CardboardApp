package it.unibg.p3d4amb.carracingcarboard.Animation;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by fabio on 24/01/2015.
 */
public class AnimationLane extends Animation {

    public AnimationSet animationSetLane1 = new AnimationSet(false);
    public AnimationSet animationSetLane3 = new AnimationSet(false);


    //Create AnimationSet Lane 1


    public void animateLane1(final ImageView ivLeft, final ImageView ivRight, int displayWidth, int displayHeight) {

        TranslateAnimation TranslateAnimation1 = new TranslateAnimation(0,
                ((int) (Animation.RELATIVE_TO_SELF - (displayWidth * 0.14))),
                0, ((int) (Animation.RELATIVE_TO_SELF + (displayHeight * 0.38))));
        ScaleAnimation ScaleAnimation1 = new ScaleAnimation(1, 1f,
                1, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animationSetLane1.addAnimation(TranslateAnimation1);
        animationSetLane1.addAnimation(ScaleAnimation1);
        animationSetLane1.setDuration(5900);
        animationSetLane1.setFillAfter(true);

        ivLeft.startAnimation(animationSetLane1);
        ivRight.startAnimation(animationSetLane1);

    }

    public void animateLane3(final ImageView ivLeft, final ImageView ivRight, int displayWidth, int displayHeight) {
        TranslateAnimation TranslateAnimation3 = new TranslateAnimation(0,
                ((int) (Animation.RELATIVE_TO_SELF + (displayWidth * 0.14))),
                0, ((int) (Animation.RELATIVE_TO_SELF + (displayHeight * 0.38))));
        ScaleAnimation ScaleAnimation3 = new ScaleAnimation(1, 1f,
                1, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        //Create AnimationSet Lane 3
        animationSetLane3.addAnimation(TranslateAnimation3);
        animationSetLane3.addAnimation(ScaleAnimation3);
        animationSetLane3.setDuration(5900);
        animationSetLane3.setFillAfter(true);


        ivLeft.startAnimation(animationSetLane3);
        ivRight.startAnimation(animationSetLane3);
    }


    public void hideImage(ImageView img) {
        img.setAlpha(0f);
    }

    public void showImage(ImageView img) {
        img.setAlpha(255f);
    }
}

package it.unibg.p3d4amb.carracingcarboard.Animation;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by matteo on 27/01/2015.
 */
public class AnimationPanorama implements AnimationPanoramaInterface{

    //start panorama animation
    public void animatePanoramaLeftView(final ImageView ivLeft, final ImageView ivRight, int displayWidth, int displayHeight) {
        TranslateAnimation TranslateAnimation = new TranslateAnimation(0, ((int) (Animation.RELATIVE_TO_SELF - (displayWidth * 0.06))),
                0,((int) (Animation.RELATIVE_TO_SELF + (displayHeight * 0.08))));
        ScaleAnimation ScaleAnimation = new ScaleAnimation(1, 5f,
                1, 5f,
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 1f
        );
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(TranslateAnimation);
        animationSet.addAnimation(ScaleAnimation);
        animationSet.setDuration(20000);
        animationSet.setFillAfter(true);

        ivLeft.startAnimation(animationSet);
        ivRight.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
             /* showImage(ivLeft);
                showImage(ivRight);*/
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hideImage(ivLeft);
                hideImage(ivRight);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }


        });
    }


    public void animatePanoramaRightView(final ImageView ivLeft, final ImageView ivRight, int displayWidth, int displayHeight) {
        TranslateAnimation TranslateAnimation = new TranslateAnimation(0, ((int) (Animation.RELATIVE_TO_SELF + (displayWidth * 0.005))),
                0,((int) (Animation.RELATIVE_TO_SELF + (displayHeight * 0.08))));
        ScaleAnimation ScaleAnimation = new ScaleAnimation(1, 5f,
                1, 5f,
                Animation.RELATIVE_TO_SELF, -1f,
                Animation.RELATIVE_TO_SELF, 1f
        );
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(TranslateAnimation);
        animationSet.addAnimation(ScaleAnimation);
        animationSet.setDuration(20000);
        animationSet.setFillAfter(true);

        ivLeft.startAnimation(animationSet);
        ivRight.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                 /* showImage(ivLeft);
                showImage(ivRight);*/
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hideImage(ivLeft);
                hideImage(ivRight);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void animatePanoramaCloud(final ImageView ivLeft, final ImageView ivRight, int displayWidth, int displayHeight) {
        TranslateAnimation TranslateAnimation = new TranslateAnimation(0,((int) (Animation.RELATIVE_TO_SELF + (displayWidth * 0.4))),
                0,0);
        ScaleAnimation ScaleAnimation = new ScaleAnimation(1, 1,
                1, 1,
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 1f
        );
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(TranslateAnimation);
        animationSet.addAnimation(ScaleAnimation);
        animationSet.setDuration(20000);
        animationSet.setFillAfter(true);

        ivLeft.startAnimation(animationSet);
        ivRight.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
             /* showImage(ivLeft);
                showImage(ivRight);*/
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hideImage(ivLeft);
                hideImage(ivRight);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }


        });
    }

    public void animatePanoramaSun(final ImageView ivLeft, final ImageView ivRight, int displayWidth, int displayHeight) {
        TranslateAnimation TranslateAnimation = new TranslateAnimation(0,((int) (Animation.RELATIVE_TO_SELF + (displayWidth * 0.4))),
                0,((int) (Animation.RELATIVE_TO_SELF - (displayWidth * 0.1))));
        ScaleAnimation ScaleAnimation = new ScaleAnimation(1, 1,
                1, 1,
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 1f
        );
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(TranslateAnimation);
        animationSet.addAnimation(ScaleAnimation);
        animationSet.setDuration(100000);
        animationSet.setFillAfter(true);

        ivLeft.startAnimation(animationSet);
        ivRight.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
             /* showImage(ivLeft);
                showImage(ivRight);*/
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hideImage(ivLeft);
                hideImage(ivRight);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }


        });
    }
    //end panorama animation

    public void hideImage(ImageView img){
        img.setAlpha(0f);
    }

    public void showImage(ImageView img){
        img.setAlpha(255f);
    }
}

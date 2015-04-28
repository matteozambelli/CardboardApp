package it.unibg.p3d4amb.carracingcarboard.Animation;

import android.widget.ImageView;

/**
 * Created by matteo on 27/01/2015.
 */
public interface AnimationEnemiesInterface {

    public void animateFrontCarLane1(final ImageView ivLeft, final ImageView ivRight, int displayWidth, int displayHeight) ;


    public void animateFrontCarLane2(final ImageView ivLeft, final ImageView ivRight, int displayWidth, int displayHeight);

    public void animateFrontCarLane3(final ImageView ivLeft, final ImageView ivRight, int displayWidth, int displayHeight);

    public void hideImage(ImageView img);

    public void showImage(ImageView img);
}

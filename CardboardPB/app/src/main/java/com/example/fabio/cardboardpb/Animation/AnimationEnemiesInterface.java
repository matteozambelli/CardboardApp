package com.example.fabio.cardboardpb.Animation;

import android.view.animation.AnimationSet;
import android.widget.ImageView;

/**
 * Created by matteo on 27/01/2015.
 */
public interface AnimationEnemiesInterface {

    public void animateFrontCarLane1(final ImageView ivLeft, final ImageView ivRight) ;


    public void animateFrontCarLane2(final ImageView ivLeft, final ImageView ivRight);

    public void animateFrontCarLane3(final ImageView ivLeft, final ImageView ivRight);

    public void hideImage(ImageView img);

    public void showImage(ImageView img);
}

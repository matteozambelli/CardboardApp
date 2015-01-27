package com.example.fabio.cardboardpb.Animation;

import android.view.animation.AnimationSet;
import android.widget.ImageView;

/**
 * Created by matteo on 27/01/2015.
 */
public interface AnimationEnemiesInterface {

    public AnimationSet animateFrontCarLane1(final ImageView ivLeft, final ImageView ivRight) ;


    public AnimationSet animateFrontCarLane2(final ImageView ivLeft, final ImageView ivRight);

    public AnimationSet animateFrontCarLane3(final ImageView ivLeft, final ImageView ivRight);

    public void hideImage(ImageView img);

    public void showImage(ImageView img);
}

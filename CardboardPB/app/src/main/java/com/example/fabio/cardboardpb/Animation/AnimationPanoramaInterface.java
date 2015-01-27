package com.example.fabio.cardboardpb.Animation;

import android.widget.ImageView;

/**
 * Created by matteo on 27/01/2015.
 */
public interface AnimationPanoramaInterface {

    public void animatePanoramaLeftView(final ImageView ivLeft, final ImageView ivRight);


    public void animatePanoramaRightView(final ImageView ivLeft, final ImageView ivRight);

    public void hideImage(ImageView img);

    public void showImage(ImageView img);
}

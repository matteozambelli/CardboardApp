package com.example.fabio.cardboardpb;

import android.graphics.Bitmap;

/**
 * Created by matteo on 20/01/2015.
 */
public class GameThread extends Thread{

    private Bitmap [] bubbleVector;

    public GameThread(){
        bubbleVector= new Bitmap[5];

    }
}

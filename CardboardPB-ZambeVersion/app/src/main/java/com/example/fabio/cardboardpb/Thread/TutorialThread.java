package com.example.fabio.cardboardpb.Thread;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.widget.TextView;

import com.example.fabio.cardboardpb.Activity.SplashActivity;
import com.example.fabio.cardboardpb.Activity.TutorialActivity;

/**
 * Created by matteo on 02/04/2015.
 */
public class TutorialThread extends  Thread {

    private TextView text;
    private Activity activity;


    public TutorialThread(Activity activity,TextView text){
        this.activity=activity;
        this.text=text;
    }


    @Override
    public void run() {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText("Pick up your phone and connect to the earphones (with volume control keys)");


            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                text.setText("+ key move to right"+'\n'+"- key move to left");

            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText("insert your phone in cardboard");


            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText("the game will start in a few seconds");

            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                text.setText("Enjoy");
            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}







package com.example.fabio.cardboardpb.Manager;

<<<<<<< HEAD

import android.app.Activity;
import android.os.CountDownTimer;
import android.util.Log;
=======
import android.os.CountDownTimer;
>>>>>>> origin/master
import android.widget.ImageView;
import android.widget.TextView;


import com.example.fabio.cardboardpb.Animation.AnimationEnemies;


import java.util.Timer;

/**
 * Created by matteo on 27/01/2015.
 */
public class GameThread {

    private GameManager gameManager;
    private AnimationEnemies animationEnemies;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    Activity activity;
    Thread thread;







    public GameThread(Activity a, ImageView i1, ImageView i2,ImageView i3) {
        gameManager = new GameManager();
        gameManager.generateGameData();
        animationEnemies = new AnimationEnemies();
        img1 = i1;
        img2 = i2;
        img3 = i3;
        activity = a;

    }


<<<<<<< HEAD
    int[] vett = {1, 2, 3};
    int i = 0;
=======
    int min=0;
    int sec=5;
    int []vett={1,2,3};
>>>>>>> origin/master

    int pick;


<<<<<<< HEAD
    public void gioca() {


        Thread thread = new Thread(new Runnable() {
            boolean state;

            @Override
            public void run() {
                try {
                    while(true){
                        if(state){
                            // Se è vero fai questo
                        }else{
                            // Se non è vero fai altro
                        }
                        state = !state;

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(vett[i]==0) {
                                    animationEnemies.showImage(img1);
                                    animationEnemies.animateFrontCarLane1(img1, img1);
                                }
                                    /*if (vett[i] == 1) {
                                        animationEnemies.showImage(img2);
                                        animationEnemies.animateFrontCarLane2(img2, img2);
                                    }
                                    if(vett[i]==2) {
                                        animationEnemies.showImage(img3);
                                        animationEnemies.animateFrontCarLane3(img3, img3);
                                    }*/
                                }

                        });

                        Thread.sleep(2000);
                        i++;
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
=======
            pick = vett[i];/*gameManager.getIdEnemy().get(i).getSelectedLane();*/

            if (pick == 1) {
                animationEnemies.showImage(img1);
                animationEnemies.animateFrontCarLane1(img1, img1);
            }
            if (pick == 2) {
                animationEnemies.showImage(img2);
                animationEnemies.animateFrontCarLane2(img2, img2);
            }
            if (pick == 3) {
                animationEnemies.showImage(img3);
                animationEnemies.animateFrontCarLane3(img3, img3);
            } else {
                //TODO catturare eccezione
            }


            Thread thread = new Thread(new Runnable() {
                boolean state;

                @Override
                public void run() {
                    try {
                        while(true){
                            if(state){
                                // Se è vero fai questo
                            }else{
                                // Se non è vero fai altro
                            }
                            state = !state;
                            Thread.sleep(20000);
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            thread.start();
>>>>>>> origin/master


    }


}
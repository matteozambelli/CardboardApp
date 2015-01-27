package com.example.fabio.cardboardpb.Manager;

import android.widget.ImageView;

import com.example.fabio.cardboardpb.Animation.AnimationEnemies;

import java.util.Timer;

/**
 * Created by matteo on 27/01/2015.
 */
public class GameThread{

    private GameManager gameManager;
    private AnimationEnemies animationEnemies;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;


    Timer t= new Timer();

    public GameThread(ImageView i1,ImageView i2, ImageView i3) {
        gameManager = new GameManager();
        gameManager.generateGameData();
        animationEnemies = new AnimationEnemies();
        img1=i1;
        img2=i2;
        img3=i3;
    }


    int []vett={1,2,3};

    public  void gioca()  {

        int pick;
        for (int i = 0; i < 3/*gameManager.getIdEnemy().size()*/; i++) {

             pick=vett[i];/*gameManager.getIdEnemy().get(i).getSelectedLane();*/

             if(pick==1){
                 animationEnemies.showImage(img1);
                 animationEnemies.animateFrontCarLane1(img1,img1 );
             }
             if(pick==2){
                 animationEnemies.showImage(img2);
                 animationEnemies.animateFrontCarLane2(img2,img2 );
             }if(pick==3){
                 animationEnemies.showImage(img3);
                 animationEnemies.animateFrontCarLane3(img3, img3);
             }else{
                 //TODO catturare eccezione
             }

                /* try {
                     Thread.sleep(gameManager.getInterval());
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }*/


        }
    }
}
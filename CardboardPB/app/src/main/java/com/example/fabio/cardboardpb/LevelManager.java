package com.example.fabio.cardboardpb;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by fabio on 22/01/2015.
 */



public class LevelManager{

    private int startIntervalTime=10000;
    private int startNumEnemies=3;
    private int minNumEnemies=2;
    private int minIntervalTime=4000;
    private int idLevel=1;
    private int numEnemies;
    private int timeInterval;

    //Variables to define the range to choose random values
    private int timeRange;
    private int enemiesRange;

    //int Range=Sup-Inf+1;
    //(int)(Range*Math.random())+Inf

    //Level generator, set the parameters of the level
    public void generateLevel(){
        timeRange=startIntervalTime-minIntervalTime+1;
        enemiesRange=startNumEnemies-minNumEnemies+1;
        timeInterval=(int)(timeRange*Math.random())+minIntervalTime;
        numEnemies=(int)(enemiesRange*Math.random())+minNumEnemies;
    }

    //Updating the parameters of the level just finished, for the next level
    public void onLevelEnd(){
        startIntervalTime-=200;
        startNumEnemies++;
        idLevel++;
    }

    /**
     *
     * @return idLevel
     */
    public int getIdLevel() {
        return idLevel;
    }

    /**
     *
     * @return timeInterval level
     */
    public int getTimeInterval() {
        return timeInterval;
    }

    /**
     *
     * @return total number of Enemies
     */
    public int getNumEnemies() {
        return numEnemies;
}

    /* FOR TEST ONLY
    public static void main(String [] args){
        LevelManager l=new LevelManager();
        l.generateLevel();
        System.out.println("Level: "+l.getIdLevel());
        System.out.println("Enemies: "+l.getNumNumEnemies());
        System.out.println("Time: "+l.getTimeInterval());
        l.onLevelEnd();
        l.generateLevel();
        System.out.println("Level: "+l.getIdLevel());
        System.out.println("Enemies: "+l.getNumNumEnemies());
        System.out.println("Time: "+l.getTimeInterval());
        l.onLevelEnd();
        l.generateLevel();
        System.out.println("Level: "+l.getIdLevel());
        System.out.println("Enemies: "+l.getNumNumEnemies());
        System.out.println("Time: "+l.getTimeInterval());
        l.onLevelEnd();
        l.generateLevel();
        System.out.println("Level: "+l.getIdLevel());
        System.out.println("Enemies: "+l.getNumNumEnemies());
        System.out.println("Time: "+l.getTimeInterval());
        l.onLevelEnd();
        l.generateLevel();
        System.out.println("Level: "+l.getIdLevel());
        System.out.println("Enemies: "+l.getNumNumEnemies());
        System.out.println("Time: "+l.getTimeInterval());
        l.onLevelEnd();
        l.generateLevel();
        System.out.println("Level: "+l.getIdLevel());
        System.out.println("Enemies: "+l.getNumNumEnemies());
        System.out.println("Time: "+l.getTimeInterval());

    } */
}


package com.example.fabio.cardboardpb;

/**
 * Created by matteo on 22/01/2015.
 */
public class GameManager {

    private EnemiesManager enemiesManager;
    private LevelManager levelManager;

    //game data
    private int lane;
    private int enemy;
    private int idLevel;
    private int numEnemies;
    private int interval;

    public void Game(){
        enemiesManager.randomFunction();
        levelManager.generateLevel();

        lane=enemiesManager.getSelectedLane();
        //enemy=
    }




}

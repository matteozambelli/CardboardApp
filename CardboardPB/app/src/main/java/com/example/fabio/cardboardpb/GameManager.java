package com.example.fabio.cardboardpb;

/**
 * Created by matteo on 22/01/2015.
 */
public class GameManager {

    private EnemiesManager enemiesManager;
    private LevelManager levelManager;

    //game data
    private int lane;
    private int idEnemy;
    private int idLevel;
    private int numEnemies;
    private int interval;

    /**
     * this function merge the random data from EnemiesManager and
     * LevelManager
     */
    public void GenerateGameData(){
        enemiesManager.randomFunction();
        levelManager.generateLevel();
        lane=enemiesManager.getSelectedLane();
        idEnemy=enemiesManager.getNumberOfCar();
        idLevel=levelManager.getIdLevel();
        numEnemies=levelManager.getNumNumEnemies();
        interval=levelManager.getTimeInterval();
        levelManager.onLevelEnd();
    }

    /**
     *
     * @return the selected lane
     */
     public int getLane() {
        return lane;
    }

    /**
     *
     * @return the selected enemy
     */
    public int getIdEnemy(){
        return idEnemy;
    }

    /**
     *
     * @return the level
     */
    public int getIdLevel(){
        return idLevel;
    }

    /**
     *
     * @return how many enemies for this level
     */
    public int getNumEnemies(){
        return numEnemies;
    }

    public int getInterval(){
        return interval;
    }
}

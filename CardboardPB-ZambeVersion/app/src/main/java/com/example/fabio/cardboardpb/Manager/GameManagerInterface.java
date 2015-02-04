package com.example.fabio.cardboardpb.Manager;

import java.util.ArrayList;

/**
 * Created by matteo on 27/01/2015.
 */
public interface GameManagerInterface {

    public void generateGameData();

    /**
     *
     * @return the selected enemy and the selected lane
     */
    public ArrayList<EnemiesManager> getIdEnemy();

    /**
     *
     * @return the level
     */
    public int getIdLevel();

    /**
     *
     * @return how many enemies for this level
     */
    public int getNumEnemies();

    /**
     *
     * @return the interval between two enemies car
     */
    public int getInterval();
}

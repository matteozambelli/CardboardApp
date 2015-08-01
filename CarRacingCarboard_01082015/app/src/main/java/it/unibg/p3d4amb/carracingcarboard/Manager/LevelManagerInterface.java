package it.unibg.p3d4amb.carracingcarboard.Manager;

/**
 * Created by matteo on 27/01/2015.
 */
public interface LevelManagerInterface {

    //Level generator, set the parameters of the level
    public void generateLevel();

    //Updating the parameters of the level just finished, for the next level
    public void onLevelEnd();

    public int getIdLevel();

    public int getTimeInterval();

    public int getNumEnemies();
}

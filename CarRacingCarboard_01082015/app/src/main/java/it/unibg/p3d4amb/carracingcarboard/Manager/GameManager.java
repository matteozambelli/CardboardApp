package it.unibg.p3d4amb.carracingcarboard.Manager;

import java.util.ArrayList;

/**
 * Created by matteo on 22/01/2015.
 */
public class GameManager implements GameManagerInterface{

    private LevelManager levelManager= new LevelManager();

    //game data
    private ArrayList<EnemiesManager> enemiesManager= new ArrayList<EnemiesManager>();
    private int idLevel;
    private int numEnemies;
    private int interval;

    /**
     * this function merge the random data from EnemiesManager and
     * LevelManager
     */
    public void generateGameData(){
        levelManager.generateLevel();
        idLevel=levelManager.getIdLevel();
        numEnemies=levelManager.getNumEnemies();
        interval=levelManager.getTimeInterval();

        for(int i=0;i<numEnemies;i++) {
            EnemiesManager temp= new EnemiesManager();
            temp.randomFunction();
            enemiesManager.add(temp);
        }
        //we need this for setup the next level
        levelManager.onLevelEnd();
    }

    public void newGame(){
        levelManager=new LevelManager();
        enemiesManager= new ArrayList<EnemiesManager>();
    }


    /**
     *
     * @return the selected enemy and the selected lane
     */
    public ArrayList<EnemiesManager> getIdEnemy(){
        return enemiesManager;
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

    /**
     *
     * @return the interval between two enemies car
     */
    public int getInterval(){
        return interval;
    }

 /* FOR TEST ONLY

    public void printIdEnemy(){
        EnemiesManager print= new EnemiesManager();
        for(int i=0;i<numEnemies;i++){
            System.out.println("corsia: "+enemiesManager.get(i).getSelectedLane());
            System.out.println("id nemico: "+enemiesManager.get(i).getNumberOfCar());
        }
    }


    public static void main(String [ ] args)
    {
        GameManager g= new GameManager();
        g.generateGameData();
        System.out.println("Livello: " + g.getIdLevel());
        System.out.println("numero nemici: "+ g.getNumEnemies());
        System.out.println("intervallo nemici: "+ g.getInterval());
        g.printIdEnemy();
        g.generateGameData();
        System.out.println("Livello: " + g.getIdLevel());
        System.out.println("numero nemici: "+ g.getNumEnemies());
        System.out.println("intervallo nemici: "+ g.getInterval());
        g.printIdEnemy();
        g.generateGameData();
        System.out.println("Livello: " + g.getIdLevel());
        System.out.println("numero nemici: "+ g.getNumEnemies());
        System.out.println("intervallo nemici: "+ g.getInterval());
        g.printIdEnemy();
        g.generateGameData();
        System.out.println("Livello: " + g.getIdLevel());
        System.out.println("numero nemici: "+ g.getNumEnemies());
        System.out.println("intervallo nemici: "+ g.getInterval());
        g.printIdEnemy();


    }*/

}

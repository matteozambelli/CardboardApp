package com.example.fabio.cardboardpb;

/**
 * Created by matteo on 22/01/2015.
 */
public class EnemiesManager {

    private final int lane=3;  //we have 3 lane
    private final int numberOfCar=10; //we have 10 different picture of car

    private int selectedLane; //what's the selected lane
    private int selectedCar; //what's the enemy

    /**
     * chooses lane and enemy
     */
    public void randomFunction(){
        selectedLane=2;//(int)(lane*Math.random())+1;
        selectedCar=(int)(numberOfCar*Math.random())+1;
    }
    public void setLane(){
        selectedLane=2;
    }

    /**
     *
     * @return the selected lane
     */
    public int getSelectedLane(){
        return selectedLane;
    }

    /**
     *
     * @return the selected enemies
     */
    public int getNumberOfCar(){
        return  selectedCar;
    }

    /* FOR TEST ONLY
    public static void main(String [ ] args)
    {
        EnemiesManager e= new EnemiesManager();
        e.randomFunction();
        System.out.println("corsia "+ e.getSelectedLane());
        System.out.println("nemico "+ e.getNumberOfCar());
        e.randomFunction();
        System.out.println("corsia "+ e.getSelectedLane());
        System.out.println("nemico "+ e.getNumberOfCar());
        e.randomFunction();
        System.out.println("corsia "+ e.getSelectedLane());
        System.out.println("nemico "+ e.getNumberOfCar());
        e.randomFunction();
        System.out.println("corsia "+ e.getSelectedLane());
        System.out.println("nemico "+ e.getNumberOfCar());
        e.randomFunction();
        System.out.println("corsia "+ e.getSelectedLane());
        System.out.println("nemico "+ e.getNumberOfCar());
        e.randomFunction();
        System.out.println("corsia "+ e.getSelectedLane());
        System.out.println("nemico "+ e.getNumberOfCar());

    }*/

}


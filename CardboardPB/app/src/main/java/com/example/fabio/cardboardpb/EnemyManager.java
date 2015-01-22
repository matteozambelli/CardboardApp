package com.example.fabio.cardboardpb;

/**
 * Created by matteo on 22/01/2015.
 */
public class EnemyManager {

    private final int lane=3;
    private final int numberOfCar=10;

    private int selectedLane;
    private int selectedCar;

    /**
     * chooses lane and enemy
     */
    public void randomFunction(){
        this.selectedLane=(int)(lane*Math.random())+1;
        this.selectedCar=(int)(numberOfCar*Math.random())+1;
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
     * @return the selected enemy
     */
    public int getNumberOfCar(){
        return selectedCar;
    }

}

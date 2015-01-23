package com.example.fabio.cardboardpb;



/**
 * Created by matteo on 23/01/2015.
 */
public class CollisionManager {

    private int position;
    private boolean isEndEnemyLane1;
    private boolean isEndEnemyLane2;
    private boolean isEndEnemyLane3;


    public CollisionManager(int position, boolean isEndEnemyLane1,boolean isEndEnemyLane2,boolean isEndEnemyLane3){
        this.position=position;
        this.isEndEnemyLane1=isEndEnemyLane1;
        this.isEndEnemyLane2=isEndEnemyLane2;
        this.isEndEnemyLane3=isEndEnemyLane3;

     }

    public int detectCollision() {
        //Detect collision on Line 1
        if (isEndEnemyLane1 && position==1) {
                return 1;
                //TODO Explosion animation on Line 1
        }
        //Detect collision on Line 2
        else if (isEndEnemyLane2 && position == 2) {

                return 2;
                //TODO Explosion animation on Line 2

        }else{
        //Detect collision on Line 3
        //if (isEndEnemyLane3 && position==3) {
                return 3;
                //TODO Explosion animation on Line 3
       }

    }
}

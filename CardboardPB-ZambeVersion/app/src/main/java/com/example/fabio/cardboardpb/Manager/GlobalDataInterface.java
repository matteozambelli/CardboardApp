package com.example.fabio.cardboardpb.Manager;

import com.example.fabio.cardboardpb.Manager.Enum.Eye;

/**
 * Created by matteo on 14/04/2015.
 */
public interface GlobalDataInterface {

    public boolean isGameover() ;

    public void setGameover(boolean gameover);

    public boolean isRunnable();

    public void setRunnable(boolean runnable);

    public int getAbsolutePosition();

    public boolean isEnd1();

    public boolean isEnd2();

    public boolean isEnd3();

    public Eye getEye();

    public void setAbsolutePosition(int absolutePosition) ;

    public void decreaseAbosolutePosition();

    public void increaseAbosolutePosition();

    public void setEnd1(boolean isEnd1);

    public void setEnd2(boolean isEnd2);

    public void setEnd3(boolean isEnd3);

    public void setLife(int life);

    public void setEye(Eye eye);

    public Integer getLevel() ;

    public Integer getLife() ;

    public void increaseScore();

    public Integer getScore();

    public void setScore(int s);

    public void setLevel(int level);

    public void increaseLevel();

    public void decreaseLife();

    public void resetLife();
}

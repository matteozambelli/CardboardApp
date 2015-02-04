package com.example.fabio.cardboardpb.Manager;

import com.example.fabio.cardboardpb.Manager.Enum.Side;

/**
 * Created by matteo on 27/01/2015.
 */
public interface PanoramaManagerInterface {

    public void randomPanorama() ;

    public Side getSelectedSide();

    public int getIdSubject();

}

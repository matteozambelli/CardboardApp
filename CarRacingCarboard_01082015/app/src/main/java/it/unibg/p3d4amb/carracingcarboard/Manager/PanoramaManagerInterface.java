package it.unibg.p3d4amb.carracingcarboard.Manager;

import it.unibg.p3d4amb.carracingcarboard.Manager.Enum.Side;

/**
 * Created by matteo on 27/01/2015.
 */
public interface PanoramaManagerInterface {

    public void randomPanorama() ;

    public Side getSelectedSide();

    public int getIdSubject();

}

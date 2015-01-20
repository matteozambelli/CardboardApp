package it.unibg.p3d4amb.stereoacuitytest.shapes;

import java.util.ArrayList;

/**
 * Created by Silvia on 29/09/2014.
 */
public class PointsShape {

    ArrayList<Float> allpoints=new ArrayList<Float>(); // Contiene i punti bianchi
    ArrayList<Float> shapepoints=new ArrayList<Float>(); // Contiene i punti rossi delle figure da visualizzare

    /**
     *
     * @param ap: Array contenente tutti i punti da disegnare
     * @param sp: Array contenente i punti delle figure da disegnare
     */
    PointsShape(ArrayList<Float> ap, ArrayList<Float> sp) {
        allpoints = ap;
        shapepoints = sp;
    }

}

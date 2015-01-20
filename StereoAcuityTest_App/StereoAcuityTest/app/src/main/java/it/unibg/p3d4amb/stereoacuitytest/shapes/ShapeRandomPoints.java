package it.unibg.p3d4amb.stereoacuitytest.shapes;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Silvia on 01/10/2014.
 */
public abstract class ShapeRandomPoints extends Shape{

    // add the central divider (skip th last points and add as total offset)
    private static final int CNTR_DIVIDER = 2;

    /**
     * @param w:   larghezza schermo
     * @param h:   altezza schermo
     */
    ShapeRandomPoints(int w, int h){
        super(w,h);
    }



    /**
     * @param d:   densità di pixel
     * @param offpix: indica di quanti pixel deve essere traslata l'immagine di destra (verso sinistra)
     * @return
     */
    @Override
    protected PointsShape getShapePoints(float d, int offpix) {
        PointsShape points;
        ArrayList<Float> alllist=new ArrayList<Float>();
        ArrayList<Float> shapelist=new ArrayList<Float>();
        points=new PointsShape(alllist,shapelist);
        alllist.clear();
        shapelist.clear();
        Random rnd = new Random();

        for (int x = 0; x < (w / 2) - CNTR_DIVIDER; x++) {
            for (int y = 0; y < h; y++) {
              if (rnd.nextFloat() > d) { //seleziona casualmente i punti da illuminare con probabilità d
                    // aggiungi il punto
                    alllist.add(Float.valueOf(x) + CNTR_DIVIDER);
                    alllist.add(Float.valueOf(y));
                    // aggiungi anche nell'altra metà'
                    if (belongsToShape(x, y)){
                        alllist.add(Float.valueOf(x - offpix + w / 2) + CNTR_DIVIDER);
                        alllist.add(Float.valueOf(y));
                        shapelist.add(Float.valueOf(x)+ CNTR_DIVIDER);
                        shapelist.add(Float.valueOf(y));
                        shapelist.add(Float.valueOf(x - offpix + w / 2)+ CNTR_DIVIDER);
                        shapelist.add(Float.valueOf(y));
                        if (!belongsToTranslatedShape(x, y, offpix)){
                            //Ricopia i punti se appartengono alla figura in posizione originale, ma non a quella traslata
                            alllist.add(Float.valueOf(x + w / 2)+ CNTR_DIVIDER);
                            alllist.add(Float.valueOf(y));
                        }
                    }else{
                        if ( !belongsToTranslatedShape(x, y, offpix)){
                            //Ricopia i punti nella seconda metà dello schermo senza modificarli se non appartengono alla figura
                            alllist.add(Float.valueOf(x + w / 2)+ CNTR_DIVIDER);
                            alllist.add(Float.valueOf(y));
                        }
                    }
                }
            }
        }
        //Inserisci i punti in un array float[]
        return points;
    }


    /**
     *
     * @param x: x del punto
     * @param y: y del punto
     * @param offpix: indica di quanti pixel è stata traslata l'immagine di destra (verso sinistra)
     * @return true se il punto appartiene all'immagine traslata, false altrimenti
     */
    boolean belongsToTranslatedShape(int x, int y, int offpix){
        return belongsToShape(x + offpix, y);
    }


    /**
     *
     * @param x: x del punto
     * @param y: y del punto
     * @return true se il punto appartiene alla figura, false altrimenti
     */
    protected abstract boolean belongsToShape(int x, int y);
}

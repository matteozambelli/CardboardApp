package it.unibg.p3d4amb.stereoacuitytest.shapes;

/**
 * Created by Silvia on 24/09/2014.
 */
public class CircleRandom extends ShapeRandomPoints {

    /**
     * Tipo di figura
     */
    public static final String CIRCLE = "CIRCLE";
    private final int xc; //x centro
    private final int yc; //y centro
    private final int raggio; //raggio


    /**
     *
     * @param w: larghezza
     * @param h: altezza
     * @param r: raggio
     */
    public CircleRandom(int w, int h, int r) {
        super(w,h);
        xc = w/4; //x centro
        yc = h/2; //y centro
        raggio = r; //raggio
    }


    // verifica se il punto appartiene al cerchio
    /**
     *
     * @param x: x del punto
     * @param y: y del punto
     * @return true se il punto appartiene al cerchio, false altrimenti
     */
    public boolean belongsToShape(int x, int y) {
        if (((Math.pow((x - xc), 2)) + (Math.pow((y - yc), 2))) <= (Math.pow(raggio, 2)))
            return true;
        else
            return false;
    }

    @Override
    public String getSimpleName() {
        return CIRCLE;
    }
}

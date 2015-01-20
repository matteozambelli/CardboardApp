package it.unibg.p3d4amb.stereoacuitytest.shapes;

/**
 * Created by Silvia on 24/09/2014.
 */
public class SquareRandom extends ShapeRandomPoints {

    public static final String SQUARE = "SQUARE";
    private int yMin;
    private int yMax;
    private int xMin;
    private int xMax;


    /**
     *
     * @param w: larghezza
     * @param h: altezza
     * @param s: lato
     */
    public SquareRandom(int w, int h, int s){
        super(w,h);
        yMin = h / 2 - s / 2;
        yMax = h / 2 + s / 2;
        xMin = w / 4 - s / 2;
        xMax = w / 4 + s / 2;

    }


    // verifica se il punto appartiene al quadrato
    /**
     *
     * @param x: x del punto
     * @param y: y del punto
     * @return: true se il punto appartiene al quadrato, false altrimenti
     */
    public boolean belongsToShape(int x, int y){
        if( (x >= xMin && x <= xMax) && (y >= yMin && y <= yMax))
            return true;
        else
            return false;
    }

    @Override
    public String getSimpleName() {
        return SQUARE;
    }
}

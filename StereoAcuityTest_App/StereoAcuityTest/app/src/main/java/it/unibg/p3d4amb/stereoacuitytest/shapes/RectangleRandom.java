package it.unibg.p3d4amb.stereoacuitytest.shapes;

/**
 * Created by Silvia on 24/09/2014.
 */
public class RectangleRandom extends ShapeRandomPoints {

    public static final String RECTANGLE = "RECTANGLE";
    private int yMin;
    private int yMax;
    private int xMin;
    private int xMax;


    /**
     *
     * @param w: larghezza
     * @param h: altezza
     * @param b: base rett
     * @param he: altezza rett
     */
    public RectangleRandom(int w, int h, int b, int he) {
        super(w,h);
        yMin = h / 2 - he / 2;
        yMax = h / 2 +he / 2;
        xMin = w / 4 - b / 2;
        xMax = w / 4 + b / 2;

    }


    // verifica se il punto appartiene al rettangolo

    /**
     * @param x: x del punto
     * @param y: y del punto
     * @return: true se il punto appartiene al rettangolo, false altrimenti
     */
    public boolean belongsToShape(int x, int y) {
        if ((x >= xMin && x <= xMax) && (y >= yMin && y <= yMax))
            return true;
        else
            return false;
    }

    @Override
    public String getSimpleName() {
        return RECTANGLE;
    }



}

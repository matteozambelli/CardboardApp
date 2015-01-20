package it.unibg.p3d4amb.stereoacuitytest.shapes;

/**
 * Created by Silvia on 24/09/2014.
 */
public class TriangleRandom extends ShapeRandomPoints {

    public static final String TRIANGLE = "TRIANGLE";
    private float v0x;
    private float v0y;
    private float v1x;
    private float v1y;
    private float v2x;
    private float v2y;


    /**
     *
     * @param w: larghezza
     * @param h: altezza
     * @param b: base tri
     * @param he: altezza tri
     */
    public TriangleRandom(int w, int h, int b, int he) {
        super(w,h);
        v0x = w / 4 - (float)b / 2;
        v0y = h / 2 - (float)he / 2;
        v1x = w / 4 +(float)b/ 2;
        v1y = h / 2 - (float)he/ 2;
        v2x = w / 4;
        v2y = h / 2 + (float)he / 2;
    }


    // verifica se il punto appartiene al triangolo

    /**
     * @param x: x del punto
     * @param y: y del punto
     * @return: true se il punto appartiene al triangolo, false altrimenti
     */
    public boolean belongsToShape(int x, int y) {
        float d1, d2, dtot, a, b;
        d1 = ((float) x - v0x) * (v2y - v0y) - (y - v0y) * (v2x - v0x);
        d2 = (v0x - (float) x) * (v1y - v0y) - (v0y - y) * (v1x - v0x);
        dtot = (v1x - v0x) * (v2y - v0y) - (v1y - v0y) * (v2x - v0x);
        a = d1 / dtot;
        b = d2 / dtot;
        if (a > 0f && b > 0f && (a + b) < 1f)
            return true;
        else
            return false;
    }

    @Override
    public String getSimpleName() {
        return TRIANGLE;
    }

}
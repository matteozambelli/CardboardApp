package it.unibg.p3d4amb.stereoacuitytest.shapes;

import java.nio.FloatBuffer;

/**
 * Created by Silvia on 24/09/2014.
 */
public class Points {

    private FloatBuffer fb; // FloatBuffer che contiene i punti bianchi
    private int length; // lunghezza float buffer
    private FloatBuffer fbShape; // FloatBuffer che contiene i punti delle figure da visualizzare
    private int lengthShape; // lunghezza float buffer

    /**
     *
     * @param f: FloatBuffer all points
     * @param l: lenght FloatBuffer f
     * @param fs: FloatBuffer shape points
     * @param ls: lenght FloatBuffer fs
     */
    Points(FloatBuffer f, int l, FloatBuffer fs, int ls) {
        fb = f;
        length = l;
        fbShape=fs;
        lengthShape=ls;
    }

    public FloatBuffer getFb() {
        return fb;
    }

    public int getLength() {
        return length;
    }

    public FloatBuffer getFbShape() {
        return fbShape;
    }

    public int getLengthShape() {
        return lengthShape;
    }
}

package it.unibg.p3d4amb.stereoacuitytest.shapes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Silvia on 24/09/2014.
 */
public abstract class Shape {

    int w;
    int h;

    /**
     *
     * @param w:   larghezza schermo
     * @param h:   altezza schermo
     */
    Shape(int w, int h){
        this.w=w;
        this.h=h;
    }


    //data larghezza e altezza restituisce l'array di punti da colorare
    /**
     * @param d:   densità di pixel
     * @param off: indica di quanti pixel deve essere traslata l'immagine di destra (verso sinistra)
     * @return: Array contenente tutti i punti da disegnare e un array contenente i punti delle figure da disegnare
     */
    protected abstract PointsShape getShapePoints(float d, int off);


    //Insert points into a buffer

    /**
      * @param d:   densità di pixel
     * @param off: indica di quanti pixel deve essere traslata l'immagine di destra (verso sinistra)
     * @return: oggetto di tipo Points: FloatBuffer contenente tutti i punti, lunghezza buffer, FloatBuffer contenente i punti delle figure da disegnare, lunghezza buffer
     */
    public final Points pointBuffer(float d, int off) {
        PointsShape points = getShapePoints(d, off);

        float[] floatArrayAll = new float[points.allpoints.size()];
        float[] floatArrayShape = new float[points.shapepoints.size()];
        int i = 0;
        for (Float f : points.allpoints) {
            floatArrayAll[i++] = f;
        }
        i = 0;
        for (Float f : points.shapepoints) {
            floatArrayShape[i++] = f;
        }
        int lengthall = floatArrayAll.length;
        int lengthshape = floatArrayShape.length;


        FloatBuffer pointsbufferall;
        ByteBuffer bball = ByteBuffer.allocateDirect(lengthall * 4);
        bball.clear();
        bball.order(ByteOrder.nativeOrder());
        pointsbufferall = bball.asFloatBuffer();
        pointsbufferall.put(floatArrayAll);
        pointsbufferall.position(0);

        FloatBuffer pointsbuffershape;
        ByteBuffer bbshape = ByteBuffer.allocateDirect(lengthshape * 4);
        bbshape.clear();
        bbshape.order(ByteOrder.nativeOrder());
        pointsbuffershape = bbshape.asFloatBuffer();
        pointsbuffershape.put(floatArrayShape);
        pointsbuffershape.position(0);

        return new Points(pointsbufferall, lengthall, pointsbuffershape, lengthshape);
    }

    /** larghezza e altezza dello schermo in cui viene visualizzata l'immagine*/
    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public abstract String getSimpleName();
}

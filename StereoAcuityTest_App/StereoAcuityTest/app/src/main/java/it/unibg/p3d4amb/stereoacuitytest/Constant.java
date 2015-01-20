package it.unibg.p3d4amb.stereoacuitytest;

/**
 * Created by Silvia on 27/09/2014.
 */
class Constant {



    /**
     * numero di coordinate per individuare il punto: x - y
     */
    public static final int COORDNUMBER = 2;


    /**
     * raggio cerchio
     */
    public static final String RADIUS="Radius";


    /**
     * Base rettangolo
     */
    public static final String BASERECT = "BaseRect";
    /**
     * Altezza rettangolo
     */
    public static final String HEIGHTRECT = "HeightRect";


    /**
     * Lato quadrato
     */
    public static final String SIDESQUARE = "SideSquare";



    /**
     * Base triangolo
     */
    public static final String BASETRI = "BaseTri";
    /**
     * Altezza triangolo
     */
    public static final String HEIGHTTRI = "HeightTri";


    /**
     * colore dei punti da visualizzare
     */
    public static final float[] COLOR = new float[]{1, 1, 1, 1};
    /**
     * colore dei punti da visualizzare nel training
     */
    public static final float[] COLOR2 = new float[]{0.4f, 0.8f, 0.8f, 1};


    /**
     * dimensione massima dei punti
     */
    public static final float MAXDIMPOINTS = 10f;
    /**
     * dimensione minima dei punti
     */
    public static final float MAXDENPOINTS = 1f;


    /**
     * Densità punti Sharepreferences
     */
    public static final String DENPREF = "DENPOINTS";
    /**
     * Dimensione punti Sharepreferences
     */
    public static final String DIMPREF = "DIMPOINTS";
    /**
     * Offset immagine Sharepreferences
     */
    public static final String OFFSETSHAPE = "OFFSETSHAPE";


    /**
     * Tipo di attività: test
     */
    public static final String TESTTYPE = "Test";
    /**
     * Tipo di attività: training
     */
    public static final String TRAININGTYPE = "Training";


    public static final String FORGET = "FORGET";
}

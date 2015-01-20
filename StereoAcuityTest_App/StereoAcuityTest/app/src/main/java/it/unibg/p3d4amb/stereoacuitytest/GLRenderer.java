package it.unibg.p3d4amb.stereoacuitytest;

/**
 * Created by Silvia on 17/09/2014.
 */

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import it.unibg.p3d4amb.stereoacuitytest.shapes.Points;
import it.unibg.p3d4amb.stereoacuitytest.shapes.Shape;

class GLRenderer implements GLSurf.Renderer {

    // Our matrices
    private final float[] mtrxProjection = new float[16];
    private final float[] mtrxView = new float[16];
    private final float[] mtrxProjectionAndView = new float[16];

    // Geometric variables
    private Points pointsBuffer; // Contiene i punti che verranno visualizzati

    // Our screenresolution
    private int mScreenWidth;
    private int mScreenHeight;
    private float denpoints; // densità dei punti
    private float dimpoints; // dimensione dei punti
    private int offset; // numero pixel da spostare la figura a destra verso il centro

    private Shape shape; // figura da visualizzare
    private String typecall; // indica se si è nella fase di test o di training: serve per sapere se devo visualizzare le figure in rosso oppure no


    /**
     *
     * @param c: context
     * @param width: larghezza schermo
     * @param height: altezza schermo
     * @param dimpoints: dimensione dei punti da disegnare
     * @param denpoints: densità dei punti da disegnare
     * @param s: tipo di figura
     * @param typecall: indica se si è nella sezione di training o test
     * @param offset: indica di quanti pixel deve essere traslata l'immagine di destra (verso sinistra)
     */
    public GLRenderer(Context c, int width, int height, float dimpoints, float denpoints, Shape s, String typecall, int offset) {
        Context mContext = c;
        mScreenHeight = height;
        mScreenWidth = width;
        shape = s;
        this.dimpoints = dimpoints;
        this.denpoints = denpoints;
        this.typecall = typecall;
        this.offset = offset;
        assert s.getW() == width;
        assert s.getH() == height;
    }


    public void onPause() {
        /* Do stuff to pause the renderer */
    }


    public void onResume() {
        /* Do stuff to resume the renderer */
    }


    @Override
    public void onDrawFrame(GL10 unused) {
        System.out.println("onDrawFrame");
        int COORDNUMBER = Constant.COORDNUMBER;
        // clear Screen and Depth Buffer, we have set the clear color as black.
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // get handle to vertex shader's vPosition, vColor and vPointSize members
        int mPositionHandle = GLES20.glGetAttribLocation(riGraphicTools.sp_SolidColor, "vPosition");
        int mColorHandle = GLES20.glGetUniformLocation(riGraphicTools.sp_SolidColor, "vColor");
        int mDimPoint = GLES20.glGetUniformLocation(riGraphicTools.sp_SolidColor, "vPointSize");

        //assegna i valori alle variabili SHADER
        float[] color = Constant.COLOR;
        float dim = dimpoints;
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        GLES20.glUniform1f(mDimPoint, dim);
        // Enable generic vertex attribute array
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        // Prepare the points coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDNUMBER,
                GLES20.GL_FLOAT, false,
                0, pointsBuffer.getFb());
        // Get handle to shape's transformation matrix
        int mtrxhandle = GLES20.glGetUniformLocation(riGraphicTools.sp_SolidColor, "uMVPMatrix");
        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mtrxhandle, 1, false, mtrxProjectionAndView, 0);
        // Draw points
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, pointsBuffer.getLength() / COORDNUMBER);

        if (typecall.equalsIgnoreCase(Constant.TRAININGTYPE)) {
            //NEW COLOR
            float[] color2 = Constant.COLOR2;
            GLES20.glUniform4fv(mColorHandle, 1, color2, 0);
            // Prepare the points coordinate data
            GLES20.glVertexAttribPointer(mPositionHandle, COORDNUMBER,
                    GLES20.GL_FLOAT, false,
                    0, pointsBuffer.getFbShape());
            // Draw points
            GLES20.glDrawArrays(GLES20.GL_POINTS, 0, pointsBuffer.getLengthShape() / COORDNUMBER);
            //NB: il terzo elemento è il numero di punti: siccome lengthShape è il numero di x più y il numero di punti si ottiene dividendo il numero delle coordinate per il numero di coordinate che identificano ogni punto
        }

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // width e height sono l'altezza e la larghezza attuali, siccome lo schermo deve sempre restare in landscape mode si mantengono altezza e larghezza dell'activity Test
        System.out.println("onSurfaceChanged");
        // Redo the Viewport, making it fullscreen.
        GLES20.glViewport(0, 0, mScreenWidth, mScreenHeight);

        // Clear our matrices
        for (int i = 0; i < 16; i++) {
            mtrxProjection[i] = 0.0f;
            mtrxView[i] = 0.0f;
            mtrxProjectionAndView[i] = 0.0f;
        }

        // Setup our screen width and height for normal sprite translation.
        Matrix.orthoM(mtrxProjection, 0, 0f, mScreenWidth, 0.0f, mScreenHeight, 0, 50);
        // Set the camera position (View matrix)
        Matrix.setLookAtM(mtrxView, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        // Calculate the projection and view transformation
        Matrix.multiplyMM(mtrxProjectionAndView, 0, mtrxProjection, 0, mtrxView, 0);
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        System.out.println("onSurfaceCreated");
        // Create the points
        pointsBuffer = shape.pointBuffer(denpoints, offset);
        // Set the clear color to black
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1);
        // Create the shaders
        int vertexShader = riGraphicTools.loadShader(GLES20.GL_VERTEX_SHADER, riGraphicTools.vs_SolidColor);
        int fragmentShader = riGraphicTools.loadShader(GLES20.GL_FRAGMENT_SHADER, riGraphicTools.fs_SolidColor);
        riGraphicTools.sp_SolidColor = GLES20.glCreateProgram();             // create empty OpenGL ES Program
        GLES20.glAttachShader(riGraphicTools.sp_SolidColor, vertexShader);   // add the vertex shader to program
        GLES20.glAttachShader(riGraphicTools.sp_SolidColor, fragmentShader); // add the fragment shader to program
        GLES20.glLinkProgram(riGraphicTools.sp_SolidColor);                  // creates OpenGL ES program executables
        // Set our shader programm
        GLES20.glUseProgram(riGraphicTools.sp_SolidColor);
    }


}
package it.unibg.p3d4amb.stereoacuitytest;

/**
 * Created by Silvia on 17/09/2014.
 */

import android.content.Context;
import android.opengl.GLSurfaceView;

import it.unibg.p3d4amb.stereoacuitytest.shapes.Shape;


public class GLSurf extends GLSurfaceView {


    private final GLRenderer mRenderer;

    /**
     *
     * @param context: context
     * @param width: larghezza
     * @param height: altezza
     * @param dimpoints: dimensione dei punti
     * @param denpoints: densità dei punti
     * @param shape: figura da disegnare
     * @param typecall: indica se si è nella fase di test o di training
     * @param offsetshape: indica di quanti pixel deve essere traslata l'immagine di destra (verso sinistra)
     */
    public GLSurf(Context context, int width, int height, float dimpoints, float denpoints, Shape shape, String typecall, int offsetshape) {
        super(context);
        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);
        //
        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new GLRenderer(context, width, height, dimpoints, denpoints, shape, typecall, offsetshape);
        setRenderer(mRenderer);

        // Render the view continuously
        // setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }


    @Override
    public void onPause() {
        super.onPause();
        mRenderer.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();
        mRenderer.onResume();
    }

}
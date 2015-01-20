package it.unibg.p3d4amb.stereoacuitytest;

/**
 * Created by Silvia on 17/09/2014.
 */

import android.opengl.GLES20;

class riGraphicTools {

    /* SHADER Solid
     *
     * This shader is for rendering a colored primitive.
     *
     */
    public static final String vs_SolidColor =
            "uniform    mat4        uMVPMatrix;" +
                    "attribute  vec4        vPosition;" +
                    "uniform float  vPointSize;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;"
                    + " gl_PointSize = vPointSize;" +
                    "}";
    public static final String fs_SolidColor =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";


    // Program variables
    public static int sp_SolidColor;


    public static int loadShader(int type, String shaderCode) {
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);
        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        // return the shader
        return shader;
    }
}
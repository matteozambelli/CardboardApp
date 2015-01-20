package it.unibg.p3d4amb.stereoacuitytest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Random;

import it.unibg.p3d4amb.stereoacuitytest.shapes.CircleRandom;
import it.unibg.p3d4amb.stereoacuitytest.shapes.RectangleRandom;
import it.unibg.p3d4amb.stereoacuitytest.shapes.Shape;
import it.unibg.p3d4amb.stereoacuitytest.shapes.SquareRandom;
import it.unibg.p3d4amb.stereoacuitytest.shapes.TriangleRandom;

public class StereoAcuityTest extends Activity {

    private SharedPreferences appsetting; //variabile per accedere alle SharedPreferences
    private float dimpoints; // dimensione dei punti da disegnare
    private float denpoints; // densità dei punti da disegnare
    private int offsetshape; // indica di quanti pixel deve essere traslata l'immagine di destra (verso sinistra)
    private Shape shapeselected; //figura da indovinare
    private int width; //larghezza schermo
    private int height; //altezza schermo
    private String typecall; // indica se si è nella fase di test o di training
    private int points = 0; // figure indovinate
    private int totshape = 0; // figure totale
    private int forget = 0; //figure dimenticate
    // Our OpenGL Surfaceview
    private GLSurfaceView glSurfaceView;
    private int selection=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Turn off the window's title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Super
        super.onCreate(savedInstanceState);

        // Read settings value
        appsetting = PreferenceManager.getDefaultSharedPreferences(this);
        dimpoints = appsetting.getFloat(Constant.DIMPREF, 1.0f);
        denpoints = appsetting.getFloat(Constant.DENPREF, 0.8f);
        offsetshape = appsetting.getInt(Constant.OFFSETSHAPE, 4);
        typecall = getIntent().getExtras().getString("TipoTest");
        // Fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Acquisisci larghezza/altezza schermo (essendo in modalità LANDSCAPE la larghezza è il lato più lungo
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        if (size.x > size.y) {
            width = size.x;
            height = size.y;
        } else {
            width = size.y;
            height = size.x;
        }
        changeShape();
    }


    private void changeShape() {
        // get the next shape
        shapeselected = getNextRndShape();
        // We create our Surfaceview for our OpenGL here.
        glSurfaceView = new GLSurf(this, width, height, dimpoints, denpoints, shapeselected, typecall, offsetshape);
        // Set our view.
        setContentView(R.layout.activity_test);
        // Retrieve our Relative layout from our main layout we just set to our view.
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.testlayout);
        // Attach our surfaceview to our relative layout from our main layout.
        RelativeLayout.LayoutParams glParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.addView(glSurfaceView, glParams);

        int btnwidth=400;
        int btnheight=60;
        Button b1 = new Button(this);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(btnwidth, btnheight);
        params1.leftMargin = width/2-btnwidth/2;
        params1.topMargin=0;
        b1.setLayoutParams(params1);
        if (typecall.equalsIgnoreCase(Constant.TESTTYPE)) {
            b1.setBackgroundResource(R.drawable.test);
            layout.addView(b1);
        }else{
            b1.setBackgroundResource(R.drawable.training);
            layout.addView(b1);
        }
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                //Quando preme il pulsante + del volume
                if (action == KeyEvent.ACTION_UP) {
                    volumeUp();
                }
                return true;
            case KeyEvent.KEYCODE_MEDIA_NEXT:
                //Quando preme il pulsante + del volume
                if (action == KeyEvent.ACTION_UP) {
                    volumeUp();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    volumeDown();
                }
                return true;
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                if (action == KeyEvent.ACTION_DOWN) {
                    volumeDown();
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    private void volumeDown() {
        if (typecall.equalsIgnoreCase(Constant.TESTTYPE)) {
            viewResults();
            finish();
        }else  if (typecall.equalsIgnoreCase(Constant.TRAININGTYPE))
        {
            finish();
        }
    }

    private void volumeUp() {
        // Se si è nella fase di test passa alla sezione in cui si sceglie l'immagine visualizzata
        if (typecall.equalsIgnoreCase(Constant.TESTTYPE)) {
            //Se si è nella fase di test si deve selezionare la figura da indovinare
            Intent intent = new Intent(this, ChooseImage.class);
            intent.putExtra("Width", width);
            startActivityForResult(intent, 1);
        } else { //Se si è nella fase di training scorri le immagini
            changeShape();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                //se si è nella fase di training quando si ritorna dall'Activity in cui si sceglie l'immagine si aggiorna il punteggio
                totshape++; //FIGURE TOTALI
                String chosenShape = data.getExtras().getString(ChooseImage.selectedShapeKey);
                if (shapeselected.getSimpleName().equals(chosenShape))
                    points++; //FIGURE INDOVINATE
                else if (chosenShape.equalsIgnoreCase(Constant.FORGET))
                    forget++; //FIGURE DIMENTICATE
                changeShape();
            }
        }
    }


    //Seleziona casualmente la figura da visualizzare
    Shape getNextRndShape() {
        Shape shapechoosenrandom = null;
        if (typecall.equalsIgnoreCase(Constant.TESTTYPE)) {
           selection = randInt(1, 4);
        }else
        {
            if (selection==4)
                selection=1;
            else selection++;
        }
        if (selection==1) {
            shapechoosenrandom = new CircleRandom(width, height, appsetting.getInt(Constant.RADIUS, 50));
        } else if (selection==2) {
            shapechoosenrandom = new TriangleRandom(width, height, appsetting.getInt(Constant.BASETRI, 100), appsetting.getInt(Constant.HEIGHTTRI, 100));
        } else if (selection==3) {
            shapechoosenrandom = new RectangleRandom(width, height, appsetting.getInt(Constant.BASERECT, 100), appsetting.getInt(Constant.HEIGHTRECT, 50));
        } else if (selection==4) {
            shapechoosenrandom = new SquareRandom(width, height, appsetting.getInt(Constant.SIDESQUARE, 100));
        } else {
            throw new RuntimeException("no shape selected ???");
        }
        return shapechoosenrandom;
    }


    //Genera un numero casuale tra min e max
    private static int randInt(int min, int max) {
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }


    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }


    @Override
    public void onBackPressed() {
        viewResults();
        super.onBackPressed();
        finish();
    }

    private void viewResults() {
        Intent returnIntent = new Intent(this, MainActivity.class);
        returnIntent.putExtra("TotPoints", points);
        returnIntent.putExtra("TotShape", totshape);
        returnIntent.putExtra("TotForget", forget);
        setResult(RESULT_OK, returnIntent);
    }
}

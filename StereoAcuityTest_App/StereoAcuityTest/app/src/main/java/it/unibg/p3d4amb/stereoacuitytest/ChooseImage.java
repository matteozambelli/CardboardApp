package it.unibg.p3d4amb.stereoacuitytest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import it.unibg.p3d4amb.stereoacuitytest.shapes.CircleRandom;
import it.unibg.p3d4amb.stereoacuitytest.shapes.RectangleRandom;
import it.unibg.p3d4amb.stereoacuitytest.shapes.SquareRandom;
import it.unibg.p3d4amb.stereoacuitytest.shapes.TriangleRandom;


public class ChooseImage extends Activity {

    private ImageView image1, image2; // immagini visualizzate --> una per l'occhio destro e una per l'occhio sinistro
    private String shapeselected = ""; // contiene il nome della figura selezionata
    private int i = 1; // Serve per scorrere le immagini da visualizzare tra quelle da scegliere
    final static String selectedShapeKey = "ShapeSelected";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choose_image);

        int width = getIntent().getExtras().getInt("Width");
        RelativeLayout rel = (RelativeLayout) findViewById(R.id.layoutparent);
        //crea la immagini
        image1 = new ImageView(this);
        image2 = new ImageView(this);
        // centra le immagini: la prima immagine visualizzata è il cerchio
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        //"w": indica che si deve restituire la larghezza
        params1.leftMargin = width / 4 - getImageWidth() / 2;
        params1.rightMargin = width / 2 + width / 4 - getImageWidth() / 2;
        image1.setLayoutParams(params1);
        image1.setImageResource(R.drawable.circle);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        //"h": indica che si deve restituire l'altezza
        params2.rightMargin = width / 4 - getImageWidth() / 2;
        params2.leftMargin = width / 2 + width / 4 - getImageWidth() / 2;
        image2.setLayoutParams(params2);
        image2.setImageResource(R.drawable.circle);
        shapeselected = CircleRandom.CIRCLE;
        rel.addView(image1);
        rel.addView(image2);
        int btnwidth=600;
        int btnheight=60;
        Button b1 = new Button(this);
        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(btnwidth, btnheight);
        params3.leftMargin = width/2-btnwidth/2;
        params3.topMargin=0;
        b1.setLayoutParams(params3);
        b1.setBackgroundResource(R.drawable.answer);
        rel.addView(b1);
        i=1;
    }


    // Return altezza/larghezza in pixel dell'immagine da visualizzare per centrarla, viene fatto solo per la prima visualizzata, le altre utilizzano gli stessi parametri impostati

    /**
     *
     * @return altezza/larghezza schermo
     */
    private int getImageWidth() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.circle, options);
            return options.outWidth;
    }

    // listener per pressione +- volume
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                // Quando preme + del volume cambia la figura
                if (action == KeyEvent.ACTION_UP) {
                    if (i==5)
                        i=1;
                    else
                        i++;
                    volumeUp();
                }
                return true;
            case KeyEvent.KEYCODE_MEDIA_NEXT:
                // Quando preme + del volume cambia la figura
                if (action == KeyEvent.ACTION_UP) {
                    if (i==5)
                        i=1;
                    else
                        i++;
                    volumeUp();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                // Quando preme - del volume seleziona la soluzione
                if (action == KeyEvent.ACTION_DOWN) {
                    if (i==1)
                        i=5;
                    else
                        i--;
                    volumeDown();
                }
                return true;
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                // Quando preme - del volume seleziona la soluzione
                if (action == KeyEvent.ACTION_DOWN) {
                    if (i==1)
                        i=5;
                    else
                        i--;
                    volumeDown();
                }
                return true;
            case KeyEvent.KEYCODE_HEADSETHOOK: //chiudi dialog quando preme il pulsante di risposta alla chiamata delle cuffie
                    volumeOk();
                return true;
            case KeyEvent.KEYCODE_MEDIA_PLAY:
                    volumeOk();
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    private void volumeOk() {
        Intent returnIntent = new Intent(this, StereoAcuityTest.class);
        returnIntent.putExtra(selectedShapeKey, shapeselected);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    private void volumeUp() {
        switch (i) {
            case 1:
                image1.setImageResource(R.drawable.circle);
                image2.setImageResource(R.drawable.circle);
                shapeselected = CircleRandom.CIRCLE;
                break;
            case 2:
                image1.setImageResource(R.drawable.square);
                image2.setImageResource(R.drawable.square);
                shapeselected = SquareRandom.SQUARE;
                break;
            case 3:
                image1.setImageResource(R.drawable.rectangle);
                image2.setImageResource(R.drawable.rectangle);
                shapeselected = RectangleRandom.RECTANGLE;
                break;
            case 4:
                image1.setImageResource(R.drawable.triangle);
                image2.setImageResource(R.drawable.triangle);
                shapeselected = TriangleRandom.TRIANGLE;
                break;
            case 5:
                image1.setImageResource(R.drawable.forget);
                image2.setImageResource(R.drawable.forget);
                shapeselected = Constant.FORGET;
                break;
        }
    }

    private void volumeDown() {
        switch (i) {
            case 1:
                image1.setImageResource(R.drawable.circle);
                image2.setImageResource(R.drawable.circle);
                shapeselected = CircleRandom.CIRCLE;
                break;
            case 2:
                image1.setImageResource(R.drawable.square);
                image2.setImageResource(R.drawable.square);
                shapeselected = SquareRandom.SQUARE;
                break;
            case 3:
                image1.setImageResource(R.drawable.rectangle);
                image2.setImageResource(R.drawable.rectangle);
                shapeselected = RectangleRandom.RECTANGLE;
                break;
            case 4:
                image1.setImageResource(R.drawable.triangle);
                image2.setImageResource(R.drawable.triangle);
                shapeselected = TriangleRandom.TRIANGLE;
                break;
            case 5:
                image1.setImageResource(R.drawable.forget);
                image2.setImageResource(R.drawable.forget);
                shapeselected = Constant.FORGET;
                break;
        }
    }

}

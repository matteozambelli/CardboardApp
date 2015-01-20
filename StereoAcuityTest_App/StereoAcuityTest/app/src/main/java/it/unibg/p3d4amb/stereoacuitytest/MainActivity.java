package it.unibg.p3d4amb.stereoacuitytest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {


    private AlertDialog alertDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Super
        super.onCreate(savedInstanceState);
        // Set our view.
        setContentView(R.layout.activity_main);
        int width;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        if (size.x < size.y) {
            width = size.x;
        } else {
            width = size.y;
        }

        int btnwidth=500;
        int btnheight=60;
        Button b1 = new Button(this);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(btnwidth, btnheight);
        params1.leftMargin = width/2-btnwidth/2;
        params1.topMargin=0;
        b1.setLayoutParams(params1);
        b1.setBackgroundResource(R.drawable.mainpage);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.gamelayout);
        layout.addView(b1);
/*

        IntentFilter filter = new IntentFilter(Intent.ACTION_MEDIA_BUTTON);
        filter.setPriority(10000);

        MediaButtonIntentReceiver r = new MediaButtonIntentReceiver();
        registerReceiver(r, filter);
*/

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start:
                //Si nizia il test quando si preme il pulsante "Start Test"
                volumeUp();
                break;
            case R.id.button_options:
                //Si accede al menu delle opzioni quando si preme il pulsante "Select test level"
                Intent intent2 = new Intent(this, Options.class);
                startActivity(intent2);
                break;
            case R.id.button_training:
                //Si nizia il test quando si preme il pulsante "Start Test"
                volumeDown();
                break;
            case R.id.button_shape:
                // Gestione dimensione immagini
                Intent intent4 = new Intent(this, ShapeSetting.class);
                startActivity(intent4);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_help:
                Intent intent5 = new Intent(this, Help.class);
                startActivity(intent5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
                    //Si nizia il test quando si preme il pulsante - del volume
                    volumeDown();
                }
                return true;
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
               if (action == KeyEvent.ACTION_DOWN) {
                    //Si nizia il test quando si preme il pulsante - del volume
                   volumeDown();
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    private void volumeDown() {
        Intent intent3 = new Intent(this, StereoAcuityTest.class);
        intent3.putExtra("TipoTest", Constant.TRAININGTYPE);
        startActivity(intent3);
    }

    private void volumeUp() {
        //Si nizia il test quando si preme il pulsante + del volume
        Intent intent = new Intent(this, StereoAcuityTest.class);
        intent.putExtra("TipoTest", Constant.TESTTYPE);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                //Visualizza il punteggio ottenuto
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Results:"); // Titolo Dialog.
                final TextView inputFileName = new TextView(this); // Testo contenente i risultati
                inputFileName.setTextSize(20);
                inputFileName.setText("Points: " + data.getExtras().getInt("TotPoints") + "\n" + "Forget: " + data.getExtras().getInt("TotForget") +  "\n" + "Wrong: " + (data.getExtras().getInt("TotShape")-data.getExtras().getInt("TotForget")-data.getExtras().getInt("TotPoints"))+ "\n" +"Total: " + data.getExtras().getInt("TotShape") ); // Scritta di default nel
                // Dialog.
                builder.setView(inputFileName); // Visualizza la scritta di default nel
                // Dialog.
                builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        switch (event.getKeyCode()) {
                            case KeyEvent.KEYCODE_HEADSETHOOK: //chiudi dialog quando preme il pulsante di risposta alla chiamata delle cuffie
                                alertDialog1.cancel();
                                return true;
                            case KeyEvent.KEYCODE_MEDIA_PLAY:
                                alertDialog1.cancel();
                                return true;
                           }
                        return false;
                                           }
                });
                alertDialog1 = builder.create();
                alertDialog1.show();
            }
        }
    }

}

package com.example.fabio.cardboardpb.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.fabio.cardboardpb.Exception.MyException;
import com.example.fabio.cardboardpb.Manager.Enum.Language;
import com.example.fabio.cardboardpb.R;

public class LanguageActivity extends Activity {

    private TextView languageLeft,languageRight, titleLeft,titleRight,startLeft,startRight;
    private Language language= Language.ENGLISH;
    private int width,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        languageLeft= (TextView)findViewById(R.id.textViewLanguageLeft);
        languageRight= (TextView)findViewById(R.id.textViewLanguageRight);
        titleLeft= (TextView) findViewById(R.id.textViewSelectLeft);
        titleRight= (TextView) findViewById(R.id.textViewSelectRight);
        startLeft=(TextView) findViewById(R.id.textViewStart1);
        startRight=(TextView) findViewById(R.id.textViewStart2);

        startLeft.setTextSize(16);
        startRight.setTextSize(16);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_language, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                //on key + press
                if (action == KeyEvent.ACTION_DOWN) {
                   changeLanguage();
                }
                return true;
            case KeyEvent.KEYCODE_MEDIA_NEXT:
                //on key + press
                if (action == KeyEvent.ACTION_DOWN) {
                    changeLanguage();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    //on key - press
                    changeLanguage();

                }
                return true;
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                if (action == KeyEvent.ACTION_DOWN) {
                    //on key - press
                    changeLanguage();

                }
                return true;
            case KeyEvent.KEYCODE_HEADSETHOOK:
                if (action == KeyEvent.ACTION_DOWN) {
                    //on key home press
                    Intent startSettings = new Intent(this, SettingsActivity.class);
                    startSettings.putExtra("language", language);
                    startActivity(startSettings);
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }


    private void changeLanguage(){

        if(languageLeft.getText().equals("English") ){
            languageLeft.setText("Italiano");
            languageRight.setText("Italiano");
            titleLeft.setText("SELEZIONA LA LINGUA");
            titleRight.setText("SELEZIONA LA LINGUA");
            startLeft.setText("PREMI HOME PER INIZIARE");
            startRight.setText("PREMI HOME PER INIZIARE");
            language=Language.ITALIANO;
        }else if(languageLeft.getText().equals("Italiano") ){
            languageLeft.setText("English");
            languageRight.setText("English");
            titleLeft.setText("SELECT THE LANGUAGE");
            titleRight.setText("SELECT THE LANGUAGE");
            startLeft.setText("PRESS HOME TO START");
            startRight.setText("PRESS HOME TO START");
            language=Language.ENGLISH;
        }else{
            try {
                throw new MyException("No language selected");
            } catch (MyException e) {
                e.printStackTrace();
            }
        }

    }
}

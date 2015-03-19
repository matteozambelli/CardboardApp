package com.example.fabio.cardboardpb.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabio.cardboardpb.Exception.MyException;
import com.example.fabio.cardboardpb.Manager.Enum.Eye;
import com.example.fabio.cardboardpb.Manager.Enum.Language;
import com.example.fabio.cardboardpb.Manager.LanguageManager;
import com.example.fabio.cardboardpb.R;


public class SettingsActivity extends ActionBarActivity {


    private String id_user;
    private LanguageManager languageManager;
    private Eye eye= Eye.LEFT_EYE;
    private Language language=Language.ENGLISH;
    private TextView textEyeLeft,textEyeRight,textSelectLeft,textSelectRight,textStartLeft,textStartRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        Typeface font = Typeface.createFromAsset(getAssets(), "orange juice 2.0.ttf");

        Intent intent=getIntent();
        Bundle data = getIntent().getExtras();
        language=(Language) intent.getSerializableExtra("language");

        id_user=(String) intent.getSerializableExtra("id_user");
        languageManager=new LanguageManager(language);
        languageManager.suitable();

        textEyeLeft=(TextView) findViewById(R.id.textViewEye1);
        textEyeLeft.setText(languageManager.getEyeLeft());
        textEyeLeft.setTypeface(font);

        textEyeRight=(TextView) findViewById(R.id.textViewEye2);
        textEyeRight.setText(languageManager.getEyeLeft());
        textEyeRight.setTypeface(font);

        textStartLeft=(TextView) findViewById(R.id.textSettingsStart1);
        textStartLeft.setText(languageManager.getSettingsStart());
        textStartLeft.setTextSize(16);
        textStartLeft.setTypeface(font);

        textStartRight=(TextView) findViewById(R.id.textSettingsStart2);
        textStartRight.setText(languageManager.getSettingsStart());
        textStartRight.setTextSize(16);
        textStartRight.setTypeface(font);

        textSelectLeft=(TextView) findViewById(R.id.textViewSelectEyeLeft);
        textSelectLeft.setText(languageManager.getSettingsTitle());
        textSelectLeft.setTypeface(font);

        textSelectRight=(TextView) findViewById(R.id.textViewSelectEyeRight);
        textSelectRight.setText(languageManager.getSettingsTitle());
        textSelectRight.setTypeface(font);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {

            case KeyEvent.KEYCODE_VOLUME_UP:
                //on key + press
                if (action == KeyEvent.ACTION_DOWN) {
                   changeEye();

                }
                return true;
            case KeyEvent.KEYCODE_MEDIA_NEXT:
                //on key + press
                if (action == KeyEvent.ACTION_DOWN) {
                   changeEye();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    //on key - press
                    changeEye();
                }
                return true;
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                if (action == KeyEvent.ACTION_DOWN) {
                    //on key - press
                    changeEye();
                }
                return true;
            case KeyEvent.KEYCODE_HEADSETHOOK:
                if(action== KeyEvent.ACTION_DOWN){
                    //on key home press
                    goToMain();
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    private void goToMain(){

        Intent startGame = new Intent(SettingsActivity.this, MainActivity.class);
        startGame.putExtra("eye", eye);
        startGame.putExtra("language", language);
        startGame.putExtra("id_user",id_user);
        startActivity(startGame);

   /*new AlertDialog.Builder(this)
            .setTitle("test mode")
            .setMessage("PAUSE").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            // continue with delete
        }
    }).show();
*/
    }


    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }

    private void changeEye(){
        if(textEyeLeft.getText().equals(languageManager.getEyeLeft())){
            textEyeLeft.setText(languageManager.getEyeRight());
            textEyeRight.setText(languageManager.getEyeRight());
            eye=Eye.RIGHT_EYE;
        }else if(textEyeLeft.getText().equals(languageManager.getEyeRight())){
            textEyeLeft.setText(languageManager.getEyeLeft());
            textEyeRight.setText(languageManager.getEyeLeft());
            eye=Eye.LEFT_EYE;
        }else{
            try {
                throw new MyException("No eye");
            } catch (MyException e) {
                e.printStackTrace();
            }
        }
    }
}

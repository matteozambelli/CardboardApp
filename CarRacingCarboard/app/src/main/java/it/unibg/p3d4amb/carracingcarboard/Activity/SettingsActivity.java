package it.unibg.p3d4amb.carracingcarboard.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import it.unibg.p3d4amb.carracingcarboard.Exception.MyException;
import it.unibg.p3d4amb.carracingcarboard.Manager.Enum.Eye;
import it.unibg.p3d4amb.carracingcarboard.R;


public class SettingsActivity extends ActionBarActivity {


    private String id_user;

    private Eye eye= Eye.LEFT_EYE;
    private Eye lazyEye;

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


        id_user=(String) intent.getSerializableExtra("id_user");



        textEyeLeft=(TextView) findViewById(R.id.textViewEye1);
        textEyeLeft.setText("Left");
        textEyeLeft.setTypeface(font);

        textEyeRight=(TextView) findViewById(R.id.textViewEye2);
        textEyeRight.setText("Left");
        textEyeRight.setTypeface(font);

        textStartLeft=(TextView) findViewById(R.id.textSettingsStart1);
        textStartLeft.setText("PRESS HOME TO START");
        textStartLeft.setTextSize(16);
        textStartLeft.setTypeface(font);

        textStartRight=(TextView) findViewById(R.id.textSettingsStart2);
        textStartRight.setText("PRESS HOME TO START");
        textStartRight.setTextSize(16);
        textStartRight.setTypeface(font);

        textSelectLeft=(TextView) findViewById(R.id.textViewSelectEyeLeft);
        textSelectLeft.setText("SELECT THE LAZY EYE");
        textSelectLeft.setTypeface(font);

        textSelectRight=(TextView) findViewById(R.id.textViewSelectEyeRight);
        textSelectRight.setText("SELECT THE LAZY EYE");
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
        if(eye.equals(Eye.LEFT_EYE)){
            lazyEye=Eye.RIGHT_EYE;
        }else{
            lazyEye=Eye.LEFT_EYE;
        }
        startGame.putExtra("eye", lazyEye);

        startGame.putExtra("id_user",id_user);
        startActivity(startGame);
        finish();
    }


    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }

    private void changeEye(){
        if(textEyeLeft.getText().equals("Left")){
            textEyeLeft.setText("Right");
            textEyeRight.setText("Right");
            eye=Eye.RIGHT_EYE;
        }else if(textEyeLeft.getText().equals("Right")){
            textEyeLeft.setText("Left");
            textEyeRight.setText("Left");
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

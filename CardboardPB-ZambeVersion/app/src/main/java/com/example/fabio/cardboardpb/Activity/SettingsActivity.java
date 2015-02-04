package com.example.fabio.cardboardpb.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabio.cardboardpb.R;


public class SettingsActivity extends ActionBarActivity {

    private TextView textEyeLeft,textEyeRight;

    private String eye="Left";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        textEyeLeft=(TextView) findViewById(R.id.textViewEye1);




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
        if(textEyeLeft.getText().equals("Left")){
            textEyeLeft.setText("Right");
            eye="Right";
        }else{
            textEyeLeft.setText("Left");
            eye="Left";
        }
    }
}

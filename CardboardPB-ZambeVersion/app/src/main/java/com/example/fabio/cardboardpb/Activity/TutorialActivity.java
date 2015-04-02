package com.example.fabio.cardboardpb.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.fabio.cardboardpb.R;
import com.example.fabio.cardboardpb.Thread.TutorialThread;

public class TutorialActivity extends ActionBarActivity {

    private CheckBox checkBox;
    private TextView text;
    private SharedPreferences settings1;
    private String id_user;
    private TutorialThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        Typeface font = Typeface.createFromAsset(getAssets(), "orange juice 2.0.ttf");

        Intent intent=getIntent();
        id_user=(String) intent.getSerializableExtra("id_user");
        checkBox= (CheckBox) findViewById(R.id.dont_show);
        text= (TextView) findViewById(R.id.textView);
        text.setTypeface(font);
        thread= new TutorialThread(this,text,id_user);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences settings = getSharedPreferences("TUTORIAL", 0);
                SharedPreferences.Editor editor = settings.edit();
                if(isChecked){
                    editor.putBoolean("isCheck",true);
                }
                else{
                    editor.putBoolean("isCheck",false);
                }

            }
        });
        if (getSharedPreferences("LOG_IN", 0) != null) {
            settings1 = getSharedPreferences("LOG_IN", 0);
            if (settings1.getBoolean("isCheck", false)) {
                Intent i = new Intent(TutorialActivity.this, SplashActivity.class);
                i.putExtra("id_user", id_user);
                startActivity(i);
            }
        }


        thread.start();





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial, menu);
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
}

package com.example.fabio.cardboardpb.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.fabio.cardboardpb.R;
import com.example.fabio.cardboardpb.Thread.TutorialThread;

public class TutorialActivity extends ActionBarActivity {

    private CheckBox checkBox;
    private TextView text;

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
        text.setGravity(View.TEXT_ALIGNMENT_CENTER);

        checkBox.setTypeface(font);
        text.setTextSize(40);



        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences settings = getSharedPreferences("TUTORIAL",0);
                SharedPreferences.Editor editor = settings.edit();
                if(isChecked){
                    editor.putBoolean("isCheck",true);
                    editor.commit();
                }
                else{
                    editor.putBoolean("isCheck",false);
                    editor.commit();
                }

            }
        });

        SharedPreferences settings3= getSharedPreferences("TUTORIAL",0);

        checkBox.setChecked(settings3.getBoolean("isCheck",false));


        thread= new TutorialThread(this,text,id_user);
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

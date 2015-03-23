package com.example.fabio.cardboardpb.Activity;

import android.app.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fabio.cardboardpb.DB.PostCall;
import com.example.fabio.cardboardpb.Manager.Enum.TypeCall;
import com.example.fabio.cardboardpb.R;

import java.util.Calendar;
import java.util.StringTokenizer;


public class DoctorActivity extends Activity {


    private EditText firstName, lastName, birthday;
    private TextView status;
    private Button start;
    private Activity doctorActivity;
    private int year, month, day;
    private String id_doctor, date;
    private PostCall post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_doctor);
        Intent intent=getIntent();
        id_doctor=(String) intent.getSerializableExtra("id_doctor");
        doctorActivity=this;

        start= (Button) findViewById(R.id.start);
        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        birthday = (EditText) findViewById(R.id.date);
        status= (TextView) findViewById(R.id.status);

        // Process to get Current Date
        final Calendar c = Calendar.getInstance();

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        final DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                // Display Selected date in textbox
                month+=1;
                birthday.setText(day + "/" + month + "/" + year);
            }

        }
                , year, month, day);

        birthday.setInputType(0);
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show();
            }
        });
        date=day+"/"+month+"/"+year;

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post=new PostCall(firstName.getText().toString(),lastName.getText().toString(),date,id_doctor);
                post.myPostCall(TypeCall.DOCTORCALL,doctorActivity);
                launchRingDialog();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctor, menu);
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

    private void launchRingDialog() {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(DoctorActivity.this, "Please wait ...", "contacting server ...", true);
        ringProgressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Here you should write your time consuming task...
                    // Let the progress ring for 10 seconds...

                    Thread.sleep(2000);
                   /* StringTokenizer token= new StringTokenizer(status.getText().toString());
                    token.nextToken("/");
                    id_user=token.nextToken("/");
                    doctor=token.nextToken("/");
*/                    //status.setText(id_user+" "+doctor);
                    if(true){
                        Intent i = new Intent(DoctorActivity.this, SplashActivity.class);
                        i.putExtra("id_doctor", id_doctor);
                        i.putExtra("id_user", 2);
                        startActivity(i);
                    }
                } catch (Exception e) {
                }
                ringProgressDialog.dismiss();
            }
        }).start();

    }
}

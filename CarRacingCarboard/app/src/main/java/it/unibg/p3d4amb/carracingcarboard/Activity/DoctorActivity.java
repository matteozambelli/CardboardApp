package it.unibg.p3d4amb.carracingcarboard.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import it.unibg.p3d4amb.carracingcarboard.DB.PostCall;
import it.unibg.p3d4amb.carracingcarboard.Manager.Enum.TypeCall;
import it.unibg.p3d4amb.carracingcarboard.R;

import java.util.Calendar;
import java.util.Date;


public class DoctorActivity extends Activity {


    private EditText firstName, lastName, birthday;
    private TextView status;
    private Button start;
    private Activity doctorActivity;
    private int year, month, day;
    private String monthString;
    private String dayString;
    private String id_doctor;
    private String date;
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
        status.setAlpha(0);
        birthday.setFocusable(false);

        // Process to get Current Date
        final Calendar c = Calendar.getInstance();

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        final Date currentDate = new Date(year, month, day);
        final DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                // Display Selected date in textbox
                month += 1;
                Date sectedDate = new Date(year, month, day);
                if (currentDate.before(sectedDate)) {
                    Toast.makeText(getBaseContext(), "Please insert a correct date", Toast.LENGTH_LONG).show();

                } else {
                    birthday.setText(day + "/" + month + "/" + year);
                    monthString=new Integer(month).toString();
                    dayString=new Integer(day).toString();

                    if(month<10){
                        monthString="0"+month;
                    }
                    if(day<10){
                        dayString="0"+day;
                    }
                    date = year + "-" + monthString + "-" + dayString ;
                    status.setText(date);
                }
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


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(firstName.getText().toString().isEmpty()||lastName.getText().toString().isEmpty()||birthday.getText().toString().isEmpty()){
                    emptyFieldAlert();

                }else {
                    post = new PostCall(firstName.getText().toString(), lastName.getText().toString(), id_doctor, date, status, true);
                    post.myPostCall(TypeCall.DOCTORCALL, doctorActivity);
                    launchRingDialog();
                }
            }
        });

    }


    /**
     * thread for the connection to the DB
     */
    private void launchRingDialog() {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(DoctorActivity.this, "Please wait ...", "contacting server ...", true);
        ringProgressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                        Intent i = new Intent(DoctorActivity.this, SplashActivity.class);
                        i.putExtra("id_doctor", id_doctor);
                        i.putExtra("id_user", status.getText().toString());
                        startActivity(i);
                        finish();
                } catch (Exception e) {
                }
                ringProgressDialog.dismiss();
            }
        }).start();

    }

    /**
     * showed when firstname or lastname or bithday field is empty
     */
    private void emptyFieldAlert(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Warning");
        alert.setMessage("some field is empty");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
}

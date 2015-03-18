package com.example.fabio.cardboardpb.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabio.cardboardpb.DB.PostCall;
import com.example.fabio.cardboardpb.Manager.Enum.TypeCall;
import com.example.fabio.cardboardpb.Manager.PasswdManager;
import com.example.fabio.cardboardpb.R;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;


public class LogInActivity extends Activity {

    private EditText email;
    private EditText password;
    private Button logIn;
    private TextView signUp;
    private TextView status;
    private Button play;
    private Button forgot;
    private Button workWithUs;
    private String passwordToSend;
    private CheckBox keepLog;
    private PostCall post;
    private Activity logInActivity;
    private String id_user;
    private String doctor;
    private int year, month,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);



        logInActivity = this;

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        logIn = (Button) findViewById(R.id.logInButton);
        signUp = (TextView) findViewById(R.id.textViewSignUp);
        keepLog = (CheckBox) findViewById(R.id.checkBox);
        workWithUs = (Button) findViewById(R.id.workWithUs);

        passwordToSend = PasswdManager.calculateHash(password.toString());
        play = (Button) findViewById(R.id.playWithoutReg);
        forgot = (Button) findViewById(R.id.forgotPassword);
        status = (TextView) findViewById(R.id.status);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                passwordToSend = PasswdManager.calculateHash(password.getText().toString());
                post = new PostCall(email.getText().toString(), passwordToSend, status);
                post.myPostCall(TypeCall.LOG_IN, logInActivity);
                if (status.getText().toString().contains("password errata")) {
                    wrongPasswordAlert();
                }
                launchRingDialog();

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertSignUp("", "", "", "");
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warningNoRegistration();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPasswordAlert();
            }
        });

        workWithUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workWithUsAlert();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
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


    private void alertSignUp(String firstname, String lastname, String email, String color) {


        final EditText firstName = new EditText(this);
        final EditText lastName = new EditText(this);
        final EditText eMail = new EditText(this);
        final EditText myColor = new EditText(this);
        final EditText password = new EditText(this);
        final EditText confirmPassword = new EditText(this);
        final EditText formDate= new EditText(this);

        LinearLayout layout = new LinearLayout(this);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);


        alert.setMessage("SIGN UP");

        if (firstname.equals("") && lastname.equals("") && email.equals("")) {
            firstName.setHint("first name");
            lastName.setHint("last name");
            eMail.setHint("email");

        }
        if (firstname.equals("") && !lastname.equals("") && !email.equals("")) {
            firstName.setHint("first name");
            lastName.setText(lastname);
            eMail.setText(email);
        }
        if (firstname.equals("") && lastname.equals("") && !email.equals("")) {
            firstName.setHint("first name");
            lastName.setHint("last name");
            eMail.setText(email);
        }
        if (!firstname.equals("") && lastname.equals("") && !email.equals("")) {
            firstName.setText(firstname);
            lastName.setHint("last name");
            eMail.setText(email);
        }
        if (!firstname.equals("") && !lastname.equals("") && email.equals("")) {
            firstName.setText(firstname);
            lastName.setText(lastname);
            eMail.setHint("email");
        }
        if (!firstname.equals("") && lastname.equals("") && email.equals("")) {
            firstName.setText(firstname);
            lastName.setHint("last name");
            eMail.setHint("email");
        }

        if (firstname.equals("") && !lastname.equals("") && email.equals("")) {
            firstName.setHint("firstname");
            lastName.setText(lastname);
            eMail.setHint("email");
        } else {
            firstName.setText(firstname);
            lastName.setText(lastname);
            eMail.setText(email);
        }

        formDate.setHint("bithday");
        myColor.setHint("your favourite color");
        password.setHint("password");
        confirmPassword.setHint("confirm password");
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(firstName);
        layout.addView(lastName);
        layout.addView(eMail);
        layout.addView(formDate);
        layout.addView(myColor);
        layout.addView(password);
        layout.addView(confirmPassword);
        alert.setView(layout);

        // Process to get Current Date
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        final DatePickerDialog dpd = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                // Display Selected date in textbox
                 month+=1;
                 formDate.setText(day + "/"+ month + "/" + year);
            }

        }
    ,year, month, day);

   formDate.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           dpd.show();
       }
   });


        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String backUpFirstName = firstName.getText().toString();
                String backUpLastName = lastName.getText().toString();
                String backUpEmail = eMail.getText().toString();
                String backUpColor = myColor.getText().toString();
                if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    //ALERT MESSAGE
                    Toast.makeText(getBaseContext(), "Please insert the same password", Toast.LENGTH_LONG).show();
                    alertSignUp(backUpFirstName, backUpLastName, backUpEmail, backUpColor);


                } else {

                    Toast.makeText(getBaseContext(), "Please wait", Toast.LENGTH_LONG).show();
                    //Cript the password
                    passwordToSend = PasswdManager.calculateHash(password.getText().toString());
                    //send data
                    post = new PostCall(firstName.getText().toString(), lastName.getText().toString(), eMail.getText().toString(), myColor.getText().toString(), passwordToSend, status);
                    post.myPostCall(TypeCall.SIGN_UP, logInActivity);
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        alert.show();

    }


    private void warningNoRegistration() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Warning");
        alert.setMessage("you can play without registration, but we can't trace your improvement");
        alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                launchRingDialog();
                passwordToSend = PasswdManager.calculateHash("default_user");
                post = new PostCall("3d4ambcardboard@gmail.com", passwordToSend, status);
                post.myPostCall(TypeCall.LOG_IN, logInActivity);
            }
        });
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

        alert.show();
    }

    private void forgotPasswordAlert() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("resert your password: ");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText mailTo = new EditText(this);
        final EditText myColor = new EditText(this);
        mailTo.setHint("insert email");
        myColor.setHint("your favourite color");
        layout.addView(mailTo);
        layout.addView(myColor);
        alert.setView(layout);
        alert.setTitle("Warning");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newPasswd = generateRandom();
                passwordToSend = PasswdManager.calculateHash(newPasswd);
                post = new PostCall(mailTo.getText().toString(), myColor.getText().toString(), newPasswd, passwordToSend, status);
                post.myPostCall(TypeCall.RESET, logInActivity);
                // launchRingDialog();
            }
        });
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

        alert.show();
    }

    private void wrongPasswordAlert() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Please re-enter your password");
        alert.setTitle("Warning");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    private void registrationComplete() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("registration complete");
        alert.setTitle("welcome");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    private void launchRingDialog() {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(LogInActivity.this, "Please wait ...", "contacting server ...", true);
        ringProgressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Here you should write your time consuming task...
                    // Let the progress ring for 10 seconds...

                    Thread.sleep(2000);
                    id_user = status.getText().toString().substring(11, 12);
                    doctor = status.getText().toString().substring(14, 15);
                    //status.setText(doctor);
                    if(doctor.contains("1")){
                        Intent i = new Intent(LogInActivity.this, DoctorActivity.class);
                        i.putExtra("id_doctor", doctor);
                        startActivity(i);
                    }
                    else if (status.getText().toString().contains("connection")) {
                        Intent i = new Intent(LogInActivity.this, SplashActivity.class);
                        i.putExtra("id_user", id_user);
                        startActivity(i);
                    } else if (status.getText().toString().contains("password errata")) {
                        logInActivity.runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            wrongPasswordAlert();

                                                        }
                                                    }


                        );


                    }else if(status.getText().toString().contains("emailChecked")){
                        logInActivity.runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            registrationComplete();

                                                        }
                                                    }


                        );
                    }
                } catch (Exception e) {

                }
                ringProgressDialog.dismiss();
            }
        }).start();

    }

    private void workWithUsAlert() {

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("The 3D4Amb project aims at developing a system based on the 3D for the diagnosis and treatment of amblyopia in young children." + '\n' + '\n' +
                "if you are a Doctor and you want to collaborate with us, send a mail, just click ok here " + '\n' + "Best regards," + '\n' + '\n' + "3d4amb staff " + '\n' + '\n' + "3d4ambcardboard@gmail.com"+ '\n' + '\n' +"http://3d4amb.unibg.it");
        alert.setTitle("COLLABORATE WITH US");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"3d4ambcardboard@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "3d4amb DOCTOR COLLABORATION");
                i.putExtra(Intent.EXTRA_TEXT, "");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(LogInActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        alert.show();
    }

    private String generateRandom() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(40, random).toString(32);
    }


}


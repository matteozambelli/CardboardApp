package com.example.fabio.cardboardpb.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabio.cardboardpb.DB.DBConnect;
import com.example.fabio.cardboardpb.Manager.PasswdManager;
import com.example.fabio.cardboardpb.R;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LogInActivity extends Activity {

    private boolean logInOrRegistration=true; //false: registration; true: log in;
    private EditText email;
    private EditText password;
    private Button logIn;
    private TextView signUp;
    private String passwordToSend;

    private DBConnect DBConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        DBConnect=new DBConnect();

        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        logIn= (Button) findViewById(R.id.logInButton);
        signUp= (TextView) findViewById(R.id.textViewSignUp);

       passwordToSend= PasswdManager.calculateHash(password.toString());


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ALERT MESSAGE
                Toast.makeText(getBaseContext(), "Please wait, connecting to server.", Toast.LENGTH_LONG).show();

                try{

                    // Create http cliient object to send request to server

                    HttpClient Client = new DefaultHttpClient();

                    // Create URL string
                    String URL = "http://localhost:3306/cardboard.php?email="+email.toString()+"&password="+passwordToSend;


                    //Log.i("httpget", URL);

                    try
                    {
                        String SetServerString = "";

                        // Create Request to server and get response

                        HttpGet httpget = new HttpGet(URL);
                        ResponseHandler<String> responseHandler = new BasicResponseHandler();
                        SetServerString = Client.execute(httpget, responseHandler);


                    }
                    catch(Exception ex)
                    {
                       ex.printStackTrace();
                    }
                }
                catch(Exception ex)
                {
                  ex.printStackTrace();
                }

            }
        });


        signUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertSignUp();
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

    private void alertSignUp(){

        final EditText firstName = new EditText(this);
        final EditText lastName = new EditText(this);
        final EditText email = new EditText(this);
        final EditText password = new EditText(this);
        final EditText confirmPassword = new EditText(this);
        LinearLayout layout = new LinearLayout(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setMessage("SIGN UP");
        firstName.setHint("first name");
        lastName.setHint("last name");
        email.setHint("email");
        password.setHint("password");
        confirmPassword.setHint("confirm password");
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(firstName);
        layout.addView(lastName);
        layout.addView(email);
        layout.addView(password);
        layout.addView(confirmPassword);
        alert.setView(layout);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    //ALERT MESSAGE
                    Toast.makeText(getBaseContext(), "Please insert the same password", Toast.LENGTH_LONG).show();

                }
                Toast.makeText(getBaseContext(), "Please wait", Toast.LENGTH_LONG).show();
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

    private void alertWrongPassword(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Warning");
        alert.setMessage("please insert the same password");
        alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
            }
        });
    }


}

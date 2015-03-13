package com.example.fabio.cardboardpb.DB;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.fabio.cardboardpb.Manager.Enum.TypeCall;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matteo on 09/03/2015.
 */
public class PostCall {

    private Thread thread;
    private String response;
    //LOG_IN
    private String username;
    private String password;
    //SIGN_UP
    private String firstName;
    private String lastName;
    private String email;

    /**
     * @param username
     * @param password
     */
    public PostCall(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     */
    public PostCall(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void myPostCall(final  TypeCall type,final Activity logInActivity) {
        // Create a new HttpClient and Post Header
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://3d4amb.unibg.it/3dcar/tmp_provaPost.php");

                //This is the data to send

                final Activity activity = logInActivity;


                try {
                    // Add your data
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    if (type.equals(TypeCall.LOG_IN)) {
                        nameValuePairs.add(new BasicNameValuePair("type", "log_in"));
                        nameValuePairs.add(new BasicNameValuePair("email", username));
                        nameValuePairs.add(new BasicNameValuePair("password", password));
                    }
                    if (type.equals(TypeCall.SIGN_UP)) {
                        nameValuePairs.add(new BasicNameValuePair("type", "sign_up"));
                        nameValuePairs.add(new BasicNameValuePair("first_name", firstName));
                        nameValuePairs.add(new BasicNameValuePair("last_name", lastName));
                        nameValuePairs.add(new BasicNameValuePair("email", email));
                        nameValuePairs.add(new BasicNameValuePair("password", password));
                    }
                    if (type.equals(TypeCall.RESET)) {
                        nameValuePairs.add(new BasicNameValuePair("type", "reset"));
                    }
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    // Execute HTTP Post Request
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    response = httpclient.execute(httppost, responseHandler);
                     //This is the response from a php application
                    final String reverseString = response;

                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(activity, "response: " + reverseString, Toast.LENGTH_LONG).show();
                }
            });


                } catch (ClientProtocolException e) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(activity, "CPE response ", Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (IOException e) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(activity, "IOE response ", Toast.LENGTH_LONG).show();
                        }
                    });

                }

            }//end postData()

        });
        thread.start();
    }

}


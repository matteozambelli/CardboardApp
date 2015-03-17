package com.example.fabio.cardboardpb.DB;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabio.cardboardpb.Manager.Enum.TypeCall;

import junit.framework.Test;

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
    private String myColor;
    //REPORT
    private String score;
    private String level;
    private String id_user;
    //RESET
    private String newPassword;
    private String passwordCfr;

    TextView status;


    /**
     *
     * @param username
     * @param password
     * @param status
     */
    public PostCall(String username, String password,TextView status) {
        this.username = username;
        this.password = password;
        this.status=status;
    }

    /**
     *
     * @param email
     * @param myColor
     * @param newPassword
     * @param passwordCfr
     * @param status
     */
    public PostCall(String email, String myColor, String newPassword,String passwordCfr, TextView status) {
        this.email = email;
        this.myColor = myColor;
        this.newPassword=newPassword;
        this.passwordCfr=passwordCfr;
        this.status=status;
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param myColor
     * @param password
     * @param status
     */
    public PostCall(String firstName, String lastName, String email, String myColor, String password,TextView status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.myColor=myColor;
        this.status=status;
    }



    /**
     *
     * @param score
     * @param level
     */
    public PostCall(String score, String level,String id_user) {
        this.score = score;
        this.level = level;
        this.id_user=id_user;
    }

    public void myPostCall(final  TypeCall type,final Activity logInActivity) {
        // Create a new HttpClient and Post Header
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://3d4amb.unibg.it/3dcar/3d4ambService.php");

                //This is the data to send
                final Activity activity = logInActivity;
                try {
                    // Add your data
                    List<NameValuePair> nameValuePairsLogIn = new ArrayList<NameValuePair>(3);
                    List<NameValuePair> nameValuePairsReset = new ArrayList<NameValuePair>(5);
                    List<NameValuePair> nameValuePairsSignUp = new ArrayList<NameValuePair>(6);
                    List<NameValuePair> nameValuePairsReport = new ArrayList<NameValuePair>(4);
                    if (type.equals(TypeCall.LOG_IN)) {
                        nameValuePairsLogIn.add(new BasicNameValuePair("type", "log_in"));
                        nameValuePairsLogIn.add(new BasicNameValuePair("email", username));
                        nameValuePairsLogIn.add(new BasicNameValuePair("password", password));
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsLogIn));
                    }
                    if (type.equals(TypeCall.SIGN_UP)) {
                        nameValuePairsSignUp.add(new BasicNameValuePair("type", "sign_up"));
                        nameValuePairsSignUp.add(new BasicNameValuePair("first_name", firstName));
                        nameValuePairsSignUp.add(new BasicNameValuePair("last_name", lastName));
                        nameValuePairsSignUp.add(new BasicNameValuePair("email", email));
                        nameValuePairsSignUp.add(new BasicNameValuePair("color", myColor));
                        nameValuePairsSignUp.add(new BasicNameValuePair("password", password));
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsSignUp));
                    }
                    if (type.equals(TypeCall.RESET)) {
                        nameValuePairsReset.add(new BasicNameValuePair("type", "reset"));
                        nameValuePairsReset.add(new BasicNameValuePair("email", email));
                        nameValuePairsReset.add(new BasicNameValuePair("color", myColor));
                        nameValuePairsReset.add(new BasicNameValuePair("new_password",newPassword));
                        nameValuePairsReset.add(new BasicNameValuePair("new_passwordCfr",passwordCfr));
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsReset));
                    }
                    if(type.equals(TypeCall.REPORT)){
                        nameValuePairsReport.add(new BasicNameValuePair("type", "report"));
                        nameValuePairsReport.add(new BasicNameValuePair("score", score));
                        nameValuePairsReport.add(new BasicNameValuePair("level", level));
                        nameValuePairsReport.add(new BasicNameValuePair("id_user", id_user));
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsReport));
                    }

                    // Execute HTTP Post Request
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    response = httpclient.execute(httppost, responseHandler);
                     //This is the response from a php application
                    final String reverseString = response;
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                           status.setText(reverseString);
                           // Toast.makeText(activity, "response: " + reverseString, Toast.LENGTH_LONG).show();
                         /* if(type.equals(TypeCall.LOG_IN) || type.equals(TypeCall.RESET)){ status.setText(reverseString);}
                            else if(type.equals(TypeCall.REPORT)){
                              //TODO
                          }*/
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


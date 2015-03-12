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

    public void myPostCall(TypeCall type, final String username, final String password, final Activity logInActivity) {
        // Create a new HttpClient and Post Header
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://3d4amb.unibg.it/3dcar/tmp_provaPost.php");

//This is the data to send
                String MyName = username; //any data to send
                String MyPassword = password; //any data to send
                final Activity activity = logInActivity;


                try {
// Add your data
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                    nameValuePairs.add(new BasicNameValuePair("email", MyName));
                    nameValuePairs.add(new BasicNameValuePair("password", MyPassword));

                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

// Execute HTTP Post Request

                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    String response = httpclient.execute(httppost, responseHandler);

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
// TODO Auto-generated catch block
                } catch (IOException e) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(activity, "IOE response ", Toast.LENGTH_LONG).show();
                        }
                    });
// TODO Auto-generated catch block
                }

            }//end postData()

        });
        thread.start();
    }

}


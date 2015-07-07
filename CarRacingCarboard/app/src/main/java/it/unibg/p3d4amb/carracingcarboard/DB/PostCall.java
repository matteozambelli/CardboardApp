package it.unibg.p3d4amb.carracingcarboard.DB;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;
import android.widget.Toast;

import it.unibg.p3d4amb.carracingcarboard.Manager.Enum.TypeCall;

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
    private String email;
    private String password;
    //SIGN_UP
    private String firstName;
    private String lastName;
    private String birthday;
    private String myColor;
    //REPORT
    private String score;
    private String level;
    private String id_user;
    //RESET
    private String newPassword;
    private String passwordCfr;
    //DOCTOR
    private String id_doctor;
    //UPDATE
    private String update;


    TextView status;

    /**UPDATE
     * @param id_user
     * @param string
     * @param status
     * @param update
     */
    public PostCall(String id_user, String string, TextView status, boolean update) {
        this.id_user = id_user;
        this.update = string;
        this.status = status;
    }


    /**LOGIN
     * @param email
     * @param password
     * @param status
     */
    public PostCall(String email, String password, TextView status) {
        this.email = email;
        this.password = password;
        this.status = status;
    }

    /**RESET PASSWORD
     * @param email
     * @param myColor
     * @param newPassword
     * @param passwordCfr
     * @param status
     */
    public PostCall(String email, String myColor, String newPassword, String passwordCfr, TextView status) {
        this.email = email;
        this.myColor = myColor;
        this.newPassword = newPassword;
        this.passwordCfr = passwordCfr;
        this.status = status;
    }

    /**SIGN UP
     * @param firstName
     * @param lastName
     * @param email
     * @param myColor
     * @param birthday
     * @param password
     * @param status
     */
    public PostCall(String firstName, String lastName, String email, String myColor, String birthday, String password, TextView status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.myColor = myColor;
        this.status = status;
    }

    /**DOCTOR CALL
     * @param firstName
     * @param lastName
     * @param id_doctor
     * @param birthday
     */
    public PostCall(String firstName, String lastName, String id_doctor, String birthday,TextView status, boolean doctorActivity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id_doctor = id_doctor;
        this.birthday = birthday;
        this.status=status;
    }

    /**
     * REPORT
     * @param score
     * @param level
     * @param id_user
     * @param textView
     */
    public PostCall(String score, String level, String id_user,TextView textView) {
        this.score = score;
        this.level = level;
        this.id_user = id_user;
        this.status=textView;
    }

    public void myPostCall(final TypeCall type, final Activity logInActivity) {
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
                    List<NameValuePair> nameValuePairsUpdateString = new ArrayList<NameValuePair>(3);
                    List<NameValuePair> nameValuePairsLogIn = new ArrayList<NameValuePair>(3);
                    List<NameValuePair> nameValuePairsReset = new ArrayList<NameValuePair>(5);
                    List<NameValuePair> nameValuePairsSignUp = new ArrayList<NameValuePair>(7);
                    List<NameValuePair> nameValuePairsDoctorCall = new ArrayList<NameValuePair>(5);
                    List<NameValuePair> nameValuePairsReport = new ArrayList<NameValuePair>(4);

                        if (type.equals(TypeCall.LOG_IN)) {
                            nameValuePairsLogIn.add(new BasicNameValuePair("type", "log_in"));
                            nameValuePairsLogIn.add(new BasicNameValuePair("email", email));
                            nameValuePairsLogIn.add(new BasicNameValuePair("password", password));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsLogIn));
                        }
                        if (type.equals(TypeCall.SIGN_UP)) {
                            nameValuePairsSignUp.add(new BasicNameValuePair("type", "sign_up"));
                            nameValuePairsSignUp.add(new BasicNameValuePair("first_name", firstName));
                            nameValuePairsSignUp.add(new BasicNameValuePair("last_name", lastName));
                            nameValuePairsSignUp.add(new BasicNameValuePair("email", email));
                            nameValuePairsSignUp.add(new BasicNameValuePair("color", myColor));
                            nameValuePairsSignUp.add(new BasicNameValuePair("date", birthday));
                            nameValuePairsSignUp.add(new BasicNameValuePair("password", password));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsSignUp));
                        }
                        if (type.equals(TypeCall.RESET)) {
                            nameValuePairsReset.add(new BasicNameValuePair("type", "reset"));
                            nameValuePairsReset.add(new BasicNameValuePair("email", email));
                            nameValuePairsReset.add(new BasicNameValuePair("color", myColor));
                            nameValuePairsReset.add(new BasicNameValuePair("new_password", newPassword));
                            nameValuePairsReset.add(new BasicNameValuePair("new_passwordCfr", passwordCfr));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsReset));
                        }
                        if (type.equals(TypeCall.DOCTORCALL)) {
                            nameValuePairsDoctorCall.add(new BasicNameValuePair("type", "doctorcall"));
                            nameValuePairsDoctorCall.add(new BasicNameValuePair("first_name", firstName));
                            nameValuePairsDoctorCall.add(new BasicNameValuePair("last_name", lastName));
                            nameValuePairsDoctorCall.add(new BasicNameValuePair("id_doctor", id_doctor));
                            nameValuePairsDoctorCall.add(new BasicNameValuePair("birthday", birthday));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsDoctorCall));
                        }
                        if (type.equals(TypeCall.REPORT)) {
                            nameValuePairsReport.add(new BasicNameValuePair("type", "report"));
                            nameValuePairsReport.add(new BasicNameValuePair("score", score));
                            nameValuePairsReport.add(new BasicNameValuePair("level", level));
                            nameValuePairsReport.add(new BasicNameValuePair("id_user", id_user));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsReport));
                        }
                        if (type.equals(TypeCall.UPDATE_MAIL)) {
                            nameValuePairsUpdateString.add(new BasicNameValuePair("type", "update_email"));
                            nameValuePairsUpdateString.add(new BasicNameValuePair("id_user", id_user));
                            nameValuePairsUpdateString.add(new BasicNameValuePair("email", update));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsUpdateString));

                        }
                        if (type.equals(TypeCall.UPDATE_FIRSTNAME)) {
                            nameValuePairsUpdateString.add(new BasicNameValuePair("type", "update_firstname"));
                            nameValuePairsUpdateString.add(new BasicNameValuePair("id_user", id_user));
                            nameValuePairsUpdateString.add(new BasicNameValuePair("firstname", update));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsUpdateString));

                        }
                        if (type.equals(TypeCall.UPDATE_LASTNAME)) {
                            nameValuePairsUpdateString.add(new BasicNameValuePair("type", "update_lastname"));
                            nameValuePairsUpdateString.add(new BasicNameValuePair("id_user", id_user));
                            nameValuePairsUpdateString.add(new BasicNameValuePair("lastname", update));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsUpdateString));

                        }
                        if (type.equals(TypeCall.UPDATE_BIRTHDAY)) {
                            nameValuePairsUpdateString.add(new BasicNameValuePair("type", "update_birthday"));
                            nameValuePairsUpdateString.add(new BasicNameValuePair("id_user", id_user));
                            nameValuePairsUpdateString.add(new BasicNameValuePair("birthday", update));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsUpdateString));
                        }
                        if (type.equals(TypeCall.UPDATE_PASSWORD)) {
                            nameValuePairsUpdateString.add(new BasicNameValuePair("type", "update_password"));
                            nameValuePairsUpdateString.add(new BasicNameValuePair("id_user", id_user));
                            nameValuePairsUpdateString.add(new BasicNameValuePair("password", update));
                            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairsUpdateString));

                        }

                        // Execute HTTP Post Request
                        final ResponseHandler<String> responseHandler = new BasicResponseHandler();

                        response = httpclient.execute(httppost, responseHandler);
                        //This is the response from a php application
                       final String reverseString = response;

                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                             status.setText(reverseString);


                            if (response.contains("password errata")) {
                                logInActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        customAlert("Warning","Please re-enter your password", logInActivity);
                                    }
                                });
                            }
                            else if(response.contains("updateOk")){
                                logInActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        customAlert("update","successfully updated",logInActivity);
                                    }
                                });
                            }
                            else if(response.contains("emailChecked")){
                                logInActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        customAlert("Welcome to 3D4Amb","registration complete",logInActivity);
                                    }
                                });
                            }
                            else if(response.contains("emailNotChecked")){
                                logInActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        customAlert("Warning","this email: "+ email+" doesn't exist",logInActivity);
                                    }
                                });
                            }
                            else if(response.contains("emailAlreadyExist")){
                                logInActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        customAlert("Warning","email address is already in use."+'\n'+"Sign up failed",logInActivity);
                                    }
                                });
                            }
                            else if(response.contains("emailNotFound")){

                                logInActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        customAlert("Warning","wrong email",logInActivity);
                                    }
                                });
                            }
                            else if(response.contains("colorFalse")){

                                logInActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        customAlert("Warning","wrong color",logInActivity);
                                    }
                                });
                            }
                            else if(response.contains("colorTrue")){
                                logInActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        customAlert("Reset password","Your password has been reset successfully!"+'\n' +
                                                "Your new password has been sent to your email address."+'\n'+'\n'+"3D4Amb staff",logInActivity);
                                    }
                                });
                            }

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
                            Toast.makeText(activity, "Network error "+'\n'+"check out your connection ", Toast.LENGTH_LONG).show();
                        }
                    });

                }

            }//end postData()

        });
        thread.start();
    }


    private void customAlert(String title,String message,Activity activity){
        final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }


}


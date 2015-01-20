package it.unibg.p3d4amb.stereoacuityvideo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import it.unibg.p3d4amb.stereoacuityvideo.searchFile.FileSelection;
import it.unibg.p3d4amb.stereoacuityvideo.videoManagement.CreatePlayer;
import it.unibg.p3d4amb.stereoacuityvideo.videoManagement.OurMediaController;
import it.unibg.p3d4amb.stereoacuityvideo.videoManagement.ViewVideo;


public class MainActivity extends Activity{

    private CreatePlayer p1, p2;
    private SurfaceView video1, video2;
    private OurMediaController mediaController;  // Manages the video display--rewind, play, pause, stop, etc.
    private MediaPlayer player, player2;
    private FrameLayout f1;
    private RelativeLayout layout;
    private String SrcPath = "/storage/emulated/0/Movies/ADELE.wmv";
    private SharedPreferences appsetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_video:
                manageAlertDialog();
                break;
            case R.id.button_settings:
                //Si accede al menu delle opzioni del video
                Intent intent2 = new Intent(this, Settings.class);
                startActivity(intent2);
                break;
           }
    }

    private void manageAlertDialog() {
        appsetting = PreferenceManager.getDefaultSharedPreferences(this);
        SrcPath=appsetting.getString(Constant.PATH,"");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select video:"); // Titolo Dialog.
        final TextView inputFileName = new TextView(this); // Testo contenente i risultati
        inputFileName.setTextSize(20);
        inputFileName.setText(SrcPath); // Scritta di default nel
        // Dialog.
        builder.setView(inputFileName); // Visualizza la scritta di default nel
        // Dialog.
        builder.setPositiveButton("Find...",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog,
                            int which) {
                        openFileSelection();

                    }

                });

        builder.setNeutralButton("View",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog,
                            int which) {
                        if (SrcPath.equalsIgnoreCase("")) {
                            Toast.makeText(getApplicationContext(),
                                    "No file selected", Toast.LENGTH_LONG).show();
                        }
                        else {
                            startViewVideo();

                        }

                    }



                });

        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialog,
                            int which) {
                    }
                });

        AlertDialog alertDialog1 = builder.create();
        alertDialog1.show();
    }

    private void startViewVideo() {
        //Si inizia la visione del video
        Intent intent = new Intent(this, ViewVideo.class);
        startActivity(intent);
    }

    private void openFileSelection() {
        Intent intent = new Intent(this, FileSelection.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
      finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

            }
        }

    }
}

package it.unibg.p3d4amb.stereoacuityvideo.videoManagement;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import it.unibg.p3d4amb.stereoacuityvideo.Constant;
import it.unibg.p3d4amb.stereoacuityvideo.R;
import it.unibg.p3d4amb.stereoacuityvideo.Settings;


public class ViewVideo extends Activity implements MediaPlayer.OnPreparedListener, OurMediaController.MediaPlayerControl {

    private CreatePlayer p1, p2;
    private SurfaceView video1, video2;
    private OurMediaController mediaController;  // Manages the video display--rewind, play, pause, stop, etc.
    private MediaPlayer player, player2;
    private FrameLayout f1;
    private RelativeLayout layout;
    private String SrcPath = "/storage/emulated/0/Movies/ADELE.wmv";
    SharedPreferences appsetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Turn off the window's title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_video);
        appsetting = PreferenceManager.getDefaultSharedPreferences(this);
        SrcPath=appsetting.getString(Constant.PATH,"");
        createVideoPlayer();
    }


    private void createVideoPlayer() {
        layout = (RelativeLayout) findViewById(R.id.layoutvideo);
        video1 = new SurfaceView(this);
        layout.addView(video1);
        video2 = new SurfaceView(this);
        layout.addView(video2);

        if (mediaController == null) {
            mediaController = new OurMediaController(this);
        }
        if (player == null) {
            player = new MediaPlayer();
            player2 = new MediaPlayer();
        }

        p1 = new CreatePlayer(video1, player);
        p2 = new CreatePlayer(video2, player2);
        p1.getHolderMy();
        p2.getHolderMy();

        try {
            p1.p.setAudioStreamType(AudioManager.STREAM_MUSIC);
            p1.p.setDataSource(SrcPath);
            p1.p.setOnPreparedListener(this);
            p2.p.setDataSource(SrcPath);
            p2.p.setOnPreparedListener(this);
        } catch (Exception e) {
            finish();
        }
    }


    @Override
    public void onBackPressed() {
        if (p1.p.isPlaying()) {
            p1.p.stop();
            p1.p.release();
            p2.p.stop();
            p2.p.release();
        }
        finish();
    }


    @Override
    public void onDestroy() {
        super.onStop();
          /*  if (p1.p !=null) {
                p1.p.stop();
                p1.p.release();
                p2.p.stop();
                p2.p.release();
                p1.p=null;
                p2.p=null;
            }*/
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mediaController.show();
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent=new Intent(this, Settings.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void start() {
        p1.p.start();
        p2.p.start();
    }


    @Override
    public void pause() {
        p1.p.pause();
        p2.p.pause();
    }


    @Override
    public int getDuration() {
        return p1.p.getDuration();
    }


    @Override
    public int getCurrentPosition() {
        return p1.p.getCurrentPosition();
    }


    @Override
    public void seekTo(int pos) {
        p1.p.seekTo(pos);
        p2.p.seekTo(pos);
    }


    @Override
    public boolean isPlaying() {
        return p1.p.isPlaying();
    }


    @Override
    public int getBufferPercentage() {
        return 0;
    }


    @Override
    public boolean canPause() {
        return false;
    }


    @Override
    public boolean canSeekBackward() {
        return false;
    }


    @Override
    public boolean canSeekForward() {
        return false;
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        resizeRatio();
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(f1);
        p1.p.start();
        p2.p.start();
    }


    private void resizeRatio() {
        int reducew = appsetting.getInt(Constant.PIXELW,0); //ridurre dimensioni se è troppo grande e non si vede all'interno degli occhialini
        int reduceh = appsetting.getInt(Constant.PIXELH,0);
        int videoWidth = player.getVideoWidth();
        int videoHeight = player.getVideoHeight();
        float videoProportion = (float) videoWidth / (float) videoHeight;
        int screenWidth, screenHeight, screenWO, screenHO;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        if (size.x > size.y) {
            screenWO = size.x;
            screenHO = size.y;
        } else {
            screenWO = size.y;
            screenHO = size.x;
        }
        screenHeight = screenHO - reduceh;
        screenWidth = screenWO - reducew;
        float screenProportion = (float) (screenWidth / 2) / (float) (screenHeight);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(screenWO / 2, screenHO);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(screenWO / 2, screenHO);
        if (videoProportion > screenProportion) {
            params1.width = screenWidth / 2;
            params2.width = screenWidth / 2;
            params1.height = (int) ((float) (screenWidth / 2) / videoProportion);
            params2.height = (int) ((float) (screenWidth / 2) / videoProportion);
            params1.topMargin = (screenHO - params1.height) / 2;
            params2.topMargin = (screenHO - params2.height) / 2;
            params1.leftMargin = screenWO / 4 - (params1.width / 2);
            params2.leftMargin = 3 * screenWO / 4 - params1.width / 2;
        } else {
            params1.width = (int) (videoProportion * (float) screenHeight);
            params2.width = (int) (videoProportion * (float) screenHeight);
            params1.height = screenHeight;
            params2.height = screenHeight;
            params1.topMargin = (screenHO - params1.height) / 2;
            params2.topMargin = (screenHO - params2.height) / 2;
            params1.leftMargin = screenWO / 4 - (params1.width / 2);
            params2.leftMargin = 3 * screenWO / 4 - params1.width / 2;
        }
        video1.setLayoutParams(params1);
        video2.setLayoutParams(params2);

        f1 = new FrameLayout(this);
        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        f1.setLayoutParams(params3);
        layout.addView(f1);
    }
}

package net.qwuke.unblyopia;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends Activity {

    // Views
    private TetrisView mTetrisView;

      /******   OVERRIDDEN ACTIVITY METHODS   ******/

    @Override
    public void onResume() {
        super.onResume();
        mTetrisView = new TetrisView(this);
        mTetrisView.setBackgroundColor(Color.BLACK);
        setContentView(mTetrisView);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    /******   INTERFACING: Methods for triggers, buttons, sensors   ******/

    /**
     * Handles the user touching the screen
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        mTetrisView.tm.actionButton();
        return true;
    }

    /**
     * Handles the user pressing the volume buttons
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();

        if(action == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_VOLUME_UP:
                    mTetrisView.tm.keyPressed(TetrisModel.Input.LEFT);
                    return true;
                case KeyEvent.KEYCODE_VOLUME_DOWN:
                    mTetrisView.tm.keyPressed(TetrisModel.Input.RIGHT);
                    return true;
                case KeyEvent.KEYCODE_HEADSETHOOK:
                    mTetrisView.tm.keyPressed(TetrisModel.Input.UP);
                    return true;
                default:
                    return super.dispatchKeyEvent(event);
            }
        }

        return super.dispatchKeyEvent(event);
    }

    /**
     * This method prevents the volume buttons from making a noise
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP) || (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    /******   UNCHANGED INTERFACE METHODS   ******/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onStart() { super.onStart(); }
    public void onStop() { super.onStop(); }
    public void onDestroy() { super.onDestroy(); }
    protected void onCreate(Bundle savedInstanceState) {
        // Turn off the window's title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        // Fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


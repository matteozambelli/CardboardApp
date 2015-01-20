package it.unibg.p3d4amb.stereoacuityvideo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;


public class Settings extends Activity {

    private int reductionW, reductionH;
    private EditText reductionW_txt,reductionH_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SharedPreferences appsetting = PreferenceManager.getDefaultSharedPreferences(this);
        reductionH_txt=(EditText)findViewById(R.id.editText_ReductionH);
        reductionW_txt=(EditText)findViewById(R.id.editText_ReductionW);
        reductionH=appsetting.getInt(Constant.PIXELH,0);
        reductionW=appsetting.getInt(Constant.PIXELW,0);
        reductionH_txt.setText(Integer.toString(reductionH));
        reductionW_txt.setText(Integer.toString(reductionW));
    }

    private void savePreferences(String key, int value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    @Override
    public void onBackPressed() {
        if(!(reductionH_txt.getText().toString().equalsIgnoreCase("")))
            reductionH = Integer.parseInt(reductionH_txt.getText().toString());
        if(!(reductionW_txt.getText().toString().equalsIgnoreCase("")))
            reductionW = Integer.parseInt(reductionW_txt.getText().toString());
        savePreferences(Constant.PIXELW,reductionW);
        savePreferences(Constant.PIXELH,reductionH);
        super.onBackPressed();
    }



}

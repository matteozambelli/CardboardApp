package it.unibg.p3d4amb.stereoacuitytest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.Toast;


public class Options extends Activity {


    private float dimpoints; // dimensione dei punti visualizzati,
    private float denpoints; // densità dei punti visualizzati
    private int offsetshape; // pixel offset
    private EditText density_txt, dimension_txt, offset_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        SharedPreferences appsetting = PreferenceManager.getDefaultSharedPreferences(this);
        dimension_txt = (EditText) findViewById(R.id.editText_PointDimension);
        density_txt = (EditText) findViewById(R.id.editText_PointDensity);
        offset_txt = (EditText) findViewById(R.id.editText_Offset);
        dimpoints = appsetting.getFloat(Constant.DIMPREF, 1.0f);
        denpoints = appsetting.getFloat(Constant.DENPREF, 0.8f);
        offsetshape = appsetting.getInt(Constant.OFFSETSHAPE, 4);
        density_txt.setText(Float.toString(denpoints));
        dimension_txt.setText(Float.toString(dimpoints));
        offset_txt.setText(Integer.toString(offsetshape));
        Toast.makeText(getApplicationContext(),
                "Press back button to save", Toast.LENGTH_LONG).show();

    }

    // Salva valori in SharePreferences
    private void savePreferences(String key, float value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sp.edit();
        edit.putFloat(key, value);
        edit.commit();
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
        float maxDim = Constant.MAXDIMPOINTS;
        float maxDen = Constant.MAXDENPOINTS;
        if(!(density_txt.getText().toString().equalsIgnoreCase("")))
        denpoints = Float.parseFloat(density_txt.getText().toString());
        if(!(dimension_txt.getText().toString().equalsIgnoreCase("")))
        dimpoints = Float.parseFloat(dimension_txt.getText().toString());
        if(!(offset_txt.getText().toString().equalsIgnoreCase("")))
          offsetshape = Integer.parseInt(offset_txt.getText().toString());
        if (dimpoints < 0f)
            dimpoints = 0f;
        else if (dimpoints > maxDim)
            dimpoints = maxDim;
        if (denpoints < 0f)
            denpoints = 0f;
        else if (denpoints > maxDen)
            denpoints = maxDen;
        if (offsetshape < 0)
            offsetshape = 0;
        savePreferences(Constant.DENPREF, denpoints);
        savePreferences(Constant.DIMPREF, dimpoints);
        savePreferences(Constant.OFFSETSHAPE, offsetshape);
        super.onBackPressed();
    }

}

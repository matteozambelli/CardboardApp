package it.unibg.p3d4amb.stereoacuitytest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.Toast;


public class ShapeSetting extends Activity {

    private EditText radius_txt, heightRect_txt, baseRect_txt, sideSquare_txt, heightTri_txt, baseTri_txt;
    private int radius, heightRect, baseRect, sideSquare, heightTri, baseTri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_setting);

        SharedPreferences appsetting = PreferenceManager.getDefaultSharedPreferences(this);
        radius_txt = (EditText) findViewById(R.id.editTextRadius);
        heightRect_txt = (EditText) findViewById(R.id.editTextHeightRect);
        baseRect_txt = (EditText) findViewById(R.id.editTextBaseRect);
        heightTri_txt = (EditText) findViewById(R.id.editTextHeightTri);
        baseTri_txt = (EditText) findViewById(R.id.editTextBaseTri);
        sideSquare_txt = (EditText) findViewById(R.id.editTextSideSquare);
        radius = appsetting.getInt(Constant.RADIUS, 50);
        heightRect = appsetting.getInt(Constant.HEIGHTRECT, 50);
        baseRect = appsetting.getInt(Constant.BASERECT, 100);
        sideSquare = appsetting.getInt(Constant.SIDESQUARE, 100);
        heightTri = appsetting.getInt(Constant.BASETRI, 100);
        baseTri = appsetting.getInt(Constant.HEIGHTTRI, 100);

        radius_txt.setText(Integer.toString(radius));
        heightRect_txt.setText(Integer.toString(heightRect));
        baseRect_txt.setText(Integer.toString(baseRect));
        heightTri_txt.setText(Integer.toString(heightTri));
        baseTri_txt.setText(Integer.toString(baseTri));
        sideSquare_txt.setText(Integer.toString(sideSquare));

        Toast.makeText(getApplicationContext(),
                "Press back button to save", Toast.LENGTH_LONG).show();
    }



    // Salva valori in SharePreferences
    private void savePreferences(String key, int value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();
    }


    /*Save all when press back button.
     Se la casella di testo è vuota utilizza il valore precedentemente salvato
     */
    @Override
    public void onBackPressed() {
        if(!(radius_txt.getText().toString().equalsIgnoreCase("")))
        radius =Integer.parseInt(radius_txt.getText().toString());
        savePreferences(Constant.RADIUS, radius);
        if(!(heightRect_txt.getText().toString().equalsIgnoreCase("")))
        heightRect =Integer.parseInt(heightRect_txt.getText().toString());
        savePreferences(Constant.HEIGHTRECT, heightRect);
        if(!(baseRect_txt.getText().toString().equalsIgnoreCase("")))
        baseRect =Integer.parseInt(baseRect_txt.getText().toString());
        savePreferences(Constant.BASERECT, baseRect);
        if(!(sideSquare_txt.getText().toString().equalsIgnoreCase("")))
        sideSquare =Integer.parseInt(sideSquare_txt.getText().toString());
        savePreferences(Constant.SIDESQUARE, sideSquare);
        if(!(heightTri_txt.getText().toString().equalsIgnoreCase("")))
        heightTri =Integer.parseInt(heightTri_txt.getText().toString());
        savePreferences(Constant.HEIGHTTRI, heightTri);
        if(!(baseTri_txt.getText().toString().equalsIgnoreCase("")))
        baseTri =Integer.parseInt(baseTri_txt.getText().toString());
        savePreferences(Constant.BASETRI, baseTri);
        super.onBackPressed();
    }
}

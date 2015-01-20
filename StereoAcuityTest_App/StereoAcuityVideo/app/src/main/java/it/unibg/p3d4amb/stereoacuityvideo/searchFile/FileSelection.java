package it.unibg.p3d4amb.stereoacuityvideo.searchFile;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibg.p3d4amb.stereoacuityvideo.Constant;
import it.unibg.p3d4amb.stereoacuityvideo.R;
import it.unibg.p3d4amb.stereoacuityvideo.videoManagement.ViewVideo;

// This activity manages the FileSelection user screen.

public class FileSelection extends ListActivity {

    private File currentDir;
    private FileArrayAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        currentDir = new File("/sdcard/");
        fill(currentDir);
    }

    private void fill(File f) {
        File[] dirs = f.listFiles();

        // Set the title on the action bar of the FileSelection activity.

        this.setTitle("Current Dir: " + f.getName());
        List<Item> dir = new ArrayList<Item>();
        List<Item> fls = new ArrayList<Item>();
        try {
            for (File ff : dirs) {
                if (ff.isDirectory()) {
                    File[] fbuf = ff.listFiles();
                    int buf = 0;
                    if (fbuf != null) {
                        buf = fbuf.length;
                    } else
                        buf = 0;
                    String num_item = String.valueOf(buf);
                    if (buf == 0)
                        num_item = num_item + " item";
                    else
                        num_item = num_item + " items";

                    dir.add(new Item(ff.getName(), ff.getAbsolutePath(),
                            "directory_icon"));
                } else {
                    String nameext = ff.getName();
                    fls.add(new Item(nameext,
                            ff.getAbsolutePath(), "file_icon"));

                }
            }
        } catch (Exception e) {

        }
        Collections.sort(dir);
        Collections.sort(fls);
        dir.addAll(fls);
        if (!f.getName().equalsIgnoreCase("/sdcard/"))
            dir.add(0, new Item("..", f.getParent(), "directory_up"));
        adapter = new FileArrayAdapter(FileSelection.this,
                R.layout.activity_file_selection, dir);
        this.setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Item o = adapter.getItem(position);
        if (o.getImage().equalsIgnoreCase("directory_icon")
                || o.getImage().equalsIgnoreCase("directory_up")) {

            // Handle the case where the clicked image is not a file:  it's either a directory
            // icon (in which case go into the directory) or it's an arrow indicating to go into
            // the parent directory (in which case, go into that directory).

            currentDir = new File(o.getPath());
            fill(currentDir);
        } else {

            // User clicked on a file name.  Build dialog box to see whether he/she wants to delete the file,
            // send it, or review it.
            savePreferences(Constant.PATH, o.getPath());
            startViewVideo();


        }
    }

    private void startViewVideo() {
        //Si inizia la visione del video
        Intent intent = new Intent(this, ViewVideo.class);
        startActivity(intent);
        finish();
    }

    private void savePreferences(String key, String value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }
}

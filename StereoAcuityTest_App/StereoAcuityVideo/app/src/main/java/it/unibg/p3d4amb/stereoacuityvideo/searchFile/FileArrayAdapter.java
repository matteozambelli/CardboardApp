package it.unibg.p3d4amb.stereoacuityvideo.searchFile;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.unibg.p3d4amb.stereoacuityvideo.R;

public class FileArrayAdapter extends ArrayAdapter<Item> {

	private Context c;
	private int id;
	private List<Item> items;

	public FileArrayAdapter(Context context, int textViewResourceId,
			List<Item> objects) {
		super(context, textViewResourceId, objects);
		c = context;
		id = textViewResourceId;
		items = objects;
	}

	public Item getItem(int i) {
		return items.get(i);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(id, null);
		}

		final Item o = items.get(position);
		if (o != null) {
			TextView t1 = (TextView) v.findViewById(R.id.textName);
			/* Take the ImageView from layout and set the image */
			ImageView imageCity = (ImageView) v.findViewById(R.id.fd_Icon1);
			String uri = "drawable/" + o.getImage();
			int imageResource = c.getResources().getIdentifier(uri, null,
					c.getPackageName());
			Drawable image = c.getResources().getDrawable(imageResource);
			imageCity.setImageDrawable(image);
			if (t1 != null)
				t1.setText(o.getName());
		}
		return v;
	}

}

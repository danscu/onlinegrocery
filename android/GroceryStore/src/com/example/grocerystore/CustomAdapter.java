package com.example.grocerystore;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grocerystore.R;

public class CustomAdapter extends ArrayAdapter<String> {
	Context context;
	List<String> labels;
	List<String> thumbnails;
	URL imgurl = null;
	Bitmap bitmap;
	
	public CustomAdapter(Context context, int resource,
			List<String> label, List<String> thumbnails) {
		super(context, resource, R.layout.activity_adapter, label);			
		this.context = context;
		this.labels = label;
		this.thumbnails = thumbnails;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
		View v = layoutInflater.inflate(R.layout.activity_adapter, null);
		
		TextView label = (TextView)v.findViewById(R.id.label);
		ImageView thumbnail = (ImageView)v.findViewById(R.id.image);
		
		label.setText(labels.get(position));
		
		try {
			imgurl = new URL(thumbnails.get(position));
		} catch (MalformedURLException e) {
			// TODO handle this by using a default image
			return v;
		} 
		try {
			bitmap = BitmapFactory.decodeStream(imgurl.openConnection() .getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return v;
		} 
		thumbnail.setImageBitmap(bitmap);

		return v;
	}
}

package com.example.grocerystore;

import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {
	Context context;
	List<String> labels;
	List<String> thumbnails;
	List<Double> priceList;
	URL imgurl = null;
	Bitmap bitmap;
	
	public CustomAdapter(Context context, int resource,
			List<String> label, List<String> thumbnails, List<Double> priceList) {
		super(context, resource, R.layout.activity_adapter, label);			
		this.context = context;
		this.labels = label;
		this.thumbnails = thumbnails;
		this.priceList = priceList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
		View v = layoutInflater.inflate(R.layout.activity_adapter, null);
		
		TextView label = (TextView)v.findViewById(R.id.label);
		TextView price = (TextView)v.findViewById(R.id.price);
		ImageView thumbnail = (ImageView)v.findViewById(R.id.image);

		label.setText(labels.get(position));
		if (priceList != null) {
			if (priceList.size() > position && priceList.get(position) != null)
				price.setText("$" + priceList.get(position));
			else
				price.setText("N/A");
		}

		BitmapWorkerTask task = new BitmapWorkerTask(thumbnail);
	    task.execute(thumbnails.get(position));

		return v;
	}
}

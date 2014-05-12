package com.example.grocerystore;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import edu.scu.ogstest.APITask;
import edu.scu.ogstest.APITask.APIErrorCode;
import edu.scu.ogstest.APITask.APIResult;
import edu.scu.ogstest.APITask.Callback;
import edu.scu.ogstest.InventoryDetail;

public class BrowseActivity extends MenuActivity implements Callback, OnItemClickListener {
	ListView lv;
	CustomAdapter adapter;
	List<String> labels;
	List<String> thumbnails;
	List<Double> priceList;
	List<Float> ratings;
	
	protected List<InventoryDetail> products;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse);
		lv = (ListView)findViewById(R.id.listView);
		lv.setOnItemClickListener(this);
		
		labels = new ArrayList<String>();
		thumbnails = new ArrayList<String>();
		priceList = new ArrayList<Double>();
		ratings = new ArrayList<Float>();

		listProducts();
	}

	private void listProducts() {
		APITask api = new APITask(APITask.WhatToAccess.INVENTORY_DETAIL, this);
		api.listData();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse, menu);
		return true;
	}
	
	protected void updateProducts() {
		labels.clear();
		thumbnails.clear();
		priceList.clear();
		for (InventoryDetail inv : products) {
		     labels.add(inv.itemName);
		     thumbnails.add(inv.imageSrc);

		     Double price = null;
		     try {
		    	 price = Double.parseDouble(inv.itemPricePerUnit);
		     } catch (NumberFormatException e) { }

		     priceList.add(price);
		}
		
		// hardcoded ratings ArrayList for testing, will need to pull ratings from DB
		ratings.add((float) 3.5); 
		ratings.add((float) 2.0);
		ratings.add((float) 4.0);
		ratings.add((float) 3.5);
		
		adapter = new CustomAdapter(this, R.layout.activity_adapter, labels, thumbnails, priceList, ratings);
		lv.setAdapter(adapter);
	}

	public void onResultCallback(APITask apiTask, APIResult result) {
		if (result.error != APIErrorCode.OK)
			return;
	
		switch (result.op) {
		case LIST:
			products = (List<InventoryDetail>) result.dataList;
			updateProducts();
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		//System.out.println(pos + " " + id);
		Intent intent = new Intent(BrowseActivity.this, ProductDetails.class);
		intent.putExtra("pos", pos);
		intent.putExtra("label", labels.get(pos));
		intent.putExtra("image", thumbnails.get(pos));
		intent.putExtra("price", priceList.get(pos));
		startActivity(intent);
	}
}

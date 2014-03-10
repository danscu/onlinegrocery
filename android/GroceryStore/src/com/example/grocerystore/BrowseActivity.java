package com.example.grocerystore;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.grocerystore.R;

import edu.scu.ogstest.APITask;
import edu.scu.ogstest.APITask.APIErrorCode;
import edu.scu.ogstest.APITask.APIResult;
import edu.scu.ogstest.APITask.Callback;
import edu.scu.ogstest.InventoryDetail;

public class BrowseActivity extends Activity implements Callback, OnItemClickListener {
	ListView lv;
	CustomAdapter adapter;
	List<String> labels;
	List<String> thumbnails;
	
	protected List<InventoryDetail> products;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse);
		lv = (ListView)findViewById(R.id.listView);
		lv.setOnItemClickListener(this);
		
		labels = new ArrayList<String>();
		thumbnails = new ArrayList<String>();
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
		for (InventoryDetail inv : products) {
		     labels.add(inv.itemName);
		     thumbnails.add(inv.imageSrc);
		     /*
		     double price = 0;
		     try {
		    	 price = Double.parseDouble(inv.itemPricePerUnit);
		     } catch (NumberFormatException e) { }
		     System.out.println(price);
		     */
		}
		
		adapter = new CustomAdapter(this, R.layout.activity_adapter, labels, thumbnails);
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
		System.out.println(pos + " " + id);
	}
}

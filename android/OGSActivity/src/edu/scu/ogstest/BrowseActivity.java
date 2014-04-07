package edu.scu.ogstest;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.example.grocerystore.R;

import edu.scu.ogstest.APITask.APIResult;

public class BrowseActivity extends Activity {
	ListView lv;
	CustomAdapter adapter;
	List<String> labels;
	List<String> thumbnails;
	List<String> priceList;
	
	protected List<InventoryDetail> products;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse);
		
		ListProducts(); 

		lv = (ListView)findViewById(R.id.listView);
		adapter = new CustomAdapter(this, R.layout.activity_adapter, labels, thumbnails, priceList);
		lv.setAdapter(adapter);
		
		
		
	}
	
	private void ListProducts() {
		APITask api = new APITask(APITask.WhatToAccess.INVENTORY_DETAIL, this);
		api.listData();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse, menu);
		return true;
	}

	public void onResultCallback(APITask apiTask, APIResult result) {
		// TODO Auto-generated method stub
		
	}

}

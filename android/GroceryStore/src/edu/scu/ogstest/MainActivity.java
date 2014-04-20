package edu.scu.ogstest;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener, APITask.Callback {
	private Button btnCreateShopper;
	private Button btnListShopper;
	private Button btnGetShopper;
	private TextView textViewResponse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ogstest);

		// This is a new comment
		textViewResponse = (TextView) findViewById(R.id.textViewResponse);
		
		btnCreateShopper = (Button) findViewById(R.id.button_new_shopper);
		btnCreateShopper.setOnClickListener(this);
		
		btnListShopper = (Button) findViewById(R.id.button_list_shoppers);
		btnListShopper.setOnClickListener(this);
		
		btnGetShopper = (Button) findViewById(R.id.button_get_shopper);
		btnGetShopper.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View from) {
		switch (from.getId()) {
		case R.id.button_new_shopper:
			testCreateShopper();
			break;
		case R.id.button_list_shoppers:
			testListshoppers();
			break;
		case R.id.button_get_shopper:
			testGetShopper();
			break;
		}
	}

	protected void testCreateShopper() {
		ShopperAccount shopper_account = new ShopperAccount();
		shopper_account.userName = "test_username_" + (new Random()).nextInt(100);
		shopper_account.password = "1234";
		shopper_account.firstName = "Tom";
		shopper_account.lastName = "LastNameOfTom";
		shopper_account.streetAddress = "n/a";
		shopper_account.city = "n/a";
		shopper_account.state = "n/a";
		shopper_account.zipcode = "n/a";
		shopper_account.phone = "n/a";
		shopper_account.email = "n/a";
		
		APITask api = new APITask(APITask.WhatToAccess.SHOPPER_ACCOUNT, this);
		api.create(shopper_account);
	}
	
	protected void testListshoppers() {
		APITask api = new APITask(APITask.WhatToAccess.SHOPPER_ACCOUNT, this);
		api.listData();
	}

	protected void testGetShopper() {
		APITask api = new APITask(APITask.WhatToAccess.SHOPPER_ACCOUNT, this);
		api.read(23);
	}
	
	/* We get API results here */
	@Override
	public void onResultCallback(APITask from, APITask.APIResult result) {
		if (result.error != APITask.APIErrorCode.OK) {
			textViewResponse.setText(result.status);
			return;
		}

		String strDisplay = result.content; // json string
		switch (result.op) {
		case LIST:
			// use toString() from reconstructed objects
			StringBuilder sb = new StringBuilder();
			for (APIData data : result.dataList) {
				sb.append(data.toString());
				sb.append("\n");
			}
			strDisplay = "List of objects: \n" + sb.toString();
			break;
		case READ:
			strDisplay = "Object.toString() = " + result.data.toString();
			break;
		}

		textViewResponse.setText(strDisplay);
	}
}

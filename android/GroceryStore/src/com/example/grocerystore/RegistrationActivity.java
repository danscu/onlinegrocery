package com.example.grocerystore;

import edu.scu.ogstest.APITask;
import edu.scu.ogstest.APITask.APIResult;
import edu.scu.ogstest.APITask.Callback;
import edu.scu.ogstest.ShopperAccount;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends Activity implements Callback {

	protected EditText firstName;
	protected EditText lasttName;
	protected EditText userName;
	protected EditText password;
	protected EditText streetAddress;
	protected EditText city;
	protected EditText state;
	protected EditText zipCode;
	protected Button submitButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		firstName = (EditText) findViewById(R.id.FirstName);
		lasttName=(EditText)findViewById(R.id.LastName);
		userName=(EditText)findViewById(R.id.Username);
		password=(EditText)findViewById(R.id.Password);
		streetAddress=(EditText)findViewById(R.id.Address);
		city=(EditText)findViewById(R.id.City);
		state=(EditText)findViewById(R.id.State);
		zipCode=(EditText)findViewById(R.id.Zipcode);
		submitButton=(Button)findViewById(R.id.button_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void addShoppers(ShopperAccount sa) {
		APITask api = new APITask(APITask.WhatToAccess.SHOPPER_ACCOUNT, this);
		api.create(sa);
	}
	//to map in the submit button
	public void onClickLogin(View view) throws InterruptedException {
		if (firstName.equals("")||lasttName==null||userName==null||password==null||streetAddress==null||city==null
				|| state==null|| zipCode==null|| submitButton==null) {
			
			//reportError("Try again...");
			Toast toast = Toast.makeText(getBaseContext(), "Error in input", Toast.LENGTH_LONG);
			toast.show();
			return;
		}
		ShopperAccount sa = new ShopperAccount();
		
		sa.firstName = firstName.getText().toString();
		sa.lastName = lasttName.getText().toString();
		sa.userName = userName.getText().toString();
		sa.password = password.getText().toString();
		sa.streetAddress = streetAddress.getText().toString();
		sa.city = city.getText().toString();
		sa.state = state.getText().toString();
		sa.zipcode = zipCode.getText().toString();
		
		addShoppers(sa);
		
		// failed to login
		//reportError("Username/password error");
	}

	@Override
	public void onResultCallback(APITask from, APIResult result) {
		// TODO Auto-generated method stub
		if (result.error != APITask.APIErrorCode.OK) {
			System.out.println("***** REGISTRATION ERROR *****");
			Toast toast = Toast.makeText(getBaseContext(), "Error in input", Toast.LENGTH_LONG);
			toast.show();
		} else {
			Toast toast = Toast.makeText(getBaseContext(), "Success", Toast.LENGTH_LONG);
			toast.show();
		}
	}

}

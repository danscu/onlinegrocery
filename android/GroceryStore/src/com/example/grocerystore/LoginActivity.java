package com.example.grocerystore;

import java.util.List;

import edu.scu.ogstest.APIData;
import edu.scu.ogstest.APITask;
import edu.scu.ogstest.APITask.APIResult;
import edu.scu.ogstest.APITask.Callback;
import edu.scu.ogstest.ShopperAccount;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements Callback {

	protected EditText textUsername;
	protected EditText textPassword;
	protected TextView textError;
	
	// Temporary user authentication
	private List<ShopperAccount> users;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		textUsername = (EditText) findViewById(R.id.Username);
		textPassword = (EditText) findViewById(R.id.Password);
		textError = (TextView) findViewById(R.id.textViewError);
		
		listShoppers();
	}
	
	public void onClickLogin(View view) throws InterruptedException {
		if (users == null) {
			reportError("Try again...");
			return;
		}
		
		String username = textUsername.getText().toString();
		String password = textPassword.getText().toString();
		for (ShopperAccount acc : users) {
			if (acc.userName.equals(username) && acc.password.equals(password)) {
				// take to login
				reportError("Logging in...");
				
				// transit to browsing page
				Intent intent = new Intent(this, BrowseActivity.class);
				startActivity(intent);
				return;
			}
		}
		
		// failed to login
		reportError("Username/password error");
	}
	
	public void onClickSignup(View view) {
		Intent intent = new Intent(this, RegistrationActivity.class);
		startActivity(intent);
	}
	
	protected void listShoppers() {
		APITask api = new APITask(APITask.WhatToAccess.SHOPPER_ACCOUNT, this);
		api.listData();
	}

	public void reportError(String str) {
		textError.setText(str);
	}
	
	@Override
	public void onResultCallback(APITask from, APIResult result) {
		if (result.error != APITask.APIErrorCode.OK) {
			textError.setText(result.status);
			return;
		}

		switch (result.op) {
		case LIST:
			users = (List<ShopperAccount>) result.dataList;
			textError.setText("Ready");
			break;
		case READ:
			break;
		}
	}
}

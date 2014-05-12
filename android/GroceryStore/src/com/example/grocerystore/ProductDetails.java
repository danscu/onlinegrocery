package com.example.grocerystore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ProductDetails extends MenuActivity {
	String label;
	String thumbnail;
	double price;
	int pos;
	protected static final double TAX = 0.08625;
	double shipping;
	double total;
	int quantity;
	
	ImageView iv;
	TextView tvBrad;
	TextView tvPrice;
	TextView tvDesc;
	EditText etQuantity;
	TextView tvTax;
	TextView tvShipping;
	TextView tvTotal;
	RadioGroup rg;
	RadioButton rb;
	Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productdetails);
		
		etQuantity = (EditText)findViewById(R.id.quantity);
		etQuantity.setHint("quantity");
		bt = (Button)findViewById(R.id.button1);
		
		Intent intent = getIntent();
		label = intent.getStringExtra("labels");
		thumbnail = intent.getStringExtra("thumbnails");
		price = intent.getDoubleExtra("price", 0);

		tvPrice = (TextView) findViewById(R.id.label_price);
		tvPrice.setText("$" + price);
		
		rg = (RadioGroup)findViewById(R.id.radioGroup1);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				rb = (RadioButton)findViewById(checkedId);
				if(rb.isChecked()){
					switch(checkedId){
					case R.id.radio0:
						shipping = 10.00;
						break;
					case R.id.radio1:
						shipping = 0.00;
						break;
					default: break;
					}
				}
				
			}
		
		});
		
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				quantity = Integer.parseInt(etQuantity.getText().toString());
				total = price * (quantity + TAX) + shipping;
				tvTotal.setText(Double.toString(total));
			}
			
			
		});
		
	}

}

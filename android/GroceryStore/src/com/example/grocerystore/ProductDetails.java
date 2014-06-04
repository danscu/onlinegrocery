package com.example.grocerystore;

import edu.scu.ogstest.Cart;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetails extends MenuActivity {
	String label;
	String thumbnail;
	double price;
	int pos;
	protected static final double TAX = 0.08625;
	double shipping;
	double total;
	int quantity;
	int id;
	
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
		id = intent.getIntExtra("id", -1);
		label = intent.getStringExtra("label");
		thumbnail = intent.getStringExtra("image");
		price = intent.getDoubleExtra("price", 0);
		
		tvBrad = (TextView) findViewById(R.id.label_brand);
		tvBrad.setText(label);
		
		tvPrice = (TextView) findViewById(R.id.label_price);
		tvPrice.setText("$" + price);
		
		tvTotal = (TextView) findViewById(R.id.total);
		etQuantity.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	try {
	        	quantity = Integer.parseInt(etQuantity.getText().toString());
	        	} catch (Exception e) {
	        		quantity = 0;
	        	}
	        	total = price * quantity * (1 + TAX) + shipping;
				total = Math.floor(total * 100 + 0.5) / 100;
				tvTotal.setText(Double.toString(total));
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
		
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
				Cart.getInstance().addToCart(id, label, quantity, thumbnail, price);
				Toast.makeText(ProductDetails.this, "Added to cart", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(ProductDetails.this, BrowseActivity.class);
				startActivity(intent);
			}
			
		});
		
		iv = (ImageView) findViewById(R.id.imageView1);
		BitmapWorkerTask task = new BitmapWorkerTask(iv);
        task.execute(thumbnail);
	}

}

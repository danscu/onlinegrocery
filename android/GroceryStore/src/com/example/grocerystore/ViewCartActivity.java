package com.example.grocerystore;

import edu.scu.ogstest.Cart;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ViewCartActivity extends Activity {
    ListView lv;
    CartAdapter adapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_cart);
		lv = (ListView)findViewById(R.id.listView);
		
		adapter = new CartAdapter(this, R.layout.activity_adapter, Cart.getInstance().getAllItems());
        lv.setAdapter(adapter);
	}
}

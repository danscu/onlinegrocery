package com.example.grocerystore;

import edu.scu.ogstest.Cart;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ThankYouActivity extends MenuActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment_done);
		double total = Cart.getInstance().getTotal();
		TextView tv = (TextView)findViewById(R.id.textViewPay);
		tv.setText("Your payment $" + total + " has been processed!");
		Cart.getInstance().clear();
	}
	
	public void onOk(View view) {
		Intent intent = new Intent(this, BrowseActivity.class);
		startActivity(intent);
	}
}

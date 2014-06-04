package com.example.grocerystore;

import edu.scu.ogstest.Cart;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CheckoutActivity extends MenuActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment);
	}
	
	public void onCheckout(View view) {
		Intent intent = new Intent(this, ThankYouActivity.class);
		startActivity(intent);
	}
}

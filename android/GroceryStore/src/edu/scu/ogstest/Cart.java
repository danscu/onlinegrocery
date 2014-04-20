package edu.scu.ogstest;

import java.util.List;

public class Cart {
	class CartItem {
		int itemId;
		int quantity;
		String imageUrl;
		double unitPrice;
	}
	private static Cart mInstance;
	public Cart getInstance() {
		if (mInstance == null)
			mInstance = new Cart();
		return mInstance;
	}

	public boolean addToCart(int itemId, int quantity) {
		return true;
	}

	public boolean editCart(int itemId, int quantity) {
		return true;
	}

	public boolean removeItem(int itemId) {
		return true;
	}

	public List<CartItem> getAllItems() {
		return null;
	}
	
	public void clear() {
	}
}
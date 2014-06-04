package edu.scu.ogstest;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	public class CartItem {
		public int itemId;
		public int quantity;
		public String label;
		public String imageUrl;
		public double unitPrice;
	}

	private static Cart mInstance;
	protected List<CartItem> mCart;

	private Cart() {
		mCart = new ArrayList<CartItem>();
	}

	// Get the singleton instance
	public static Cart getInstance() {
		if (mInstance == null)
			mInstance = new Cart();
		return mInstance;
	}

	// Find an item in the cart (helper function)
	private CartItem getItem(int itemId, boolean canCreate) {
		CartItem found = null;
		for (CartItem ci : mCart) {
			if (ci.itemId == itemId) {
				found = ci;
				break;
			}
		}
		if (found == null && canCreate) {
			found = new CartItem();
			found.itemId = itemId;
			mCart.add(found);
		}
	
		return found;
	}

	// Add an item to cart
	public boolean addToCart(int itemId, String label, int quantity,
			                 String imageUrl, double unitPrice) {
		CartItem ci = getItem(itemId, true);
		ci.label = label;
		ci.itemId = itemId;
		ci.quantity = quantity;
		ci.imageUrl = imageUrl;
		ci.unitPrice = unitPrice;
		return true;
	}

	// Edit the quantity of an item
	public boolean editCart(int itemId, int quantity) {
		CartItem ci = getItem(itemId, false);
		if (ci == null)
			return false;
		ci.itemId = quantity;
		return true;
	}

	// Remove an item
	public boolean removeItem(int itemId) {
		CartItem ci = getItem(itemId, false);
		if (ci == null)
			return false;
		mCart.remove(ci);
		return true;
	}

	// Get all items
	public List<CartItem> getAllItems() {
		return mCart;
	}

	// Clear cart
	public void clear() {
		mCart.clear();
	}
	
	public double getTotal() {
		double sum = 0d;
		for (CartItem ci : getAllItems())
			sum += ci.unitPrice * ci.quantity;
		return sum;
	}
}
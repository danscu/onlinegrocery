package com.example.grocerystore;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import edu.scu.ogstest.Cart.CartItem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class CartAdapter extends ArrayAdapter<CartItem> {
    Context context;
    List<CartItem> cartItems;
    URL imgurl = null;
    Bitmap bitmap;

    public CartAdapter(Context context, int resource, List<CartItem> cartItems) {
        super(context, resource, R.layout.activity_adapter, cartItems);			
        this.context = context;    
        this.cartItems = cartItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.cart_adapter, null);

        TextView label = (TextView)v.findViewById(R.id.label);
        TextView price = (TextView)v.findViewById(R.id.price);
        TextView qty = (TextView)v.findViewById(R.id.qty);
        ImageView thumbnail = (ImageView)v.findViewById(R.id.image);
//        RatingBar ratingBar= (RatingBar)v.findViewById(R.id.ratingBar);

        CartItem ci = cartItems.get(position);
        
        label.setText(ci.label);
        price.setText("$" + ci.unitPrice);
        qty.setText("$" + ci.quantity);

//        ratingBar.setRating(ratings.get(position));

        BitmapWorkerTask task = new BitmapWorkerTask(thumbnail);
        task.execute(ci.imageUrl);

        return v;
    }
}

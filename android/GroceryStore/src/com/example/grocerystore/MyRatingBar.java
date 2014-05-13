package com.example.grocerystore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RatingBar;

public class MyRatingBar extends RatingBar {

	public MyRatingBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyRatingBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyRatingBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    super.onTouchEvent(event);
	    return false;
	}
}

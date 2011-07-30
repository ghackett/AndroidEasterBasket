package com.android.easter.backet;

import java.util.ArrayList;
import java.util.LinkedList;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class EasterEggTracker {
	
	private int mMaxCodeLength = 0;
	private LinkedList<EasterEggEvent> mRecentEvents;
	private ArrayList<EasterEggCode> mValidCodes;
	
	
	
	public EasterEggTracker(int maxCodeLength) {
		mMaxCodeLength = maxCodeLength;
		mRecentEvents = new LinkedList<EasterEggEvent>();
		mValidCodes = new ArrayList<EasterEggCode>();
	}

	public void registerCode(EasterEggCode code) {
		if (!mValidCodes.contains(code))
			mValidCodes.add(code);
	}
	
	public void onEvent(EasterEggEvent event) {
		mRecentEvents.add(event);
		while (mRecentEvents.size() > mMaxCodeLength) {
			mRecentEvents.removeFirst();
		}
		
		//TODO: validate against codes
	}
	
	public EasterEggGestureListener newGestureListener() {
		return new EasterEggGestureListener();
	}
	
	public OnClickWrapper newOnClickWrapper(View.OnClickListener onClickListener) {
		return new OnClickWrapper(onClickListener);
	}
	
	
	
	
	
	public class EasterEggGestureListener extends SimpleOnGestureListener {

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			onEvent(new SwipeEasterEggEvent(velocityX, velocityY));
			return false;
		}

	}
	
	public class OnClickWrapper implements View.OnClickListener {
		
		private View.OnClickListener mBaseListener;
		
		public OnClickWrapper(View.OnClickListener baseListener) {
			mBaseListener = baseListener;
		}

		@Override
		public void onClick(View v) {
			onEvent(new OnClickEasterEggEvent(v));
			mBaseListener.onClick(v);
		}
		
	}
}

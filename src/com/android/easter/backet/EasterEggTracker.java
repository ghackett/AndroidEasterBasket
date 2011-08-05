package com.android.easter.backet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class EasterEggTracker {
	
	private int mMaxCodeLength = 0;
	private LinkedList<EasterEggEvent> mRecentEvents;
	private ArrayList<EasterEggCode> mValidCodes;
	private OnEasterEggCodeUnlockedListener mListener = null;
	
	
	public EasterEggTracker(int maxCodeLength) {
		mMaxCodeLength = maxCodeLength;
		mRecentEvents = new LinkedList<EasterEggEvent>();
		mValidCodes = new ArrayList<EasterEggCode>();
	}

	public void registerCode(EasterEggCode code) {
		if (!mValidCodes.contains(code))
			mValidCodes.add(code);
	}
	
	public void setUnlockListener(OnEasterEggCodeUnlockedListener listener) {
	    mListener = listener;
	}
	
	public void onEvent(EasterEggEvent event) {
		mRecentEvents.add(event);
		while (mRecentEvents.size() > mMaxCodeLength) {
			mRecentEvents.removeFirst();
		}
		
		for (EasterEggCode code : mValidCodes) {
		    if (mListener != null && isCodeUnlocked(code)) {
		        mListener.onEasterEggCodeUnlocked(code);
		    }
		}
	}
	
	private boolean isCodeUnlocked(EasterEggCode code) {
	    if (code != null) {
	        List<EasterEggEvent> events = code.getEventList();
	        if (events != null && events.size() > 0 && events.size() <= mRecentEvents.size()) {
	            int offset = mRecentEvents.size() - events.size();
	            for (int i = 0; i<events.size(); i++) {
	                if (!events.get(i).equals(mRecentEvents.get(i+offset)))
	                    return false;
	            }
	            return true;
	        }
	    }
	    return false;
	}
	
	
	public EasterEggOnTouchListener newOnTouchListener(Context context) {
	    return new EasterEggOnTouchListener(context);
	}
	
	public EasterEggGestureListener newGestureListener() {
		return new EasterEggGestureListener();
	}
	
	public OnClickWrapper newOnClickWrapper(View.OnClickListener onClickListener) {
		return new OnClickWrapper(onClickListener);
	}
	
	
	
	public class EasterEggOnTouchListener implements OnTouchListener {
	    private GestureDetector mGestureDetector;
	    private EasterEggGestureListener mGestureListener;
	    
	    public EasterEggOnTouchListener(Context context) {
	        mGestureListener = new EasterEggGestureListener();
	        mGestureDetector = new GestureDetector(context, mGestureListener);
	    }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mGestureDetector.onTouchEvent(event);
            return false;
        }
	    
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

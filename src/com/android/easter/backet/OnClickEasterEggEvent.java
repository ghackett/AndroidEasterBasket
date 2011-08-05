package com.android.easter.backet;

import android.view.View;

public class OnClickEasterEggEvent extends EasterEggEvent {
	
	private String mViewToString = null;
	private int mId = View.NO_ID;
	
	public OnClickEasterEggEvent(int viewId) {
		if (viewId == View.NO_ID)
			throw new RuntimeException("cant declare an easter egg on click with View.NO_ID");
		mId = viewId;
	}
	
	public OnClickEasterEggEvent(View view) {
		if (view.getId() != View.NO_ID) {
			mId = view.getId();
		} else {
			mViewToString = view.toString();
		}
	}
	
	public String getViewString() {
		return mViewToString;
	}
	
	public int getViewId() {
		return mId;
	}

	@Override
	public boolean equals(EasterEggEvent otherEvent) {
		if (otherEvent == null)
			return false;
		if (otherEvent instanceof OnClickEasterEggEvent) {
			if (mViewToString != null)
				return ((OnClickEasterEggEvent) otherEvent).getViewString().equals(mViewToString);
			else 
				return ((OnClickEasterEggEvent) otherEvent).getViewId() == mId;
		}
		return false;
	}

}

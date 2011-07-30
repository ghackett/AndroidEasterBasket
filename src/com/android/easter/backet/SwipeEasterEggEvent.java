package com.android.easter.backet;

public class SwipeEasterEggEvent implements EasterEggEvent {
	
	public static final int EVENT_SWIPE_UP = 1;
	public static final int EVENT_SWIPE_DOWN = 2;
	public static final int EVENT_SWIPE_LEFT = 3;
	public static final int EVENT_SWIPE_RIGHT = 4;
	
	private int mEventType;
	
	public SwipeEasterEggEvent(int eventType) {
		mEventType = eventType;
	}
	
	public SwipeEasterEggEvent(float velocityX, float velocityY) {
		if (Math.abs(velocityY) > Math.abs(velocityX)) {
			mEventType = velocityY > 0 ? SwipeEasterEggEvent.EVENT_SWIPE_DOWN : SwipeEasterEggEvent.EVENT_SWIPE_UP;
		} else {
			mEventType = velocityX > 0 ? SwipeEasterEggEvent.EVENT_SWIPE_RIGHT : SwipeEasterEggEvent.EVENT_SWIPE_LEFT;
		}
	}
	
	public int getEventType() {
		return mEventType;
	}

	@Override
	public boolean equals(EasterEggEvent otherEvent) {
		if (otherEvent == null)
			return false;
		if (otherEvent instanceof SwipeEasterEggEvent)
			return ((SwipeEasterEggEvent) otherEvent).getEventType() == mEventType;
		return false;
	}

}

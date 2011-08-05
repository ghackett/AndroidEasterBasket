package com.android.easter.backet;

import java.util.ArrayList;
import java.util.List;

public class KonamiSwipeCode implements EasterEggCode {
    private ArrayList<EasterEggEvent> mEvents;
    
    public KonamiSwipeCode() {
        mEvents = new ArrayList<EasterEggEvent>();
        mEvents.add(new SwipeEasterEggEvent(SwipeEasterEggEvent.EVENT_SWIPE_UP));
        mEvents.add(new SwipeEasterEggEvent(SwipeEasterEggEvent.EVENT_SWIPE_UP));
        mEvents.add(new SwipeEasterEggEvent(SwipeEasterEggEvent.EVENT_SWIPE_DOWN));
        mEvents.add(new SwipeEasterEggEvent(SwipeEasterEggEvent.EVENT_SWIPE_DOWN));
        mEvents.add(new SwipeEasterEggEvent(SwipeEasterEggEvent.EVENT_SWIPE_LEFT));
        mEvents.add(new SwipeEasterEggEvent(SwipeEasterEggEvent.EVENT_SWIPE_RIGHT));
        mEvents.add(new SwipeEasterEggEvent(SwipeEasterEggEvent.EVENT_SWIPE_LEFT));
        mEvents.add(new SwipeEasterEggEvent(SwipeEasterEggEvent.EVENT_SWIPE_RIGHT));
    }

    @Override
    public List<EasterEggEvent> getEventList() {
        return mEvents;
    }
}

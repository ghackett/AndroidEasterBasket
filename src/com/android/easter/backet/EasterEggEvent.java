package com.android.easter.backet;

public abstract class EasterEggEvent {
	public abstract boolean equals(EasterEggEvent otherEvent);

    @Override
    public boolean equals(Object o) {
        if (o instanceof EasterEggEvent)
            return equals((EasterEggEvent)o);
        return false;
    }
	
	
}

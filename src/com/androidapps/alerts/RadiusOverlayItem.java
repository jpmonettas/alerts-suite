package com.androidapps.alerts;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class RadiusOverlayItem extends OverlayItem{
	private int radius;

	public RadiusOverlayItem(GeoPoint p, int radius, String title, String body){
		super(p,title,body);
		this.setRadius(radius);
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}
}

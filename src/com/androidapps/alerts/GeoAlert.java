package com.androidapps.alerts;

import android.location.Location;
import android.util.Log;

public class GeoAlert extends Alert {
	
	private Location location;
	private int radius;
	
	public GeoAlert(String name,double latitude, double longitude,String note,int radius){
		this.name=name;
		location=new Location("");
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		this.note=note;
		this.radius=radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}
	
	public boolean matches(Location point){
		Log.d("Location", "Comparing : (" + point.getLatitude() + "," + point.getLongitude() + ") with (" + location.getLatitude() + "," + location.getLongitude() + ") with distance : " + location.distanceTo(point) ); 
		return location.distanceTo(point) < radius;
	}
}

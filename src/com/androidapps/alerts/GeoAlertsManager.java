package com.androidapps.alerts;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class GeoAlertsManager {
	private AlertsSuiteActivity ctx;
	private LocationManager locationManager;
	private List<GeoAlert> geoAlerts;
	private Location lastLocation;
	//private Handler guiHandler;
	

	public GeoAlertsManager(AlertsSuiteActivity ctx) {
		locationManager = (LocationManager) ctx
				.getSystemService(Context.LOCATION_SERVICE);
		this.ctx = ctx;
		geoAlerts = new ArrayList<GeoAlert>();

		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				Log.d("Location", "Location changed");
	
				if (lastLocation ==null || lastLocation.distanceTo(location) > 50) {
					// Called when a new location is found by the network
					// location provider.
					for (GeoAlert ga : geoAlerts) {
						if (ga.matches(location)) {

							// We are at alert location
							// Fire the alert
							/*
							 * Message msg = new Message(); Bundle bundle=new
							 * Bundle(); bundle.putString("message", ga.note);
							 * msg.setData(bundle);
							 */
							// guiHandler.sendMessage(msg);
							fireAlert(ga.note);
						}

					}
					lastLocation = location;
				}
			}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}

			@Override
			public void onStatusChanged(String provider, int status,Bundle extras) {
				// TODO Auto-generated method stub
				
			}
		  };
		  
		  // Register the listener with the Location Manager to receive location updates
		  //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

	}
	
	private void fireAlert(String msg){
		ctx.showAlert(msg);
	}
	
	
	public void addGeoAlert(GeoAlert ga){
		geoAlerts.add(ga);
	}
		 
	public void removeGeoAlert(int alertId){
		
	}
}

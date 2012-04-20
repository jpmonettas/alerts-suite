package com.androidapps.alerts;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;

public class AlertsSuiteActivity extends MapActivity {
    private BackgroundThread bgThread;
    private Handler handler;
    private MyItemizedOverlay itemizedoverlay;
    private GeoAlertsManager gam;
    
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        GeoPoint point = new GeoPoint(-34897142,-56148527);
        
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        //mapView.setClickable(true);
        mapView.getController().setCenter(point);
        mapView.getController().setZoom(17);
        
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
        itemizedoverlay = new MyItemizedOverlay(drawable, this);
        
        RadiusOverlayItem o = new RadiusOverlayItem(point,0, "", "");
        itemizedoverlay.addOverlay(o);
        
        mapOverlays.add(itemizedoverlay);
        
        gam=new GeoAlertsManager(this);
    }
    
    
    
    public void showAlert(String alertText){ 	
    	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    	alertDialog.setTitle("Geo Alert");
		alertDialog.setMessage(alertText);
		alertDialog.show();
    }
    
    public void addAlert(RadiusOverlayItem o,String name,String note){
    	GeoPoint p=o.getPoint();
    	itemizedoverlay.addOverlay(o);
    	gam.addGeoAlert(new GeoAlert(name,(p.getLatitudeE6() / 1E6),(p.getLongitudeE6() / 1E6),note,o.getRadius()));
    }
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
    
    @Override
	protected void onStart() {
		super.onStart();
		/*
		bgThread = new BackgroundThread(this,handler);
		bgThread.start();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				showAlert(msg.getData().getString("message"));
			}
		};
		*/
	}

	@Override
	protected void onStop() {
		super.onStop();
		//bgThread.stop();
	}
}
package com.androidapps.alerts;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

public class MyItemizedOverlay extends ItemizedOverlay{
	
	private ArrayList<RadiusOverlayItem> mOverlays = new ArrayList<RadiusOverlayItem>();
	private AlertsSuiteActivity mContext;
	
	private Dialog dialog;
	private GeoPoint p;
	
	public MyItemizedOverlay(Drawable defaultMarker, AlertsSuiteActivity context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;

	}

	public void addOverlay(RadiusOverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}
	
	@Override
	protected RadiusOverlayItem createItem(int i) {
	  return mOverlays.get(i);
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);

		for (RadiusOverlayItem o : mOverlays) {
			Point point = new Point();
			
			Projection projection = mapView.getProjection();
			projection.toPixels(o.getPoint(), point);

			Paint paint = new Paint();
			paint.setStyle(Paint.Style.FILL);
			paint.setARGB(100, 100, 100, 100);

			canvas.drawCircle((float) point.x, (float) point.y, projection.metersToEquatorPixels(o.getRadius()), paint);
		}
	}
	
	@Override
	public int size() {
	  return mOverlays.size();
	}
	
	private OnClickListener mOkButtonListener = new OnClickListener() {
	        public void onClick(View v) {
	        	
	            EditText nameText = (EditText) dialog.findViewById(R.id.nameText);
	            String name=nameText.getText().toString();

	            EditText noteText = (EditText) dialog.findViewById(R.id.noteText);
	            String note=noteText.getText().toString();
	            
	            EditText radiusText = (EditText) dialog.findViewById(R.id.radiusText);
	            int radius=Integer.parseInt(radiusText.getText().toString());
	            
	            RadiusOverlayItem overlayitem = new RadiusOverlayItem(p,radius, name, note);
	            mContext.addAlert(overlayitem,name,note);
	            
	        	dialog.dismiss();
	        }
	};
	
	private OnClickListener mDeleteButtonListener = new OnClickListener() {
        public void onClick(View v) {
        	
        	dialog.dismiss();
        }
	};

	
	private boolean cancelItemCreation=false;
    @Override
    public boolean onTouchEvent(MotionEvent e, MapView mapView) 
    {   
        if (e.getAction() == MotionEvent.ACTION_UP && !cancelItemCreation) {                
            p = mapView.getProjection().fromPixels((int) e.getX(),(int) e.getY());
            
            dialog = new Dialog(mContext);

    		dialog.setContentView(R.layout.alert_edit);
    		dialog.setTitle("Custom Dialog");

    		Button okButton = (Button) dialog.findViewById(R.id.okButton);
    		okButton.setOnClickListener(mOkButtonListener);

    		Button deleteButton = (Button) dialog.findViewById(R.id.deleteButton);
    		deleteButton.setOnClickListener(mDeleteButtonListener);	  	  	
	        
	  	  	dialog.show();
	  	  	
        }else if (e.getAction() == MotionEvent.ACTION_MOVE) {                
            cancelItemCreation=true;
        }else if (e.getAction() == MotionEvent.ACTION_UP && cancelItemCreation){
        	cancelItemCreation=false;
        }
        return false;
    }        
	
	@Override
	protected boolean onTap(int index) {
		/*
	      OverlayItem item = mOverlays.get(index);
	  	  Dialog dialog = new Dialog(mContext);
	
	  	  dialog.setContentView(R.layout.alert_edit);
	  	  dialog.setTitle("Custom Dialog");
	  	
	  	  dialog.show();
	  */
	  return true;
	}
}

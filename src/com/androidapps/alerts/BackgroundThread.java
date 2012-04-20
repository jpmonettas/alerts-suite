package com.androidapps.alerts;

import android.content.Context;
import android.os.Handler;

public class BackgroundThread extends Thread {
	private Context context;
	private Handler guiHandler;
	
	public BackgroundThread(Context context,Handler guiH){
		this.guiHandler=guiH;
		this.context=context;
	}
	
	@Override
	public void run() {
		
		while (true) {
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

package com.inetstd.android.phonegap.customsplashscreen;

import org.json.JSONArray;

import android.util.Log;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;
import com.phonegap.api.PluginResult.Status;

public class CustomSplashScreenPhugin extends Plugin {
	/**
	 * Hide splash screen 
	 */
	@Override
	public PluginResult execute(String action, JSONArray data, String callbackId) {
		PluginResult result = new PluginResult(Status.OK);
		
		if (action.equals("hide") && this.ctx instanceof DroidGapWithCustomSplashScreen) {
			// because of - Only the original thread that created a view hierarchy can touch its views.
			this.ctx.runOnUiThread(new Runnable() {				
				@Override
				public void run() {
				  Log.i("CustomSplashScreenPhugin", "pre hide");
					((DroidGapWithCustomSplashScreen) CustomSplashScreenPhugin.this.ctx).hideCustomSplashScreen();
					Log.i("CustomSplashScreenPhugin", "post hide");
				}
			});
			
		}		
		return result;
	}
}

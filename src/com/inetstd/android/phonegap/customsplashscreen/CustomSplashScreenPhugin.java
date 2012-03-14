package com.inetstd.android.phonegap.customsplashscreen;

import org.json.JSONArray;

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
					((DroidGapWithCustomSplashScreen) CustomSplashScreenPhugin.this.ctx).hideCustomSplashScreen();
				}
			});
			
		}		
		return result;
	}
}

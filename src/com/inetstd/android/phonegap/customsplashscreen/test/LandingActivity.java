package com.inetstd.android.phonegap.customsplashscreen.test;

import com.inetstd.android.phonegap.customsplashscreen.DroidGapWithCustomSplashScreen;
import com.inetstd.android.phonegap.customsplashscreen.R;

import android.content.Intent;

import android.os.Bundle;

public class LandingActivity extends DroidGapWithCustomSplashScreen {
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);				
		super.setIntegerProperty("loadUrlTimeoutValue", 60000);
		super.setIntegerProperty(C_CUSTOM_SPLASH_SCREEN, R.drawable.splash);		
		super.loadUrl("file:///android_asset/www/index.html");
	}

	@Override
	protected void onTryAgain() {
		Intent i = new Intent(this, LandingActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		this.startActivity(i);
	}	
}

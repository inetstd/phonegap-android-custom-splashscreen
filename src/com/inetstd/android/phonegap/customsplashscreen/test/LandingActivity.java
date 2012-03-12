package com.inetstd.android.phonegap.customsplashscreen.test;

import com.inetstd.android.phonegap.customsplashscreen.DroidGapWithCustomSplashScreen;
import com.inetstd.android.phonegap.customsplashscreen.R;
import com.phonegap.DroidGap;
import com.phonegap.api.LOG;


import android.net.ConnectivityManager;
import android.net.http.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.net.NetworkInfo;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LandingActivity extends DroidGapWithCustomSplashScreen {
	
	public static String REMOTE_URL = "file://android_asset/www/index.html";
	
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
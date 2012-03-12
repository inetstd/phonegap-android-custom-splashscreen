package com.inetstd.android.phonegap.customsplashscreen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.inetstd.android.phonegap.customsplashscreen.test.LandingActivity;
import com.phonegap.DroidGap;



public abstract class DroidGapWithCustomSplashScreen extends DroidGap {

	public static final String C_CUSTOM_SPLASH_SCREEN = "C_CUSTOM_SPLASH_SCREEN";
	public static final String C_ERROR_URL = null;

	ViewGroup pgContainerView = null;
	ViewGroup webViewContainer = null;
	ImageView customSpashScreen = null; 

	protected int hideCustomSplashScreenTimeout = 5000;

	public DroidGapWithCustomSplashScreen() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);				
	}	


	@Override
	public void init() {		

		super.init();		
		// root is child of FrameLayout. In init method PG puts root to viewstack. Before root.getParent() returns null. 		
		pgContainerView = (ViewGroup) root.getParent();

		// better to use invisible. with View.GONE - WebView has 0,0 size and on show will call window.onresize 	
		customSpashScreen = new ImageView(this);		
		customSpashScreen.setImageResource(super.getIntegerProperty(C_CUSTOM_SPLASH_SCREEN, 0));
		customSpashScreen.setScaleType(ScaleType.CENTER_CROP);
		pgContainerView.addView(customSpashScreen);

		showCustomSplashScreen();		
	}

	public void showCustomSplashScreen() {		
		if (customSpashScreen == null) return;
		root.setVisibility(View.INVISIBLE);
		appView.setVisibility(View.INVISIBLE);		
		customSpashScreen.setImageResource(super.getIntegerProperty(C_CUSTOM_SPLASH_SCREEN, 0));
		customSpashScreen.setVisibility(View.VISIBLE);		

	}

	public void hideCustomSplashScreen() {		
		if (customSpashScreen == null) return;		
		try {		
			customSpashScreen.setVisibility(View.GONE);		
			root.setVisibility(View.VISIBLE);
			appView.setVisibility(View.VISIBLE);
		} catch (Exception e) {
			Log.e("DroidGapWithCustomSplashScreen", "hideCustomSplashScreen - do it in ui thread");
		}
	}

	protected void hideCustomSplashScreen(final int timeout) {
		if (customSpashScreen == null) return;
		// emulate callback 
		new CountDownTimer(timeout, timeout) {
			public void onFinish() {
				hideCustomSplashScreen();
			}
			@Override
			public void onTick(long millisUntilFinished) {}
		}.start();			
	}

	protected abstract void onTryAgain();

	@Override
	public void displayError(final String title, final String message, final String button, final boolean exit) {
		final DroidGap me = this;
		showCustomSplashScreen();	
		me.runOnUiThread(new Runnable() {
			public void run() {
				AlertDialog.Builder dlg = new AlertDialog.Builder(me);
				dlg.setMessage("No Internet connection");
				dlg.setTitle("Please, check connection settings");
				dlg.setCancelable(false);
				dlg.setPositiveButton("Try again", new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						onTryAgain();						
					}
				});
				dlg.setNegativeButton("Exit",
						new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						me.endActivity();                        
					}
				});
				dlg.create();
				dlg.show();
			}
		});
	}


}

package com.inetstd.android.phonegap.customsplashscreen;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.phonegap.DroidGap;

public abstract class WebViewClientAdapter extends WebViewClient {

	WebViewClient nativeClient = null;
	DroidGap ctx;

	public WebViewClientAdapter(DroidGap ctx, WebViewClient nativeClient) {
		this.nativeClient = nativeClient;
		this.ctx = ctx;
	}	

	

	
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		Log.i("decorator", view.getId() + " shouldOverrideUrlLoading: " + url);		
		return nativeClient.shouldOverrideUrlLoading(view, url);
	}

	
	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {			
		Log.i("decorator", view.getId() + " onPageStarted: " + url);		
		nativeClient.onPageStarted(view, url, favicon);
		this.decoratedOnPageStarted(view, url, favicon);
		
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		Log.i("decorator", view.getId() + " onPageFinished" + url);		
		nativeClient.onPageFinished(view, url);		
		this.decoratedOnPageFinished(view, url);
	}

	@Override
	public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
		Log.i("decorator", "onReceivedError " + description + " " + errorCode + " " + failingUrl);		
		nativeClient.onReceivedError(view, errorCode, description, failingUrl);
		this.decoratedOnReceivedError(view, errorCode, description, failingUrl);

	}

	public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
		Log.i("decorator", "onReceivedSslError ");	
		nativeClient.onReceivedSslError(view, handler, error);
	}	
	
	public abstract void decoratedOnPageFinished(WebView view, String url);
	
	public abstract void decoratedOnPageStarted(WebView view, String url, Bitmap favicon);
	
	public abstract void decoratedOnReceivedError(WebView view, int errorCode, String description, String failingUrl);
	
	
}

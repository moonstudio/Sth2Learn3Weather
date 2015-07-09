package com.moonstudio.weather;

import android.app.Application;

import com.thinkland.juheapi.common.CommonFun;


public class WeatherApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		CommonFun.initialize(getApplicationContext());
	}

}

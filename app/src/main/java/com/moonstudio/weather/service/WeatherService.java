package com.moonstudio.weather.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Michael on 2015/7/8.
 */
public class WeatherService extends Service {

    static public String ServiceState="";
    @Override
    public IBinder onBind(Intent arg0) {
        Log.e("Service", "onBind");
        System.out.println("Service onBind");
        ServiceState="onBind";
        return null;
    }
    @Override
    public boolean onUnbind(Intent intent){
        super.onUnbind(intent);
        Log.e("Service", "onUnbind");
        System.out.println("Service onUnbind");
        ServiceState="onUnbind";
        return false;

    }
    @Override
    public void onCreate(){
        super.onCreate();
        Log.e("Service", "onCreate");
        System.out.println("Service onCreate");
        ServiceState="onCreate";
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.e("Service", "onDestroy");
        System.out.println("Service onDestroy");
        ServiceState="onDestroy";
    }
    @Override
    public void onStart(Intent intent,int startid){
        super.onStart(intent, startid);
        Log.e("Service", "onStart");
        System.out.println("Service onStart");
        ServiceState="onStart";
    }

    public class WeatherServiceBinder extends Binder {
        public WeatherService getService() {
            return WeatherService.this;
        }
    }
}

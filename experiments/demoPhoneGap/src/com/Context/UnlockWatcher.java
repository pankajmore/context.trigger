package com.Context;

import org.apache.cordova.api.Plugin;

import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.*;
import android.provider.*;
import android.util.Log;
import android.widget.Toast;


public class UnlockWatcher extends Plugin {

    private String callback;
    private Boolean flag=false;
    public ScreenBroadcastReceiver screenReceiver;
    public UserBroadcastReceiver userReceiver;
    @Override
    public PluginResult execute(String action, JSONArray data, String callbackId) {
	this.callback = callbackId;
	screenReceiver = new ScreenBroadcastReceiver(this);
	userReceiver = new UserBroadcastReceiver(this);
	PluginResult result = new PluginResult(Status.NO_RESULT);
	this.cordova.getActivity().registerReceiver(screenReceiver, new IntentFilter(Intent.ACTION_SCREEN_ON));
	this.cordova.getActivity().registerReceiver(userReceiver, new IntentFilter(Intent.ACTION_USER_PRESENT));
	result.setKeepCallback(true);
	return result;
	}
    
    public void changed() {
	JSONObject status = new JSONObject();
	PluginResult result = new PluginResult(PluginResult.Status.OK, status);
	result.setKeepCallback(true);
	this.success(result, this.callback);
    }

    public class ScreenBroadcastReceiver extends BroadcastReceiver
    {     
        protected UnlockWatcher watcher; 
        
        public ScreenBroadcastReceiver(UnlockWatcher watcher) {
	    super();
	    this.watcher = watcher; 
        }
        
        @Override        
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
                Log.d("UnlockWatcher","screen on");
                this.watcher.flag = true;
	    }
	}
    }
    
    public class UserBroadcastReceiver extends BroadcastReceiver
    {     
        protected UnlockWatcher watcher; 
        
        public UserBroadcastReceiver(UnlockWatcher watcher) {
        super();
        this.watcher = watcher; 
        }
        
        @Override        
        public void onReceive(Context context, Intent intent) {
            Log.d("UnlockWatcher","user present");
            this.watcher.flag=false;
            watcher.changed();
	}
    }
}

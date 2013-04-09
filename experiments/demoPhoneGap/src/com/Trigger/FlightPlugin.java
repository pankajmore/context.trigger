package com.Trigger;

import org.apache.cordova.api.CallbackContext;


import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import android.app.Activity;
import android.media.*;
import android.provider.*;
import android.content.*;

public class FlightPlugin extends CordovaPlugin {
	public FlightPlugin() {
	    super();
	}
    
    @Override
    public boolean execute(String action, JSONArray args,
			   final CallbackContext callbackContext) throws JSONException {
        Activity ctx = this.cordova.getActivity();
        boolean isEnabled = Settings.System.getInt(
                ctx.getContentResolver(), 
                Settings.System.AIRPLANE_MODE_ON, 0) == 1;
		if (action.equals("off")) {
		    Log.d("FlightPlugin","Off Triggered "+isEnabled);
		    Settings.System.putInt(
		            ctx.getContentResolver(),
		            Settings.System.AIRPLANE_MODE_ON, 0);
	        Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
	        intent.putExtra("state", 0);
	        ctx.sendBroadcast(intent);
		    callbackContext.success("off");
		    return true;
		} else if (action.equals("on")) {
		    Log.d("FlightPlugin","On Triggered "+isEnabled);
		    Settings.System.putInt(
                    ctx.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 1);
	        Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
	        intent.putExtra("state", 1);
	        ctx.sendBroadcast(intent);
		    callbackContext.success("on");
		    return true;
		}		
		return false;
    }
}

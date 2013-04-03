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
import android.util.Log;
import android.widget.Toast;


public class CallWatcher extends Plugin {

	private String callback;
	public CallBroadcastReceiver callReceiver;
	@Override
	public PluginResult execute(String action, JSONArray data, String callbackId) {
		this.callback = callbackId;
		callReceiver = new CallBroadcastReceiver(this);
		PluginResult result = new PluginResult(Status.NO_RESULT);
		this.cordova.getActivity().registerReceiver(callReceiver, new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED));
		result.setKeepCallback(true);
		return result;
	}

	public void changed(int state,String number) {

		JSONObject status = new JSONObject();
		try {
			status.put("incoming", state == TelephonyManager.CALL_STATE_RINGING ? true : false);
			status.put("number", number);
		} catch (Exception ex) {
			Log.e("Call", "JSON error " + ex.toString());
			return;
		}
		PluginResult result = new PluginResult(PluginResult.Status.OK, status);
		result.setKeepCallback(true);
		this.success(result, this.callback);
	}

	public class CallBroadcastReceiver extends BroadcastReceiver
    {     
        protected CallWatcher watcher; 
        
        public CallBroadcastReceiver(CallWatcher watcher) {
        	super();
        	this.watcher = watcher; 
        }
        
        @Override        
        public void onReceive(Context context, Intent intent) {
        	TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        	int state = tm.getCallState();
        	Bundle extra=intent.getExtras();//new
        	String number = extra.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            watcher.changed(state,number);           
 
        }

    }


}

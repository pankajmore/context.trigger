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


public class SmsWatcher extends Plugin {

    private String callback;
    public SmsBroadcastReceiver smsReceiver;
    @Override
    public PluginResult execute(String action, JSONArray data, String callbackId) {
	this.callback = callbackId;
	smsReceiver = new SmsBroadcastReceiver(this);
	PluginResult result = new PluginResult(Status.NO_RESULT);
	this.cordova.getActivity().registerReceiver(smsReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
	result.setKeepCallback(true);
	Log.d("SmsWatcher","sms plugin executed");
	return result;
	}
    
    public void changed(String number,String message) {
	JSONObject status = new JSONObject();
	try {
	    status.put("number", number);
	    status.put("message", message);
	} catch (Exception ex) {
	    Log.e("Sms", "JSON error " + ex.toString());
	    return;
	}
	PluginResult result = new PluginResult(PluginResult.Status.OK, status);
	result.setKeepCallback(true);
	this.success(result, this.callback);
    }

    public class SmsBroadcastReceiver extends BroadcastReceiver
    {     
        protected SmsWatcher watcher; 
        
        public SmsBroadcastReceiver(SmsWatcher watcher) {
	    super();
	    this.watcher = watcher; 
        }
        
        @Override        
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
                Log.d("SMS","sms received event");
                Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
                SmsMessage[] msgs = null;
                String msg_from;
                if (bundle != null){
                    //---retrieve the SMS message received---
                    try{
                        Object[] pdus = (Object[]) bundle.get("pdus");
                        msgs = new SmsMessage[pdus.length];
                        for(int i=0; i<msgs.length; i++){
                            msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                            msg_from = msgs[i].getOriginatingAddress();
                            String msgBody = msgs[i].getMessageBody();
                            watcher.changed(msg_from, msgBody);
                        }
                    }catch(Exception e){
			//                                Log.d("Exception caught",e.getMessage());
                    }
                }
	    }    
	}
    }
}

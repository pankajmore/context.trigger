package com.Trigger;

import org.apache.cordova.api.CallbackContext;

import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import android.app.Activity;
import android.media.*;

public class RingerPlugin extends CordovaPlugin {
	public RingerPlugin() {
	    super();
	}
    
    @Override
    public boolean execute(String action, JSONArray args,
			   final CallbackContext callbackContext) throws JSONException {
        Activity ctx = this.cordova.getActivity();
        AudioManager am= (AudioManager) ctx.getSystemService(ctx.AUDIO_SERVICE);
        if (null != am){
		if (action.equals("silent")) {
		    //For Silent mode
		    am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		    callbackContext.success("silent");
		    return true;
		} else if (action.equals("normal")) {
		    //For Normal mode
		    am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		    callbackContext.success("normal");
		    return true;
		} else if (action.equals("vibrate")) {
		    //For Vibrate mode
		    am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		    callbackContext.success("vibrate");
		    return true;
		} 
        } else
        {
            Log.d("RingerPlugin","Audiomanager is null");
        }
        callbackContext.success();
		return true;
    }
}

package com.Trigger;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;
import android.webkit.WebView;
import android.app.Activity;
import android.app.PendingIntent;
import android.media.*;
import android.content.Intent;
import android.*;
public class Setvolume extends CordovaPlugin {
	public Setvolume() {
	    super();
	}
    
    @Override
    public boolean execute(String action, JSONArray args,
			   final CallbackContext callbackContext) throws JSONException {
        if (action.equals("settvol")) {
        	 Log.d("foundd","reached");
        	 
      
            int inputVol = 0; // set volume here
		   
			Activity ctx = this.cordova.getActivity();
			AudioManager am2= (AudioManager) ctx.getSystemService(ctx.AUDIO_SERVICE);
			if (inputVol > am2.getStreamMaxVolume(AudioManager.STREAM_MUSIC))
                inputVol = am2.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            am2.setStreamVolume(AudioManager.STREAM_MUSIC, inputVol,
                    AudioManager.FLAG_SHOW_UI);
            
        
		} 
          callbackContext.success();
		return true;
    }
    
}
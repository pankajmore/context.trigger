/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.Foo.DemoPhoneGap;

import android.os.Bundle;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.media.*;
import android.net.*;
import android.net.wifi.*;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.*;
import org.apache.cordova.*;
import android.util.Log;

public class DemoPhoneGap extends DroidGap
{
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Set by <content src="index.html" /> in config.xml
        super.loadUrl(Config.getStartUrl());
        //super.loadUrl("file:///android_asset/www/index.html")
        this.registerReceiver(this.mNetworkEnabled,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        this.registerReceiver(this.rHeadsetWired,
                new IntentFilter(Intent.ACTION_HEADSET_PLUG));
        this.registerReceiver(this.rIncomingCall,
                new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED));

    }
    private BroadcastReceiver mNetworkEnabled = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {

        	ConnectivityManager connectivityManager = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
        	NetworkInfo currentNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if(currentNetworkInfo != null){
            if(currentNetworkInfo.isConnected()){
            	if (currentNetworkInfo.getType() == 0){ //If Wifi is off, Mobile is active
            		WifiManager wifi;
            		wifi=(WifiManager)getSystemService(Context.WIFI_SERVICE);

            		//wifi.setWifiEnabled(false);//Turn off Wifi

            		wifi.setWifiEnabled(true);//Turn on Wifi
                Toast.makeText(getApplicationContext(), "Mobile Netowrk Connected", Toast.LENGTH_LONG).show();
            	}else if(currentNetworkInfo.getType() ==1){ //If Wifi is on , always here
                    Toast.makeText(getApplicationContext(), "Wifi Connected", Toast.LENGTH_LONG).show();		
            	}else{
            		
                    Toast.makeText(getApplicationContext(), "Unknown Connected", Toast.LENGTH_LONG).show();
            	}
            }}else {
                Toast.makeText(getApplicationContext(), "Not Connected", Toast.LENGTH_LONG).show();
            }
        }
    };
    
    private BroadcastReceiver rHeadsetWired = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
        	int state = intent.getIntExtra("state", 0);
        	if (state==1) {
                Toast.makeText(getApplicationContext(), "Headphone Connected", Toast.LENGTH_LONG).show();
        	}
        	else {
        		Toast.makeText(getApplicationContext(), "Headphone Disconnected", Toast.LENGTH_LONG).show();
        	}
        	
        }
    };
    
    private BroadcastReceiver rIncomingCall = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
        	TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        	int state = tm.getCallState();
        	if (state==TelephonyManager.CALL_STATE_RINGING) {
                Toast.makeText(getApplicationContext(), "Call ringing", Toast.LENGTH_LONG).show();
        	}
        	else {
//        		Log.d("rIncomingCall","State="+state);
        	}
        	
        }
    };

}


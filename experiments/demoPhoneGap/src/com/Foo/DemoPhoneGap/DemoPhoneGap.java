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
import android.net.*;
import android.net.wifi.*;
import android.widget.*;
import org.apache.cordova.*;
import java.lang.reflect.Field;

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

    }
    private BroadcastReceiver mNetworkEnabled = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {

        	ConnectivityManager connectivityManager = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
        	NetworkInfo currentNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if(currentNetworkInfo != null){
            if(currentNetworkInfo.isConnected()){
            	if (currentNetworkInfo.getType() == 0){
                Toast.makeText(getApplicationContext(), "Mobile Netowrk Connected", Toast.LENGTH_LONG).show();
            	}else if(currentNetworkInfo.getType() ==1){
                    Toast.makeText(getApplicationContext(), "Wifi Connected", Toast.LENGTH_LONG).show();		
            	}else{
                    Toast.makeText(getApplicationContext(), "Unknown Connected", Toast.LENGTH_LONG).show();
            	}
            }}else {
                Toast.makeText(getApplicationContext(), "Not Connected", Toast.LENGTH_LONG).show();
            }
        }
    };

}


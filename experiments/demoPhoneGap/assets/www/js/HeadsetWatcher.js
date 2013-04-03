/**
 * HeadsetWatcher plugin for Cordova/Phonegap
 *
 * Copyright (c) Triggertrap Ltd. 2012. All Rights Reserved.
 * Available under the terms of the MIT License.
 * 
 */
function HeadsetContext() {
 	var hPlugged = document.createEvent('Events');
	hPlugged.initEvent('headsetplug', true, true);
	var hUnplugged = document.createEvent('Events');
	hUnplugged.initEvent('headsetunplug', true, true);	
	HeadsetWatcher.watch(function(result) {
    			if(result.plugged) {
					document.dispatchEvent(hPlugged);
    			} else {
      				document.dispatchEvent(hUnplugged);
    		}})
}
var HeadsetWatcher = {
	watch: function(callback) {
		return cordova.exec(function(result) {
			HeadsetWatcher.plugged = result.plugged;
			if(callback) {
				callback(result);
			}

		}, HeadsetWatcher.fail, "HeadsetWatcher", "watch", []);
	},
	plugged: false
}
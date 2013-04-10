// switches flight mode on , when headset is plugged in
// switches flight mode off , when headset is plugged out
this.on('headsetplug',function() {trigger.device.flight.on( function (){console.log("Flight on");})});
this.on('headsetunplug',function() {trigger.device.flight.off(function(){console.log("Flight off");})});
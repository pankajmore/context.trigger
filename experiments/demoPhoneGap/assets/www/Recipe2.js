// switches flight mode on/off , when headset is plugged in/out
this.on('headsetplug',function() {trigger.device.flight.on( function (){console.log("Flight on");})});
this.on('headsetunplug',function() {trigger.device.flight.off(function(){console.log("Flight off");})});
//this.on('batterystatus',function(b) {if (b.level < 10) {trigger.device.flight.on( function (){console.log("Flight on");})}});
//this.on('batterystatus',function(b) {if (b.level > 30) {trigger.device.flight.off(function(){console.log("Flight off");})}});
//document.addEventListener('batterystatus',function(b) { console.log("Level " + b.level) });

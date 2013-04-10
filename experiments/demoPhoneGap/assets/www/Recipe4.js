this.on('batterystatus',function(b) {if (b.level < 10) {trigger.device.flight.on( function (){console.log("Flight on");})}});
this.on('batterystatus',function(b) {if (b.level > 30) {trigger.device.flight.off(function(){console.log("Flight off");})}});
console.log("Phonegap "+device.cordova);
document.addEventListener('batterystatus',function(b) { console.log("Level " + b.level) }); 
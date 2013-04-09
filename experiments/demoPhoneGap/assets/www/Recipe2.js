console.log("Recipe 2 start");
this.addEventListener('offline',function() { console.log("offline event")});
this.addEventListener('online',function() { console.log("online event")});
this.addEventListener('locationchange',function(e) { console.log("Location: " + e.position.coords)});
//this.addEventListener('headsetplug',function() { trigger.sms.send("9532835045"
//											   ,"Sms sending plugin 2")
//											   });
//this.addEventListener('headsetplug',function() { trigger.device.mainCapture("context.jpeg");});
this.addEventListener('headsetunplug',function() { trigger.device.normal()});

// when headphone is connected, volume becomes equal to argument.
// argument must be between [0,15]
// I came up with this scale with hit and trial.
// I believe that this scaling is done in decibel or whatever.
this.addEventListener('headsetplug',function() { trigger.setvol.adjust("0")});


console.log("Recipe 2 end");

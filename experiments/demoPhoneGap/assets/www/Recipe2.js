console.log("Recipe 2 start");
this.addEventListener('offline',function() { console.log("offline event")});
this.addEventListener('online',function() { console.log("online event")});
this.addEventListener('locationchange',function(e) { console.log("Location: " + e.position.coords)});
//this.addEventListener('headsetplug',function() { trigger.sms.send("9532835045"
//											   ,"Sms sending plugin 2")
//											   });
//this.addEventListener('headsetplug',function() { trigger.device.mainCapture("context.jpeg");});
this.addEventListener('headsetunplug',function() { trigger.device.normal()});
// when headphone is connected, volume becomes zero
// volume level (zero here) can be set at setvolume.java
this.addEventListener('headsetplug',function() { trigger.setvol.adjust("settvol")});
console.log("Recipe 2 end");

console.log("Recipe 2 start");
this.addEventListener('offline',function() { console.log("offline event")});
this.addEventListener('online',function() { console.log("online event")});
this.addEventListener('locationchange',function(e) { console.log("Location: " + e.position.coords)});
//this.addEventListener('headsetplug',function() { sms.send("9532835045"
//											   ,"Sms sending plugin"
//											   ,function(){console.log("send")}
//											   ,function(){console.log("not send")})
//											   });
console.log("Recipe 2 end");

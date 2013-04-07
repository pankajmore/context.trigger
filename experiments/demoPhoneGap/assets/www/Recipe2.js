console.log("Recipe 2 start");
this.addEventListener('offline',function() { console.log("offline event")});
this.addEventListener('online',function() { console.log("online event")});
this.addEventListener('locationchange',function(e) { console.log("Location: " + e.position.coords)});
console.log("Recipe 2 end");

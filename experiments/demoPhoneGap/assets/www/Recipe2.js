//at("9:42:00",function() {
//   console.log("Day Event");
   // navigator.notification.vibrate(1000)
//});
this.addEventListener("incomingsms",function(e) {
	console.log("From: " + e.number + " Body: " + e.message);
});
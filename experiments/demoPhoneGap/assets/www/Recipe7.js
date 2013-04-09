this.on('incomingcall',function(){
	console.log("Incoming");
});
this.on('offhook',function(){
	console.log("Talking");
});

this.on('cutcall',function(){
	console.log("Call cut");
});
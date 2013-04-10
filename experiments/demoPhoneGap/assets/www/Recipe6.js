// Incoming call first vibrate and then increase volume silently
this.on('incomingcall',function(){
	console.log("Inside incoming call");
	trigger.device.vibrate();
	setTimeout(function(){
		trigger.device.normal();
		for(var i=0; i<=15;i++)
		{
			setTimeout(function(){trigger.device.adjust(i.toString())},1000*i)
		}
	},5000);
});
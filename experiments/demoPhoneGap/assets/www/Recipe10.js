// Put phone in normal mode , when a sms "play" is recieved
this.on('incomingsms',function(e){
    console.log("playy");
      if(e.message=="play")
      {
       console.log("playy2");
      trigger.device.normal();
	}});
      

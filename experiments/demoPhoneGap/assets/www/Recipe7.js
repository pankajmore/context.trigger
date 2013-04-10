// Incoming call recorder
this.on('incomingcall',function(e){
	console.log("Incoming");
	var src = e.number.toString() ;
	var mediaRec = null;
	this.on('offhook',function(e1){
		console.log("Talking");
//		
     var d = new Date();
    // src = src.concat(d.getTime().tostring());
    src = src + d.getTime().toString()+ '.mp3';
    console.log(src);
//     
		mediaRec = new Media(src,function() {console.log("recordAudio():Audio Success");},function(err) {console.log("recordAudio():Audio Error: "+ err.code);});
    	mediaRec.startRecord();
	});
	this.on('cutcall',function(e2){
		if(mediaRec){
			mediaRec.stopRecord();
			mediaRec.release();}
		console.log("Call cut");
	});		
});


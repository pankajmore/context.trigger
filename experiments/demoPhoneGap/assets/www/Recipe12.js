this.on('speachready',function(){
        this.on('compass',function(e) {
        	if (e.heading < 10 || e.heading > 350)
        		{window.plugins.tts.speak("Its North")}});
       });

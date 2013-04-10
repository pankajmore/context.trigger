this.on('speachready',function(){
        this.on('compass',function(e) {
        	if (e.heading < 3 || e.heading > 357)
        		{window.plugins.tts.speak("Its North")}});
       });

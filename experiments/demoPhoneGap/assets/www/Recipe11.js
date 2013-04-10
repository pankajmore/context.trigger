this.on('speachready',function(){
        this.on('facedown',function() {window.plugins.tts.speak("Lets go Down");trigger.device.silent();});
        this.on('faceup',function() {window.plugins.tts.speak("Lets go up");trigger.device.normal();});});

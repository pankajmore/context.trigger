// Plays the given audio at the time specified
var media = null;
var src = "love.mp3";
this.atTime("12:10:00",function() {
	media = new Media(src, function(){console.log("playAudio():Audio Success");}, function(e){console.log("Failure")});
	media.play();
	setTimeout(function(){media.stop(); media.release();},10000);
	});

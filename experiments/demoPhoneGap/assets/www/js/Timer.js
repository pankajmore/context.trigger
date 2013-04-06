// Accepts a string of date and time when to trigger the event
// eg. of accepted full string "April 6,2013 10:20:00" 
function atDate(time,callback){
    var now = new Date();
    var at = new Date(time);
    var millisecs = at - now;
    if (millisecs > 0) {
	setTimeout(callback,millisecs);
    }
}

// Accepts a string of time in 24hours format like 13:00:00 and
// triggers the event ever day at that time
function everyDay(time,callback){
    var now = new Date();
    var at = new Date(now.toDateString() + " " + time);
    var millisecs = at - now;
    if (millisecs <= 0) {
	millisecs += 86400000;
	}
    setTimeout(function() { 
	callback();
	everyDay(time,callback);
	},millisecs);
}
    

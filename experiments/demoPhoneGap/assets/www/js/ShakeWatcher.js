function Shake(){ 
	this.dx = null;
	this.dy = null;
	this.dz = null;
	this.watchId = null;
	this.intervalId = null;
    this.gotMovement = function(coords) {
	//console.log("Shaker fired");
	if (!this.dz) {this.dz = coords.dz};
	if (!this.dy) {this.dy = coords.dy};
	if (!this.dx) {this.dx = coords.dx};
	//console.log(this.dx + '#' + this.dy + '#' + this.dz);
	var delx = Math.abs(coords.x - this.dx);
	var dely = Math.abs(coords.y - this.dy);
	var delz = Math.abs(coords.z - this.dz);
	//console.log(delx + '_' + dely + '_' + delz);
	var max = 9;
	if (delx > max || dely > max || delz > max) {
		console.log(coords.x + "#" + coords.y + "#" + coords.z);
	
	    //disable shaking for 10 seconds to prevent too many updates
	   	navigator.accelerometer.clearWatch(this.watchId);
	    
	    //prevent endless looping: store interval 
	    this.intervalId=setTimeout(this.watch, 1000);
        
        console.log("harlem");     
	    recipes.dispatchEvent({type: 'shake'});
	    
	}
	this.dx = coords.x;
	this.dy = coords.y;
	this.dz = coords.z; 			
    }
    this.watch = function () { 
       if(this.intervalId){
           clearInterval(sessionStorage.intervalId);
       }
	console.log("Shaker run"); 
	this.watchId = navigator.accelerometer.watchAcceleration(this.gotMovement, function(){console.log("Shake error")},{frequency:100});
    }	

}    

var shake = new Shake();
    
document.addEventListener("deviceready",function(){

	   		shake.watch();
	   		});

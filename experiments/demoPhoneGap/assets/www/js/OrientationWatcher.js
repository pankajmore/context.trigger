function Orientation(){ 
	this.z = null;
	this.watchId = null;
	this.intervalId = null;
    this.gotMovement = function(coords) {
    console.log(coords.z + "#" + this.z);
    if (!this.z) {this.z=coords.z}
	if (coords.z <0 && this.z>=0) {
		console.log("Down fired")          
	    recipes.dispatchEvent({type: 'facedown'});}
	else if (coords.z >=0 && this.z <=0){
		console.log("Up fired")          
	    recipes.dispatchEvent({type: 'faceup'});}
	this.z = coords.z; 			
    }
    this.watch = function () { 
       if(this.intervalId){
           clearInterval(sessionStorage.intervalId);
       }
	console.log("Orientaion watcher run"); 
	this.watchId = navigator.accelerometer.watchAcceleration(this.gotMovement, function(){console.log("Shake error")},{frequency:1000});
    }	
}    

var orient = new Orientation();

document.addEventListener("deviceready",function(){
	orient.watch();});

    

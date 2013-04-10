function Compass(){ 
	this.watchId = null;
	this.intervalId = null;
    this.gotMovement = function(head) {
    console.log(head);
	recipes.dispatchEvent({type: 'compass',heading : head});
	}		
    this.watch = function () { 
       if(this.intervalId){
           clearInterval(this.intervalId);
       }
	console.log("Compass watcher run"); 
	this.watchId = navigator.compass.watchHeading(this.gotMovement, function(){console.log("Compass error")},{frequency:1000});
    }	
}    

var compass = new Compass();

document.addEventListener("deviceready",function(){
	compass.watch();});

    

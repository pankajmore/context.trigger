console.log("Inside Recipes");
function Recipes() {
    this._count = 0;
}

Recipes.prototype.addRecipe = function(recipe) {
    if (this._recipes === undefined) { this._recipes = {}; }
    var count = this._count;
    this._count += 1;
    this._recipes[count] = recipe;
    console.log("Recipe added with count = " + count + " and newcount = " + this._count);
    return count;
};


Recipes.prototype.removeRecipe = function(id) {
    this._recipes[id].close();
    delete this._recipes[id];
};

Recipes.prototype.dispatchEvent = function(event) {
    console.log("Dispatching event " + event.type);
    var key , recipes = this._recipes;
    for (key in recipes) {
        recipes[key].dispatchEvent(event);
    }
};


console.log("Event dispatcher code");
/**
 * @author mrdoob / http://mrdoob.com/
 */

var Recipe = function () {};

Recipe.prototype = {

	constructor: Recipe,
	
	atTime: function (time,callback) {
	    console.log("atTime called");
	    var now = new Date();
	    var flag = false;
	    var at = new Date(time)
	    if (at.getTime() !== at.getTime()) {
		at = new Date(now.toDateString() + " " + time);
		flag = true;
	    }
	    var millisecs = at - now;
	    if (millisecs <= 0 && flag) {
		millisecs += 86400000;
	    }
	    console.log("Millisecs " + millisecs);
	    if (millisecs > 0) {
		timeout = setTimeout(callback,millisecs);
		if ( this._timeouts === undefined ) {
			this._timeouts = [];
		}
		this._timeouts.push(timeout);
	    }
	},
	
	addEventListener: function ( type, listener ) {

		if ( this._listeners === undefined ) this._listeners = {};

		var listeners = this._listeners;

		if ( listeners[ type ] === undefined ) {

			listeners[ type ] = [];

		}

		if ( listeners[ type ].indexOf( listener ) === - 1 ) {

			listeners[ type ].push( listener );

		}

	},

	hasEventListener: function ( type, listener ) {

		if ( this._listeners === undefined ) return false;

		var listeners = this._listeners;

		if ( listeners[ type ] !== undefined && listeners[ type ].indexOf( listener ) !== - 1 ) {

			return true;

		}

		return false;

	},

	removeEventListener: function ( type, listener ) {

		if ( this._listeners === undefined ) return;

		var listeners = this._listeners;
		var index = listeners[ type ].indexOf( listener );

		if ( index !== - 1 ) {

			listeners[ type ].splice( index, 1 );

		}

	},
	
	close: function () {
	    if (!(this._timeouts === undefined)) {
		this._timeouts.forEach(clearTimeout);
		}
	},

	dispatchEvent: function ( event ) {

		if ( this._listeners === undefined ) return;

		var listeners = this._listeners;
		var listenerArray = listeners[ event.type ];

		if ( listenerArray !== undefined ) {

			event.target = this;

			for ( var i = 0, l = listenerArray.length; i < l; i ++ ) {

				listenerArray[ i ].call( this, event );

			}

		}

	}

};
	
		
console.log("Recipe code");	

var recipes = new Recipes();

function addFromFile(filename) {
    var recipe = new Recipe();
    var recid = recipes.addRecipe(recipe);
    console.log("Recipe id of " + filename + " is "+recid);
    xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET",filename,false);
    xmlhttp.send(null);
    var contents = xmlhttp.responseText;
    //console.log("contents = " + contents);
    eval("var recipecode = function() { " + contents + " } "); 
    recipecode.call(recipe);
    return recid;
}

console.log("Recipes code completed");

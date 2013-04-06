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

var EventDispatcher = function () {};

EventDispatcher.prototype = {

	constructor: EventDispatcher,

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

var Recipe = function () {};
Recipe.prototype = Object.create( EventDispatcher.prototype );

var recipes = new Recipes();

console.log("Recipes code completed");
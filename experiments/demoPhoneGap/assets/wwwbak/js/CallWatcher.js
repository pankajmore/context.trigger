function CallContext() {
 	var incoming = { type : 'incomingcall'};
	CallWatcher.watch(function(result) {
    			if(result.incoming) {
    				incoming.number = result.number;
					recipes.dispatchEvent(incoming);
    			}})
}
var CallWatcher = {
	watch: function(callback) {
		return cordova.exec(function(result) {
			CallWatcher.incoming = result.incoming;
			CallWatcher.number = result.number;
			if(callback) {
				callback(result);
			}

		}, CallWatcher.fail, "CallWatcher", "watch", []);
	},
	incoming: false,
	number: ""
}
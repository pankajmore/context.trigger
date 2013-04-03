function CallContext() {
 	var incoming = document.createEvent('Events');
	incoming.initEvent('incomingcall', true, true);
	console.log("Inside call context");
	CallWatcher.watch(function(result) {
    			if(result.incoming) {
    				incoming.number = result.number;
					document.dispatchEvent(incoming);
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
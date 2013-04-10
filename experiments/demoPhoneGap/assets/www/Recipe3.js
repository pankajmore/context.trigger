//  Set media volume to half, when earphone is adjusted
// argument must be from 0 to 15 inclusive.
this.on('headsetplug',function() {trigger.device.adjust("8")});

(function() {
    function log(whatever) { console.log(whatever); }
    function ajax(callback, path) {
	var xhr;
	if (window.ActiveXObject) {
            try {
		xhr = new ActiveXObject("Microsoft.XMLHTTP");
            } catch(e) {
		alert(e.message);
		xhr = null;
            }
	} else {
            xhr = new XMLHttpRequest();
	}
	xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
		callback(xhr);
            }
	}
	xhr.open('GET', path, true);
	//xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xhr.send();
	return xhr;
    }

    
    window.addEventListener('load', function() {
	var fetch = document.getElementById('fetch');
        var output = document.getElementById('output');

	fetch.addEventListener('click', function() {
	    ajax(function(xhr) {
		output.innerHTML = xhr.response;
            }, 'http://127.0.0.1:8080/type');
        });

        var wsOutput = document.getElementById('ws-output');

	(function() {
        var socket = new SockJS('/websocket');
	    var client = Stomp.over(socket);
	    client.connect({}, function(frame) {
		console.log("Connected to server");
		console.log(frame);
		client.subscribe('/fitness', function(msg) {
		    console.log("Received from server");
		    var info = JSON.parse(msg.body);
		    console.log(info);
		    wsOutput.innerHTML = msg.body + '<br />' +  wsOutput.innerHTML;
		});
	    });
	})();
    });
})();
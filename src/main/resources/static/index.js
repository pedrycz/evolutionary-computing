(function () {
    function log(whatever) {
        console.log(whatever);
    }

    function ajax(callback, path) {
        var xhr;
        if (window.ActiveXObject) {
            try {
                xhr = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                alert(e.message);
                xhr = null;
            }
        } else {
            xhr = new XMLHttpRequest();
        }
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                callback(xhr);
            }
        };
        xhr.open('GET', path, true);
        //xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send();
        return xhr;
    }

    function changeVisualization(name) {
        console.log("change vis " + name);
        for (var i = 0; i<visualizations.length; i++) {
            document.getElementById(visualizations[i]).style.display = (visualizations[i] === name ? "" : "none");
        }
    }

    function setConfigLogType(name) {
        if (name === "AllInTime")
            changeVisualization("chartAllInTime");
        else if (name === "BestInTime")
            changeVisualization("chartBestInTime");
        else if (name === "BestOfAll")
            changeVisualization("chartBestOfAll");
        else if (name === "Table")
            changeVisualization("tableOutput");
        else if (name === "LogType")
            changeVisualization("ws-output");
    }


    window.addEventListener('load', function () {
        var fetch = document.getElementById('fetch');
        var output = document.getElementById('output');

        ajax(function (xhr) {
            var config = JSON.parse(xhr.response);
            setConfigLogType(config.type);
        }, 'http://localhost:8080/config');

        fetch.addEventListener('click', function () {
            ajax(function (xhr) {
                output.innerHTML = xhr.response;
            }, 'http://127.0.0.1:8080/type');
        });

        var wsOutput = document.getElementById('ws-output');

        (function () {
            var socket = new SockJS('/websocket');
            var client = Stomp.over(socket);
            client.connect({}, function (frame) {
                console.log("Connected to server");
                console.log(frame);
                client.subscribe('/fitness', function (msg) {
                    // console.log("Received from server");
                    var info = JSON.parse(msg.body);
                    // console.log(info);
                    addPointToCharts(info.workerId, info.timestamp, info.fitness);

                    wsOutput.innerHTML = msg.body + '<br />' + wsOutput.innerHTML;
                });
            });
        })();


        document.getElementById("buttonAllInTime").addEventListener("click", function() {
            changeVisualization("chartAllInTime");
        });
        document.getElementById("buttonBestInTime").addEventListener("click", function() {
            changeVisualization("chartBestInTime");
        });
        document.getElementById("buttonBestOfAll").addEventListener("click", function() {
            changeVisualization("chartBestOfAll");
        });
        document.getElementById("buttonTable").addEventListener("click", function() {
            changeVisualization("tableOutput");
        });
        document.getElementById("buttonLogs").addEventListener("click", function() {
            changeVisualization("ws-output");
        });
    });


})();
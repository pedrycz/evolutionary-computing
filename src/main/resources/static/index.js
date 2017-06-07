var chartAllInTime;
var chartBestInTime;
var chartBestOfAll;
var dataTable;

(function () {
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
        xhr.send();
        return xhr;
    }

    function changeVisualization(name, name2) {
        for (var i = 0; i < visualizations.length; i++) {
            document.getElementById(visualizations[i]).style.display = (visualizations[i] === name ? "" : "none");
        }
    }

    function setConfigLogType(name) {
        if (name === "ChartAllInTimeType")
            changeVisualization("chartAllInTime");
        else if (name === "ChartBestInTimeType")
            changeVisualization("chartBestInTime");
        else if (name === "ChartBestOfAllType")
            changeVisualization("chartBestOfAll");
        else if (name === "TableType")
            changeVisualization("tableOutput_wrapper");
        else if (name === "LogType")
            changeVisualization("ws-output");
    }

    function subscribeForNewData() {
        var socket = new SockJS('/websocket');
        var client = Stomp.over(socket);
        client.debug = null;
        client.connect({}, function (frame) {
            console.log("Connected to server");
            client.subscribe('/fitness', function (msg) {
                var info = JSON.parse(msg.body);
                addPointToCharts(info.workerId, info.timestamp, info.fitness);
            });
        });
    }

    function setInitialData() {
        ajax(function (xhr) {
            var messages = JSON.parse(xhr.response);
            setInitialPointsToCharts(messages);

            subscribeForNewData();
        }, 'http://localhost:8080/messages');
    }

    $(document).ready( function () {
        dataTable = $('#tableOutput').DataTable();

        ajax(function (xhr) {
            var config = JSON.parse(xhr.response);
            setConfigLogType(config.type);
            biggerFitnessIsBetter = config.biggerFitnessIsBetter;
            bestFitness = biggerFitnessIsBetter ? Number.MIN_VALUE : Number.MAX_VALUE;

            setInitialData();
        }, 'http://localhost:8080/config');

        document.getElementById("buttonAllInTime").addEventListener("click", function () {
            changeVisualization("chartAllInTime");
        });
        document.getElementById("buttonBestInTime").addEventListener("click", function () {
            changeVisualization("chartBestInTime");
        });
        document.getElementById("buttonBestOfAll").addEventListener("click", function () {
            changeVisualization("chartBestOfAll");
        });
        document.getElementById("buttonTable").addEventListener("click", function() {
            changeVisualization("tableOutput_wrapper");
        });
        document.getElementById("buttonLogs").addEventListener("click", function () {
            changeVisualization("ws-output");
        });
    });

})();
var bestFitness = 0.0;
var bestSerie;
var workerSeries = [];
var bestOfAllPoints = [];
var biggerFitnessIsBetter = true;

var visualizations = ["chartAllInTime", "chartBestInTime", "chartBestOfAll",
    // "tableOutput",
    "ws-output"];

function checkWorkerSeries(workerId) {
    if (workerSeries[workerId] === undefined) {
        workerSeries[workerId] = chartAllInTime.addSeries({
            name: "Worker " + workerId,
            data: [],
            marker: {
                enabled: true,
                radius: 3
            }
        });
    }
}

function setInitialPointsToCharts(points) {
    // messages[i].workerId, messages[i].timestamp, messages[i].fitness)
    var dataBestInTime = [];
    var dataAllInTime = [];
    var dataBestOfAll = [];

    for (var i = 0; i < points.length; i++) {
        var point = {
            category: points[i].workerId,
            x: points[i].timestamp,
            y: points[i].fitness
        };

        // best in time
        if ((biggerFitnessIsBetter && point.y > bestFitness) || (!biggerFitnessIsBetter && point.y < bestFitness)) {
            dataBestInTime.push(point);
            bestFitness = point.y;
        }

        // all in time
        checkWorkerSeries(point.category);

        if (dataAllInTime[point.category] === undefined) {
            dataAllInTime[point.category] = [];
        }

        dataAllInTime[point.category].push(point);
    }

    chartBestInTime.series[0].setData(dataBestInTime);

    for (var series in dataAllInTime) {
        workerSeries[series].setData(dataAllInTime[series]);
    }

}

function addPointToCharts(workerId, xVal, value) {
    var point = {
        category: workerId,
        x: xVal,
        y: value
    };

    // best in time
    if ((biggerFitnessIsBetter && value > bestFitness) || (!biggerFitnessIsBetter && value < bestFitness)) {
        chartBestInTime.series[0].addPoint(point);
        bestFitness = value;
    }

    // all in time
    checkWorkerSeries(workerId);

    workerSeries[workerId].addPoint(point, true, false);

    // best of all
    if (bestOfAllPoints[workerId] === undefined) {
        chartBestOfAll.series[0].addPoint({
            category: "Worker " + workerId,
            x: workerId,
            y: value
        });
        bestOfAllPoints[workerId] = chartBestOfAll.series[0].data[chartBestOfAll.series[0].data.length - 1];
    } else {
        if (value > bestOfAllPoints[workerId].y) {
            bestOfAllPoints[workerId].update(value);
        }
    }

    // table
    // var date = new Date(xVal);
    //
    // var table = document.getElementById("tableOutput");
    // var row = table.insertRow(-1);
    // var cell1 = row.insertCell(0);
    // var cell2 = row.insertCell(1);
    // var cell3 = row.insertCell(2);
    // cell1.innerHTML = "Worker " + workerId;
    // cell2.innerHTML = date.getDay() + '.' +
    //     date.getMonth() + '.' +
    //     date.getFullYear() + ' ' +
    //     date.getHours() + ':' +
    //     date.getMinutes() + ':' +
    //     date.getSeconds();
    // cell3.innerHTML = value;
}

Highcharts.setOptions({
    lang: {
        noData: "Waiting for data from server"
    },

    global: {
        useUTC: false
    }
});

var chartSettings = {
    rangeSelector: {
        allButtonsEnabled: true,
        buttons: [{
            type: 'second',
            count: 30,
            text: '30 secs'
        }, {
            type: 'minute',
            count: 1,
            text: '1 min'
        }, {
            type: 'minute',
            count: 2,
            text: '2 min'
        }, {
            type: 'minute',
            count: 5,
            text: '5 min'
        }, {
            type: 'minute',
            count: 10,
            text: '10 min'
        }, {
            type: 'minute',
            count: 30,
            text: '30 min'
        }, {
            type: 'hour',
            count: 1,
            text: '1 h'
        }],
        buttonTheme: {
            width: 60
        },
        selected: 2
    }
};
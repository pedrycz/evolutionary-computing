/**
 * Created by huber on 30.05.2017.
 */
function setupChartAllInTime (dataSeries) {
    chartAllInTime = Highcharts.stockChart('chartAllInTime', {

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
        },

        title: {
            text: 'All Workers In Time'
        },

        plotOptions: {
            line: {
                enableMouseTracking: false
            },

            series: {
                pointInterval: 86400000
            }
        },

        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },

        series: dataSeries

    });

    chartAllInTime.yAxis[0].isDirty = true;
}

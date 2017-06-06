/**
 * Created by huber on 30.05.2017.
 */

var chartBestInTime = Highcharts.stockChart('chartBestInTime', {

    rangeSelector: chartSettings.rangeSelector,

    title: {
        text: 'Best Fitness In Time'
    },

    plotOptions: {
        line: {
            enableMouseTracking: false
        },

        series:{
            pointInterval: 86400000
        }
    },

    legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
    },

    series: [{
        name: 'best',
        data: [],
        marker: {
            enabled: true,
            radius: 4
        },
        tooltip: {
            valueDecimals: 2
        },
        states: {
            hover: {
                lineWidthPlus: 0
            }
        }
    }]

});

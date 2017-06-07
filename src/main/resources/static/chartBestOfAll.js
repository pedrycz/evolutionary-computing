function setupCharBestOfAll(data) {
    chartBestOfAll = Highcharts.chart('chartBestOfAll', {

        chart: {
            type: 'bar'
        },

        title: {
            text: 'Best fitness of all workers'
        },

        xAxis: {
            categories: ['Worker 0', 'Worker 1', 'Worker 2', 'Worker 3']
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Fitness of worker'
            }
        },
        legend: {
            reversed: true
        },

        plotOptions: {
            line: {
                enableMouseTracking: false
            },

            series: {
                pointInterval: 86400000
            }
        },

        series: [{
            data: data
        }]

    });
}
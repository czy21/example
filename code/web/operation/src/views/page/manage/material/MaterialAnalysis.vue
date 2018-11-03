<template>
  <div id="myChart" style="width: auto;height: 100%">

  </div>
</template>

<script>
  export default {
    name: "MaterialAnalysis",
    data() {
      return {
        hours: ["8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00"],
        days: ["周六", "周五", "周四", "周三", "周二", "周一", "周日"],
        chatRecord: [[0, 0, 4], [1, 4, 9]]
      }
    },
    methods: {
      drawLine() {
        let myChart = this.$echarts.init(document.getElementById('myChart'));

        myChart.setOption({
          title: {text: 'Vue ECharts Demo'},
          grid: {
            height: '50%',
          },
          xAxis: {
            type: 'category',
            data: this.hours,
            splitArea: {
              show: true
            }
          },
          yAxis: {
            type: 'category',
            data: this.days,
            splitArea: {
              show: true
            }
          },
          visualMap: {
            min: 0,
            max: 10,
            orient: 'horizontal',
            left: 'center',
            bottom: '30%'
          },
          series: [{
            name: 'Punch Card',
            type: 'heatmap',
            data: this.chatRecord,
            label: {
              normal: {
                show: true
              }
            },
          }]
        })
      },
    },
    mounted() {
      this.drawLine();
    }
  }
</script>


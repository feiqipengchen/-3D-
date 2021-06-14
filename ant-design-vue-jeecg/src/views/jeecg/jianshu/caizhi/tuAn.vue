<template>
  <div>
    <span style="background:#ECECEC; padding:30px;width: 500px;">
      <a-card title="储存量" :bordered="false" style="width: 300px">
        排序：<a-select default-value="0" style="width: 120px" v-model="paixu">
          <a-icon slot="suffixIcon" type="smile" />
          <a-select-option value="0">升序</a-select-option>
          <a-select-option value="1">降序</a-select-option>
        </a-select>
        <br />
        <br />
        <a-config-provider :auto-insert-space-in-button="false" style="float: right">
          <a-button type="primary" @click="gaibian">按钮</a-button>
        </a-config-provider>
      </a-card>
    </span>
    <div style="width: 800px;float: right;margin-top: -190px;">
      <a-radio-group v-model="dotPosition" style="margin-bottom: 8px">
        <a-radio-button value="top">
          Top
        </a-radio-button>
        <a-radio-button value="bottom">
          Bottom
        </a-radio-button>
        <a-radio-button value="left">
          Left
        </a-radio-button>
        <a-radio-button value="right">
          Right
        </a-radio-button>
      </a-radio-group>
      <a-carousel :dot-position="dotPosition" id="zhanxian">
        <span style="width:500px;height:500px;margin-top: 40px;" id="bingzhuangtu"></span>
        <span style="width:500px;height:500px;" id="chart"></span>
        <span style="width:500px;height:500px;" id="zhexiantu"></span>
        <span style="width:500px;height:500px; font-size: xx-large;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;font-style: oblique;">
          <p style="line-height: 350px;">谢谢观赏</p>
        </span>
      </a-carousel>
    </div>

  </div>
</template>
<script>
import { getAction, postAction, putAction, deleteAction } from '@/api/manage'
import * as echarts from 'echarts'

export default {
  created() {

  },
  data() {
    return {
      //图数据
      xAxisData: [],
      seriesData: [],
      //排序
      paixu: 0,
      //走马灯
      dotPosition: 'top'
    }
  },
  mounted() {
    let params = {
      paixu: 0
    }
    getAction('/usercustomrecord/caizhishuliangtubiao/select', params).then((res) => {
      this.xAxisData = res.result[0]
      this.seriesData = res.result[1]
      this.initCharts()
      this.bingzhuangtu()
      this.zhexiantu()
    })
  },
  methods: {
    //直方图
    initCharts() {
      let chartDom = document.getElementById('chart')
      let myChart = echarts.init(chartDom)
      // 绘制图表
      let option = {
        title: { text: '面料件数' },
        tooltip: {},
        xAxis: {
          name: '面料编号',
          data: this.xAxisData
        },
        yAxis: {
          name: '件数',
        },
        series: [{
          name: '件数',
          type: 'bar',
          data: this.seriesData
        }]
      }
      myChart.setOption(option)
    },
    //饼状图
    bingzhuangtu() {
      let chartDom = document.getElementById('bingzhuangtu')
      let myChart = echarts.init(chartDom)
      let option = {
        title: {
          text: '面料件数',
          subtext: '饼状图',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          name: '面料编号',
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '件数：',
            type: 'pie',
            radius: '70%',
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      for (var i = 0; i < this.xAxisData.length; i++) {
        option['series'][0]['data'].push({"value":this.seriesData[i] ,"name":this.xAxisData[i]});
      }
      myChart.setOption(option)
    },
    //折线图
    zhexiantu() {
      let chartDom = document.getElementById('zhexiantu')
      let myChart = echarts.init(chartDom)
      let option

      option = {
        title: { text: '面料件数' },
        tooltip: {},
        xAxis: {
          name: '面料编号',
          type: 'category',
          data: this.xAxisData
        },
        yAxis: {
          name: '件数',
          type: 'value'
        },
        series: [{
          data: this.seriesData,
          type: 'line',
          smooth: true
        }]
      }
      myChart.setOption(option)
    },
    //衣服种类图案查看
    gaibian() {
      let params = {
        paixu: this.paixu
      }
      getAction('/usercustomrecord/caizhishuliangtubiao/select', params).then((res) => {
        this.xAxisData = res.result[0]
        this.seriesData = res.result[1]
        this.initCharts()
        this.bingzhuangtu()
        this.zhexiantu()
      })
    }
  }
}
</script>
<style>
/* 走马灯 */
#zhanxian {
  text-align: center;
  background: #8cc8ff;
  overflow: hidden;
}
</style>

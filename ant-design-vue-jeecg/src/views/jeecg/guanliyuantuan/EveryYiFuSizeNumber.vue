<template>
  <div>
    <span style="background:#ECECEC; padding:30px;width: 500px;">
      <a-card title="衣服型号销售量分析" :bordered="false" style="width: 300px">
        衣服种类：<a-select default-value="xiezi" style="width: 120px" v-model="yiFuZhongLei">
          <a-icon slot="suffixIcon" type="smile" />
          <a-select-option value="shangyi">上衣</a-select-option>
          <a-select-option value="kuzi">裤子</a-select-option>
          <a-select-option value="xiezi">鞋子</a-select-option>
          <a-select-option value="quanshen">整套</a-select-option>
          <a-select-option value="peishi">其他</a-select-option>
        </a-select>
        <br />
        <br />
        衣服编号： <a-input-number :min="1" :max="1000" :default-value="3" v-model="yiFuBianHao" />
        <br />
        <br />
        <a-config-provider :auto-insert-space-in-button="false" style="float: right">
          <a-button type="primary" @click="gaibian">按钮</a-button>
        </a-config-provider>
      </a-card>
    </span>
    <div style="width: 800px;float: right;margin-top: -242px;">
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
          <p style="line-height: 350px;">谢谢观赏</p></span>
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
      xAxisData: ['M', 'L', 'XL', '2XL', '3XL'],
      seriesData: [],
      //衣服种类选择
      yiFuZhongLei: 'xiezi',
      //衣服编号
      yiFuBianHao: 1,
      //走马灯
      dotPosition: 'top'
    }
  },
  mounted() {
    let params = {
      yifuzhonglei: 'xiezi',
      yifubianhao: 1
    }
    getAction('/usercustomrecord/yonghugoumaiyifuzhongleijianshu/sizejianshufenxi', params).then((res) => {
      this.seriesData = res.result
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
        title: { text: '衣服型号销售件数' },
        tooltip: {},
        xAxis: {
          name: '衣服型号',
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
      console.log('饼状图')
      let chartDom = document.getElementById('bingzhuangtu')
      let myChart = echarts.init(chartDom)
      let option = {
        title: {
          text: '衣服型号销售件数',
          subtext: '饼状图',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: 'bottom'
        },
        toolbox: {
          show: true,
          feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            restore: {show: true},
            saveAsImage: {show: true}
          }
        },
        series: [
          {
            name: '衣服型号',
            type: 'pie',
            radius: [30, 120],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: [
              { value: this.seriesData[0], name: this.xAxisData[0] },
              { value: this.seriesData[1], name: this.xAxisData[1] },
              { value: this.seriesData[2], name: this.xAxisData[2] },
              { value: this.seriesData[3], name: this.xAxisData[3] },
              { value: this.seriesData[4], name: this.xAxisData[4] }
            ],
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
      myChart.setOption(option)
    },
    //折线图
    zhexiantu() {
      let chartDom = document.getElementById('zhexiantu')
      let myChart = echarts.init(chartDom)
      let option

      option = {
        title: { text: '衣服型号销售件数' },
        tooltip: {},
        xAxis: {
          name: '衣服型号',
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
        yifuzhonglei: this.yiFuZhongLei,
        yifubianhao: this.yiFuBianHao
      }
      getAction('/usercustomrecord/yonghugoumaiyifuzhongleijianshu/sizejianshufenxi', params).then((res) => {
        this.seriesData = res.result
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

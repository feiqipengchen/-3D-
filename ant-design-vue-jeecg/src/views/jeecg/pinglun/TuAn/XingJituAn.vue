<template>
  <div>
    <span style="background:#ECECEC; padding:30px;width: 500px;">
      <a-card title="用户星级评论分析" :bordered="false" style="width: 300px">
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
      xAxisData: ['1', '2', '3', '4', '5'],
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
    getAction('/usercustomrecord/pinglunfenxi/xingji', params).then((res) => {
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
      var dataAxis =this.xAxisData ;
      var data = this.seriesData;
      var yMax = 100;
      var dataShadow = [];

      for (var i = 0; i < data.length; i++) {
        dataShadow.push(yMax);
      }

     let option = {
        title: {
          text: '用户星级评论分析',
        },
       tooltip: {},
        xAxis: {
          name: '星级/颗',
          data: dataAxis,
          axisLabel: {
            inside: true,
            textStyle: {
              color: '#fff'
            }
          },
          axisTick: {
            show: false
          },
          axisLine: {
            show: false
          },
          z: 10
        },
        yAxis: {
          name: '个数/个',
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            textStyle: {
              color: '#999'
            }
          }
        },
        dataZoom: [
          {
            type: 'inside'
          }
        ],
        series: [
          {
            type: 'bar',
            showBackground: true,
            itemStyle: {
              color: new echarts.graphic.LinearGradient(
                0, 0, 0, 1,
                [
                  {offset: 0, color: '#83bff6'},
                  {offset: 0.5, color: '#188df0'},
                  {offset: 1, color: '#188df0'}
                ]
              )
            },
            emphasis: {
              itemStyle: {
                color: new echarts.graphic.LinearGradient(
                  0, 0, 0, 1,
                  [
                    {offset: 0, color: '#2378f7'},
                    {offset: 0.7, color: '#2378f7'},
                    {offset: 1, color: '#83bff6'}
                  ]
                )
              }
            },
            data: data
          }
        ]
      };

// Enable data zoom when user click bar.
      var zoomSize = 6;
      myChart.on('click', function (params) {
        console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
        myChart.dispatchAction({
          type: 'dataZoom',
          startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
          endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
        });
      });

      myChart.setOption(option)
    },
    //饼状图
    bingzhuangtu() {
      let chartDom = document.getElementById('bingzhuangtu')
      let myChart = echarts.init(chartDom)
      let option = option = {
        title: {
          text: '用户星级评论分析',

          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          name:'星级/颗',
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: '星级/颗；个数/个',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              { value: this.seriesData[0], name: this.xAxisData[0] },
              { value: this.seriesData[1], name: this.xAxisData[1] },
              { value: this.seriesData[2], name: this.xAxisData[2] },
              { value: this.seriesData[3], name: this.xAxisData[3] },
              { value: this.seriesData[4], name: this.xAxisData[4] }
            ]
          }
        ]
      };

      myChart.setOption(option)
    },
    //折线图
    /*zhexiantu() {
      let chartDom = document.getElementById('zhexiantu')
      let myChart = echarts.init(chartDom)
      let option

      option = {
        title: { text: '衣服型号件数' },
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
    },*/
    zhexiantu() {
      let chartDom = document.getElementById('zhexiantu')
      let myChart = echarts.init(chartDom)
      let option

     option = {
       title: { text: '用户星级评论分析' },
       tooltip: {},
        xAxis: {
         name:'星级/颗',
          type: 'category',
          boundaryGap: false,
          data:this.xAxisData
        },
        yAxis: {
         name:'个数/个',
          type: 'value'
        },
        series: [{
          data:this.seriesData,
          type: 'line',
          smooth: true,
          areaStyle: {}
        }]
      };
      myChart.setOption(option)
    },
    //衣服种类图案查看
    gaibian() {
      let params = {
        yifuzhonglei: this.yiFuZhongLei,
        yifubianhao: this.yiFuBianHao
      }
      getAction('/usercustomrecord/pinglunfenxi/xingji', params).then((res) => {
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

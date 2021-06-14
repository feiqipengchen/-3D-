<template>
  <div>
    <a-card hoverable style="width: 240px">
      <img
        slot="cover"
        alt="example"
        src="https://img.zcool.cn/community/01afb35a0aab90a80121985ce72aac.JPG@1280w_1l_2o_100sh.jpg"
      />
      <a-card-meta title="销售衣服种类件数分析">
        <template slot="description">
        见右图
          <br />
          <a style="float: right" href="http://localhost:3000/jeecg/3D/3D1">衣服定制</a>
        </template>
      </a-card-meta>
    </a-card>
    <a-carousel effect="fade" style="width: 900px;float: right;margin-top: -473px;">
      <span style="width:500px;height:500px;" id="chart"></span>
      <span style="width:500px;height:500px;" id="zhexiantu"></span>
      <span style="width:500px;height:500px;margin-top: 40px;" id="bingzhuangtu"></span>
      <span style="width:500px;height:500px; font-size: xx-large;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;font-style: oblique;">
          <p style="line-height: 350px;">谢谢观赏</p>
        </span>
    </a-carousel>
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
      xAxisData: ['上衣','裤子','鞋子','整套','其他'],
      seriesData: [],
      //走马灯
      dotPosition: 'top'
    }
  },
  mounted() {
    let params = {
    }
    getAction('/usercustomrecord/yonghugoumaiyifuzhongleijianshu/jianshufenxi', params).then((res) => {
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
      let  option = {
        title: {
          text: '销售衣服种类数量分析',
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: []
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          name:'件数',
          type: 'value',
          boundaryGap: [0, 0.01]
        },
        yAxis: {
          name:'衣服种类',
          type: 'category',
          data: this.xAxisData
        },
        series: [
          {
            type: 'bar',
            data: this.seriesData
          }
        ]
      };

      myChart.setOption(option)
    },
    //饼状图
    bingzhuangtu() {
      let chartDom = document.getElementById('bingzhuangtu')
      let myChart = echarts.init(chartDom)
      let option = {
        title: {
          text: '销售衣服种类数量分析',
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
            name: '衣服种类',
            type: 'pie',
            radius: [30, 150],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: []
          }
        ]
      };

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
        title: { text: '销售衣服种类数量分析' },
        tooltip: {},
        xAxis: {
          name: '衣服种类',
          type: 'category',
          boundaryGap: false,
          data: this.xAxisData
        },
        yAxis: {
          name:'件数',
          type: 'value'
        },
        series: [{
          data:this.seriesData,
          type: 'line',
          areaStyle: {}
        }]
      };

      myChart.setOption(option)
    }
  }
}
</script>
<style scoped>
/* For demo */
.ant-carousel >>> .slick-slide {
  text-align: center;
  height:600px;
  line-height: 160px;
  background: gainsboro;
  overflow: hidden;
}

.ant-carousel >>> .slick-slide h3 {
  color: #fff;
}
</style>
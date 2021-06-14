<template>
  <div>
    <a-card hoverable style="width: 240px">
      <img
        slot="cover"
        alt="example"
        src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
      />
      <a-card-meta title="购买衣服种类件数分析">
        <template slot="description">
        见右图
          <br />
          <a style="float: right" href="http://localhost:3000/jeecg/3D/3D1">衣服定制</a>
        </template>
      </a-card-meta>
    </a-card>
    <div style="width: 75%;float: right;margin-top:-411px;">
      <a-carousel arrows>
        <div
          slot="prevArrow"
          slot-scope="props"
          class="custom-slick-arrow"
          style="left: 10px;zIndex: 1"
        >
          <a-icon type="left-circle" />
        </div>
        <div slot="nextArrow" slot-scope="props" class="custom-slick-arrow" style="right: 10px">
          <a-icon type="right-circle" />
        </div>
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
import store from '@/store'

export default {
  created() {

  },
  data() {
    return {
      //图数据
      xAxisData: ["上衣","裤子","鞋子","整套","其他"],
      seriesData: [],
      //走马灯
      dotPosition: 'top'
    }
  },
  mounted() {
    let params = {
      dingdanren:store.getters.userInfo.username
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
      let option = {
        title: { text: '用户购买衣服种类件数分析' },
        tooltip: {},
        xAxis: {
          name:'衣服种类',
          type: 'category',
          data: this.xAxisData,
        },
        yAxis: {
          name:'件数',
          type: 'value'
        },
        series: [{
          data: this.seriesData,
          type: 'bar',
          showBackground: true,
          backgroundStyle: {
            color: 'rgba(180, 180, 180, 0.2)'
          }
        }]
      };
      myChart.setOption(option)
    },
    //饼状图
    bingzhuangtu() {
      let chartDom = document.getElementById('bingzhuangtu')
      let myChart = echarts.init(chartDom)
      let option = {
        title: {
          text: '用户购买衣服种类件数分析',
          subtext: '饼状图',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'left'
        },
        series: [
          {
            name: '衣服种类件数',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
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
            data: []
          }
        ]
      };

      for (var i = 0; i < this.xAxisData.length; i++) {
        option['series'][0]['data'].push({"value": this.seriesData[i],"name":this.xAxisData[i]});
      }
      myChart.setOption(option)
    },
    //折线图
    zhexiantu() {
      let chartDom = document.getElementById('zhexiantu')
      let myChart = echarts.init(chartDom)
      let option

      option = {
        title: { text: '用户购买衣服种类件数分析' },
        tooltip: {},
        xAxis: {
          name: '衣服种类',
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
        }]
      }
      myChart.setOption(option)
    },
  }
}
</script>
<style scoped>
/* For demo */
.ant-carousel >>> .slick-slide {
  text-align: center;
  height: 100%;
  line-height: 160px;
  background: #409eff;
  overflow: hidden;
}

.ant-carousel >>> .custom-slick-arrow {
  width: 25px;
  height: 25px;
  font-size: 25px;
  color: #fff;
  background-color: rgba(31, 45, 61, 0.11);
  opacity: 0.3;
}

.ant-carousel >>> .custom-slick-arrow:before {
  display: none;
}

.ant-carousel >>> .custom-slick-arrow:hover {
  opacity: 0.5;
}

.ant-carousel >>> .slick-slide h3 {
  color: #fff;
}
</style>

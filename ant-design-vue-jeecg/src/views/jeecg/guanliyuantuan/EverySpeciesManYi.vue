<template>
  <div>
    <a-card hoverable style="width: 240px">
      <img
        width="300px"
        slot="cover"
        alt="example"
        src="https://img.alicdn.com/i2/343341492/TB26RGuoVXXXXaLXpXXXXXXXXXX-343341492.jpg"
      />
      <a-card-meta title="用户衣服满意分析">
        <template slot="description">
          衣服种类：<a-select default-value="shangyi" style="width: 120px" v-model="yiFuZhongLei">
          <a-icon slot="suffixIcon" type="smile" />
          <a-select-option value="shangyi">上衣</a-select-option>
          <a-select-option value="kuzi">裤子</a-select-option>
          <a-select-option value="xiezi">鞋子</a-select-option>
          <a-select-option value="quanshen">整套</a-select-option>
          <a-select-option value="peishi">其他</a-select-option>
        </a-select>
          <br />
          <br />
          <a-config-provider :auto-insert-space-in-button="false" style="margin-left: 130px;">
            <a-button type="primary" @click="gaibian">按钮</a-button>
          </a-config-provider>
          <br />
          <br />
          <a style="float: right" href="http://localhost:3000/jeecg/3D/3D1">衣服定制</a>
        </template>
      </a-card-meta>
    </a-card>
    <div style="width: 80%;float: right;margin-top:-438px;">
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
      xAxisData: [],
      seriesData: [],
      //走马灯
      dotPosition: 'top',
      //衣服种类
      yiFuZhongLei:'shangyi'
    }
  },
  mounted() {
    let params = {
      yifuzhonglei:this.yiFuZhongLei
    }
    getAction('/usercustomrecord/pinglunfenxi/xiaolian', params).then((res) => {
      this.xAxisData=res.result[0]
      this.seriesData = res.result[1]
      this.initCharts()
      this.bingzhuangtu()
      this.zhexiantu()
    })
  },
  methods: {
    //改变衣服种类
    gaibian(){
      let params = {
        yifuzhonglei:this.yiFuZhongLei
      }
      getAction('/usercustomrecord/pinglunfenxi/xiaolian', params).then((res) => {
        this.xAxisData=res.result[0]
        this.seriesData = res.result[1]
        this.initCharts()
        this.bingzhuangtu()
        this.zhexiantu()
      })
    },
    //直方图
    initCharts() {
      let chartDom = document.getElementById('chart')
      let myChart = echarts.init(chartDom)
      // 绘制图表
      let  option = {
        title: { text: '用户衣服满意分析' },
        tooltip: {},
        xAxis: {
          name:'衣服标号',
          type: 'category',
          data: this.xAxisData
        },
        yAxis: {
          name:'个数',
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
          text: '用户衣服满意分析',
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
            name: '衣服编号；个数',
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
        title: { text: '用户衣服满意分析' },
        tooltip: {},
        xAxis: {
          name: '衣服编号',
          type: 'category',
          data: this.xAxisData
        },
        yAxis: {
          name: '个数',
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
  background: #ebebeb;
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

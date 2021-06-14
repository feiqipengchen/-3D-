<template>
  <div>
    <a-card hoverable style="width: 240px">
      <img
        slot="cover"
        alt="example"
        src="https://img.zcool.cn/community/0188935796d7490000012e7ed473a3.jpg@1280w_1l_2o_100sh.jpg"
      />
      <a-card-meta title="衣服种类编号件数分析">
        <template slot="description">
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
          <a-config-provider :auto-insert-space-in-button="false" style="float: right">
            <a-button type="primary" @click="gaibian">按钮</a-button>
          </a-config-provider>
          <br />
          <br />
          <a style="float: right" href="http://localhost:3000/jeecg/3D/3D1">衣服定制</a>
        </template>
      </a-card-meta>
    </a-card>
    <a-carousel effect="fade" style="width: 890px;float: right;margin-top: -428px;">
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
      xAxisData: [],
      seriesData: [],
      //走马灯
      dotPosition: 'top',
      yiFuZhongLei:'xiezi'
    }
  },
  mounted() {
    let params = {
      yifuzhonglei:this.yiFuZhongLei
    }
    getAction('/usercustomrecord/yonghugoumaiyifuzhongleijianshu/zhongleineijianshufenxi', params).then((res) => {
     this.xAxisData=res.result[0]
      this.seriesData = res.result[1]
      this.initCharts()
      this.bingzhuangtu()
      this.zhexiantu()
    })
  },
  methods: {
    // 改变衣服种类
    gaibian(){
      let params = {
        yifuzhonglei:this.yiFuZhongLei
      }
      getAction('/usercustomrecord/yonghugoumaiyifuzhongleijianshu/zhongleineijianshufenxi', params).then((res) => {
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
        title: {
          text: '销售衣服种类编号数量分析',
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
          name:'衣服编号',
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
          text: '销售衣服种类编号数量分析',
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
            name: '衣服编号',
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
        title: { text: '销售衣服种类标号数量分析' },
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
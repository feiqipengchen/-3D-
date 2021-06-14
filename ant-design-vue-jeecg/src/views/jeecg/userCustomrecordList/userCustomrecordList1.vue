<template>
  <div>
    <div class="components-input-demo-size" style="margin-bottom:10px;">
      &nbsp;&nbsp;&nbsp;&nbsp;
      <a-tooltip placement="topLeft" title="请输入需要搜索的衣服种类">
        <a-select default-value="shangyi" style="width: 120px" v-model="yifuzhonglei">
          <a-icon slot="suffixIcon" type="smile" />
          <a-select-option value="shangyi">
            上衣
          </a-select-option>
          <a-select-option value="kuzi">
            裤子
          </a-select-option>
          <a-select-option value="xiezi">
            鞋子
          </a-select-option>
          <a-select-option value="quanshen">
            整套
          </a-select-option>
          <a-select-option value="peishi">
            其他
          </a-select-option>
        </a-select>
      </a-tooltip>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <a-input-number style="width: 150px" :min="0" :max="10" :default-value="1"  placeholder="请输入搜索衣服编号" v-model="yifubianhao"/>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <a-input-number style="width: 150px" :min="0" :max="10" :default-value="1"  placeholder="请输入搜索材质编号" v-model="caizhibainhao"/>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <a-tooltip placement="topLeft" title="请输入需要搜索的衣服型号">
        <a-select default-value="M" style="width: 120px" v-model="yifuxinghao">
          <a-icon slot="suffixIcon" type="smile" />
          <a-select-option value="M">
            M
          </a-select-option>
          <a-select-option value="L">
            L
          </a-select-option>
          <a-select-option value="XL">
            XL
          </a-select-option>
          <a-select-option value="2XL">
            2XL
          </a-select-option>
          <a-select-option value="3XL">
            3XL
          </a-select-option>
        </a-select>
      </a-tooltip>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <a-button type="primary" @click="userCustomRecordSelect">
        搜索
      </a-button>
      &nbsp;&nbsp;
      <a-button type="primary" @click="reset">
        重置
      </a-button>
    </div>
    <a-table :columns="columns" :data-source="dataSource" rowKey="id" bordered>
      <template slot="name" slot-scope="text">
        <a>{{ text }}</a>
      </template>
      <template slot="title" slot-scope="currentPageData">
        <center><h3>用户订单记录</h3></center>
      </template>
      <p slot="id" slot-scope="text,tags,i">
        {{ tags.id }}
      </p>
      <template slot="footer" slot-scope="currentPageData">
        这里是您的订单记录哟！！！
      </template>
    </a-table>
  </div>
</template>

<script>
import store from '@/store'
import { getAction } from '@api/manage'

export default {
data(){
  return{
    columns:[
      {
        title: '流水编号',
        dataIndex: 'id',
        scopedSlots: { customRender: 'id' },
      }
      ,
      {
        title: '订单人',
        dataIndex: 'dingdanren',
        scopedSlots: { customRender: 'name' },
      },
      {
        title: '衣服种类',
        className: 'column-money',
        dataIndex: 'yifuzhonglei',
      },
      {
        title: '衣服编号',
        dataIndex: 'yifubianhao',
      },
      {
        title: '材质编号',
        dataIndex: 'caizhibainhao',
      },
      {
        title: '颜色r',
        dataIndex: 'yifuyanser',
      },
      {
        title: '颜色g',
        dataIndex: 'yifuyanseg',
      },
      {
        title: '颜色b',
        dataIndex: 'yifuyanseb',
      },
      {
        title: '衣服型号',
        dataIndex: 'yifuxinghao',
      },
      {
        title: '件数',
        dataIndex: 'jianshu',
      },
      {
        title: '订单日期',
        dataIndex: 'createTime',
      },
    ],
    dataSource:[

    ],
    yifuzhonglei:null,
    yifubianhao: null,
    yifuxinghao: null,
    caizhibainhao: null
  }
},
  created() {//页面渲染前加载，mutation页面渲染后加载
    let params={}
    params['dingdanren']=store.getters.userInfo.username
    getAction('/usercustomrecord/user',params).then((res) => {
      console.log('------------')
      console.log('res',res)
      this.dataSource = res.result;
    })
  },
  methods: {
    userCustomRecordSelect() {
      let params={}
      params['dingdanren']=store.getters.userInfo.username
      params['yifuzhonglei']=this.yifuzhonglei
      params['yifubianhao']=this.yifubianhao
      params['yifuxinghao']=this.yifuxinghao
      params['caizhibainhao']=this.caizhibainhao
      getAction('/usercustomrecord/getUserCustomRecordSelect',params).then((res) => {
        console.log('------------')
        console.log('res',res)
        this.dataSource = res.result;
      })
    },
    reset(){
      this.yifuzhonglei=null
      this.yifubianhao=null
      this.yifuxinghao=null
      this.caizhibainhao=null
    }
  },
}
</script>

<style>
th.column-money,
td.column-money {
  text-align: right !important;
}
.components-input-demo-size .ant-input {
  width: 200px;
  margin: 0 8px 8px 0;
}
.ant-input-number {
  margin-right: 10px;
}
</style>

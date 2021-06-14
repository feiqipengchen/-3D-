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
      <a-input-number style="width: 150px" :min="0" :max="10" :default-value="1" placeholder="请输入搜索衣服编号"
                      v-model="yifubianhao" />
      &nbsp;&nbsp;&nbsp;&nbsp;
      <a-input-number style="width: 150px" :min="0" :max="10" :default-value="1" placeholder="请输入搜索材质编号"
                      v-model="caizhibainhao" />
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
        <center><h3>用户收藏单</h3></center>
      </template>
      <p slot="id" slot-scope="text,tags,i">
        {{ tags.id }}
      </p>
      <p slot="wancheng" slot-scope="text,tags,i">
        <a-popconfirm
          title="确认定制吗?"
          ok-text="确认"
          cancel-text="取消"
          @confirm="edit(text,tags,i)"
          @cancel="cancel"
        >
          <a-config-provider :auto-insert-space-in-button="false">
            <a-button type="primary">
              {{ tags.wancheng }}
            </a-button>
          </a-config-provider>
        </a-popconfirm>
      </p>
      <template slot="footer" slot-scope="currentPageData">
        这里是目前您的收藏哟！！！
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
          scopedSlots: { customRender: 'id' }
        },
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
          title:'状态',
          dataIndex: 'wancheng',
          scopedSlots: { customRender: 'wancheng' },
        }
      ],
      dataSource:[

      ],
      yifuzhonglei:null,
      yifubianhao: null,
      yifuxinghao: null,
      caizhibainhao: null,
      yongHu:null,
      yiFuBiaoJia1:0
    }
  },
  created() {//页面渲染前加载，mutation页面渲染后加载
    let params={}
    params['dingdanren']=store.getters.userInfo.username
    getAction('/usercustomrecord/getUserUnCustom',params).then((res) => {
      this.dataSource = res.result;
    })
  },
  methods: {
    //8调用  搜索输入框的记录
    userCustomRecordSelect() {
      let params={}
      params['dingdanren']=store.getters.userInfo.username
      params['yifuzhonglei']=this.yifuzhonglei
      params['yifubianhao']=this.yifubianhao
      params['yifuxinghao']=this.yifuxinghao
      params['caizhibainhao']=this.caizhibainhao
      getAction('/usercustomrecord/getUserUnCustomSelect',params).then((res) => {
        this.dataSource = res.result;
        this.yifuzhonglei=null
        this.yifuxinghao=null
        this.caizhibainhao=null
        this.yifubianhao=null
      })
    },
    //11 重置搜索框
    reset(){
      this.yifuzhonglei=null
      this.yifubianhao=null
      this.yifuxinghao=null
      this.caizhibainhao=null
    },
    //确认框取消
    cancel() {
      this.$message.error('已经取消');
    },
    //定制衣服
    edit(text,tags,i){
      let params1={}
      params1['yonghu']=store.getters.userInfo.username
      //获取用户信息
      getAction('/usercustomrecord/yonghuqian/select', params1).then((res) => {
        this.yongHu= res.result
        if (this.yongHu===null){
          this.openNotificationWithIcon1('warning')
          return false
        }
        let params2={}
        params2['yifuzhonglei']=tags.yifuzhonglei
        params2['yifubianhao']=tags.yifubianhao
        params2['caizhibianhao']=tags.caizhibainhao
        getAction('/usercustomrecord/yifubiaojia/select', params2).then((res1) => {
          this.yiFuBiaoJia1= res1.result
          if (this.yongHu['qian']< this.yiFuBiaoJia1*parseInt(tags.jianshu)){
            this.openNotificationWithIcon('error')
            return false;
          }
          this.yongHu['qian']=this.yongHu['qian']-this.yiFuBiaoJia1*parseInt(tags.jianshu)
          getAction('/usercustomrecord/yonghuqian/update', this.yongHu).then((res2) => {
            this.$message.success('已经确认');
            let params3={}
            params3['id']=tags.id
            getAction('/usercustomrecord/updateUserUnCustomSelect1',params3).then((res3) => {
              this.dataSource = res3.result;
              let params4={}
              params4['dingdanren']=store.getters.userInfo.username
              getAction('/usercustomrecord/getUserUnCustom',params4).then((res) => {
                this.dataSource = res.result;
              })
              alert("定制成功")
            })
          })
        })
      })
    },

    //25  用户定制操作
    openNotificationWithIcon(type) {
      this.$notification[type]({
        message: '定制失败',
        description:
          '账户余额不足，请充值后在定制',
      });
    },
    openNotificationWithIcon1(type) {
      this.$notification[type]({
        message: '定制失败',
        description:
          '钱包业务没开通，请开通后再次定制',
      });
    },
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
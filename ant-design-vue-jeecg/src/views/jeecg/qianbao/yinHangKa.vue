<template>
  <div>
    <chong-zhi-qian :kaHao="kaHao" v-show="chongZhiTanKuang" @chongzhixiaoshi="chongzhixiaoshi"
                    style="margin:auto;"></chong-zhi-qian>
    <br />
    <br />
    <a-tag color="pink">
      <strong>用户绑定银行卡列表</strong>
    </a-tag>
    <a-table :columns="columns" :data-source="dataSource" rowKey="kahao"
             style="width:800px;float: right;overflow:scroll;height: 210px;">
      <div slot="chongzhi" slot-scope="text,tags,i">
        <a-config-provider :auto-insert-space-in-button="false">
          <a-button type="primary" @click="chongzhixianashi(text,tags,i)">
            充值
          </a-button>
        </a-config-provider>
      </div>
      <div slot="tixian" slot-scope="text,tags,i">
        <a-config-provider :auto-insert-space-in-button="false">
          <a-button type="primary" @click="showModal(text,tags,i)">
            提现
          </a-button>
        </a-config-provider>
        <a-modal v-model="visible" title="提现" @ok="handleOk">
          <ti-xian-qian :kaHao="kaHao"></ti-xian-qian>
        </a-modal>
      </div>
      <div slot="jiechubangding" slot-scope="text,tags,i">
        <a-config-provider :auto-insert-space-in-button="false">
          <a-button type="primary" @click="showDeleteConfirm(text,tags,i)">
            解绑
          </a-button>
        </a-config-provider>
      </div>
    </a-table>

  </div>
</template>
<script>
import { getAction } from '@api/manage'
import store from '@/store'
import chongZhiQian from '@views/jeecg/qianbao/chongZhiQian'
import tiXianQian from '@views/jeecg/qianbao/tiXianQian'

export default {
  name: 'yinHangKa',
  data() {
    return {
      columns: [
        {
          title: '卡号',
          dataIndex: 'kahao',
          key: 'kahao'
        },
        {
          title: '充值',
          dataIndex: 'chongzhi',
          scopedSlots: { customRender: 'chongzhi' }
        },
        {
          title: '提现',
          dataIndex: 'tixian',
          scopedSlots: { customRender: 'tixian' }
        },
        {
          title: '解除绑定',
          dataIndex: 'jiechubangding',
          scopedSlots: { customRender: 'jiechubangding' }
        }
      ],
      dataSource: [],
      //充值弹出框变量
      chongZhiTanKuang: false,
      //卡号
      kaHao: '0',
      //提现弹出框显示
      visible: false
    }
  },
  created() {
    let params = []
    params['yonghu'] = store.getters.userInfo.username
    getAction('/usercustomrecord/bangding/select', params).then((res) => {
      this.dataSource = res.result
    })
  },
  components: {
    chongZhiQian,
    tiXianQian
  },
  methods: {
    //充值输入框显示
    chongzhixianashi(text, tags, i) {
      this.chongZhiTanKuang = true
      this.kaHao = tags.kahao
    },
    //充值输入框消失
    chongzhixiaoshi() {
      let that = this
      that.chongZhiTanKuang = false
    },
    //提现弹出框显示消失
    showModal(text, tags, i) {
      this.visible = true
      this.kaHao = tags.kahao
    },
    handleOk() {
      this.visible = false
    },
    //解除绑定确认
    showDeleteConfirm(text, tags, i) {
      var that = this
      this.$confirm({
        title: '解除绑定',
        content: '确认解除绑定？',
        okText: '确认',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          let params = {}
          params['yonghu'] = store.getters.userInfo.username
          params['kahao'] = tags.kahao
          getAction('/usercustomrecord/bangding/delete', params).then((res) => {
            let params1 = {}
            params1['yonghu'] = store.getters.userInfo.username
            getAction('/usercustomrecord/bangding/select', params1).then((res1) => {
              that.dataSource = res.result
              that.openNotification()
            })
          })
        },
        onCancel() {
        }
      })

    },
    openNotification() {
      this.$notification.open({
        message: '成功',
        description:
          '解除与该银行卡的绑定成功',
        icon: <a-icon type="smile" style="color: #108ee9" />
      })
    }
  }
}
</script>
<style>
a-table {

}

strong {
  font-family: 仿宋;
  font-style: revert;
  color: #4D90FE;
  font-size: large;
}
</style>

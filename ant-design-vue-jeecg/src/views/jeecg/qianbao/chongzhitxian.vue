<template>
  <div id="components-layout-demo-basic">
    <a-layout>
      <a-layout-header><strong><h2>我的钱包</h2></strong></a-layout-header>
      <a-layout>
        <a-layout-sider>
          <div v-if="qianbao()">
            <kaiTong></kaiTong>
          </div>
          <div v-else>
            <chong-zhi></chong-zhi>
          </div>
        </a-layout-sider>
        <a-layout-content>
          <a-space size="middle" id="bangding">
            <strong>绑定银行卡</strong>
            <br />
            <a-input placeholder="请输入卡号" style="width: 200px;" v-model="kaHao" />
            <a-input-password placeholder="请输入卡号密码" style="width: 200px;" v-model="miMa" />
            <a-popconfirm
              title="确认绑定该银行卡?"
              ok-text="确认"
              cancel-text="取消"
              @confirm="confirm"
              @cancel="cancel"
            >
              <a-config-provider :auto-insert-space-in-button="false">
                <a-button type="primary">
                  绑定
                </a-button>
              </a-config-provider>
            </a-popconfirm>
          </a-space>
          <br />
          <br />
          <br />
          <yin-hang-ka></yin-hang-ka>
        </a-layout-content>
      </a-layout>
      <a-layout-footer>
       <u><p>我的余额：{{ yuE }}元</p></u>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script>
import { getAction } from '@api/manage'
import store from '@/store'
import kaiTong from '@views/jeecg/qianbao/kaiTong'
import chongZhi from '@views/jeecg/qianbao/chongZhi'
import yinHangKa from '@views/jeecg/qianbao/yinHangKa'

export default {
  data() {
    return {
      //搜索到的用户
      yongHu: [],
      yuE: 0,
      //绑定银行卡号密码
      kaHao: '1479008950',
      miMa: '1479008950',
      //搜索的卡号信息，
      yingHangKa: []
    }
  },
  components: {
    kaiTong,
    chongZhi,
    yinHangKa
  },
  methods: {
    //判断用户是否开通我的钱包
    qianbao() {
      let params = {}
      params['yonghu'] = store.getters.userInfo.username
      getAction('/usercustomrecord/yonghuqian/select', params).then((res) => {
        this.yongHu = res.result
      })
      if (this.yongHu === null) {
        this.openNotificationWithIcon('warning')
        return true
      } else {
        this.yuE = this.yongHu['qian']
        return false
      }
    },
    //业务开通提示框(通知提醒框)
    openNotificationWithIcon(type) {
      this.$notification[type]({
        message: '钱包业务开通',
        description:
          '您的钱包业务还没有开通，请设置钱包支付密码及时开通'
      })
    },
    //确认绑定银行卡与否
    confirm() {
      let params = {}
      params['kahao'] = this.kaHao
      getAction('/usercustomrecord/kahaoqian/select', params).then((res) => {
        this.yingHangKa = res.result
        if (this.yingHangKa === null) {
          this.$message.success('没有该银行卡，请确认后再绑定')
          return
        }
        if (this.yingHangKa['mima'] !== this.miMa) {
          this.$message.success('输入的银行卡密码错误')
          return
        }
        let params1 = {}
        params1['kahao'] = this.kaHao
        params1['yonghu'] = store.getters.userInfo.username
        getAction('/usercustomrecord/bangding/insert', params1).then((res1) => {
          console.log('res', res)
        })
        this.$message.success('绑定成功')
      })

    },
    cancel() {
      this.$message.error('取消绑定')
    }
  }
}
</script>

<style>

#components-layout-demo-basic {
  text-align: center;
  background: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic169.nipic.com%2Ffile%2F20180614%2F11949215_212325983000_2.jpg&refer=http%3A%2F%2Fpic169.nipic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1621773544&t=ae81e3e24d5f5a7b97e753115a4a3f59");
}

#components-layout-demo-basic .ant-layout-header,
#components-layout-demo-basic .ant-layout-footer {
  background-color: #ebf3fd;

}

h2 {
  font-family: 仿宋;
  font-style: normal;
  color: #2eabff;
}

#components-layout-demo-basic .ant-layout-sider {
  background-color: #ebf3fd;
}

#components-layout-demo-basic .ant-layout-content {
  background-color: #ebf3fd;
}

#components-layout-demo-basic > .ant-layout {
}

#components-layout-demo-basic > .ant-layout:last-child {
  margin: 0;
}

strong{
  font-family: 仿宋;
  font-style: revert;
  color: #4D90FE;
  font-size: large;
}
p{
  font-family: 仿宋;
  font-style: revert;
  color: #5c6065;
  font-size: large;
  font-weight: 700;
}
</style>

<template>
  <div id="color">
    <div id="xuanze">
      <p>颜色调试</p>
      <input type="range" max="255" v-model:value="message[0]" />
      <input type="range" max="255" v-model:value="message[1]" />
      <input type="range" max="255" v-model:value="message[2]" />
      <p>衣服种类：<select v-model="zhongLei">
        <option value="kuzi">裤子</option>
        <option value="shangyi">上衣</option>
        <option value="xiezi">鞋子</option>
        <option value="quanshen">全身</option>
        <option value="peishi">配饰</option>
      </select></p>
      <p>选<select v-model="yiFu">
        <option v-for="index in xuHao" :value="index">{{ index }}</option>
      </select>号
      </p>
      <p>选<select v-model="caiZhi">
        <option v-for="index in xuHao" :value="index">{{ index }}</option>
      </select>号材质
      </p>
      <p>型号：<select v-model="size">
        <option value="M">M</option>
        <option value="L">L</option>
        <option value="XL">XL</option>
        <option value="2XL">2XL</option>
        <option value="3XL">3XL</option>
      </select></p>
        <a-icon type="skin" style="font-size: 18px;color:#DDA0DD; " /> &nbsp;剩余件数：{{ shengYuJianShu }}
      <p>
        定制件数：
        <a-input-number size="small" :min="1" :max="10" :default-value="1" v-model="jianshu" />
      </p>
      单价：{{ jiaGe }}&nbsp;&nbsp;&nbsp;
      <a-icon type="pay-circle" style="font-size: 18px;color:#DDA0DD; " />
      <br />
      总价：{{ jiaGe * jianshu }}&nbsp;&nbsp;&nbsp;
      <a-icon type="pay-circle" style="font-size: 18px;color:#DDA0DD; " />
    </div>
    <a-space size="middle" id="dingzhi">
      <a-config-provider :auto-insert-space-in-button="false">
        <a-button type="primary" @click="handleSubmit">
          定制
        </a-button>
      </a-config-provider>
      <a-config-provider :auto-insert-space-in-button="false">
        <a-button type="primary" @click="shoucang">
          收藏
        </a-button>
      </a-config-provider>
      <a-config-provider :auto-insert-space-in-button="false">
        <a-button type="primary">
          <a :href="lianJie">发表言论</a>
        </a-button>
      </a-config-provider>
    </a-space>
    <div>
      <div>
        <a-button @click="successdianzan" style="background-color: #87e8de">
          <a-icon type="heart" theme="twoTone" two-tone-color="#eb2f96" style="font-size: 18px" />
          {{ dianZan }}
        </a-button>
      </div>
      <div id="pingjia">
        衣服评价：
        <a-space size="middle">
          <a-button @click="successxiaolian" style="background-color: #87e8de">
            <a-icon type="smile" theme="twoTone" style="font-size: 18px" />
            {{ xiaoLian }}
          </a-button>
          <a-button @click="successzhonglian" style="background-color: #87e8de">
            <a-icon type="meh" theme="twoTone" style="font-size: 18px" />
            {{ zhongLian }}
          </a-button>
          <a-button @click="successkulian" style="background-color: #87e8de">
            <a-icon type="frown" theme="twoTone" style="font-size: 18px" />
            {{ kuLian }}
          </a-button>
        </a-space>
      </div>
    </div>
  </div>
</template>

<script>
import PubSub from 'pubsub-js'
import { getAction, postAction } from '@/api/manage'
import store from '@/store'

export default {
  data() {
    return {
      //定制衣服信息
      message: [1, 2, 3],
      yiFu: 1,
      caiZhi: 1,
      xuHao: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15],
      zhongLei: 'shangyi',
      size: 'L',
      jianshu: 1,
      //点赞
      dianZan: 0,
      xiaoLian: 0,
      zhongLian: 0,
      kuLian: 0,
      //链接
      lianJie: 'http://localhost:3000/jeecg/pinglun/pinglun?yifuzhonglei=' + this.zhongLei + '&yifubianhao=' + this.yiFu + '&caizhibianhao=' + this.caiZhi,
      //衣服价格
      jiaGe: 0,
      //用户余额
      yongHu: null,
      //衣服剩余件数
      shengYuJianShu: 0
    }
  },
  watch: {
    message: {
      deep: true,
      handler: function(value) {
        PubSub.publish('updateMessage', value)
      }
    },
    yiFu: {
      deep: true,
      handler: function(value) {
        this.update()
        PubSub.publish('updateYiFu', value)
      }
    },
    caiZhi: {
      deep: true,
      handler: function(value) {
        this.update()
        PubSub.publish('updateCaiZhi', value)
      }
    },
    zhongLei: {
      deep: true,
      handler: function(value) {
        this.update()
        PubSub.publish('updateZhongLei', value)
      }
    },
    size: {
      deep: true,
      handler: function(value) {
        PubSub.publish('updateSize', value)
      }
    }
  },
  created() {//页面渲染前加载，mutation页面渲染后加载
    let params = {}
    params['yifubianhao'] = this.yiFu
    params['caizhibianhao'] = this.caiZhi
    params['yifuzhonglei'] = this.zhongLei
    getAction('/usercustomrecord/dianzan/select', params).then((res) => {
      //console.log('------------')
      //console.log('res', res)
      this.dianZan = res.result['dianzan']
      this.xiaoLian = res.result['xiaolian']
      this.zhongLian = res.result['zhonglian']
      this.kuLian = res.result['kulian']
      this.lianJie = 'http://localhost:3000/jeecg/pinglun/pinglun?yifuzhonglei=' + this.zhongLei + '&yifubianhao=' + this.yiFu + '&caizhibianhao=' + this.caiZhi

    })
    //获取衣服价格
    getAction('/usercustomrecord/yifubiaojia/select', params).then((res) => {
      // console.log('------------')
      // console.log('res', res)
      this.jiaGe = res.result
    })
    //获取衣服剩余件数
    params['yifuxinghao'] = this.size
    getAction('/usercustomrecord/yifu/select', params).then((res) => {
      //console.log('res', res)
      let yifushengyu = res.result[0]['jianshu']
      getAction('/usercustomrecord/caizhi/select', params).then((res1) => {
        // console.log('res', res1)
        let caizhishengyu = res1.result['jianshu']
        if (yifushengyu > caizhishengyu)
          this.shengYuJianShu = caizhishengyu
        else
          this.shengYuJianShu = yifushengyu
      })
    })
  },
  methods: {
    //定制
    handleSubmit() {
      let params = {}
      params['yonghu'] = store.getters.userInfo.username
      //获取用户信息
      getAction('/usercustomrecord/yonghuqian/select', params).then((res) => {
        this.yongHu = res.result
        let dingDan = {
          dingdanren: store.getters.userInfo.username,
          yifuzhonglei: this.zhongLei,
          yifubianhao: this.yiFu,
          caizhibainhao: this.caiZhi,
          yifuyanser: this.message[0],
          yifuyanseg: this.message[1],
          yifuyanseb: this.message[2],
          yifuxinghao: this.size,
          jianshu: this.jianshu,
          wancheng: '待发货',
          create_time: new Date()
        }
        if (this.yongHu === null) {
          this.openNotificationWithIcon1('warning')
          return
        }
        if (this.yongHu['qian'] < this.jiaGe * this.jianshu) {
          this.openNotificationWithIcon('error')
          return
        }
        this.yongHu['qian'] = this.yongHu['qian'] - this.jiaGe * this.jianshu
        getAction('/usercustomrecord/yonghuqian/update', this.yongHu).then((res1) => {
          postAction('/customrecord/customrecord/add', dingDan).then((res2) => {
            this.shengYuJianShu-=1
            if (res2.success)
              alert('定制成功')
            else
              alert('定制失败，请稍后再试')
          })
        })
      })

    },
    openNotificationWithIcon(type) {
      this.$notification[type]({
        message: '定制失败',
        description:
          '账户余额不足，请充值后在定制'
      })
    },
    openNotificationWithIcon1(type) {
      this.$notification[type]({
        message: '定制失败',
        description:
          '钱包业务没有开通，请再开通后定制'
      })
    },
    //收藏
    shoucang() {
      let dingDan = {
        dingdanren: store.getters.userInfo.username,
        yifuzhonglei: this.zhongLei,
        yifubianhao: this.yiFu,
        caizhibainhao: this.caiZhi,
        yifuyanser: this.message[0],
        yifuyanseg: this.message[1],
        yifuyanseb: this.message[2],
        yifuxinghao: this.size,
        jianshu: this.jianshu,
        wancheng: '待定制',
        create_time: new Date()
      }
      postAction('/customrecord/customrecord/add', dingDan).then((res) => {
        if (res.success)
          alert('收藏成功')
        else
          alert('收藏失败，请稍后再试')
      })
    },
    //点赞
    successdianzan() {
      this.$success({
        title: '点赞成功',
        // JSX support
        content: (
          <div>
            <p>感谢亲对我们的支持</p>
          </div>
        )
      })
      this.dianZan = this.dianZan + 1
      let params = {}
      params['yifubianhao'] = this.yiFu
      params['caizhibianhao'] = this.caiZhi
      params['yifuzhonglei'] = this.zhongLei
      params['dianzan'] = this.dianZan
      params['xiaolian'] = this.xiaoLian
      params['zhonglian'] = this.zhongLian
      params['kulian'] = this.kuLian
      getAction('/usercustomrecord/dianzan/update', params).then((res) => {
        // console.log('------------')
        // console.log('res', res)

      })
    },
    successxiaolian() {
      this.$success({
        title: '衣服满意',
        // JSX support
        content: (
          <div>
            <p>感谢亲对我们衣服的肯定</p>
          </div>
        )
      })
      this.xiaoLian = this.xiaoLian + 1
      let params = {}
      params['yifubianhao'] = this.yiFu
      params['caizhibianhao'] = this.caiZhi
      params['yifuzhonglei'] = this.zhongLei
      params['dianzan'] = this.dianZan
      params['xiaolian'] = this.xiaoLian
      params['zhonglian'] = this.zhongLian
      params['kulian'] = this.kuLian
      getAction('/usercustomrecord/dianzan/update', params).then((res) => {
        // console.log('------------')
        // console.log('res', res)
      })
    },
    successzhonglian() {
      this.$success({
        title: '衣服一般',
        // JSX support
        content: (
          <div>
            <p>亲，我们下次定会让您满意</p>
          </div>
        )
      })
      this.zhongLian = this.zhongLian + 1
      let params = {}
      params['yifubianhao'] = this.yiFu
      params['caizhibianhao'] = this.caiZhi
      params['yifuzhonglei'] = this.zhongLei
      params['dianzan'] = this.dianZan
      params['xiaolian'] = this.xiaoLian
      params['zhonglian'] = this.zhongLian
      params['kulian'] = this.kuLian
      getAction('/usercustomrecord/dianzan/update', params).then((res) => {
        // console.log('------------')
        // console.log('res', res)

      })
    },
    successkulian() {
      this.$success({
        title: '衣服差',
        // JSX support
        content: (
          <div>
            <p>向亲说声对不起</p>
          </div>
        )
      })
      this.kuLian = this.kuLian + 1
      let params = {}
      params['yifubianhao'] = this.yiFu
      params['caizhibianhao'] = this.caiZhi
      params['yifuzhonglei'] = this.zhongLei
      params['dianzan'] = this.dianZan
      params['xiaolian'] = this.xiaoLian
      params['zhonglian'] = this.zhongLian
      params['kulian'] = this.kuLian
      getAction('/usercustomrecord/dianzan/update', params).then((res) => {
        //console.log('------------')
        // console.log('res', res)

      })
    },
    update() {
      let params = {}
      params['yifubianhao'] = this.yiFu
      params['caizhibianhao'] = this.caiZhi
      params['yifuzhonglei'] = this.zhongLei
      getAction('/usercustomrecord/dianzan/select', params).then((res) => {
        //console.log('res', res)
        this.dianZan = res.result['dianzan']
        this.xiaoLian = res.result['xiaolian']
        this.zhongLian = res.result['zhonglian']
        this.kuLian = res.result['kulian']
        this.lianJie = 'http://localhost:3000/jeecg/pinglun/pinglun?yifuzhonglei=' + this.zhongLei + '&yifubianhao=' + this.yiFu + '&caizhibianhao=' + this.caiZhi
      })
      //获取衣服价格
      getAction('/usercustomrecord/yifubiaojia/select', params).then((res) => {
        //console.log('------------')
        // console.log('res', res)
        this.jiaGe = res.result
      })
      //获取衣服剩余件数
      params['yifuxinghao'] = this.size
      getAction('/usercustomrecord/yifu/select', params).then((res) => {
        // console.log('res', res)
        let yifushengyu = res.result[0]['jianshu']
        getAction('/usercustomrecord/caizhi/select', params).then((res1) => {
          // console.log('res', res1)
          let caizhishengyu = res1.result['jianshu']
          if (yifushengyu > caizhishengyu)
            this.shengYuJianShu = caizhishengyu
          else
            this.shengYuJianShu = yifushengyu
        })
      })
    }
  }
}
</script>

<style>
#color {
  border: 0.1px dotted red;
  background-color: #87e8de
}

p {
  font-size: 1px;
}

</style>

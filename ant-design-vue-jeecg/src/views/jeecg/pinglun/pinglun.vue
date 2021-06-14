<template>
  <div id="components-layout-demo-basic">
    <a-layout>
      <a-layout-header>欢迎您来此写下您真实的感受</a-layout-header>
      <a-layout>

        <a-layout-content>
          <div id="pinglun" style="height: 400px; overflow-y:scroll">
            <a-comment>
              <a-avatar
                slot="avatar"
                src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                alt="Han Solo"
              />
            </a-comment>
            <div slot="content">
              <a-form-item>
                <a-textarea :rows="4" :value="value" @change="handleChange" />
              </a-form-item>
              <a-form-item>
                <a-button html-type="submit" :loading="submitting" type="primary" @click="handleSubmit"
                          style="float: right">
                  发表言论
                </a-button>
              </a-form-item>
            </div>
            <a-list
              v-if="comments.length"
              :data-source="comments"
              :header="`${comments.length} ${comments.length > 1 ? '条评论' : '条评论'}`"
              item-layout="horizontal"
            >
              <a-list-item slot="renderItem" slot-scope="item, index">
                <a-comment
                  :author="item.author"
                  avatar="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                  :content="item.content"
                  :datetime="item.datetime"
                />
                <a-rate :value="item.pingfen" />
              </a-list-item>
            </a-list>
          </div>
        </a-layout-content>
        <a-layout-sider>
          <br />
          <br />
          <br />
          <div id="pingfen">
            <span style="float: left;">综合评分：</span>
            <a-rate v-model="pingfen" :tooltips="desc" />
            <span class="ant-rate-text">{{ desc[pingfen - 1] }}</span>
          </div>

        </a-layout-sider>
      </a-layout>
      <a-layout-footer>3D定制平台言论区</a-layout-footer>
    </a-layout>
  </div>
</template>

<script>
import PubSub from 'pubsub-js'
import moment from 'moment'
import store from '@/store'
import { getAction } from '@api/manage'

export default {
  data() {
    return {
      //衣服信息
      caiZhi: 1,
      yiFu: 1,
      zhongLei: 'shangyi',
      //评分
      pingfen: 3,
      desc: ['极差', '差', '一般', '较好', '非常好'],
      //评论
      comments: [],
      submitting: false,
      value: '',
      moment
    }
  },
  created() {//页面渲染前加载，mutation页面渲染后加载
    let params = {}
    let str = this.GetRequest()
    this.zhongLei = str['yifuzhonglei']
    this.caiZhi = str['caizhibianhao']
    this.yiFu = str['yifubianhao']
    params['yifubianhao'] = this.yiFu
    params['yifuzhonglei'] = this.zhongLei
    params['caizhibianhao'] = this.caiZhi
    getAction('/usercustomrecord/yanlun/select', params).then((res) => {
      console.log('------------')
      console.log('res', res)
      this.comments = res.result
      if (this.comments == null)
        this.comments = []
    })
  },
  methods: {

    //查询评论
    selectpinglun() {
      let params = {}
      params['yifubianhao'] = this.yiFu
      params['yifuzhonglei'] = this.zhongLei
      params['caizhibianhao'] = this.caiZhi
      getAction('/usercustomrecord/yanlun/select', params).then((res) => {
        console.log('------------')
        console.log('res', res)
        this.comments = res.result
        if (this.comments == null)
          this.comments = []
      })
    },
    //评论
    handleSubmit() {
      if (!this.value) {
        alert('您每发表任何评论，不可提交')
        return
      }
      let params = {
        yifubianhao: this.yiFu,
        yifuzhonglei: this.zhongLei,
        caizhibianhao: this.caiZhi,
        author: store.getters.userInfo.username,
        pingfen: this.pingfen,
        content: this.value,
        datetime: new Date()
      }

      getAction('/usercustomrecord/yanlun/insert', params).then((res) => {
        console.log('------------')
        console.log('res', res)
        this.comments = res.result
        if (this.comments == null)
          this.comments = []
      })
      this.submitting = true
      this.selectpinglun()
      setTimeout(() => {
        this.submitting = false

        this.value = ''
      }, 1000)
    },
    handleChange(e) {
      this.value = e.target.value
    },
//获取请求参数
    GetRequest() {
      var url = location.search //获取url中"?"符后的字串
      var theRequest = new Object()
      if (url.indexOf('?') != -1) {
        var str = url.substr(1)
        let strs = str.split('&')
        for (var i = 0; i < strs.length; i++) {
          theRequest[strs[i].split('=')[0]] = unescape(strs[i].split('=')[1])
        }
      }
      return theRequest
    }
  }
}
</script>

<style>
#components-layout-demo-basic {
  text-align: center;
}

#components-layout-demo-basic .ant-layout-header,
#components-layout-demo-basic .ant-layout-footer {
  background-color: #ebf3fd;

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
</style>

<template>
  <div>
    流水编号：
    <a-input-number :min="1" :max="100000" :default-value="3" v-model="yiFuBianHao" style="width: 110px;" />
    <br />
    <br />
    <p>
      价格：
      <a-tag color="#f50">
        {{ shengYuJianShu }}
      </a-tag>
      <a-icon type="pay-circle" style="font-size: 18px;color:#DDA0DD; " />
    </p>
    <a-config-provider :auto-insert-space-in-button="false" style="float: right">
      <a-button type="primary" @click="tijiao">
        提交
      </a-button>
    </a-config-provider>
  </div>
</template>

<script>
import { getAction, postAction } from '@/api/manage'

export default {
  data() {
    return {
      yiFuBianHao: 1,
      shengYuJianShu: 0
    }
  },
  created() {
    this.tijiao()
  },
  methods: {
    tijiao() {
      let params = {
        caizhibianhao: this.yiFuBianHao,
      }
      getAction('/usercustomrecord/caizhi/select', params).then((res) => {
        this.shengYuJianShu=res.result['jiage']
      })
    }
  }
}
</script>

<style>
</style>

<template>
  <div>
    流水编号：
    <a-input-number :min="1" :max="100000" :default-value="3" v-model="yiFuBianHao" style="width: 110px;" />
    <br />
    <br />
    进货件数：
    <a-input-number :min="1" :max="100000" :default-value="3" v-model="shengYuJianShu" style="width: 110px;" />
    <br />
    <a-popconfirm
      title="确认进货吗?"
      ok-text="确认"
      cancel-text="取消"
      @confirm="confirm"
      @cancel="cancel"
    >
      <a-config-provider :auto-insert-space-in-button="false" style="float: right">
        <a-button type="primary">
          提交
        </a-button>
      </a-config-provider>
    </a-popconfirm>
  </div>
</template>

<script>
import { getAction, postAction } from '@/api/manage'

export default {
  data() {
    return {
      yiFuBianHao: 1,
      shengYuJianShu: 1
    }
  },
  methods: {
    //确认是否进货
    confirm() {
      this.tijiao()
      this.$message.success('进货成功')
    },
    cancel() {
      this.$message.error('进货取消')
    },
    tijiao() {
      let params = {
        caizhibianhao: this.yiFuBianHao,
        jianshu: this.shengYuJianShu
      }
      getAction('/usercustomrecord/caizhi/update', params).then((res) => {
      })
    }
  }
}
</script>

<style>
</style>

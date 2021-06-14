<template>
  <div>
    流水编号：
    <a-input-number :min="1" :max="100000" :default-value="3" v-model="yiFuBianHao" style="width: 110px;" />
    <br />
    <br />
    衣服种类：
    <a-select default-value="shangyi" style="width: 110px" v-model="yiFuZhongLei">
      <a-icon slot="suffixIcon" type="smile" />
      <a-select-option value="shangyi">
        上衣
      </a-select-option>
      <a-select-option value="xiezi">
        鞋子
      </a-select-option>
      <a-select-option value="kuzi">
        裤子
      </a-select-option>
      <a-select-option value="quanshen">
        整套
      </a-select-option>
      <a-select-option value="peishi">
        其他
      </a-select-option>
    </a-select>
    <br />
    <br />
    衣服型号：
    <a-select default-value="M" style="width: 110px" v-model="yiFuXingHao">
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
    <br />
    <br />
      设置价格：
      <a-input-number :min="1" :max="100000" :default-value="3"  v-model="shengYuJianShu" style="width: 110px;"/>
    <br />
    <a-popconfirm
      title="确认设置价格吗?"
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
      yiFuZhongLei: 'shangyi',
      yiFuXingHao: 'M',
      shengYuJianShu: 1
    }
  },
  methods: {
    //确认是否进货
    confirm() {
      this.tijiao()
      this.$message.success('衣服单价设置成功');
    },
    cancel() {
      this.$message.error('衣服单价设置取消');
    },
    tijiao() {
      let params = {
        yifuzhonglei: this.yiFuZhongLei,
        yifubianhao: this.yiFuBianHao,
        yifuxinghao: this.yiFuXingHao,
        jiage:this.shengYuJianShu
      }
      getAction('/usercustomrecord/yifu/updateJiaGe', params).then((res) => {
      })
    }
  }
}
</script>

<style>
</style>

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
    <p>
      剩余件数：
      <a-tag color="#f50">
        {{ shengYuJianShu }}
      </a-tag>
      <a-icon type="skin" style="font-size: 18px;color:#DDA0DD; " />
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
      yiFuZhongLei: 'shangyi',
      yiFuXingHao: 'M',
      shengYuJianShu: 0
    }
  },
  created() {
    this.tijiao()
  },
  methods: {
    tijiao() {
      let params = {
        yifuzhonglei: this.yiFuZhongLei,
        yifubianhao: this.yiFuBianHao,
        yifuxinghao: this.yiFuXingHao
      }
      getAction('/usercustomrecord/yifu/select', params).then((res) => {
       this.shengYuJianShu=res.result[0]['jianshu']
      })
    }
  }
}
</script>

<style>
</style>

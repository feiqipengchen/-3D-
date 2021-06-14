<template>
  <div style="border: #2f54eb">
    衣服文件：
    <a-upload
      name="file"
      :multiple="true"
      action="http://localhost:8080/jeecg-boot/usercustomrecord/upload/wenjian"
      :headers="headers"
      @change="handleChange"
    >
      <a-button>
        <a-icon type="upload" />
        上传文件
      </a-button>
    </a-upload>
    <br />
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
    衣服件数：
    <a-input-number :min="1" :max="100000" :default-value="3" v-model="jianShu" style="width: 110px;" />
    <br />
    <br />
    <a-popconfirm
      title="确定提交吗？"
      ok-text="确定"
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
import store from '@/store'

export default {
  data() {
    return {
      headers: {
        authorization: 'authorization-text'
      },
      yiFuZhongLei: '',
      yiFuBianHao: 0,
      yiFuXingHao: '',
      jianShu: 0

    }
  },
  methods: {
    //文件上传
    handleChange(info) {
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList)
      }
      if (info.file.status === 'done') {
        this.$message.success(`${info.file.name} 文件上传成功`)
      } else if (info.file.status === 'error') {
        this.$message.error(`${info.file.name}文件上传失败`)
      }
    },
    //提交与否
    confirm() {
      let params = {
        yifuzhonglei:this.yiFuZhongLei,
        yifubianhao:this.yiFuBianHao,
        yifuxinghao:this.yiFuXingHao,
        jianshu:this.jianShu
      }
      console.log('params',params)
      getAction('/usercustomrecord/yifu/insert', params).then((res) => {
        console.log(res.result)
      })
      this.$message.success('提交成功')
    },
    cancel() {
      this.$message.error('提交取消')
    }
  }
}
</script>


<style>
</style>

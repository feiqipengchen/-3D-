<template>
  <div style="border: #2f54eb">
    面料文件：
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
    <a-input-number :min="1" :max="100000" :default-value="3" v-model="caiZhiBianHao" style="width: 110px;" />
    <br />
    <br />
    面料件数：
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
      caiZhiBianHao: 0,
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
        caizhibianhao:this.caiZhiBianHao,
        jianshu:this.jianShu
      }
      console.log('params',params)
      getAction('/usercustomrecord/caizhi/insert', params).then((res) => {
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

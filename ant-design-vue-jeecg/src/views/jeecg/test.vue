<template>
  <div>
    {{ message }}  hhhhh
  </div>
</template>

<script>
import { getAction } from '@/api/manage'

export default {
  data() {
    return {
      message: ''
    }
  },
  created() {//页面渲染前加载，mutation页面渲染后加载
    let params={}
    params['dingdanren']='admin'
    params['yifuzhonglei']='xiezi'
    getAction('/usercustomrecord/getUserCustomRecordSelect',params).then((res) => {
      console.log('------------')
      console.log('res',res)
      this.message = res.result;
    })
  }
}
</script>

<style>
</style>


<template>
  <div>
    <a-card title="服饰" style="width: 100%">
      <a slot="extra" href="http://localhost:3000/jeecg/jianshu/yifu/zhuye">刷新</a>
      <biao-ge></biao-ge>
    </a-card>

  </div>
</template>

<script>
import biaoGe from '@views/jeecg/jianshu/yifu/biaoGe'
export default {
  components:{
    biaoGe
  }
}
</script>

<style>
</style>

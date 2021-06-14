<template>
  <div>
    <a-card title="面料" style="width: 100%">
      <a slot="extra" href="http://localhost:3000/jeecg/jianshu/caizhi/zhuye">刷新</a>
      <biao-ge></biao-ge>
    </a-card>

  </div>
</template>

<script>
import biaoGe from '@views/jeecg/jianshu/caizhi/biaoGe'
export default {
  components:{
    biaoGe
  }
}
</script>

<style>
</style>

<template>
  <div id="container" >
    <div id="result">
      <center-model></center-model>
    </div>
    <div id="function">
      <function1></function1>
    </div>
  </div>

</template>

<script>
import centerModel from '@views/jeecg/3D/center/centerModel'
import function1 from '@views/jeecg/3D/center/function1'
export  default{
  components:{
    centerModel,
    function1,
  },
  methods:{

  }
}
</script>

<style>
#result{
  float: left;
}
#function{
  float: right;
}
#dianzan{
  float: right;
}
</style>


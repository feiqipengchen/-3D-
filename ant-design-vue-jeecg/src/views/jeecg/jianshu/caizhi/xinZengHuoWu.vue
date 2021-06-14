<template>
  <div>
    <a-card :loading="loading" title="新增">
      <a-card title="快乐新增" style="width:400px;float: left;">
        <a slot="extra" href="http://localhost:3000/jeecg/jianshu/caizhi/xinZengHuoWu">刷新</a>
        <xin-zeng-huo-wu1></xin-zeng-huo-wu1>
      </a-card>
      <a-card hoverable style="width: 240px;margin-left: 480px;">
        <img
          slot="cover"
          alt="example"
          src="http://img.alicdn.com/imgextra/i2/160860921/TB2Pv3.dpXXXXb5XXXXXXXXXXXX-160860921.jpg"
          style="height: 300px;width: 240px;"
        />
        <a-card-meta title="多种多样">
          <template slot="description">
            面料多多
          </template>
        </a-card-meta>
      </a-card>
      <a-card hoverable style="width: 240px;margin-left: 800px; margin-top: -395px;">
        <img
          slot="cover"
          alt="example"
          src="http://img000.hc360.cn/k3/M00/6C/06/wKhQv1eDrCWEcKQlAAAAAEUl3B0765.jpg"
          style="height: 300px;width: 240px;"
        />
        <a-card-meta title="炫彩缤纷">
          <template slot="description">
            心情好好
          </template>
        </a-card-meta>
      </a-card>
    </a-card>
    <a-button style="marginTop: 16px" @click="handleClick">
     展开
    </a-button>
  </div>
</template>
<script>
import xinZengHuoWu1 from '@views/jeecg/jianshu/caizhi/xinZengHuoWu1'
export default {
  data() {
    return {
      loading: true,
    };
  },
  methods: {
    handleClick() {
      this.loading = !this.loading;
    },
  },
  components:{
    xinZengHuoWu1
  },
};
</script>

<style>
</style>

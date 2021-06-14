<template>
  <div>
    <a-card :loading="loading" title="新增">
      <a-card title="快乐新增" style="width:400px;float: left;">
        <a slot="extra" href="http://localhost:3000/jeecg/jianshu/yifu/xinZengHuoWu">刷新</a>
        <xin-zeng-huo-wu1></xin-zeng-huo-wu1>
      </a-card>
      <a-card hoverable style="width: 240px;margin-left: 480px;">
        <img
          slot="cover"
          alt="example"
          src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2032884892,3226099446&fm=26&gp=0.jpg"
        />
        <a-card-meta title="绚丽衣装">
          <template slot="description">
            穿最美的衣服
          </template>
        </a-card-meta>
      </a-card>
      <a-card hoverable style="width: 240px;margin-left: 800px; margin-top: -428px;">
        <img
          slot="cover"
          alt="example"
          src="https://img.alicdn.com/i3/TB16_DPLFXXXXaDaXXXXXXXXXXX_!!0-item_pic.jpg"
          style="height: 310px;width: 255px;"
        />
        <a-card-meta title="完美世界">
          <template slot="description">
            看最靓的世界
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
import xinZengHuoWu1 from '@views/jeecg/jianshu/yifu/xinZengHuoWu1'
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

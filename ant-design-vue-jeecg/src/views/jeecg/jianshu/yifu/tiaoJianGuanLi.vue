<template>
  <div>
    <a-card
      style="width:30%"
      title="查询管理"
      :tab-list="tabList"
      :active-tab-key="key"
      @tabChange="key => onTabChange(key, 'key')"
    >
    <!--  <span slot="customRender" slot-scope="item"> <a-icon type="home" />{{ item.tab }} </span>-->
      <a slot="extra" href="http://localhost:3000/jeecg/jianshu/yifu/tiaoJianGuanLi">刷新</a>
      <div v-if="key==='tab1'">
        <tiao-jian-guan-li-jian-shu-select></tiao-jian-guan-li-jian-shu-select>
      </div>
      <div v-else-if="key==='tab2'">
      <tiao-jian-guan-li-jian-shu-add></tiao-jian-guan-li-jian-shu-add>
      </div>
      <div v-else-if="key==='tab3'">
        <tiao-jian-guan-li-jia-ge-select></tiao-jian-guan-li-jia-ge-select>
      </div>
      <div v-else>
       <tiao-jian-guan-li-jia-ge-update></tiao-jian-guan-li-jia-ge-update>
      </div>
    </a-card>
    <a-card hoverable style="width: 300px;margin-left: 420px; margin-top: -375px;">
      <img
        slot="cover"
        alt="example"
        src="http://img14.360buyimg.com/n1/jfs/t15748/180/1574180768/176057/532d693a/5a5572daN199402bd.jpg"
        style="height: 310px;width: 300px; "
      />
      <br />
      <a-card-meta title="运动快乐">
        <template slot="description">
          时尚运动,显我个性
        </template>
      </a-card-meta>
    </a-card>
    <a-card hoverable style="width: 300px;margin-left: 800px; margin-top: -425px;">
      <img
        slot="cover"
        alt="example"
        src="http://gw3.alicdn.com/bao/uploaded/i4/TB1CZGeFVXXXXaHXFXXXXXXXXXX_%21%210-item_pic.jpg_600x600.jpg"
        style="height: 310px;width: 300px; "
      />
      <br />
      <a-card-meta title="运动健康">
        <template slot="description">
          天天运动,享受生活。
        </template>
      </a-card-meta>
    </a-card>
  </div>
</template>

<script>
import tiaoJianGuanLiJiaGeSelect from '@views/jeecg/jianshu/yifu/tiaoJianGuanLiJiaGeSelect'
import tiaoJianGuanLiJiaGeUpdate from '@views/jeecg/jianshu/yifu/tiaoJianGuanLiJiaGeUpdate'
import tiaoJianGuanLiJianShuAdd from '@views/jeecg/jianshu/yifu/tiaoJianGuanLiJianShuAdd'
import tiaoJianGuanLiJianShuSelect from '@views/jeecg/jianshu/yifu/tiaoJianGuanLiJianShuSelect'
export default {
  data() {
    return {
      tabList: [
        {
          key: 'tab1',
           tab: '剩余件数',
          scopedSlots: { tab: 'customRender' },
        },
        {
          key: 'tab2',
          tab: '查询进货' ,
        },
        {
          key: 'tab3',
          tab: '价格查询',
        },
        {
          key: 'tab4',
          tab: '价格修改',
        },
      ],
      // contentList: {
      //   tab1: 'content1',
      //   tab2: 'content2',
      //   tab3: 'content3',
      // },
      tabListNoTitle: [
        {
          key: 'article',
          tab: 'article',
        },
        {
          key: 'app',
          tab: 'app',
        },
        {
          key: 'project',
          tab: 'project',
        },
      ],
      key: 'tab1',
      noTitleKey: 'app',
    };
  },
  components:{
    tiaoJianGuanLiJiaGeSelect,
    tiaoJianGuanLiJiaGeUpdate,
    tiaoJianGuanLiJianShuSelect,
    tiaoJianGuanLiJianShuAdd
  },
  methods: {
    onTabChange(key, type) {
      console.log(key, type);
      this[type] = key;
    },
  },
}
</script>

<style>
</style>

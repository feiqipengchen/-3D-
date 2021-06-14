<template>
  <div>
    <a-card hoverable style="width: 340px; margin-left:420px;">
      <img
        slot="cover"
        alt="example"
        src="http://m.360buyimg.com/n12/jfs/t2197/120/1429928837/233997/69de4ac4/56a1b7f7Nd4a8680b.jpg%21q70.jpg"
      />
      <br />
      <a-card-meta title="运动时尚">
        <template slot="description">
          青春动起来，活力无极限
        </template>
      </a-card-meta>
    </a-card>
    <a-card
      style="width:30%;margin-top: -454px;"
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
    <a-card hoverable style="width: 300px;float: right;margin-top: -266px;height: 452px; ">
      <img
        slot="cover"
        alt="example"
        src="https://cbu01.alicdn.com/img/ibank/O1CN01VgEYzW1a9wvh46S8S_!!2200601073288-0-cib.jpg"
        style="height: 337px;"
      />
      <br />
      <a-card-meta title="运动强壮">
        <template slot="description">
          朝气天天现，活力翻一番。
        </template>
      </a-card-meta>
    </a-card>

  </div>
</template>

<script>
import tiaoJianGuanLiJiaGeSelect from '@views/jeecg/jianshu/caizhi/tiaoJianGuanLiJiaGeSelect'
import tiaoJianGuanLiJiaGeUpdate from '@views/jeecg/jianshu/caizhi/tiaoJianGuanLiJiaGeUpdate'
import tiaoJianGuanLiJianShuAdd from '@views/jeecg/jianshu/caizhi/tiaoJianGuanLiJianShuAdd'
import tiaoJianGuanLiJianShuSelect from '@views/jeecg/jianshu/caizhi/tiaoJianGuanLiJianShuSelect'
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

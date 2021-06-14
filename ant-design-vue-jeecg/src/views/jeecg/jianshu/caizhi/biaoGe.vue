<template>
  <div>
    <center><strong>面料储存清单</strong></center>
    <a-table :columns="columns" :data-source="dataSource" rowKey="caizhibianhao" style="overflow:scroll;">
      <div slot="id" slot-scope="text,tags,i">
        <p>{{ i }}</p>
      </div>
      <div slot="jinhuo" slot-scope="text,tags,i">
        <a-popconfirm
          title="确定进货该商品吗?"
          ok-text="确定"
          cancel-text="取消"
          @confirm="confirm1(text,tags,i)"
          @cancel="cancel1"
        >
          <a-config-provider :auto-insert-space-in-button="false">
            <a-button type="primary">
             进货
            </a-button>
          </a-config-provider>
        </a-popconfirm>
        <a-tooltip placement="topLeft" title="请输入进货数量">
        <a-input-number :min="1" :max="100" :default-value="3" v-model="jinhuojianshu" />
        </a-tooltip>
      </div>
      <div slot="xiajia" slot-scope="text,tags,i">
        <a-popconfirm
          title="确定下架该商品吗?"
          ok-text="确定"
          cancel-text="取消"
          @confirm="confirm(text,tags,i)"
          @cancel="cancel"
        >
        <a-config-provider :auto-insert-space-in-button="false">
          <a-button type="primary">
            下架
          </a-button>
        </a-config-provider>
        </a-popconfirm>
      </div>
    </a-table>
  </div>
</template>

<script>
import { getAction, postAction,putAction,deleteAction } from '@/api/manage'
import store from '@/store'
export default {
  data(){
    return{
      columns:[
        {
          title: '流水编号',
          dataIndex: 'id',
          scopedSlots: { customRender: 'id' }
        },

        {
          title: '面料编号',
          dataIndex: 'caizhibianhao',
          key:'caizhibianhao'
        },
        {
          title: '件数',
          dataIndex: 'jianshu',
        },
        {
          title: '进货',
          dataIndex: 'jinhuo',
          scopedSlots: { customRender: 'jinhuo' }
        },
        {
          title: '下架',
          dataIndex: 'xiajia',
          scopedSlots: { customRender: 'xiajia' }
        }
      ],
      dataSource:[],
      //进货件数
      jinhuojianshu:0
    }
  },
  created() {
    //获取需要进货的衣服
    let params={}
    getAction('/usercustomrecord/caizhi/zong', params).then((res) => {
      this.dataSource = res.result
    })
  },
  methods: {
    //进货弹出框显示与否
    cancel1() {
      this.$message.success('进货取消');
    },
    confirm1(text,tags,i) {
      let params={
        caizhibianhao:tags.caizhibianhao,
        jianshu:this.jinhuojianshu
      }
      getAction('/usercustomrecord/caizhi/update', params).then((res) => {
        this.dataSource = res.result
      })
      this.$message.success('进货完成');
    },
    //商品下架与否
    confirm(text,tags,i) {
      let params={
        caizhibianhao:tags.caizhibianhao,
      }
      getAction('/usercustomrecord/caizhi/delete', params).then((res) => {
        console.log('------------')
        console.log('res', res)
        this.dataSource = res.result
      })
      this.$message.success('下架完成');
    },
    cancel() {
      this.$message.error('下架取消');
    },
  },
}
</script>

<style>
strong {
  font-family: 仿宋;
  font-style: revert;
  color: #2f54eb;
  font-size: large;
  font-weight: bolder;
}
</style>

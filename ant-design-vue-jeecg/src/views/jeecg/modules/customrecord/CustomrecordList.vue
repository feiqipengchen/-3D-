<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="订单人">
              <a-input placeholder="请输入订单人" v-model="queryParam.dingdanren"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="衣服种类">
              <a-input placeholder="请输入衣服种类" v-model="queryParam.yifuzhonglei"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="衣服编号">
                <a-input placeholder="请输入衣服编号" v-model="queryParam.yifubianhao"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="材质编号">
                <a-input placeholder="请输入材质编号" v-model="queryParam.caizhibainhao"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="衣服颜色r">
                <a-input placeholder="请输入衣服颜色r" v-model="queryParam.yifuyanser"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="衣服颜色g">
                <a-input placeholder="请输入衣服颜色g" v-model="queryParam.yifuyanseg"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="衣服颜色b">
                <a-input placeholder="请输入衣服颜色b" v-model="queryParam.yifuyanseb"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="衣服型号">
                <a-input placeholder="请输入衣服型号" v-model="queryParam.yifuxinghao"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="件数">
                <a-input placeholder="请输入件数" v-model="queryParam.jianshu"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="完成">
                <j-dict-select-tag placeholder="请选择完成" v-model="queryParam.wancheng" dictCode="state"/>
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('定制衣服记录')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">发货</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <customrecord-modal ref="modalForm" @ok="modalFormOk"></customrecord-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CustomrecordModal from './modules/CustomrecordModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'

  export default {
    name: 'CustomrecordList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      CustomrecordModal
    },
    data () {
      return {
        description: '定制衣服记录管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'订单人',
            align:"center",
            dataIndex: 'dingdanren'
          },
          {
            title:'衣服种类',
            align:"center",
            dataIndex: 'yifuzhonglei'
          },
          {
            title:'衣服编号',
            align:"center",
            dataIndex: 'yifubianhao'
          },
          {
            title:'材质编号',
            align:"center",
            dataIndex: 'caizhibainhao'
          },
          {
            title:'衣服颜色r',
            align:"center",
            dataIndex: 'yifuyanser'
          },
          {
            title:'衣服颜色g',
            align:"center",
            dataIndex: 'yifuyanseg'
          },
          {
            title:'衣服颜色b',
            align:"center",
            dataIndex: 'yifuyanseb'
          },
          {
            title:'衣服型号',
            align:"center",
            dataIndex: 'yifuxinghao'
          },
          {
            title:'件数',
            align:"center",
            dataIndex: 'jianshu'
          },
          {
            title:'完成',
            align:"center",
            dataIndex: 'wancheng_dictText'
          },
          {
            title:'订单日期',
            align:"center",
            dataIndex: 'createTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/customrecord/customrecord/list",
          delete: "/customrecord/customrecord/delete",
          deleteBatch: "/customrecord/customrecord/deleteBatch",
          exportXlsUrl: "/customrecord/customrecord/exportXls",
          importExcelUrl: "customrecord/customrecord/importExcel",

        },
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'dingdanren',text:'订单人',dictCode:''})
        fieldList.push({type:'string',value:'yifuzhonglei',text:'衣服种类',dictCode:''})
        fieldList.push({type:'int',value:'yifubianhao',text:'衣服编号',dictCode:''})
        fieldList.push({type:'int',value:'caizhibainhao',text:'材质编号',dictCode:''})
        fieldList.push({type:'int',value:'yifuyanser',text:'衣服颜色r',dictCode:''})
        fieldList.push({type:'int',value:'yifuyanseg',text:'衣服颜色g',dictCode:''})
        fieldList.push({type:'int',value:'yifuyanseb',text:'衣服颜色b',dictCode:''})
        fieldList.push({type:'string',value:'yifuxinghao',text:'衣服型号',dictCode:''})
        fieldList.push({type:'int',value:'jianshu',text:'件数',dictCode:''})
        fieldList.push({type:'string',value:'wancheng',text:'完成',dictCode:'state'})
        fieldList.push({type:'date',value:'createTime',text:'订单日期'})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>
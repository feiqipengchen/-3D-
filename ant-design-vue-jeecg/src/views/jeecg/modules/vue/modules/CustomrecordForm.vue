<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="订单人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="dingdanren">
              <a-input v-model="model.dingdanren" placeholder="请输入订单人"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="衣服种类" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="yifuzhonglei">
              <a-input v-model="model.yifuzhonglei" placeholder="请输入衣服种类"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="衣服编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="yifubianhao">
              <a-input-number v-model="model.yifubianhao" placeholder="请输入衣服编号" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="材质编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="caizhibainhao">
              <a-input-number v-model="model.caizhibainhao" placeholder="请输入材质编号" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="衣服颜色r" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="yifuyanser">
              <a-input-number v-model="model.yifuyanser" placeholder="请输入衣服颜色r" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="衣服颜色g" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="yifuyanseg">
              <a-input-number v-model="model.yifuyanseg" placeholder="请输入衣服颜色g" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="衣服颜色b" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="yifuyanseb">
              <a-input-number v-model="model.yifuyanseb" placeholder="请输入衣服颜色b" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="衣服型号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="yifuxinghao">
              <a-input v-model="model.yifuxinghao" placeholder="请输入衣服型号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="件数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="jianshu">
              <a-input-number v-model="model.jianshu" placeholder="请输入件数" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="完成" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="wancheng">
              <a-input v-model="model.wancheng" placeholder="请输入完成"  ></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'CustomrecordForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/custromrecord1/customrecord/add",
          edit: "/custromrecord1/customrecord/edit",
          queryById: "/custromrecord1/customrecord/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>
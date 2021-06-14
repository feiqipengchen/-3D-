<template>
  <div style="width: 350px;">
    <strong>重置钱包密码业务</strong>
    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
    <br /><br />
    <a-form-model ref="ruleForm" :model="ruleForm" :rules="rules" v-bind="layout">
      <a-form-model-item has-feedback label="旧密码:" prop="oldPass">
        <a-input v-model="ruleForm.oldPass" type="password" autocomplete="off" />
      </a-form-model-item>
      <a-form-model-item has-feedback label="新密码:" prop="pass">
        <a-input v-model="ruleForm.pass" type="password" autocomplete="off" />
      </a-form-model-item>
      <a-form-model-item has-feedback label="确认:" prop="checkPass">
        <a-input v-model="ruleForm.checkPass" type="password" autocomplete="off" />
      </a-form-model-item>
      <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
        <a-button type="primary" @click="submitForm('ruleForm')">
          提交
        </a-button>
        <a-button style="margin-left: 10px" @click="resetForm('ruleForm')">
          重置
        </a-button>
      </a-form-model-item>
    </a-form-model>
  </div>
</template>

<script>
import { getAction } from '@api/manage'
import store from '@/store'

export default {
  name: 'chongZhi',
  data() {
    //密码输入判断
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('记住密码')
        }
        callback()
      }
    }
    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('密码不一致'))
      } else {
        callback()
      }
    }
    let validatePass3 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入旧密码'))
      } else {
        callback()
      }
    }
    return {
      //密码输入判断
      ruleForm: {
        pass: '',
        checkPass: '',
        oldPass: ''
      },
      rules: {
        pass: [{ validator: validatePass, trigger: 'change' }],
        checkPass: [{ validator: validatePass2, trigger: 'change' }],
        oldPass: [{ validator: validatePass3, trigger: 'change' }]
      },
      layout: {
        labelCol: { span: 4 },
        wrapperCol: { span: 14 }
      },
      yongHu: null
    }
  },
  methods: {
    //密码输入判断
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let params = {}
          params['yonghu'] = store.getters.userInfo.username
          getAction('/usercustomrecord/yonghuqian/select', params).then((res) => {
            this.yongHu = res.result
            if (this.yongHu['mima'] === this.ruleForm.oldPass) {
              let params1 = {}
              params1['yonghu'] = store.getters.userInfo.username
              params1['mima'] = this.ruleForm.pass
              getAction('/usercustomrecord/yonghuqian/updatepass', params1).then((res1) => {
              })
              alert('重置钱包支付密码成功!')
            } else {
              alert('钱包支付旧密码输入错误，重新输入!')
            }
            return true
          })

        } else {
          alert('重置失败，请再次重置!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
<style>
strong {
  font-family: 仿宋;
  font-style: revert;
  color: #4D90FE;
  font-size: large;
}
</style>

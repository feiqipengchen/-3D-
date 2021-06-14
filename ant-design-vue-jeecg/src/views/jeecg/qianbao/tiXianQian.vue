<template>
  <div id="tixian">
    <center><strong>提现业务</strong></center>
    <a-form-model ref="ruleForm" :model="ruleForm" :rules="rules" v-bind="layout">
      <a-form-model-item has-feedback label="钱包密码：" prop="pass">
        <a-input v-model="ruleForm.pass" type="password" autocomplete="off" />
      </a-form-model-item>
      <a-form-model-item has-feedback label="卡号密码：" prop="checkPass">
        <a-input v-model="ruleForm.checkPass" type="password" autocomplete="off" />
      </a-form-model-item>
      <a-form-model-item has-feedback label="金额：" prop="age">
        <a-input v-model.number="ruleForm.age" />
      </a-form-model-item>
      <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
        <a-space size="large">
          <a-button type="primary" @click="submitForm('ruleForm')">
            提交
          </a-button>
          <a-button style="margin-left: 10px" @click="resetForm('ruleForm')">
            重置
          </a-button>
        </a-space>
      </a-form-model-item>
    </a-form-model>

  </div>
</template>

<script>
import { getAction } from '@api/manage'
import store from '@/store'

export default {
  data() {
    let checkPending
    let checkAge = (rule, value, callback) => {
      clearTimeout(checkPending)
      if (!value) {
        return callback(new Error('请输入提现金额'))
      }
      checkPending = setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('请正确输入金额'))
        } else {
          if (value < 0) {
            callback(new Error('金额必须大于0'))
          } else {
            callback()
          }
        }
      }, 1000)
    }
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入钱包密码'))
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('密码输入')
        }
        callback()
      }
    }
    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入银行卡密码'))
      } else {
        callback()
      }
    }
    return {
      //充值弹出框输入
      ruleForm: {
        pass: '',
        checkPass: '',
        age: ''
      },
      rules: {
        pass: [{ validator: validatePass, trigger: 'change' }],
        checkPass: [{ validator: validatePass2, trigger: 'change' }],
        age: [{ validator: checkAge, trigger: 'change' }]
      },
      layout: {
        labelCol: { span: 4 },
        wrapperCol: { span: 14 }
      },
      //搜索用户的记录
      yonghu: [],
      //搜索卡号的记录
      params1: []
    }
  },
  props: {
    kaHao: String
  },
  methods: {
    //表单提交重置
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //用户entity
          let params = {}
          params['yonghu'] = store.getters.userInfo.username
          getAction('/usercustomrecord/yonghuqian/select', params).then((res) => {
            this.yonghu = res.result
            console.log('用户', this.yonghu['mima'])
            if (this.yonghu['mima'] != this.ruleForm.pass) {
              this.openNotificationWithIcon('error')
              return false
            }
            //卡号entity
            let params1 = {}
            params1['kahao'] = this.kaHao
            getAction('/usercustomrecord/kahaoqian/select', params1).then((res1) => {
              this.params1 = res1.result
              console.log('卡号1', res1.result)
              console.log('卡号', this.params1)
              if (this.params1['mima'] !== this.ruleForm.checkPass) {
                this.openNotificationWithIcon1('error')
                return false
              }
              if (this.yonghu['qian'] - this.ruleForm.age < 0) {
                this.openNotificationWithIcon2('error')
                return false
              }
              this.yonghu['qian'] = this.yonghu['qian'] - this.ruleForm.age
              getAction('/usercustomrecord/yonghuqian/update', this.yonghu).then((res2) => {
                this.yonghu = res2.result
                this.params1['qian'] = this.params1['qian'] + this.ruleForm.age
                getAction('/usercustomrecord/kahaoqian/update', this.params1).then((res3) => {
                  this.params1 = res3.result
                })
                alert('提现成功')
              })
            })
          })
        } else {
          alert('提现失败')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    //钱包密码错误提示框(通知提醒框)
    openNotificationWithIcon(type) {
      this.$notification[type]({
        message: '提现失败',
        description:
          '钱包密码错误，提现失败'
      })
    },
    //卡号密码错误提示框(通知提醒框)
    openNotificationWithIcon1(type) {
      this.$notification[type]({
        message: '提现失败',
        description:
          '卡号密码错误，提现失败'
      })
    },
    //卡号余额不足提示框(通知提醒框)
    openNotificationWithIcon2(type) {
      this.$notification[type]({
        message: '提现失败',
        description:
          '账号余额不足，提现失败'
      })
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

#tixian {
  width: 370px;
  height: 280px;
  margin-left: 50px;
  margin-bottom: 10px;
  background: #888888;
  /* 重点样式 */
  box-shadow: 0 0 0 10px #655,
  0 0 0 15px #DDDDDD,
  0 2px 5px 15px rgba(0, 0, 0, .6);
}
</style>

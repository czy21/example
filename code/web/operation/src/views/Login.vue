<template>
  <el-form :model="loginInfo" :rules="rules" ref="loginInfo" label-width="0px" class="login-container">
    <h3 class="ms-title">后台管理系统</h3>
    <el-form-item prop="loginName">
      <el-input v-model="loginInfo.loginName" placeholder="请输入账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" placeholder="请输入密码" v-model="loginInfo.password"
                @keyup.enter.native="submit"></el-input>
    </el-form-item>
    <el-checkbox checked class="remember">记住密码</el-checkbox>
    <el-form-item class="login-btn">
      <el-button type="primary" @click="submit">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    name: "Login",
    data: function () {
      return {
        loginInfo: {
          loginName: "",
          password: ""
        },
        rules: {
          loginName: [
            {required: true, message: "请输入用户名", trigger: "blur"},
            {max: 12, message: "账号的最大长度为12", trigger: "blur"}
          ],
          password: [{required: true, message: "请输入密码", trigger: "blur"}]
        }
      };
    },
    methods: {
      submit() {
        this.$helper.eui.actWithValidation("loginInfo", () => {
          this.$store.dispatch("CheckLogin", this.loginInfo)
        })
      }
    }
  };
</script>
<style lang="scss" scoped>
  .login-container {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    .ms-title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #505458;
    }
    .remember {
      margin: 0px 0px 20px 0px;
    }
    .login-btn {
      text-align: center;
    }
    .login-btn button {
      width: 100%;
      height: 36px;
    }
  }
</style>

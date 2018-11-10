<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <el-button type="primary" @click="login">登录</el-button>
    <el-button type="primary" @click="getData">查询</el-button>
  </div>
</template>

<script>
  export default {
    name: 'HelloWorld',
    data() {
      return {
        // msg: 'Welcome to Your Vue.js App'
      }
    },
    computed: {
      msg() {
        return this.$store.getters.sidebar
      }
    },
    methods: {
      login() {
        // this.$helper.eui.inform("你好")
        this.$store.dispatch("CheckLogin", {loginName: "admin", password: "admin"})
      },
      getData() {
        this.$api.get("user/getusers").then((data) => {
          console.log(data)
        })
      }
    }
  }
</script>

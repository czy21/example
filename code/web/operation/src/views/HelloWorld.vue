<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <el-button type="primary" @click="test">主要按钮</el-button>
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
      test() {
        this.$helper.eui.inform("你好")
      }
    }
  }
</script>

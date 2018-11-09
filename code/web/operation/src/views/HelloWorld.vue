<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <el-button type="primary">主要按钮</el-button>
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
    computed:{
      msg(){
        return this.$store.getters.sidebar
      }
    }
  }
</script>

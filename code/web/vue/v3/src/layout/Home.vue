<template>
  <el-button @click="add">hello</el-button>
</template>
<script lang="ts">
import {defineComponent} from 'vue'

export default defineComponent({
  data() {
    return {
      userList: [{
        name: "hello"
      }]
    }
  },
  methods: {
    add() {
      console.log(this.userList)
    }
  }
})
</script>
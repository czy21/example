<template>
  <el-button type="primary" @click="migrateLog">迁移日志</el-button>
</template>

<script>
  export default {
    name: "Privilege",
    methods: {
      migrateLog() {
        this.$api.post("privilege/migrateLog").then(res => {
          res.data ? this.$helper.eui.inform("迁移日志至Mongo成功")
            : this.$helper.eui.inform("迁移日志至Mongo失败")
        })
      }
    }
  }
</script>



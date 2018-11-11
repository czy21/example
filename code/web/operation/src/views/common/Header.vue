<template>
  <div class="header">
    <div class="collapse-btn" @click="collapseChage">
      <i class="el-icon-menu"></i>
    </div>
    <div class="logo">{{sysName}}</div>
    <div class="user-info">
      <el-dropdown trigger="hover">
        <span class="el-dropdown-link">
                      <img class="user-logo" src="../../../static/img/img.jpg">
                      {{userName}}
                  </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>我的消息</el-dropdown-item>
          <el-dropdown-item>设置</el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
  import {mapGetters} from "vuex";

  export default {
    name: "Header",
    data() {
      return {
        sysName: "ERP管理系统",
      };
    },
    computed: {
      ...mapGetters(["userInfo"]),
      userName() {
        return this.userInfo.userName;
      }
    },
    methods: {
      //退出登录
      logout: function () {
        this.$confirm("确认退出吗?", "提示", {})
          .then(() => {
            this.$store.commit('REMOVE_TOKEN');
          })
          .catch(() => {
          });
      },
      collapseChage() {
        this.$store.dispatch("ToggleSideBar");
      }
    }
  };
</script>

<template>
  <div class="sidebar">
    <el-menu class="sidebar-el-menu" :collapse="isCollapse" :default-active="$route.path" background-color="#304156"
             text-color="#bfcbd9" active-text-color="#409EFF" router>
      <NavMenu :colltitle="isCollapse" class="left-menu" :menuTree="menuTree" :iconSize="'18px'"></NavMenu>
    </el-menu>
  </div>
</template>

<script>
  import {mapGetters} from "vuex";
  import NavMenu from "./NavMenu.vue";

  export default {
    name: "Sidebar",
    components: {
      NavMenu: NavMenu
    },
    computed: {
      ...mapGetters(["sidebar", "userInfo"]),
      isCollapse() {
        return this.sidebar.opened;
      },
      //左侧树形菜单列表
      menuTree() {
        return this.sidebar.userMenuTree;
      },
    },
  };
</script>

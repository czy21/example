<template>
  <el-container>
    <el-aside>
      <el-menu :collapse="isCollapse" background-color="#304156" text-color="#bfcbd9" active-text-color="#409EFF"
               :default-active="getCurrentRoute"
               router>
        <div class="menu-info"/>
        <NavMenu :collapse="isCollapse" :menu-tree="getMenuTree"/>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header/>
      <el-main>
        <router-view></router-view>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </el-container>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';
import NavMenu from "@views/layout/NavMenu.vue"
import menus from "@/menu";

@Component({
  components: {
    NavMenu
  },
})
export default class Home extends Vue {
  private get isCollapse() {
    return false
  }

  private get getMenuTree() {
    return menus
  }

  private get getCurrentRoute() {
    return this.$route.path.replace('/', '');
  }
}
</script>
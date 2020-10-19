<template>
  <el-container>
    <el-aside :class="{'main-collapse':isCollapse}">
      <el-menu :collapse="isCollapse" background-color="#304156" text-color="#bfcbd9" active-text-color="#409EFF"
               :default-active="getCurrentRoute"
               router>
        <div class="menu-info"/>
        <NavMenu :collapse="isCollapse" :menu-tree="getMenuTree"/>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header>
        <div class="collapse-btn" @click="collapseChange">
          <i class="el-icon-menu" style="font-size: 20px;align-self: center"/>
        </div>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </el-container>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';
import NavMenu from "@v/layout/NavMenu.vue"
import menus from "@/menu";

@Component({
  components: {
    NavMenu
  },
})
export default class Home extends Vue {
  get isCollapse() {
    return this.$store.getters.aside.collapse
  }

  get getMenuTree() {
    return menus
  }

  get getCurrentRoute() {
    return this.$route.path.replace('/', '');
  }

  collapseChange(): void {
    this.$store.dispatch("TOGGLE_ASIDE_ACTION")
  }
}
</script>
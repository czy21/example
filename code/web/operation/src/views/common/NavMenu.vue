<template>
  <div class="navMenu">
    <template v-for="model in menuTree">
      <el-submenu v-if="model.children.length!=0"
                  :key="model.menuId"
                  :index="model.menuName">
        <template slot="title">
          <svg-icon :icon-class="model.icon" :style="{fontSize:iconSize}"></svg-icon>
          <span slot="title" v-if="!colltitle"> {{model.menuName}}</span>
        </template>
        <NavMenu :menuTree="model.children" :iconSize="iconSize"></NavMenu>
      </el-submenu>
      <el-menu-item v-if="model.children.length==0"
                    :key="model.menuId"
                    :index="model.url">
        <svg-icon :icon-class="model.icon" :style="{fontSize:iconSize}"></svg-icon>
        <span slot="title">{{model.menuName}}</span>
      </el-menu-item>

    </template>
  </div>
</template>
<script scoped>
  export default {
    name: "NavMenu",
    props: ["menuTree", "iconSize", "colltitle"]
  };
</script>

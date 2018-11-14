<template>
  <div class="navMenu">
    <template v-for="model in menuTree">
      <el-submenu v-if="model.children.length!=0"
                  :key="model.menuId"
                  :data="model"
                  :index="model.menuName">
        <template slot="title">
          <i :class="model.icon" :style="{fontSize:iconSize}"></i>
          <span slot="title" v-if="!colltitle"> {{model.menuName}}</span>
        </template>
        <NavMenu :menuTree="model.children" :iconSize="iconSize"></NavMenu>
      </el-submenu>
      <el-menu-item v-if="model.children.length==0"
                    :key="model.menuId"
                    :data="model"
                    :index="model.url"
                    :route="model.url">
        <i :class="model.icon" :style="{fontSize:iconSize}"></i>
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

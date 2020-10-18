<template>
  <div>
    <template v-for="t in menuTree">
      <el-submenu v-if="t.children" :key="t.id" :data="t" :index="t.name">
        <template slot="title">
          <i :class="t.icon" :style="{fontSize:iconSize}"></i>
          <span slot="title" v-if="!collapse"> {{ t.name }}</span>
        </template>
        <NavMenu :menuTree="t.children"/>
      </el-submenu>
      <el-menu-item v-if="!t.children" :key="t.id" :data="t" :index="t.path" :route="t.path">
        <i :class="t.icon" :style="{fontSize:iconSize}"></i>
        <span slot="title">{{ t.name }}</span>
      </el-menu-item>
    </template>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue} from "vue-property-decorator";

@Component
export default class NavMenu extends Vue {
  @Prop({default: []}) private menuTree?: any
  @Prop({default: false}) private collapse?: boolean
  @Prop({default: "16px"}) private iconSize?: string
}
</script>
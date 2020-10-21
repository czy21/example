<template>
  <div>
    <el-form :model="model" :rules="rules" :label-width="width">
      <template v-for="t in columns">
        <el-form-item :label="t.label" :prop="t.name">
          <el-input v-model="model[t]"></el-input>
        </el-form-item>
      </template>
    </el-form>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'vue-property-decorator';

export interface Column {
  prop: string
  label: string
  width?: string
}

@Component
export default class Form extends Vue {
  @Prop({default: () => []}) private columns!: Array<Column>
  @Prop({default: () => {}}) private model!: Object;
  @Prop({default: () => {}}) private rules?: Object;
  @Prop({default: () => "100px"}) private width?: String;
}


</script>
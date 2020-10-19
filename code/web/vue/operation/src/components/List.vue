<template>
  <div>
    <el-table :data="data">
      <template v-for="t in columns">
        <el-table-column :label="t.label" :width="t.width">
          <template slot-scope="scope">
            {{ scope.row[t.prop] }}
          </template>
        </el-table-column>
      </template>
    </el-table>
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
export default class List extends Vue {
  @Prop({default: () => []}) private data?: Array<any>;
  @Prop({default: () => []}) private columns!: Array<Column>
}


</script>
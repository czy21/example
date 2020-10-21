<template>
  <el-table v-bind="table">
    <template v-for="t in tableColumns">
      <el-table-column v-bind="t">
        <template slot-scope="scope">
          {{ scope.row[t.prop] }}
          <template v-for="a in t.actions">
            <el-button @click="a.func(scope)" v-bind="a">{{ a.label }}</el-button>
          </template>
        </template>
      </el-table-column>
    </template>
  </el-table>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'vue-property-decorator';
import {Table as ElTable, TableColumn as ELTableColumn} from "element-ui";

export interface Action {
  func: Function
  label: String
}

export interface TableColumn extends ELTableColumn {
  actions: Action[],
  children: ELTableColumn
}

@Component
export default class List extends Vue {
  @Prop({default: {}}) private table!: ElTable
  @Prop({default: []}) private tableColumns?: Array<TableColumn>

}


</script>
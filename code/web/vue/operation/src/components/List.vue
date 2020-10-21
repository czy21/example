<template>
  <div>
    <el-table v-bind="table">
      <template v-for="t in tableColumns">
        <el-table-column v-bind="t">
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
import {Table, TableColumn as ELTableColumn} from "element-ui";

export interface TableColumn extends ELTableColumn {
  children: ELTableColumn
}

@Component
export default class List extends Vue {
  @Prop({default: () => new Table()}) private table!: Table
  @Prop({default: () => new Array<TableColumn>()}) private tableColumns?: Array<TableColumn>

}


</script>
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

@Component
export default class List extends Vue {
  @Prop({default: {}}) private table!: Object
  @Prop({default: []}) private tableColumns?: Object[]

}


</script>
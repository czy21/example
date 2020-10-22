<script lang="tsx">
import {Component, Prop, Vue} from 'vue-property-decorator';

@Component
export default class List extends Vue {
  @Prop({default: {}}) private table!: Object
  @Prop({default: []}) private tableColumns!: Object[]

  render(h: any) {
    return (
        <el-table {...{attrs: this.table}}>
          {
            this.tableColumns.map((t: any) => {
              return (
                  <el-table-column {...{attrs: t}}>
                    {
                      (scope: any) => (
                          <span>
                            {scope.row[t.prop]}
                            {t.actions?.map((a: any) => {
                              return (<el-button {...{attrs: a, on: {click: () => a.func(scope)}}}>{a.label}</el-button>)
                            })}
                          </span>
                      )
                    }
                  </el-table-column>
              )
            })
          }
        </el-table>)
  }
}


</script>
<script lang="tsx">
import {Component, Prop, Provide, Vue} from 'vue-property-decorator';

@Component
export default class Form extends Vue {
  @Prop({default: {}}) private form!: Object
  @Prop({default: []}) private formItems!: Object[];
  @Prop({default: []}) private actions!: Object[];

  input(form: any, item: any) {
    return (<el-input vModel_trim={form.model[item.prop]}/>)
  }

  render(h: any) {
    return (
        <el-form {...{
          attrs: Object.assign({
            "label-width": "50px"
          }, this.form)
        }}>
          {
            this.formItems.map(s => {
              return Object.assign({func: () => this.input(this.form, s), style: "width:280px"}, s)
            }).map((s: any) => {
              return (
                  s.actions && s.actions
                      ? <el-form-item>
                        {
                          s.actions.map((a: any) => <el-button {...{on: {click: () => a.func(this.form, a)}}}>{a.label}</el-button>)
                        }
                      </el-form-item>
                      : <el-form-item {...{attrs: s}}>{s.func(this.form, s)}</el-form-item>
              )
            })
          }
        </el-form>
    )
  }
}


</script>
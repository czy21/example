<script lang="tsx">
import {Component, Prop, Provide, Vue} from 'vue-property-decorator';

@Component
export default class Form extends Vue {
  @Prop({default: {}}) private form!: Object
  @Prop({default: []}) private formItems!: Object[];

  @Provide() formDefault: Object = {
    "label-width": "50px"
  }
  @Provide() formItemDefault: Object = {
    style: "width:360px",
    component: (form: any, item: any) => this.input(form, item)
  }

  input(form: any, item: any) {
    return (<el-input {...{attrs: {model: form.model[item.prop]}}}/>)
  }

  render(h: any) {
    return (
        <el-form {...{attrs: Object.assign(this.formDefault, this.form)}}>
          {
            this.formItems.map(s => Object.assign(this.formItemDefault, s))
                ?.map((s: any) => {
                  return (
                      <el-form-item {...{attrs: s}}>
                        {s.component(this.form, s)}
                      </el-form-item>
                  )
                })
          }
        </el-form>
    )
  }
}


</script>
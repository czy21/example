<template>
  <div>
    <Form :form="form" :form-items="configUserFormItemsMet"/>
    <List :table="table" :table-columns="configTableColumnsMet"/>
  </div>
</template>

<script lang="tsx">
import {Component, Provide, Vue} from 'vue-property-decorator';
import List from '@c/List.vue'
import Form from '@c/Form.vue'

@Component({
  components: {
    List,
    Form
  }
})

export default class UserIndex extends Vue {
  @Provide() form: Object = {model: {}}
  @Provide() table: any = {data: []}
  @Provide() configTableColumnsMet: Object[] = [
    {prop: "userName", label: "姓名"},
    {prop: "account", label: "账号"},
    {prop: "email", label: "邮箱"},
    {label: "操作", fixed: "right", actions: [{label: "详情", type: "text", func: (scope: any) => this.detail(scope.row)}]}
  ]
  @Provide() configUserFormItemsMet: Object[] = [
    {label: "姓名", prop: "name"},
    {actions: [{label: "确定", func: (form: any, action: any) => this.submit(form, action)}]}
  ]

  detail(row: any) {
    console.log(row)
  }

  submit(form: any, action: any) {
    console.log(form.model, action)
  }

  search() {
    this.$stub.api.post("user/search", {}).then((s: any) => {
      this.table.data = s.data.list
    })
  }

  mounted() {
    this.search()
  }

}
</script>
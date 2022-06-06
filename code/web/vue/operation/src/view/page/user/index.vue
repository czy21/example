<template>
  <div>
    <Form :form="form" :form-items="userSearchFormItemMet"/>
    <List :table="table" :table-columns="userTableColumnsMet"/>
  </div>
</template>

<script lang="tsx">
import {Component, Provide, Vue} from 'vue-property-decorator';
import List from '@c/List.vue'
import Form from '@c/Form.vue'
import {AxiosResponse} from "axios";

@Component({
  components: {
    List,
    Form
  }
})

export default class UserIndex extends Vue {
  @Provide() form: Object = {model: {}}
  @Provide() table: any = {data: []}
  @Provide() userTableColumnsMet: Object[] = [
    {prop: "userName", label: "姓名"},
    {prop: "account", label: "账号"},
    {prop: "email", label: "邮箱"},
    {prop: "department.name", label: "部门"},
    {label: "操作", fixed: "right", actions: [{label: "详情", type: "text", func: (scope: any) => this.detail(scope.row)}]}
  ]
  @Provide() userSearchFormItemMet: Object[] = [
    {label: "姓名", prop: "name"},
    {
      actions: [
        {
          label: "确定",
          func: (form: any, action: any) => this.submit(form, action)
        },
        {
          label: "导出",
          func: (form: any, action: any) => this.export(form, action)
        }
      ]
    }
  ]

  detail(row: any) {
    console.log(row)
  }

  submit(form: any, action: any) {
    console.log(form.model, action)
  }

  upload(form: any) {
    this.$stub.api.post("erp-portal/user/upload", form)
        .then(res => {
          console.log(res)
        })
  }

  export(form: any, action: any) {
    this.$stub.api.post("erp-portal/user/export", form, {responseType: "blob"})
        .then((res) => {
          this.$stub.helper.util.basic.downloadFile(res)
        })
  }

  search() {
    this.$stub.api.post("erp-portal/user/search", {})
        .then((s: AxiosResponse) => {
          this.table.data = s.data.data.list
        })
  }

  mounted() {
    this.search()
  }

}
</script>
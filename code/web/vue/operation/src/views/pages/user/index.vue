<template>
  <div>
    <Form :form=form :form-items=configUserFormItemsMet></Form>
    <List :table=table :table-columns=configTableColumnsMet></List>
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
  @Provide() table: Object = {data: [{name: "你是谁"}]}
  @Provide() configTableColumnsMet: Object[] = [
    {
      prop: "name",
      label: "姓名"
    },
    {
      prop: "age",
      label: "年龄"
    },
    {
      label: "操作",
      fixed: "right",
      actions: [
        {
          label: "详情",
          type: "text",
          func: (scope: any) => this.detail(scope.row)
        }
      ]
    }
  ]
  @Provide() configUserFormItemsMet: Object[] = [
    {
      label: "姓名",
      prop: "name",
    },
    {
      label: "确定",
      type: "action",
      func: (form: any, action: any) => this.submit(form, action)
    }
  ]

  detail(row: any) {
    console.log(row.name)
  }

  submit(form: any, action: any) {
    console.log(form.model,action)
  }

  mounted() {
  }

}
</script>
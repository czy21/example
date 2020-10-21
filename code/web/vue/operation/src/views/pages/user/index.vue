<template>
  <div>
    <Form :form="userForm" :form-items="userFormItemsMet"/>
    <List :table="table" :table-columns="tableColumnsMet"/>
  </div>
</template>

<script lang="ts">
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
  @Provide() userForm: Object = {
    model: {},
    size: "mini"
    // "label-width": "50px"
  }
  @Provide() userFormItemsMet: Object = [
    {
      label: "姓名",
    }
  ]


  @Provide() table: Object = {
    data: [{name: "ss"}]
  }
  @Provide() tableColumnsMet: Object[] = [
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
          func: (row: any) => {

          },
        }
      ]
    }
  ]

  get editRules() {
    return {
      name: [
        {required: true, message: '请输入活动名称', trigger: 'blur'},
        {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
      ],
    }
  }

  search() {
    // this.$stub.helper.eui.inform("ni")
  }

  mounted() {
    this.search()
  }

}
</script>
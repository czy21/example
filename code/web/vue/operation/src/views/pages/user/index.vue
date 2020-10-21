<template>
  <div>
    <Form :model="userForm" :columns="formColumns" :rules="editRules"/>
    <List :data="userList" :columns="listColumns"/>
  </div>
</template>

<script lang="ts">
import {Component, Provide, Vue} from 'vue-property-decorator';
import List, {Column as ListColumn} from '@c/List.vue'
import Form, {Column as FormColumn} from '@c/Form.vue'

@Component({
  components: {
    List,
    Form
  }
})
export default class UserIndex extends Vue {
  @Provide() userList: Array<any> = []
  @Provide() userForm: Object = {}

  get editRules() {
    return {
      name: [
        {required: true, message: '请输入活动名称', trigger: 'blur'},
        {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
      ],
    }
  }

  get formColumns(): Array<FormColumn> {
    return [
      {
        prop: "name",
        label: "姓名"
      },
      {
        prop: "age",
        label: "年龄"
      }
    ]
  }

  get listColumns(): Array<ListColumn> {
    return [
      {
        prop: "name",
        label: "姓名"
      },
      {
        prop: "age",
        label: "年龄"
      }
    ]
  }

  search() {

  }

  mounted() {
    this.search()
  }

}
</script>
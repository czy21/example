<template>
  <div>
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
  @Provide() userList: Array<any> = [{name: "ss"}]
  @Provide() userForm: Object = {}
  @Provide() table: Object = {}
  @Provide() tableColumnsMet: Object[] = [
    {
      prop: "name",
      label: "姓名"
    },
    {
      prop: "age",
      label: "年龄"
    },
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

  }

  mounted() {
    this.search()
  }

}
</script>
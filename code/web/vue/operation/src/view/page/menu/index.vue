<template>
  <div>
    <el-tree
        :data="treeData"
        node-key="id"
        default-expand-all
        show-checkbox
        ref="tree"
    >
    </el-tree>
    <el-button @click="setSelectedTree">设置选中</el-button>
    <el-button @click="getSelectedTree">获取选中</el-button>
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
    {prop: "department.name", label: "部门"},
    {label: "操作", fixed: "right", actions: [{label: "详情", type: "text", func: (scope: any) => this.detail(scope.row)}]}
  ]
  @Provide() treeData: Object[] = [
    {
      id: 1,
      label: '一级 1',
      children: [
        {
          id: 4,
          label: '二级 1-1',
          children: [
            {
              id: 9,
              label: '三级 1-1-1'
            },
            {
              id: 10,
              label: '三级 1-1-2'
            },
            {
              id: "func-1",
              label: '查询'
            }
          ]
        }]
    }
  ]

  setSelectedTree() {
    const ref: any = this.$refs.tree
    ref.setCheckedNodes([
      {
        id: 'func-1',
      },
      {
        id: 10
      }
    ]);
  }

  getSelectedTree() {
    const ref: any = this.$refs.tree
    console.log(ref.getCheckedKeys(true))
  }

  detail(row: any) {
    console.log(row)
  }

  submit(form: any, action: any) {
    console.log(form.model, action)
  }

  search() {
    // this.$stub.api.post("user/search", {}).then((s: any) => {
    //   this.table.data = s.data.list
    // })
  }

  mounted() {
    // this.search()
  }

}
</script>
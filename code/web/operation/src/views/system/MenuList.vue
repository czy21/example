<template>
  <div class="combine-table">
    <div class="aside-box">
      <el-tree
        style="width: 180px"
        :props="props"
        :data="$pocket.menuTree"
        default-expand-all
        node-key="value"
        highlight-current>
      </el-tree>
    </div>
    <div class="right-box">
      <div class="handle-box">
        <div class="operate-box">
          <el-cascader expand-trigger="click"
                       :options="$pocket.menuTree"
                       style="width: 300px;"
                       clearable
                       show-all-levels
                       placeholder="请选择菜单">
          </el-cascader>
          <el-button type="primary" icon="el-icon-edit">搜索</el-button>
          <el-button type="primary" icon="el-icon-edit">添加功能</el-button>
        </div>
      </div>
      <div class="container">
        <el-table :data="list" border fit highlight-current-row>
          <el-table-column prop="name" label="权限名称"></el-table-column>
          <el-table-column prop="url" label="权限地址"></el-table-column>
          <el-table-column prop="remark" label="权限描述"></el-table-column>
          <el-table-column label="操作" width="260">
            <template slot-scope="scope">
              <el-button>编辑</el-button>
              <el-button type="danger">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
  import c from '@c'

  export default {
    mixins: [c.mixins.list],
    name: "MenuList",
    data() {
      return {
        // 树形控件属性
        props: {
          label: "label",
          children: "children"
        },
        ecProps: {
          value: 'label',
          children: 'children'
        },
        menuId: '',
      }
    },
    methods: {
      selectMenuChange(data) {
        this.menuId = data[data.length - 1];
        console.log(this.menuId)
      },
      search() {
        this.$api.post("menu/search", this.searchModel).then(v => {
          v.data.page && Object.assign(this.searchModel, v.data.page)
          this.list = v.data.list
        });
      },
    },
    created() {
      this.load("menu/load")
    }
  };
</script>


<template>
  <div class="combine-table">
    <div class="aside-box">
      <!--<el-tree-->
        <!--style="width: 180px"-->
        <!--:props="props"-->
        <!--:data="menuTree"-->
        <!--default-expand-all-->
        <!--node-key="value"-->
        <!--highlight-current>-->
      <!--</el-tree>-->
    </div>
    <div class="right-box">
      <div class="handle-box">
        <div class="operate-box">
          <!--<el-cascader expand-trigger="click"-->
          <!--:options="menuTree"-->
          <!--style="width: 300px;"-->
          <!--clearable-->
          <!--show-all-levels-->
          <!--placeholder="请选择商品类别"-->
          <!--@change="selectMenuChange">-->
          <!--</el-cascader>-->
          <el-button type="primary" icon="el-icon-edit">搜索</el-button>
          <el-button type="primary" icon="el-icon-edit">添加功能</el-button>
        </div>
        <div class="search-box">
          <el-input placeholder="关键词" style="width:200px"></el-input>
          <el-button type="primary" icon="el-icon-search">查询</el-button>
          <el-button type="primary" icon="el-icon-refresh">重置</el-button>
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
        <!--<div class="pagination">-->
        <!--<el-button type="danger" icon="el-icon-delete">批量删除</el-button>-->
        <!--<el-pagination background-->
        <!--@current-change="handleCurrentChange"-->
        <!--@size-change="handleSizeChange"-->
        <!--:current-page="pageModel.pageIndex"-->
        <!--:page-size="pageModel.pageSize"-->
        <!--:page-sizes="[15,30,50,100]"-->
        <!--:total=pageModel.totalCount-->
        <!--layout="total ,sizes, prev, pager, next, jumper">-->
        <!--</el-pagination>-->
        <!--</div>-->
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
      }
    },
    computed: {
      // 树形菜单数据
      // menuTree() {
      //   return c.ref.jsUtil.forTree.transNoChild(this.$pocket.menus, "value", "parentId", "children");
      // }
    },
    methods: {
      search() {
        this.$api.post("menu/search", this.searchModel).then(v => {
          v.data.page && Object.assign(this.searchModel, v.data.page)
          this.list = v.data.list
        });
      },
    },
    mounted() {
      this.load("menu/load")
    }
  };
</script>


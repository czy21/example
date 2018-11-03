<template>
  <div class="combine-table">
    <div class="aside-box">
      <el-tree
        style="width: 180px"
        :props="props"
        :data="categoryTree"
        default-expand-all
        node-key="id"
        highlight-current>
      <span class="custom-tree-node" slot-scope="{node,data}">
      <span>{{node.label}}</span>
      </span>
      </el-tree>
    </div>
    <div class="right-box">
      <div class="handle-box">
        <div class="operate-box">
          <el-button type="primary" icon="el-icon-edit" @click="handleAdd">添加类别</el-button>
        </div>
        <div class="search-box">
          <el-input placeholder="关键词" style="width:200px"></el-input>
          <el-button type="primary" icon="el-icon-search">查询</el-button>
          <el-button type="primary" icon="el-icon-refresh">重置</el-button>
        </div>
      </div>
      <div class="container">
        <el-table :data="categoryList" border fit highlight-current-row>
          <el-table-column type="selection" prop="id" width="55"></el-table-column>
          <el-table-column prop="name" label="类别名称"></el-table-column>
          <el-table-column prop="level" label="级别"></el-table-column>
          <el-table-column label="操作" width="250">
            <template slot-scope="scope">
              <el-button @click="handleEdit(scope.row)">编辑</el-button>
              <el-button type="danger" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
            </span>
    </el-dialog>
    <category-add :show="addShow" @submitAdd="submitAdd" @close="close" :categoryList="categoryList"></category-add>
    <category-edit :show="editShow" @submitEdit="submitEdit" @close="close" :categoryList="categoryList" :rowData="rowData"></category-edit>
  </div>
</template>

<script>
  import CategoryAdd from './CategoryAdd'
  import CategoryEdit from './CategoryEdit'
  import transJson from "@/utils/transJson";

  export default {
    inject: ['reload'],
    name: "CategoryIndex",
    components: {
      CategoryAdd,
      CategoryEdit
    },
    data() {
      return {
        // 树形控件属性
        props: {
          label: "name",
          children: "children"
        },
        //默认不显示新增界面
        addShow: false,
        // 默认不显示编辑界面
        editShow: false,
        // 添加修改界面显示状态
        dialogFormVisible: false,
        categoryList: [],
        // 指定Id删除数据
        categoryId: '',
        // 删除提示界面显示状态
        delVisible: false,
        // 商品类别行数据
        rowData: {}
      }
    },
    computed: {
      categoryTree() {
        return transJson(this.categoryList, "id", "parentId", "children")
      },
    },
    methods: {
      // 添加界面
      handleAdd() {
        this.addShow = true;
      },
      //提交新增
      submitAdd() {
        this.close();
        this.getData();
      },
      //显示编辑界面
      handleEdit(row) {
        this.editShow = true
        this.rowData = row;
      },
      //提交修改
      submitEdit() {
        this.close();
        this.getData();
      },
      //关闭所有Dialog
      close() {
        this.addShow = false;
        this.editShow = false;
      },
      handleDelete(row) {
        this.delVisible = true;
        this.categoryId = row.id;
      },
      deleteRow() {
        this.$api.category.del(this.categoryId).then(res => {
          this.$notify({
            message: "删除成功",
            type: "success",
            duration: 2000
          });
          this.reload();
        });
      },
      getData() {
        this.$api.category.getCategories().then(res => {
          this.categoryList = res.data;
        })
      },
    },
    mounted() {
      this.getData();
    },
  };
</script>

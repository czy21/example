<template>
  <div class="combine-table">
    <div class="aside-box">
      <el-tree
        style="width: 180px"
        :props="props"
        :data="menuTree"
        default-expand-all
        node-key="value"
        highlight-current>
    <span class="custom-tree-node" slot-scope="{node,data}">
      <span>{{node.label}}</span>
    </span>
      </el-tree>
    </div>
    <div class="right-box">
      <div class="handle-box">
        <div class="operate-box">
          <el-cascader expand-trigger="click"
                       :options="menuTree"
                       style="width: 300px;"
                       clearable
                       show-all-levels
                       placeholder="请选择商品类别"
                       @change="selectMenuChange">
          </el-cascader>
          <el-button type="primary" icon="el-icon-edit" @click="search">搜索</el-button>
          <el-button type="primary" icon="el-icon-edit" @click="handleAdd">添加功能</el-button>
          <el-button type="primary" icon="el-icon-edit" @click="addBatchAction">批量添加</el-button>
        </div>
        <div class="search-box">
          <el-input placeholder="关键词" style="width:200px"></el-input>
          <el-button type="primary" icon="el-icon-search">查询</el-button>
          <el-button type="primary" icon="el-icon-refresh">重置</el-button>
        </div>
      </div>
      <div class="container">
        <el-table :data="actionList" border fit highlight-current-row>
          <el-table-column prop="name" label="菜单(权限)名称"></el-table-column>
          <el-table-column prop="url" label="菜单(权限)地址"></el-table-column>
          <el-table-column prop="comment" label="菜单(权限)描述"></el-table-column>
          <el-table-column label="操作" width="260">
            <template slot-scope="scope">
              <el-button @click="handleEdit(scope.row)">编辑</el-button>
              <el-button type="danger" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="pagination">
          <el-button type="danger" icon="el-icon-delete">批量删除</el-button>
          <el-pagination background
                         @current-change="handleCurrentChange"
                         @size-change="handleSizeChange"
                         :current-page="pageModel.pageIndex"
                         :page-size="pageModel.pageSize"
                         :page-sizes="[15,30,50,100]"
                         :total=pageModel.totalCount
                         layout="total ,sizes, prev, pager, next, jumper">
          </el-pagination>
        </div>
      </div>
    </div>
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
            </span>
    </el-dialog>
    <menu-add :show="addShow" @submitAdd="submitAdd" @close="close" :menuTemp="menuTree"></menu-add>
    <menu-edit :show="editShow" @submitEdit="submitEdit" @close="close" :rowData="rowData"></menu-edit>
    <action-add :show="batchAddShow" @submitBatchAdd="submitBatchAdd" @close="close"></action-add>
  </div>
</template>

<script>
  import MenuAdd from "./MenuAdd";
  import ActionAdd from "./ActionAdd";
  import MenuEdit from "./MenuEdit";
  // import transJson from '@/utils/transJson';

  export default {
    inject: ['reload'],
    name: "MenuIndex",
    components: {
      MenuAdd,
      ActionAdd,
      MenuEdit,
    },
    data() {
      return {
        // 树形控件属性
        props: {
          label: "label",
          children: "children"
        },
        //默认不显示新增界面
        addShow: false,
        // 默认不显示编辑界面
        editShow: false,
        // 默认不显示删除界面
        delVisible: false,
        batchAddShow: false,
        menuId: '',
        actionList: [],
        // 分页实体
        pageModel: {
          //分页的总行数
          totalCount: 0,
          //当前页值
          pageIndex: 1,
          // 当前页的行数
          pageSize: 15,
        },
        // 菜单或权限行数据
        rowData: {}
      }
    },
    computed: {
      // 树形菜单数据
      menuTree() {
        return transJson(this.$store.getters.pocketData.menus, "value", "parentId", "children");
      }
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
      handleDelete(row) {
        this.delVisible = true;
        this.menuId = row.id;
      },
      deleteRow() {
        this.$api.menu.del(this.menuId).then(res => {
          this.$notify({
            message: "删除成功",
            type: "success",
          });
          this.menuId = ''
          this.close()
          this.getData()
        });
      },
      //关闭所有Dialog
      close() {
        this.addShow = false;
        this.editShow = false;
        this.delVisible = false;
        this.batchAddShow = false;
      },
      //显示批量新增权限界面
      addBatchAction() {
        this.batchAddShow = true;
      },
      submitBatchAdd() {
        this.close();
        this.getData();
      },
      selectMenuChange(data) {
        this.menuId = data[data.length - 1];
      },
      //获取当前页值
      handleCurrentChange(val) {
        this.pageModel.pageIndex = val;
        this.getData()
      },
      // 当前页显示的行数
      handleSizeChange(val) {
        this.pageModel.pageSize = val;
        this.getData()
      },
      search() {
        this.getData();
      },
      getData() {
        const pageCriteria = {
          pageIndex: this.pageModel.pageIndex,
          pageSize: this.pageModel.pageSize,
          parentId: this.menuId,
        }
        this.$api.menu.getPageMenuActions(pageCriteria).then(res => {
          this.actionList = res.data.list
          this.pageModel = res.data.page
        })
      }
    },
    mounted() {
      this.getData();
    },
  };
</script>


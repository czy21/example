<template>
  <div class="combine-table">
    <div class="handle-box">
      <div class="operate-box">
        <el-button type="primary" icon="el-icon-edit" @click="handleAdd">添加仓库</el-button>
      </div>
      <div class="search-box">
        <el-input placeholder="关键词" style="width:200px"></el-input>
        <el-button type="primary" icon="el-icon-search">查询</el-button>
      </div>
    </div>
    <div class="container">
      <el-table :data="depotList" border fit highlight-current-row>
        <el-table-column type="selection" prop="id" width="55"></el-table-column>
        <el-table-column label="仓库名称" prop="name"></el-table-column>
        <el-table-column label="仓库地址" prop="address"></el-table-column>
        <el-table-column label="仓储费" prop="depotCharge"></el-table-column>
        <el-table-column label="搬运费" prop="truckage"></el-table-column>
        <el-table-column label="操作" width="250">
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
                       :total="pageModel.totalCount"
                       layout="total ,sizes, prev, pager, next, jumper">
        </el-pagination>
      </div>
    </div>
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
            </span>
    </el-dialog>
    <depot-add :show="addShow" @submitAdd="submitAdd" @close="close"></depot-add>
    <depot-edit :show="editShow" @submitEdit="submitEdit" @close="close" :rowData="rowData"></depot-edit>
  </div>
</template>

<script>
  import DepotAdd from './DepotAdd'
  import DepotEdit from './DepotEdit'

  export default {
    inject: ['reload'],
    name: "DepotIndex",
    components: {
      DepotAdd,
      DepotEdit
    },
    data() {
      return {
        //默认不显示新增界面
        addShow: false,
        // 默认不显示编辑界面
        editShow: false,
        delVisible: false,
        depotList: [],
        // 分页实体
        pageModel: {
          //分页的总行数
          totalCount: 0,
          //当前页值
          pageIndex: 1,
          // 当前页的行数
          pageSize: 15,
        },
        depotId: 0,
        // 仓库行数据
        rowData: {}
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
      //关闭所有Dialog
      close() {
        this.addShow = false;
        this.editShow = false;
      },
      //获取当前行记录数据库中的id并显示删除提示框
      handleDelete(row) {
        this.depotId = row.id;
        this.delVisible = true;
      },
      // 提交删除
      deleteRow() {
        this.$api.depot.del(this.depotId).then(res => {
          this.delVisible = false;
          this.reload();
          this.$notify({
            message: "删除成功",
            type: "success",
            duration: 2000
          });
        });
      },
      //获取当前页值
      handleCurrentChange(val) {
        this.pageModel.pageIndex = val;
      },
      // 当前页显示的行数
      handleSizeChange(val) {
        this.pageModel.pageSize = val;
      },
      // 获取用户数据
      getData() {
        this.$api.depot.getPageDepots(this.pageModel.pageIndex, this.pageModel.pageSize).then(res => {
          this.depotList = res.data.list;
          this.pageModel = res.data.page;
        })
      }
    },
    mounted() {
      this.getData();
    }
  }
</script>

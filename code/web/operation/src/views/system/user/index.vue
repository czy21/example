<template>
  <div class="combine-table">
    <div class="handle-box">
      <div class="operate-box">
        <el-button type="primary" icon="el-icon-edit" @click="handleAdd">添加用户</el-button>
        <el-button type="primary">批量修改</el-button>
        <el-button type="primary">导出</el-button>
        <el-button type="primary">重置密码</el-button>
      </div>
      <div class="search-box">
        <el-input placeholder="关键词" style="width:200px"></el-input>
        <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
      </div>
    </div>
    <div class="container">
      <!-- 列表 -->
      <el-table :data="list" border fit highlight-current-row>
        <el-table-column type="selection" prop="id" label="用户Id" width="55"></el-table-column>
        <el-table-column prop="userName" label="用户姓名">
          <template slot-scope="scope">
            {{scope.row.userName}}
          </template>
        </el-table-column>
        <el-table-column prop="loginName" label="登录名称"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="phone" label="电话"></el-table-column>
        <el-table-column prop="isHeader" label="部门经理"></el-table-column>
        <el-table-column label="操作" width="250">
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.row)">编辑</el-button>
            <el-button @click="handleAssignRole(scope.row)">分配角色</el-button>
            <!--<el-button type="danger" @click="handleDelete(scope.row)">删除</el-button>-->
          </template>
        </el-table-column>
      </el-table>
      <!-- 工具条 -->
      <div class="pagination">
        <el-button type="danger" icon="el-icon-delete">批量删除</el-button>
        <el-pagination background @current-change="handleIndexChange" @size-change="handleSizeChange"
                       :current-page="searchModel && searchModel.pageIndex"
                       :page-size="searchModel && searchModel.pageSize"
                       :page-sizes="[15,30,50,100]"
                       layout="total ,sizes, prev, pager, next, jumper"
                       :total="searchModel && searchModel.total">
        </el-pagination>
      </div>
    </div>
    <!-- 删除提示框 -->
    <!--<el-dialog title="提示" :visible.sync="delVisible" width="300px" center>-->
    <!--<div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>-->
    <!--<span slot="footer" class="dialog-footer">-->
    <!--<el-button @click="delVisible = false">取 消</el-button>-->
    <!--<el-button type="primary" @click="deleteRow">确 定</el-button>-->
    <!--</span>-->
    <!--</el-dialog>-->
    <user-add :show="addShow" @submitAdd="submitAdd" @close="close"></user-add>
    <user-edit :show="editShow" @submitEdit="submitEdit" @close="close" :rowData="rowData"></user-edit>
    <user-role :show="userRoleShow" @submitAssign="submitAssign" @close="close" :rowData="rowData"></user-role>
  </div>
</template>

<script>
  import c from '@c'
  import UserAdd from "./UserAdd";
  import UserEdit from "./UserEdit";
  import UserRole from "./UserRole";

  export default {
    mixins: [c.mixins.list],
    name: "UserList",
    components: {
      UserAdd,
      UserEdit,
      UserRole
    },
    data() {
      return {
        //默认不显示新增界面
        addShow: false,
        // 默认不显示编辑界面
        editShow: false,
        //默认不显示分配角色界面
        userRoleShow: false,
        //显示删除提示框
        delVisible: false,
        // 根据前端id删除该行
        userId: null,
        // 用户行数据
        rowData: {}
      };
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
      // 显示分配角色界面
      handleAssignRole(row) {
        this.userRoleShow = true;
        this.rowData = row
      },
      //关闭所有Dialog
      close() {
        this.addShow = false;
        this.editShow = false;
        this.userRoleShow = false;
      },
      // 提交分配角色
      submitAssign() {
        this.close();
      },
      search() {
        return this.$api.post("user/load", this.searchModel)
      },
    },
    mounted() {
      this.load("user/load")
    }
  };
</script>

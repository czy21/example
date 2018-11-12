<template>
  <div class="combine-table">
    <div class="handle-box">
      <div class="operate-box">
        <el-button type="primary" @click="addShow=true">添加角色</el-button>
      </div>
      <div class="search-box">
        <el-input placeholder="关键词" style="width:200px"></el-input>
        <el-button type="primary" icon="el-icon-search">查询</el-button>
      </div>
    </div>
    <div class="container">
      <el-table :data="roleList" border fit highlight-current-row>
        <el-table-column type="selection" prop="id" width="55"></el-table-column>
        <el-table-column v-for="(column,index) in columns" :key="column.text" :label="column.text"
                         :width="column.width">
          <template slot-scope="scope">
            <template v-if="scope.row.edit">
                <span v-if="index==0">
                 <el-input v-model="scope.row[column.value]"></el-input>
                </span>
              <span v-if="index==1">
                 <el-input v-model="scope.row[column.value]"></el-input>
                </span>
            </template>
            <span v-else>{{scope.row[column.value]}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="400">
          <template slot-scope="scope">
                    <span v-if="scope.row.edit">
                      <el-button type="success" @click="submitEdit(scope.row)">提交</el-button>
                      <el-button @click="cancelEdit(scope.row)">取消</el-button>
                    </span>
            <span v-else>
                      <el-button @click="handleAllotMenu(scope.row)">分配菜单</el-button>
                      <el-button @click="handleAllotAction(scope.row)">分配权限</el-button>
                      <el-button @click="scope.row.edit=!scope.row.edit">编辑</el-button>
                      <el-button type="danger" @click="handleDelete(scope.row)">删除</el-button>
            </span>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-button type="danger" icon="el-icon-delete">批量删除</el-button>
        <el-pagination background @current-change="handleCurrentChange"
                       @size-change="handleSizeChange"
                       :current-page="pageModel.pageIndex" :page-size="pageModel.pageSize"
                       :page-sizes="[15,30,50,100]"
                       layout="total ,sizes, prev, pager, next, jumper" :total=pageModel.totalCount>
        </el-pagination>
      </div>

    </div>
    <!-- 删除提示框 -->
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
            </span>
    </el-dialog>
    <role-add :show="addShow" @submitAdd="submitAdd" @close="close"></role-add>
    <role-menu :show="allotMenuShow" @submitAllotMenu="submitAllotMenu" @close="close" :rowData="rowData"></role-menu>
    <role-action :show="allotActionShow" @submitAllotAction="submitAllotAction" @close="close" :rowData="rowData"></role-action>
  </div>
</template>

<script>
  import RoleMenu from "./RoleMenu";
  import RoleAction from "./RoleAction";
  import RoleAdd from "./RoleAdd";

  export default {
    inject: ['reload'],
    name: "RoleIndex",
    components: {
      RoleAdd,
      RoleMenu,
      RoleAction
    },
    data() {
      return {
        //显示角色列
        columns: [
          {
            text: "角色名称",
            value: "name"
          },
          {
            text: "描述",
            value: "comment"
          },
          {
            text: "角色状态",
            value: "enabled"
          }
        ],
        // 角色列表
        roleList: [],
        //默认不显示新增界面
        addShow: false,
        // 角色Id
        roleId: -1,
        //显示删除提示框
        delVisible: false,
        // 分页实体
        pageModel: {
          //分页的总行数
          totalCount: 0,
          //当前页值
          pageIndex: 1,
          // 当前页的行数
          pageSize: 15,
        },
        // 角色行数据
        rowData: {},
        // 分配菜单界面不显示
        allotMenuShow: false,
        // 分配权限界面不显示
        allotActionShow: false
      };
    },
    computed: {
      validationRules() {
        return {
          name: [
            {required: true, message: "必须输入角色名称", trigger: "blur"}
          ],
        };
      },
    },
    methods: {
      submitAdd() {
        this.close();
      },
      // 提交行内修改角色数据
      submitEdit(row) {
        this.$confirm("确认提交吗?", "提示", {}).then(() => {
          this.$api.role.edit(row).then(res => {
            this.$notify({
              message: "更新角色成功",
              type: "success",
            });
          });
          this.reload();
        });
      },
      // 取消编辑并将数据回归到未修改状态
      cancelEdit(row) {
        row.name = row.originalName;
        row.comment = row.originalComment;
        row.edit = false;
      },
      // 显示删除界面
      handleDelete(row) {
        this.delVisible = true;
        this.roleId = row.id;
      },
      // 提交删除
      deleteRow() {
        this.$api.role.del(this.roleId).then(res => {
          this.delVisible = false;
          this.getData();
          this.$notify({
            message: "删除角色成功",
            type: "success",
          });
        });
      },
      // 显示分配菜单界面
      handleAllotMenu(row) {
        this.allotMenuShow = true;
        this.rowData = row
      },
      submitAllotMenu() {
        this.close();
      },
      // 显示分配权限界面
      handleAllotAction(row) {
        this.allotActionShow = true;
        this.rowData = row
      },
      submitAllotAction() {
        this.close();
      },
      close() {
        this.addShow = false
        this.allotMenuShow = false;
        this.allotActionShow = false;
        this.getData();
      },
      //获取当前页值
      handleCurrentChange(val) {
        this.pageindex = val;
        this.getData();
      },
      // 当前页显示的行数
      handleSizeChange(val) {
        this.pagesize = val;
        this.getData();
      },
      // 获取用户数据
      getData() {
        this.$api.role.getPageRoles(this.pageModel.pageIndex, this.pageModel.pageSize).then(res => {
          this.roleList = res.data.list.map(v => {
            this.$set(v, "edit", false);
            v.originalName = v.name;
            v.originalType = v.type;
            v.originalStatus = v.status;
            v.originalComment = v.comment;
            return v;
          });
          this.pageModel = res.data.page;
        });
      }
    },
    mounted() {
      this.getData();
    }
  };
</script>

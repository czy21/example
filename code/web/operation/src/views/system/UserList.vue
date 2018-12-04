<template>
  <div class="main-box">
    <div class="handle-box">
      <div class="operate-box">
        <el-button type="primary" icon="el-icon-edit" @click="addUser('add')">添加用户</el-button>
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
      <el-table :data="list" border highlight-current-row>
        <el-table-column type="selection" prop="userId" width="55"></el-table-column>
        <el-table-column prop="userName" label="用户姓名"></el-table-column>
        <el-table-column prop="loginName" label="登录名称"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="phone" label="电话"></el-table-column>
        <el-table-column prop="isHeader" label="部门经理"></el-table-column>
        <el-table-column label="操作" width="250">
          <template slot-scope="scope">
            <el-button @click="editUser('edit',scope.row)">编辑</el-button>
            <el-button type="primary" @click="allotRole('allot',scope.row)">分配角色</el-button>
            <el-button @click="modifiedUser(scope.row)"
                       :class="scope.row.enabled
                       ?'el-button--danger'
                       :'el-button--success'">
              {{scope.row.enabled?'禁用':'启用'}}
            </el-button>
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

    <el-dialog title="添加用户" :visible.sync="userAddShow" width="20%">
      <el-form ref="userAddForm" :rules="validationRules" :model="userAddForm" label-width="80px">
        <el-form-item label="用户名称" prop="userName">
          <el-input v-model="userAddForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="登录名称" prop="loginName">
          <el-input v-model="userAddForm.loginName"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="userAddForm.email"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="phone">
          <el-input v-model="userAddForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="所在部门" prop="departmentId">
          <el-select v-model="userAddForm.departmentId" placeholder="请选择部门">
            <el-option v-for="item in $pocket.departments" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addUser('submit')" :disabled="isDisable">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="修改用户" :visible.sync="userEditShow" width="20%">
      <el-form ref="userEditForm" :rules="validationRules" :model="userEditForm" label-width="80px">
        <el-form-item label="用户名称" prop="userName">
          <el-input v-model="userEditForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="userEditForm.email"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="phone">
          <el-input v-model="userEditForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="所在部门" prop="departmentId">
          <el-select v-model="userEditForm.departmentId" placeholder="请选择部门">
            <el-option v-for="item in $pocket.departments" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editUser('submit')">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="分配角色" :visible.sync="userRoleShow" width="35%">
      <div class="allot-transfer">
        <el-transfer :button-texts="['收回', '分配']"
                     :titles="['未分配', '已分配']"
                     :props="{key: 'value',label:'label'}"
                     :data="$pocket.roles"
                     v-model="userRoleIds">
        </el-transfer>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="allotRole('submit')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import c from '@c'

  export default {
    mixins: [c.mixins.list],
    name: "UserList",
    data() {
      return {
        userId: '',
        userAddShow: false,
        userDelShow: false,
        userEditShow: false,
        userRoleShow: false,
        userAddForm: {},
        userEditForm: {},
        userRoleIds: []
      };
    },
    computed: {
      validationRules() {
        return {
          loginName: [
            {required: true, message: "请输入账号", trigger: "blur"}
          ],
          userName: [
            {required: true, message: "用户姓名不能为空", trigger: "blur"}
          ],
          departmentId: [
            {required: true, message: "请选择部门", trigger: "blur"}
          ]
        };
      },
    },
    methods: {
      addUser(status) {
        switch (status) {
          case 'add':
            this.userAddShow = true
            break;
          case 'submit':
            this.submitOne()
            this.$helper.eui.actWithValidation("userAddForm", () => {
              this.userAddShow = false
              this.$api.post("user/add", this.userAddForm).then(res => {
                this.$refs['userAddForm'].resetFields();
                this.search();
              })
            })
            break;
          default:
            break;
        }
      },
      editUser(status, row) {
        switch (status) {
          case 'edit':
            this.userEditShow = true
            this.userEditForm = row
            break;
          case 'submit':
            this.submitOne()
            this.$helper.eui.actWithValidation("userEditForm", () => {
              this.userEditShow = false
              this.$api.post("user/edit", {
                userId: this.userEditForm.userId,
                userName: this.userEditForm.userName,
                email: this.userEditForm.email,
                phone: this.userEditForm.phone,
                departmentId: this.userEditForm.departmentId,
              }).then(res => {
                this.$refs['userEditForm'].clearValidate();
                this.userEditForm = res.data
              })
            })
            break;
          default:
            break;
        }
      },
      modifiedUser(row) {
        row.enabled = !row.enabled
        this.$api.post("user/modified", {
          userId: row.userId,
          enabled: row.enabled
        }).then(res => {
          row.enabled = res.data
        })
      },
      allotRole(status, row) {
        switch (status) {
          case 'allot':
            this.userRoleShow = true
            this.userId = row.userId
            this.$api.post("user/userRoleDetails", {userId: this.userId}).then(res => {
              this.userRoleIds = res.data
            })
            break;
          case 'submit':
            this.userRoleShow = false
            this.$api.post("user/updateUserRole", {
              userId: this.userId,
              userRoleIds: this.userRoleIds
            }).then(res => {
              this.$helper.eui.inform(res.data + "分配角色成功")
            })
            break;
          default:
            break;
        }
      },
      search() {
        this.$api.post("user/search", this.searchModel).then(v => {
          v.data.page && Object.assign(this.searchModel, v.data.page)
          this.list = v.data.list
        });
      },
    },
    mounted() {
      this.load("user/load")
    }
  };
</script>

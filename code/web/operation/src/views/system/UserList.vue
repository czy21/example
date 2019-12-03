<template>
  <div class="main-box">
    <div class="handle-box">
      <div class="search-box">
        <el-form ref="searchForm" :inline="true" :model="searchModel" label-position="left" label-width="80px">
          <el-form-item label="姓名" prop="userName">
            <el-input v-model="searchModel.userName"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="search">搜索</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="operate-box">
        <el-button type="primary" @click="addUser('add')" :disabled="!$hasPermission('AddUser')">
          添加用户
        </el-button>
        <el-button type="primary">批量修改</el-button>
        <el-button type="primary">导出</el-button>
        <el-button type="primary">重置密码</el-button>
      </div>
    </div>
    <!--    <div class="container">-->
    <!--      &lt;!&ndash; 列表 &ndash;&gt;-->
    <!--      <el-table :data="list" border highlight-current-row>-->
    <!--        <el-table-column type="selection" prop="userId" width="55"></el-table-column>-->
    <!--        <el-table-column prop="userName" label="用户姓名"></el-table-column>-->
    <!--        <el-table-column prop="loginName" label="登录名称"></el-table-column>-->
    <!--        <el-table-column prop="email" label="邮箱"></el-table-column>-->
    <!--        <el-table-column prop="phone" label="电话"></el-table-column>-->
    <!--        <el-table-column prop="isHeader" label="部门经理"></el-table-column>-->
    <!--        <el-table-column label="操作" width="250">-->
    <!--          <template slot-scope="scope">-->
    <!--            <el-button @click="editUser('edit',scope.row)" :disabled="!$hasPermission('EditUser')">编辑</el-button>-->
    <!--            <el-button type="primary" @click="allotRole('allot',scope.row)"-->
    <!--                       :disabled="!$hasPermission('AllotUserRole')">分配角色-->
    <!--            </el-button>-->
    <!--            <el-button @click="modifiedUser(scope.row)"-->
    <!--                       :class="scope.row.enabled ?'el-button&#45;&#45;danger':'el-button&#45;&#45;success'"-->
    <!--                       :disabled="!$hasPermission('DisableUser')">-->
    <!--              {{scope.row.enabled?'禁用':'启用'}}-->
    <!--            </el-button>-->
    <!--          </template>-->
    <!--        </el-table-column>-->
    <!--      </el-table>-->
    <!--      <el-pagination class="pagination" background @current-change="handleIndexChange" @size-change="handleSizeChange"-->
    <!--                     :current-page="searchModel && searchModel.pageIndex"-->
    <!--                     :page-size="searchModel && searchModel.pageSize"-->
    <!--                     :page-sizes="[20,50,100]"-->
    <!--                     layout="total ,sizes, prev, pager, next, jumper"-->
    <!--                     :total="searchModel && searchModel.total">-->
    <!--      </el-pagination>-->
    <!--    </div>-->

    <!--    <el-dialog title="添加用户" :visible.sync="userAddShow" width="20%">-->
    <!--      <el-form ref="userAddForm" :rules="validationRules" :model="userAddForm" label-width="80px">-->
    <!--        <el-form-item label="用户名称" prop="userName">-->
    <!--          <el-input v-model="userAddForm.userName"></el-input>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="登录名称" prop="loginName">-->
    <!--          <el-input v-model="userAddForm.loginName"></el-input>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="邮箱" prop="email">-->
    <!--          <el-input v-model="userAddForm.email"></el-input>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="电话号码" prop="phone">-->
    <!--          <el-input v-model="userAddForm.phone"></el-input>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="所在部门" prop="departmentId">-->
    <!--          <el-select v-model="userAddForm.departmentId" placeholder="请选择部门">-->
    <!--            <el-option v-for="item in $pocket.departments" :key="item.value" :label="item.label" :value="item.value">-->
    <!--            </el-option>-->
    <!--          </el-select>-->
    <!--        </el-form-item>-->
    <!--      </el-form>-->
    <!--      <div slot="footer" class="dialog-footer">-->
    <!--        <el-button type="primary" @click="addUser('submit')" :disabled="isDisable">确 定</el-button>-->
    <!--      </div>-->
    <!--    </el-dialog>-->

    <!--    <el-dialog title="修改用户" :visible.sync="userEditShow" width="20%">-->
    <!--      <el-form ref="userEditForm" :rules="validationRules" :model="userEditForm" label-width="80px">-->
    <!--        <el-form-item label="用户名称" prop="userName">-->
    <!--          <el-input v-model="userEditForm.userName"></el-input>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="邮箱" prop="email">-->
    <!--          <el-input v-model="userEditForm.email"></el-input>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="电话号码" prop="phone">-->
    <!--          <el-input v-model="userEditForm.phone"></el-input>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="所在部门" prop="departmentId">-->
    <!--          <el-select v-model="userEditForm.departmentId" placeholder="请选择部门">-->
    <!--            <el-option v-for="item in $pocket.departments" :key="item.value" :label="item.label" :value="item.value">-->
    <!--            </el-option>-->
    <!--          </el-select>-->
    <!--        </el-form-item>-->
    <!--      </el-form>-->
    <!--      <div slot="footer" class="dialog-footer">-->
    <!--        <el-button type="primary" @click="editUser('submit')">确 定</el-button>-->
    <!--      </div>-->
    <!--    </el-dialog>-->

    <!--    <el-dialog title="分配角色" :visible.sync="userRoleShow" width="35%">-->
    <!--      <div class="allot-transfer">-->
    <!--        <el-transfer :button-texts="['收回', '分配']"-->
    <!--                     :titles="['未分配', '已分配']"-->
    <!--                     :props="{key: 'value',label:'label'}"-->
    <!--                     :data="$pocket.roles"-->
    <!--                     v-model="userRoleIds">-->
    <!--        </el-transfer>-->
    <!--      </div>-->
    <!--      <div slot="footer" class="dialog-footer">-->
    <!--        <el-button type="primary" @click="allotRole('submit')">确 定</el-button>-->
    <!--      </div>-->
    <!--    </el-dialog>-->
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
            {required: true, message: "必须输入用户账号", trigger: "blur"}
          ],
          userName: [
            {required: true, message: "必须输入用户名称", trigger: "blur"}
          ],
          departmentId: [
            {required: true, message: "必须选择所属部门", trigger: "blur"}
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
        }
      },
      search() {

        const input = {
          page: {
            pageIndex: 1,
            pageSize: 10
          },
          filter: [
            {
              loginName: "wobuzhidao"
            }
          ]
        };
        const query = `mutation{
                          search:searchUser(input:${c.ref.jsUtil.ext.recursionObject(input)}){
                            page{
                              pageIndex
                              pageSize
                              total
                            }
                            list{
                              userName
                            }
                          }
                          user:findAllUser{
                            label:userName
                            value:id
                          }
                        }`;
        let p = {
          user: [],
          person: []
        };
        this.$api.post("graphql", {query: query}).then(v => {
          Object.keys(p).forEach(s => {
            const value = v.data[s];
            p[s] = value !== undefined ? value : p[s]
          })
        });
        // console.log(p)
      },
    },
    mounted() {
      // this.load("user/load")
    }
  };
</script>

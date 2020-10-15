<template>
  <div class="main-box">
    <div class="handle-box">
      <div class="search-box">
        <el-form ref="searchForm" :inline="true" :model="searchModel" label-position="left" label-width="80px">
          <el-form-item label="角色名称" prop="roleName">
            <el-input></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="search">查询</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="operate-box">
        <el-button type="primary" @click="addRole('add')" :disabled="!$hasPermission('AddRole')">添加角色</el-button>
      </div>
    </div>
    <div class="container">
      <el-table :data="list" border highlight-current-row>
        <el-table-column type="selection" prop="roleId" width="55"></el-table-column>
        <el-table-column prop="roleName" label="角色名称"></el-table-column>
        <el-table-column prop="remark" label="备注"></el-table-column>

        <el-table-column label="操作" width="300">
          <template slot-scope="scope">
            <el-button @click="editRole('edit',scope.row)" :disabled="!$hasPermission('EditRole')">编辑</el-button>
            <el-button type="primary" @click="allotMenu('allot',scope.row)"
                       :disabled="!$hasPermission('AllotRoleMenu')">分配菜单
            </el-button>
            <el-button type="primary" @click="allotFunc('allot',scope.row)"
                       :disabled="!$hasPermission('AllotRoleFunc')">分配权限
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination class="pagination" background @current-change="handleIndexChange" @size-change="handleSizeChange"
                     :current-page="searchModel && searchModel.pageIndex"
                     :page-size="searchModel && searchModel.pageSize"
                     :page-sizes="[20,50,100]"
                     layout="total ,sizes, prev, pager, next, jumper"
                     :total="searchModel && searchModel.total">
      </el-pagination>
    </div>

    <el-dialog title="添加角色" :visible.sync="roleAddShow" width="20%">
      <el-form ref="roleAddForm" :rules="validationRules" :model="roleAddForm" label-width="80px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="roleAddForm.roleName"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="roleAddForm.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addRole('submit')">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="编辑角色" :visible.sync="roleEditShow" width="20%">
      <el-form ref="roleEditForm" :rules="validationRules" :model="roleEditForm" label-width="80px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="roleEditForm.roleName"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="roleEditForm.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editRole('submit')">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="分配角色菜单" :visible.sync="roleMenuShow" width="20%">
      <el-tree
        :data="$pocket.menuTree"
        default-expand-all
        show-checkbox
        node-key="value"
        ref="roleMenu">
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="allotMenu('submit')">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="分配角色权限" :visible.sync="roleFuncShow" width="35%">
      <div class="allot-transfer">
        <el-transfer
          filterable
          filter-placeholder="请输入权限名称"
          v-model="roleFuncIds"
          :filter-method="filterFunc"
          :props="{label:'label',key:'value'}"
          :titles="['未分配', '已分配']"
          :button-texts="['收回', '分配']"
          :data="$pocket.functions">
        </el-transfer>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="allotFunc('submit')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import c from '@c'

  export default {
    mixins: [c.mixins.list],
    name: "RoleList",
    data() {
      return {
        roleAddShow: false,
        roleDelShow: false,
        roleEditShow: false,
        roleMenuShow: false,
        roleFuncShow: false,
        roleAddForm: {},
        roleEditForm: {},
        roleFuncIds: [],
      };
    },
    computed: {
      validationRules() {
        return {
          roleName: [
            {required: true, message: "必须输入角色名称", trigger: "blur"}
          ],
        };
      },
    },
    methods: {
      addRole(status) {
        switch (status) {
          case 'add':
            this.roleAddShow = true
            break;
          case 'submit':
            this.submitOne()
            this.$helper.eui.actWithValidation("roleAddForm", () => {
              this.roleAddShow = false
              this.$api.post("role/add", this.roleAddForm).then(res => {
                this.$refs['roleAddForm'].resetFields();
                this.search();
              })
            })
            break;

        }
      },
      editRole(status, row) {
        switch (status) {
          case 'edit':
            this.roleEditShow = true
            this.roleEditForm = row
            break;
          case 'submit':
            this.submitOne()
            this.$helper.eui.actWithValidation("roleEditForm", () => {
              this.roleEditShow = false
              this.$api.post("role/edit", this.roleEditForm).then(res => {
                this.$refs['roleEditForm'].clearValidate();
                this.roleEditForm = res.data
              })
            })
            break;

        }
      },
      allotMenu(status, row) {
        switch (status) {
          case 'allot':
            this.roleMenuShow = true
            this.roleId = row.roleId
            this.$api.post("role/roleMenuDetails", {roleId: this.roleId}).then(res => {
              this.$refs.roleMenu.setCheckedKeys(res.data)
            })
            break;
          case
          'submit':
            var roleMenus = this.$refs.roleMenu.getCheckedNodes(false, true).map(v => v.value);
            this.roleMenuShow = false
            this.$api.post("role/updateRoleMenu", {
              roleId: this.roleId,
              roleMenuIds: roleMenus
            }).then(res => {
              this.$helper.eui.inform(res.data + " 分配菜单成功")
            })
            break;
        }
      },
      allotFunc(status, row) {
        switch (status) {
          case 'allot':
            this.roleFuncShow = true
            this.roleId = row.roleId
            this.$api.post("role/roleFuncDetails", {roleId: this.roleId}).then(res => {
              this.roleFuncIds = res.data
            })
            break;
          case
          'submit':
            this.roleFuncShow = false
            this.$api.post("role/updateRoleFunc", {
              roleId: this.roleId,
              roleFuncIds: this.roleFuncIds
            }).then(res => {
              this.$helper.eui.inform(res.data + " 分配权限成功")
            })
            break;

        }
      },
      filterFunc(query, item) {
        return item.label.indexOf(query) > -1;
      },
      search() {
        this.$api.post("role/search", this.searchModel).then(v => {
          v.data.page && Object.assign(this.searchModel, v.data.page)
          this.list = v.data.list
        });
      }
    },
    mounted() {
      this.load("role/load")
    }
  };
</script>

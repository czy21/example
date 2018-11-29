<template>
  <div class="main-box">
    <div class="handle-box">
      <div class="operate-box">
        <el-button type="primary" @click="addRole('add')">添加角色</el-button>
      </div>
      <div class="search-box">
        <el-input placeholder="关键词" style="width:200px"></el-input>
        <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
      </div>
    </div>
    <div class="container">
      <el-table :data="list" border fit highlight-current-row>
        <el-table-column type="selection" prop="roleId" width="55"></el-table-column>
        <el-table-column prop="roleName" label="角色名称"></el-table-column>
        <el-table-column prop="remark" label="备注"></el-table-column>

        <el-table-column label="操作" width="400">
          <template slot-scope="scope">
            <el-button @click="editRole('edit',scope.row)">编辑</el-button>
            <el-button @click="allotMenu('allot',scope.row)">分配菜单</el-button>
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

    <el-dialog title="分配角色菜单" :visible.sync="roleMenuShow" width="60%">
      <div class="combine-box">
        <div class="aside-box">
          <el-tree
            :props="props"
            :data="$pocket.menuTree"
            default-expand-all
            show-checkbox
            node-key="value"
            ref="roleMenu">
          </el-tree>
        </div>
        <div class="right-box">
          <div class="container">
            <el-collapse v-model="activeNames">
              <el-collapse-item v-for="item in actionTree" :key="item.value" :name="item.value">
                <template slot="title">
                  {{item.label}}
                </template>
                <el-checkbox-group v-model="roleActionIds" @change="checkedActionsChange">
                  <el-checkbox v-for="action in item.children" :key="action.value" :label="action.value">
                    {{action.label}}
                  </el-checkbox>
                </el-checkbox-group>
              </el-collapse-item>
            </el-collapse>
          </div>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="allotMenu('submit')">确 定</el-button>
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
        activeNames: [1, 2],
        // 角色权限Id集合
        roleActionIds: [],
        actionTree: [],
        // 树形控件属性
        props: {
          label: "label",
          children: "children"
        },
        roleId: '',
        roleAddShow: false,
        roleDelShow: false,
        roleEditShow: false,
        roleMenuShow: false,
        roleAddForm: {},
        roleEditForm: {},
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
      checkedActionsChange(value) {
        var temp = []
        this.actionTree.forEach((t) => {
          t.children.forEach((c) => {
            value.forEach((v) => {
              if (v == c.value) {
                temp.push(c.parentId)
              }
            })
          })
        })
        this.$refs.roleMenu.setCheckedKeys(Array.from(new Set(temp)), false)
      },
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
          default:
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
            const temp = {
              roleId: this.roleEditForm.roleId,
              roleName: this.roleEditForm.roleName,
              remark: this.roleEditForm.remark
            }
            this.$helper.eui.actWithValidation("roleEditForm", () => {
              this.roleEditShow = false
              this.$api.post("role/edit", temp).then(res => {
                this.$refs['roleEditForm'].clearValidate();
                this.roleEditForm = res.data
              })
            })
            break;
          default:
            break;
        }
      },
      allotMenu(status, row) {
        switch (status) {
          case 'allot':
            this.roleMenuShow = true
            this.roleId = row.roleId
            this.$api.post("role/roleMenuDetails", {roleId: this.roleId}).then(res => {
              this.actionTree = res.data.permissions
              res.data.menuIds.forEach((t) => {
                this.actionTree.forEach((p) => {
                  p.children.forEach((c) => {
                    if (t === c.value) {
                      this.roleActionIds.push(t)
                    }
                  })
                })
              })
              this.$refs.roleMenu.setCheckedKeys(res.data.menuIds.map((t) => {
                if (this.$refs.roleMenu.getNode(t) != null && !this.$refs.roleMenu.getNode(t).data.hasOwnProperty("children")) {
                  return t
                }
              }), false)
            })
            break;
          case 'submit':
            this.roleMenuShow = false
            var roleMenus = this.$refs.roleMenu.getCheckedNodes(false, true).map(v => v.value);
            this.$api.post("role/updateRoleMenu", {
              roleId: this.roleId,
              roleMenuIds: this.roleActionIds.concat(roleMenus)
            }).then(res => {
              this.$helper.eui.inform(res.data + " 分配权限成功")
            })
            break;
          default:
            break;
        }
      },
      search() {
        this.$api.post("role/search", this.searchModel).then(v => {
          v.data.page && Object.assign(this.searchModel, v.data.page)
          this.list = v.data.list
        });
      },
    },
    mounted() {
      this.load("role/load")
    }
  };
</script>

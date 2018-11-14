<template>
  <div class="combine-table">
    <div class="handle-box">
      <div class="operate-box">
        <el-button type="primary" @click="add">添加角色</el-button>
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
            <el-button @click="edit(scope.row)">编辑</el-button>
            <el-button @click="allotMenu(scope.row)">分配菜单</el-button>
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
        <el-button type="primary" @click="addRole">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="添加角色" :visible.sync="roleEditShow" width="20%">
      <el-form ref="roleEditForm" :rules="validationRules" :model="roleEditForm" label-width="80px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="roleEditForm.roleName"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="roleEditForm.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editRole">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="分配角色菜单" :visible.sync="roleMenuShow" width="20%">
      <el-tree
        :props="props"
        :data="menuTree"
        default-expand-all
        show-checkbox
        node-key="value"
        ref="menuTree">
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitAllotMenu">确 定</el-button>
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
        roleAddForm: {},
        roleEditForm: {},
        // 树形控件属性
        props: {
          label: "label",
          children: "children"
        },
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
      menuTree() {
        return c.ref.jsUtil.forTree.transNoChild(this.$pocket.menus, "value", "parentId", "children")
      }
    },
    methods: {
      allotMenu(row) {
        this.roleMenuShow = true
      },
      submitAllotMenu() {
        this.roleMenuShow = false
      },
      add() {
        this.roleAddShow = true
      },
      addRole() {
        this.roleAddShow = false
        console.log(this.roleAddForm)
      },
      edit(row) {
        this.roleEditShow = true
        this.roleEditForm = row
        console.log(this.roleEditForm)
      },
      editRole() {
        this.roleEditShow = false
        this.reload()
      },
      search() {
        return this.$api.post("role/load", this.searchModel)
      },
    },
    mounted() {
      this.load("role/load")
    }
  };
</script>

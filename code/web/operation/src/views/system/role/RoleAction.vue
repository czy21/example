<template>
  <el-dialog title="分配角色权限" :visible.sync="dialogVisible" :before-close="close" width="55%">
    <el-collapse v-model="activeNames">
      <el-collapse-item v-for="item in actionTree" :key="item.id" :name="item.id">
        <template slot="title">
          {{item.name}}
        </template>
        <el-checkbox-group v-model="roleActionIds">
          <el-checkbox v-for="action in item.children" :key="action.id" :label="action.id">
            {{action.name}}
          </el-checkbox>
        </el-checkbox-group>
      </el-collapse-item>
    </el-collapse>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitAllotAction">确 定</el-button>
    </div>
  </el-dialog>
</template>
<script>

  export default {
    name: "RoleAction",
    props: {
      show: {
        type: Boolean,
        default: false
      },
      rowData: {
        type: Object
      },
    },
    data() {
      return {
        activeNames: [1, 2],
        // 分配权限界面是否显示
        dialogVisible: this.show,
        //树形权限列表
        actionTree: {},
        // 角色权限Id集合
        roleActionIds: []
      };
    },
    computed: {
      formData() {
        return this.rowData;
      }
    },
    methods: {
      close() {
        this.$emit("close");
      },
      // 分配权限
      getRoleActions() {
        this.$api.role.getRoleActions(this.formData.id).then(res => {
          this.actionTree = res.data.actionTree;
          this.roleActionIds = res.data.roleActions;
        })
      },
      submitAllotAction() {
        const roleActions = [];
        this.roleActionIds.forEach((data) => {
          roleActions.push({
            roleId: this.formData.id,
            menuId: data
          });
        })
        // 保存角色权限
        this.$api.role.saveRoleActions(this.formData.id, roleActions).then(res => {
          this.$emit("submitAllotAction")
          this.$notify({
            message: this.formData.name + "分配权限成功",
            type: "success",
          });
        });
      }
    },
    watch: {
      show(val) {
        this.dialogVisible = val;
        val && this.getRoleActions()
      },
    },
  };
</script>

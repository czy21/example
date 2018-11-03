<template>
  <el-dialog title="分配角色菜单" :visible.sync="dialogVisible" :before-close="close" width="20%">
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
</template>
<script>
  import transJson from '@/utils/transJson';

  export default {
    name: "RoleMenu",
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
        // 树形控件属性
        props: {
          label: "label",
          children: "children"
        },
        // 分配菜单界面是否显示
        dialogVisible: this.show,
      };
    },
    computed: {
      // 选中的用户行数据
      formData() {
        return this.rowData;
      },
      // 树形菜单列表
      menuTree() {
        return transJson(this.$store.getters.pocketData.menus, "value", "parentId", "children");
      },
      // 勾选的菜单集合
      checkedMenus() {
        return this.$refs.menuTree.getCheckedNodes();
      },
    },
    methods: {
      close() {
        this.$emit("close");
      },
      // 设置角色菜单选中
      setCheckedMenus() {
        this.$api.role.getRoleMenus(this.formData.id).then(res => {
          this.$refs.menuTree.setCheckedKeys(res.data)
        });
      },
      submitAllotMenu() {
        const roleMenus = [];
        this.checkedMenus.forEach((data) => {
          roleMenus.push({
            roleId: this.formData.id,
            menuId: data.value
          });
        });
        // 保存角色菜单
        this.$api.role.saveRoleMenus(this.formData.id, roleMenus).then(res => {
          this.$emit("submitAllotMenu")
          this.$notify({
            message: this.formData.name + "分配菜单成功",
            type: "success",
          });
        });
      }
    },
    watch: {
      show(val) {
        this.dialogVisible = val;
        val && this.setCheckedMenus();
      },
    },
  };
</script>

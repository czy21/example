<template>
  <el-dialog title="分配角色" :visible.sync="dialogVisible" :before-close="close" width="35%">
    <p class="field-wrap" label="用户" style="text-align: center">{{rowData.userName }}</p>
    <div class="allot-transfer">
      <el-transfer :button-texts="['收回', '分配']"
                   :titles="['未分配', '已分配']"
                   :props="{key: 'value',label:'label'}"
                   :data="roleList"
                   v-model="userRoleIds">
      </el-transfer>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitAssign">确 定</el-button>
    </div>
  </el-dialog>
</template>
<script>

  export default {
    name: "UserRole",
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
        // 分配角色界面是否显示
        dialogVisible: this.show,
        userRoleIds: []
      };
    },
    computed: {
      // 角色数据集合
      roleList() {
        return this.$store.getters.pocketData.roles;
      },
      // 选中的用户行数据
      formData() {
        return this.rowData;
      },
    },
    methods: {
      close() {
        this.$emit("close");
      },
      submitAssign() {
        const userRoles = [];
        this.userRoleIds.forEach((data) => {
          userRoles.push({
            userId: this.formData.id,
            roleId: data
          });
        });
        this.$api.user.saveUserRoles(this.formData.id, userRoles).then(() => {
          this.$emit("submitAssign")
          this.$notify({
            message: this.formData.userName + " 设置角色成功",
            type: "success",
          });
        });
      },
      setUserRoles() {
        this.$api.user.getUserRoles(this.formData.id).then(res => {
          this.userRoleIds = res.data
        })
      }
    },
    watch: {
      show(val) {
        this.dialogVisible = val;
        val && this.setUserRoles()
      },
    },
  };
</script>

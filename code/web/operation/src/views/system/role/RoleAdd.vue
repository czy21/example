<template>
  <el-dialog title="添加角色" :visible.sync="dialogVisible" :before-close="close" width="20%">
    <el-form :model="addForm" label-width="80px" :rules="validationRules" ref="addForm">
      <el-form-item label="角色名称" prop="name">
        <el-input v-model="addForm.name"></el-input>
      </el-form-item>
      <el-form-item label="描述 :" prop="comment">
        <el-input type="textarea" v-model="addForm.comment"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitAdd">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  export default {
    name: "RoleAdd",
    props: {
      show: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        //显示新增界面
        dialogVisible: this.show,
        addForm: {}
      }
    },
    computed: {
      validationRules() {
        return {
          name: [{required: true, message: "请输入角色名称", trigger: "blur"}],
        }
      }
    },
    methods: {
      close() {
        this.$emit("close");
      },
      submitAdd() {
        this.$refs.addForm.validate(valid => {
          if (valid) {
            this.$confirm("确认提交吗?", "提示", {}).then(() => {
              // 提交新增的请求
              this.$api.role.create(this.addForm).then(res => {
                if (res.data.indexOf("rolename already exist") !== -1) {
                  this.$message.warning("角色名称已存在");
                  return;
                } else if (res.data.indexOf("role add success") !== -1) {
                  this.$notify({
                    message: "添加角色成功",
                    type: "success",
                  });
                }
                this.$emit("submitAdd");
              });
            });
          }
        });
      }
    },
    watch: {
      show(val) {
        this.dialogVisible = val;
      }
    },
  }
</script>


<template>
  <el-dialog title="添加用户" :visible.sync="dialogVisible" :before-close="close" width="20%">
    <el-form :model="addForm" :rules="validationRules" ref="addForm" label-width="80px">
      <el-form-item label="用户名称" prop="userName">
        <el-input v-model="addForm.userName"></el-input>
      </el-form-item>
      <el-form-item label="登录名称" prop="loginName">
        <el-input v-model="addForm.loginName"></el-input>
      </el-form-item>
      <el-form-item label="登录密码" prop="password" v-show="false">
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="addForm.email"></el-input>
      </el-form-item>
      <el-form-item label="电话号码" prop="phone">
        <el-input v-model="addForm.phone"></el-input>
      </el-form-item>
      <el-form-item label="所在部门" prop="departmentId">
        <el-select v-model="addForm.departmentId" placeholder="请选择部门">
          <el-option v-for="item in $pocket.departments" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitAdd">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  export default {
    name: "UserAdd",
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
        //新增界面数据
        addForm: {
          userName: "",
          loginName: "",
          password: "123456",
          email: "",
          phone: "",
          departmentId: ""
        },
      };
    },
    computed: {
      validationRules() {
        return {
          loginName: [
            {required: true, message: "请输入账号", trigger: "blur"}
          ],
          departmentId: [
            {required: true, message: "请选择部门", trigger: "blur"}
          ]
        };
      },
      depOptions() {
        return []
      }
    },
    methods: {
      close() {
        this.$emit("close");
      },
      //提交新增
      submitAdd() {
        this.$refs.addForm.validate(valid => {
          if (valid) {
            this.$confirm("确认提交吗?", "提示", {}).then(() => {
              this.$api.user.create(this.addForm).then(res => {
                if (res.data.indexOf("loginName already exist") !== -1) {
                  this.$message.warning("账号已存在");
                } else if (res.data.indexOf("userName already exist") !== -1) {
                  this.$message.warning("用户名称已存在");
                } else if (res.data.indexOf("user add success") !== -1) {
                  this.$emit("submitAdd");
                  this.$notify({message: "添加用户成功", type: "success",});
                }
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
  };
</script>

<template>
  <el-dialog title="修改用户" :visible.sync="dialogVisible" :before-close="close" width="20%">
    <el-form :model="formData" :rules="validationRules" ref="formData" label-width="80px">
      <el-form-item label="用户名称" prop="userName">
        <el-input v-model="formData.userName"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="formData.email"></el-input>
      </el-form-item>
      <el-form-item label="电话号码" prop="phone">
        <el-input v-model="formData.phone"></el-input>
      </el-form-item>
      <el-form-item label="所在部门" prop="departmentId">
        <el-select v-model="formData.departmentId" placeholder="请选择部门">
          <el-option v-for="item in $pocket.departments" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitEdit">确 定</el-button>
    </div>
  </el-dialog>
</template>
<script>
  export default {
    name: "UserEdit",
    props: {
      show: {
        type: Boolean,
        default: false
      },
      rowData: {
        type: Object,
      }
    },
    data() {
      return {
        dialogVisible: this.show,
      };
    },
    computed: {
      //表单数据
      formData() {
        return this.rowData;
      },
      validationRules() {
        return {
          departmentId: [
            {required: true, message: "请选择部门", trigger: "blur"}
          ]
        };
      },
    },
    methods: {
      close() {
        this.$emit("close");
      },
      //提交编辑
      submitEdit() {
        this.$refs.formData.validate(valid => {
          if (valid) {
            this.$api.user.edit(this.formData).then(res => {
              res && this.$emit("submitEdit");
              this.$notify({message: "修改用户成功", type: "success"});
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

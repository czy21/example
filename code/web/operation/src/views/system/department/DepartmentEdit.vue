<template>
  <!-- 编辑界面 -->
  <el-dialog title="修改部门" :visible.sync="dialogVisible" :before-close="close" width="20%">
    <el-form :model="formData" label-width="100px" ref="formData" :rules="validationRules">
      <el-form-item label="部门名称" prop="name">
        <el-input v-model="formData.name"></el-input>
      </el-form-item>
      <el-form-item label="部门电话" prop="phone">
        <el-input v-model="formData.phone"></el-input>
      </el-form-item>
      <el-form-item label="上级部门" prop="parentId">
        <el-select v-model="formData.parentId" placeholder="请选择上级部门">
          <el-option v-for="item in depOptions" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="启用状态" prop="enabled">
        <el-radio-group v-model="formData.enabled">
          <el-radio :label="true">启用</el-radio>
          <el-radio :label="false">禁用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="描述" prop="comment">
        <el-input type="textarea" v-model="formData.comment"></el-input>
      </el-form-item>

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitEdit">确 定</el-button>
    </div>
  </el-dialog>
</template>
<script>

  export default {
    name: "DepartmentEdit",
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
      depOptions() {
        return this.$store.getters.pocketData.departments
      },
      validationRules() {
        return {
          name: [{required: true, message: "请输入部门名称", trigger: "blur"}],
        }
      }
    },
    methods: {
      close() {
        this.$emit('close');
      },
      submitEdit() {
        const dto = {
          id: this.rowData.id,
          parentId: this.rowData.parentId,
          name: this.rowData.name,
          phone: this.rowData.phone,
          companyId: this.rowData.companyId,
          comment: this.rowData.comment,
          enabled: this.rowData.enabled,
        }
        this.$refs.formData.validate(valid => {
          if (valid) {
            this.$api.department.edit(dto).then(res => {
              this.$emit('submitEdit');
              this.$notify({
                message: "更新部门成功",
                type: "success",
                duration: 2000
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

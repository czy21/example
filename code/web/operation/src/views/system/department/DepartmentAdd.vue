<template>
  <el-dialog title="添加部门" :visible.sync="dialogVisible" :before-close="close" width="20%">
    <el-form :model="addForm" label-width="100px" :rules="validationRules" ref="addForm">
      <el-form-item label="部门名称" prop="name">
        <el-input v-model="addForm.name"></el-input>
      </el-form-item>
      <el-form-item label="部门电话" prop="phone">
        <el-input v-model="addForm.phone"></el-input>
      </el-form-item>
      <el-form-item label="上级部门" prop="parentId">
        <el-select v-model="addForm.parentId" placeholder="请选择上级部门">
          <el-option v-for="item in depOptions" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="描述" prop="comment">
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
    name: "DepartmentAdd",
    props: {
      show: {
        type: Boolean,
        default: false
      },
      rowData: {
        type: Object
      }
    },
    data() {
      return {
        //显示新增界面
        dialogVisible: this.show,
        //添加页面数据
        addForm: {},
      };
    },
    computed: {
      companyRowData() {
        return this.rowData;
      },
      depOptions() {
        return this.$store.getters.pocketData.departments;
      },
      validationRules() {
        return {
          name: [{required: true, message: "请输入部门名称", trigger: "blur"}],
        }
      }
    },
    methods: {
      close() {
        this.$emit("close");
      },
      submitAdd() {
        const temp = Object.assign({}, this.addForm);
        temp.companyId = this.companyRowData.id;
        this.$refs.addForm.validate(valid => {
          if (valid) {
            this.$confirm("确认提交吗?", "提示", {}).then(() => {
              // 提交新增的请求
              this.$api.department.create(temp).then(res => {
                if (res.data.indexOf("name already exist") !== -1) {
                  this.$message.warning("部门名称已存在");
                } else if (res.data.indexOf("dep add success") !== -1) {
                  this.$emit('submitAdd');
                  this.$notify({
                    message: "添加部门成功",
                    type: "success",
                    duration: 2000
                  });
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


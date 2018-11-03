<template>
  <el-dialog title="修改商品类别" :visible.sync="dialogVisible" :before-close="close" width="20%">
    <el-form :model="formData" label-width="80px" :rules="validationRules" ref="formData">
      <el-form-item label="类别名称" prop="name">
        <el-input v-model="formData.name"></el-input>
      </el-form-item>
      <el-form-item label="级别" prop="level">
        <el-input-number v-model="formData.level" controls-position="right" :min="1" :max="6"
                         ></el-input-number>
      </el-form-item>
      <el-form-item label="上级类别" prop="parentId">
        <el-select v-model="formData.parentId" placeholder="请选择">
          <el-option
            v-for="item in categoryOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitEdit">修 改</el-button>
    </div>
  </el-dialog>
</template>

<script>

  export default {
    name: "CategoryEdit",
    props: {
      show: {
        type: Boolean,
        default: false
      },
      rowData: {
        type: Object,
      },
      categoryList: {
        type: Array,
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
          name: [{required: true, message: "请输入商品类别", trigger: "blur"}],
        };
      },
      categoryOptions() {
        return this.categoryList;
      }
    },
    methods: {
      close() {
        this.$emit("close");
      },
      //提交编辑
      submitEdit() {
        this.$refs.formData.validate(valid => {
          if (valid) {
            this.$api.category.edit(this.formData).then(res => {
              res && this.$emit("submitEdit");
              this.$notify({
                message: "修改商品类别成功",
                type: "success",
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

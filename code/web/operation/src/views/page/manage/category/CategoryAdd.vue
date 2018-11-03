<template>
  <el-dialog title="添加商品分类" :visible.sync="dialogVisible" :before-close="close" width="20%">
    <el-form :model="addForm" label-width="80px" :rules="validationRules" ref="addForm">
      <el-form-item label="类别名称" prop="name">
        <el-input v-model="addForm.name"></el-input>
      </el-form-item>
      <el-form-item label="级别" prop="level">
        <el-input-number v-model="addForm.level" controls-position="right" :min="1" :max="6"
                         ></el-input-number>
      </el-form-item>
      <el-form-item label="上级类别" prop="parentId">
        <el-select v-model="addForm.parentId" placeholder="请选择">
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
      <el-button type="primary" @click="submitAdd">添 加</el-button>
    </div>
  </el-dialog>
</template>

<script>

  export default {
    name: "CategoryAdd",
    props: {
      show: {
        type: Boolean,
        default: false
      },
      categoryList: {
        type: Array,
      }
    },
    data() {
      return {
        //显示新增界面
        dialogVisible: this.show,
        //新增界面数据
        addForm: {}
      }
    },
    computed: {
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
      submitAdd() {
        this.$refs.addForm.validate(valid => {
          if (valid) {
            this.$api.category.create(this.addForm).then(res => {
              if (res.data.indexOf("categoryName already exist") !== -1) {
                this.$message.warning("类别名称已存在");
              }
              else if (res.data.indexOf("category add success") !== -1) {
                this.$emit("submitAdd")
                this.$notify({
                  message: "添加商品类别成功",
                  type: "success",
                });
              }
            })
          }
        })
      },
    },
    watch: {
      show(val) {
        this.dialogVisible = val;
        val && (this.addForm = {})
      }
    },
  }
</script>



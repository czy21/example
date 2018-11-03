<template>
  <el-dialog title="添加商品" :visible.sync="dialogVisible" :before-close="close" width="55%">
    <el-form :model="addForm" label-width="100px" :rules="validationRules" ref="addForm">
      <el-row type="flex" class="row-bg">
        <el-col :span="6">
          <el-form-item label="商品名称" prop="name" >
            <el-input v-model="addForm.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="包装(KG/包)" prop="packing">
            <el-input-number v-model="addForm.packing" controls-position="right" :min="1"
                             :precision="2"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="安全存量(KG)" prop="safetyStock">
            <el-input-number v-model="addForm.safetyStock" controls-position="right" :min="1"
                             :precision="2"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="单位" prop="unit">
            <el-input-number v-model="addForm.unit" controls-position="right" :min="1" >
            </el-input-number>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg">
        <el-col :span="6">
          <el-form-item label="规格" prop="standard" >
            <el-input v-model="addForm.standard"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="型号" prop="model" style="width: 90%;" >
            <el-input v-model="addForm.model"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="零售价" prop="retailPrice">
            <el-input-number v-model="addForm.retailPrice" controls-position="right" :min="1"
                             :precision="2"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="最低售价" prop="lowPrice">
            <el-input-number v-model="addForm.lowPrice" controls-position="right" :min="1"
                             :precision="2"></el-input-number>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg">
        <el-form-item label="商品类别" prop="categoryId">
          <el-cascader :options="categoryOptions"

                       style="width: 385px"
                       placeholder="请选择商品类别"
                       v-model="addForm.categoryId">
          </el-cascader>
        </el-form-item>
        <el-col :span="6">
          <el-form-item label="颜色" prop="color">
            <el-input v-model="addForm.color"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="制造商" prop="manufacturer">
            <el-input v-model="addForm.manufacturer"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
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
    inject: ['reload'],
    name: "MaterialAdd",
    props: {
      show: {
        type: Boolean,
        default: false
      },
      categoryTree: {
        type: Array
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
          name: [
            {required: true, message: "必须输入商品名称", trigger: "blur"}
          ],
          categoryId: [
            {required: true, message: "必须选择商品类别", trigger: "blur"}
          ],
        };
      },
      categoryOptions() {
        return this.categoryTree;
      }
    },
    methods: {
      close() {
        this.$emit("close");
      },
      submitAdd() {
          const temp = Object.assign({}, this.addForm);
          temp.categoryId = this.addForm.categoryId[this.addForm.categoryId.length - 1]
          this.$refs.addForm.validate(valid => {
            if (valid) {
              this.$api.material.create(temp).then(res => {
                if (res.data.indexOf("materialName already exist") !== -1) {
                  this.$message.warning("商品名称已存在");
                }
                else if (res.data.indexOf("material add success") !== -1) {
                  this.$emit("submitAdd");
                  this.$notify({
                    message: "添加商品成功",
                    type: "success",
                  });
                }
              });
            }
          })
      },
    },
    watch: {
      show(val) {
        this.dialogVisible = val;
        val && (this.addForm = {});
      }
    },
  }
</script>

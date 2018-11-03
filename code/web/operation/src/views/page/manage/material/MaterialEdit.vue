<template>
  <!-- 编辑界面 -->
  <el-dialog title="编辑商品" :visible.sync="dialogVisible" :before-close="close" width="55%">
    <el-form :model="formData" label-width="100px" :rules="validationRules" ref="formData">
      <el-row type="flex" class="row-bg">
        <el-col :span="6">
          <el-form-item label="商品名称" prop="name" >
            <el-input v-model="formData.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="包装(KG/包)" prop="packing">
            <el-input-number v-model="formData.packing" controls-position="right" :min="1"
                             :precision="2"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="安全存量(KG)" prop="safetyStock">
            <el-input-number v-model="formData.safetyStock" controls-position="right" :min="1"
                             :precision="2"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="单位" prop="unit">
            <el-input-number v-model="formData.unit" controls-position="right" :min="1" >
            </el-input-number>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg">
        <el-col :span="6">
          <el-form-item label="规格" prop="standard" >
            <el-input v-model="formData.standard"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="型号" prop="model" style="width: 90%;" >
            <el-input v-model="formData.model"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="零售价" prop="retailPrice">
            <el-input-number v-model="formData.retailPrice" controls-position="right" :min="1"
                             :precision="2"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="最低售价" prop="lowPrice">
            <el-input-number v-model="formData.lowPrice" controls-position="right" :min="1"
                             :precision="2"></el-input-number>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row type="flex" class="row-bg">
        <el-form-item label="商品类别" prop="categoryId">
          <el-cascader :options="categoryOptions"

                       style="width: 385px"
                       placeholder="请选择商品类别"
                       :value="editForm.categoryId"
                       @change="selectCategoryChange">
          </el-cascader>
        </el-form-item>
        <el-col :span="6">
          <el-form-item label="颜色" prop="color">
            <el-input v-model="formData.color"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="制造商" prop="manufacturer">
            <el-input v-model="formData.manufacturer"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
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
  import findParent from "@/utils/findParent";

  export default {
    name: "DepotEdit",
    props: {
      show: {
        type: Boolean,
        default: false
      },
      rowData: {
        type: Object
      },
      categoryTree: {
        type: Array
      }
    },
    data() {
      return {
        //显示新增界面
        dialogVisible: this.show,
        editForm: {
          categoryId: []
        },
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
      formData() {
        return this.rowData
      },
      categoryOptions() {
        return this.categoryTree;
      }
    },
    methods: {
      close() {
        this.$emit("close");
      },
      submitEdit() {
        this.$refs.formData.validate(valid => {
          if (valid) {
            this.$api.material.edit(this.formData).then(res => {
              res && this.$emit("submitEdit");
              this.$notify({
                message: "修改商品成功",
                type: "success",
                duration: 2000
              });
            });
          }
        });
      },
      selectCategoryChange(data) {
        return this.formData.categoryId = data[data.length - 1]
      },
      // 找到formData所属商品类别的级联选择器的父节点集合
      setCategoryIds() {
        this.editForm.categoryId = findParent(this.categoryTree, this.formData.categoryId)
      }
    },
    watch: {
      show(val) {
        this.dialogVisible = val;
        val && this.setCategoryIds()
      }
    },
  }
</script>

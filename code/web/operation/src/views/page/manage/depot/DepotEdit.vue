<template>
  <el-dialog title="编辑仓库" :visible.sync="dialogVisible" :before-close="close" width="20%">
    <el-form :model="formData" label-width="80px" :rules="validationRules" ref="formData">
      <el-form-item label="仓库名称" prop="name">
        <el-input v-model="formData.name"></el-input>
      </el-form-item>
      <el-form-item label="仓库地址" prop="address">
        <el-input v-model="formData.address"></el-input>
      </el-form-item>
      <el-form-item label="仓储费" prop="depotCharge">
        <el-input-number v-model="formData.depotCharge" controls-position="right" :min="1"
                         :precision="2"></el-input-number>
      </el-form-item>
      <el-form-item label="搬运费" prop="truckage">
        <el-input-number v-model="formData.truckage" controls-position="right" :min="1"
                         :precision="2"></el-input-number>
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="formData.type" placeholder="请选择类型">
          <el-option v-for="item in depotOptions" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
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
    name: "DepotEdit",
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
        // 仓库类型
        depotOptions: [
          {
            label: '私有',
            value: 1
          },
          {
            label: '公有',
            value: 2
          }],
      }
    },
    computed: {
      //表单数据
      formData() {
        return this.rowData;
      },
      validationRules() {
        return {
          name: [
            {required: true, message: "请输入仓库名称", trigger: "blur"}
          ],
          address: [
            {required: true, message: "请输入仓库地址", trigger: "blur"}
          ]
        };
      }
    },
    methods: {
      close() {
        this.$emit("close");
      },
      submitEdit() {
        this.$refs.formData.validate(valid => {
          if (valid) {
            this.$api.depot.edit(this.formData).then(res => {
              res && this.$emit("submitEdit")
              this.$notify({
                message: "修改仓库成功",
                type: "success",
                duration: 2000
              });
            });
          }
        })
      }
    },
    watch: {
      show(val) {
        this.dialogVisible = val;
      }
    },
  }
</script>

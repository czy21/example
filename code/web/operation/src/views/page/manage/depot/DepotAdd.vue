<template>
  <el-dialog title="添加仓库" :visible.sync="dialogVisible" :before-close="close" width="20%">
    <el-form :model="addForm" label-width="80px" :rules="validationRules" ref="addForm">
      <el-form-item label="仓库名称" prop="name">
        <el-input v-model="addForm.name"></el-input>
      </el-form-item>
      <el-form-item label="仓库地址" prop="address">
        <el-input v-model="addForm.address"></el-input>
      </el-form-item>
      <el-form-item label="仓储费" prop="depotCharge">
        <el-input-number v-model="addForm.depotCharge" controls-position="right" :min="1"
                         :precision="2"></el-input-number>
      </el-form-item>
      <el-form-item label="搬运费" prop="truckage">
        <el-input-number v-model="addForm.truckage" controls-position="right" :min="1" :precision="2"></el-input-number>
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="addForm.type" placeholder="请选择类型">
          <el-option v-for="item in depotOptions" :key="item.value" :label="item.label" :value="item.value">
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
    inject: ['reload'],
    props: {
      show: {
        type: Boolean,
        default: false
      }
    },
    name: "DepotAdd",
    data() {
      return {
        //显示新增界面
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
        // 仓库表单
        addForm: {},
      }
    },
    computed: {
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
      submitAdd() {
        this.$refs.addForm.validate(valid => {
          if (valid) {
            // 提交新增的请求
            this.$api.depot.create(this.addForm).then(res => {
              if (res.data.indexOf("depotName already exist") !== -1) {
                this.$message.warning("仓库名称已存在");
              }
              else if (res.data.indexOf("depot add success") !== -1) {
                this.$emit("submitAdd");
                this.$notify({
                  message: "添加仓库成功",
                  type: "success",
                  duration: 2000
                });
              }
            });
          }
        })
      }
    },
    watch: {
      show(val) {
        this.dialogVisible = val;
        val && (this.addForm = {})
      }
    },
  }
</script>

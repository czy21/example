<template>
  <el-dialog title="编辑菜单" :visible.sync="dialogVisible" :before-close="close" width="30%">
    <el-form :model="formData" :validate-on-rule-change="false" label-width="120px" :rules="validationRules"
             ref="formData">
      <el-form-item label="菜单名称" prop="name">
        <el-input v-model="formData.name"></el-input>
      </el-form-item>
      <el-form-item label="图标" prop="icon">
        <el-input v-model="formData.icon"></el-input>
      </el-form-item>
      <el-form-item label="类型" prop="isMenu">
        <el-radio-group v-model="formData.isMenu">
          <el-radio :label="true">菜单导航</el-radio>
          <el-radio :label="false">功能请求</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="链接地址" prop="url">
        <el-input v-model="formData.url"></el-input>
      </el-form-item>
      <el-form-item label="上级菜单" prop="parentId">
        <el-select v-model="formData.parentId" placeholder="请选择">
          <el-option
            v-for="item in menuOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
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
    name: "MenuEdit",
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
      };
    },
    computed: {
      //需要修改的菜单或功能行数据
      formData() {
        return this.rowData;
      },
      // 树形菜单数据
      menuOptions() {
        return this.$store.getters.pocketData.menus;
      },
      validationRules() {
        return {
          name: [
            {required: true, message: "请输入菜单名称", trigger: "blur"}
          ],
          url: [
            {required: true, message: "请输入链接地址", trigger: "blur"}
          ],
        };
      },
    },
    methods: {
      close() {
        this.$emit("close");
      },
      submitEdit() {
        this.$refs.formData.validate(valid => {
          if (valid) {
            this.$api.menu.edit(this.formData).then(res => {
              res && this.$emit("submitEdit");
              this.$notify({
                message: "修改成功",
                type: "success",
              });
            });
          }
        });
      },
    },
    watch: {
      show(val) {
        this.dialogVisible = val;
      },
    },
  };
</script>


<template>
  <el-dialog title="添加菜单或权限" :visible.sync="dialogVisible" :before-close="close" width="30%">
    <el-form :model="addForm" :validate-on-rule-change="false" label-width="120px" :rules="validationRules" ref="addForm">
      <el-form-item label="菜单名称" prop="name">
        <el-input v-model="addForm.name"></el-input>
      </el-form-item>
      <el-form-item label="图标" prop="icon">
        <el-input v-model="addForm.icon"></el-input>
      </el-form-item>
      <el-form-item label="类型" prop="isMenu">
        <el-radio-group v-model="addForm.isMenu">
          <el-radio :label="true">菜单导航</el-radio>
          <el-radio :label="false">功能请求</el-radio>
        </el-radio-group>
        <el-checkbox v-model="isNode" :disabled="!addForm.isMenu" style="margin-left: 30px;">节点</el-checkbox>
      </el-form-item>
      <el-form-item label="链接地址" prop="url">
        <el-input v-model="addForm.url" :disabled="isNode&&addForm.isMenu"></el-input>
      </el-form-item>
      <el-form-item label="上级菜单" prop="parentId">
        <el-cascader expand-trigger="click"
                     v-model="addForm.parentId"
                     :options="menuTree"
                     :show-all-levels="false"
                     clearable
                     change-on-select
                     placeholder="未选中则为根菜单">
        </el-cascader>
      </el-form-item>
      <el-form-item label="启用状态" prop="enabled">
        <el-radio-group v-model="addForm.enabled">
          <el-radio :label="true">启用</el-radio>
          <el-radio :label="false">禁用</el-radio>
        </el-radio-group>
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
    name: "MenuAdd",
    props: {
      show: {
        type: Boolean,
        default: false
      },
      menuTemp: {
        type: Array
      }
    },
    data() {
      return {
        //显示新增界面
        dialogVisible: this.show,
        isNode: false,
        // 新增页面数据
        addForm: {
          isMenu: true,
          enabled: true
        },
      };
    },
    computed: {
      validationRules() {
        return {
          name: [
            {required: true, message: "请输入菜单名称", trigger: "blur"}
          ],
          url: [
            {required: true, message: "请输入链接地址", trigger: "blur"}
          ],
          parentId: [
            {required: !(this.addForm.isMenu), message: "请选择上级菜单", trigger: "blur"}
          ]
        };
      },
      // 树形菜单数据
      menuTree() {
        return this.menuTemp;
      }
    },
    methods: {
      close() {
        this.$emit("close");
      },
      submitAdd() {
        const temp = Object.assign({}, this.addForm);
        this.$refs.addForm.validate(valid => {
          if (valid) {
            temp.parentId && (temp.parentId = temp.parentId.pop())
            this.$confirm("确认提交吗?", "提示", {}).then(() => {
              // 提交新增的请求
              this.$api.menu.create(temp).then(res => {
                if (res.data.indexOf("name already exist") !== -1) {
                  this.$message.warning("名称已存在");
                } else if (res.data.indexOf("url already exist") !== -1) {
                  this.$message.warning("地址已存在");
                } else if (res.data.indexOf("menu add success") !== -1) {
                  this.$emit("submitAdd");
                  this.$notify({
                    message: "添加成功",
                    type: "success",
                  });
                }
              });
            });
          }
        });
      },
    },
    watch: {
      show(val) {
        this.dialogVisible = val;
        val && (this.addForm = {isMenu: true, enabled: true}) && (this.isNode = false)
      },
      isNode(val) {
        val && (this.addForm.isMenu) && (this.addForm.url = "#")
        !val && (this.addForm.url = "")
      }
    },
  };
</script>

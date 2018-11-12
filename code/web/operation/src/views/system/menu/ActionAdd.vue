<template>
  <el-dialog title="批量添加权限" :visible.sync="dialogVisible" :before-close="close" width="60%">
    <div class="handle-box">
      <div class="operate-box">
        <el-button type="primary" style="float:right" @click="handleController">加载控制器</el-button>
      </div>
      <div class="search-box">
        <el-button type="primary" icon="el-icon-search">查询</el-button>
      </div>
    </div>
    <div class="container">
      <!-- 列表 -->
      <el-table :data="actionData" border fit highlight-current-row @select-all="selectAllAction">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="functionName" label="权限名称">
          <template slot-scope="scope">
            <el-input v-model="scope.row.functionName"></el-input>
          </template>
        </el-table-column>
        <el-table-column prop="controlName" label="Controller"></el-table-column>
        <el-table-column prop="methodName" label="Action"></el-table-column>
      </el-table>
    </div>

    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitBatchAdd">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>

  export default {
    name: "ActionAdd",
    props: {
      show: {
        type: Boolean,
        default: false
      },
    },
    data() {
      return {
        //显示新增界面
        dialogVisible: this.show,
        actionData: [],
        // 选中的数据集合
        batchActionData: []
      };
    },
    methods: {
      close() {
        this.$emit("close");
      },
      submitBatchAdd() {
        this.$api.menu.batchAddAction(this.batchActionData).then(res => {
          this.$emit("submitBatchAdd");
        });
      },
      handleController() {
        this.$api.admin.loadController().then(res => {
          this.transData(res.result);
        });
      },
      // 解析后台树形数据为常规数据
      transData(resData) {
        this.actionData = []
        resData.forEach(control => {
          if (control.methods.length > 0) {
            for (const i in control.methods) {
              const data = {
                functionName: "",
                controlName: control.deriveClassName,
                methodName: control.methods[i].methodName,
              };
              this.actionData.push(data);
            }
          }
        });
      },
      // 获取选中的数据
      selectAllAction(selection) {
        this.batchActionData = selection;
      }
    },
    watch: {
      show(val) {
        this.dialogVisible = val;
      },
    },
  };
</script>


<template>
  <div class="recursion-menu">
    <el-table :data="formatData" :row-style="showRow" fit highlight-current-row v-bind="$attrs">
      <el-table-column type="selection"></el-table-column>
      <el-table-column v-for="(column, index) in columns" :key="column.value" :label="column.text"
                       :width="column.width">
        <template slot-scope="scope">
                    <span v-if="index === 0" v-for="space in scope.row._level" class="ms-tree-space"
                          :key="space"></span>
          <span class="tree-ctrl" v-if="iconShow(index,scope.row)" @click="toggleExpanded(scope.$index)">
                <i v-if="!scope.row._expanded" class="el-icon-plus"></i>
                <i v-else class="el-icon-minus"></i>
              </span>
          <span>{{scope.row[column.value]}}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="260">
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 删除提示框 -->
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
          <el-button @click="delVisible = false">取 消</el-button>
          <el-button type="primary" @click="deleteRow">确 定</el-button>
      </span>
    </el-dialog>
    <department-edit :rowData="rowData" :show="editShow" @submitEdit="submitEdit" @close="close"></department-edit>
  </div>
</template>

<script>
  import treeToArray from "@/utils/treeTable";
  import transData from "@/utils/transData";
  import DepartmentEdit from "./DepartmentEdit";

  export default {
    name: "TreeDepartment",
    inject: ['reload'],
    props: {
      departmentList: {
        type: [Array, Object],
        required: true
      },
      evalFunc: Function,
      evalArgs: Array,
      //默认展示所有行
      expandAll: {
        type: Boolean,
        default: () => true
      }
    },
    components: {
      DepartmentEdit
    },
    data() {
      return {
        //部门列
        columns: [
          {
            text: "部门名称",
            value: "name"
          },
          {
            text: "电话",
            value: "phone"
          },
          {
            text: "描述",
            value: "comment"
          },
        ],
        // 默认不显示编辑界面
        editShow: false,
        // 部门行数据
        rowData: {},
        //删除对话框
        delVisible: false,
        //删除部门id
        depId: -1,
        //公司Id
        companyId: -1
      };
    },
    computed: {
      // 格式化数据源
      formatData: function () {
        let tmp = transData(this.departmentList);
        const func = this.evalFunc || treeToArray;
        const args = this.evalArgs
          ? Array.concat([tmp, this.expandAll], this.evalArgs)
          : [tmp, this.expandAll];
        return func.apply(null, args);
      }
    },
    methods: {
      //显示行
      showRow: function (row) {
        const show = row.row.parent
          ? row.row.parent._expanded && row.row.parent._show
          : true;
        row.row._show = show;
        return show
          ? "animation:treeTableShow 1s;-webkit-animation:treeTableShow 1s;"
          : "display:none;";
      },
      // 切换下级是否展开
      toggleExpanded: function (trIndex) {
        const record = this.formatData[trIndex];
        record._expanded = !record._expanded;
      },
      // 图标显示
      iconShow(index, record) {
        return index === 0 && record.children && record.children.length > 0;
      },
      //显示编辑界面
      handleEdit(row) {
        this.editShow = true;
        this.rowData = row
      },
      submitEdit() {
        this.close();
      },
      close() {
        this.editShow = false;
      },
      //获取当前行记录数据库中的id并显示删除提示框
      handleDelete(row) {
        this.depId = row.id;
        this.delVisible = true;
      },
      // 提交删除
      deleteRow() {
        this.$api.department.del(this.depId).then(res => {
          this.delVisible = false;
          const index = this.departmentList.indexOf(this.depId);
          this.departmentList.splice(index, 1);
          this.$notify({
            message: "删除成功",
            type: "success",
            duration: 2000
          });
        });
      }
    },
  };
</script>

<style rel="stylesheet/css">
  @keyframes treeTableShow {
    from {
      opacity: 0;
    }
    to {
      opacity: 1;
    }
  }

  @-webkit-keyframes treeTableShow {
    from {
      opacity: 0;
    }
    to {
      opacity: 1;
    }
  }
</style>

<style lang="scss" rel="stylesheet/scss" scoped>
  $color-blue: #2196f3;
  $space-width: 18px;
  .ms-tree-space {
    position: relative;
    top: 1px;
    display: inline-block;
    font-style: normal;
    font-weight: 400;
    line-height: 1;
    width: $space-width;
    height: 14px;
    &::before {
      content: "";
    }
  }

  .processContainer {
    width: 100%;
    height: 100%;
  }

  table td {
    line-height: 26px;
  }

  .tree-ctrl {
    position: relative;
    cursor: pointer;
    color: $color-blue;
    margin-left: -$space-width;
  }

  .recursion-menu {
    margin-bottom: 20px;
  }
</style>

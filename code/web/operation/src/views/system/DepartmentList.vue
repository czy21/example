<template>
  <div class="combine-box">
    <div class="aside-box">
      <el-table :data="$pocket.companys" highlight-current-row
                cell-class-name="cell-icon">
        <el-table-column align="center" label="公司名称" prop="label" width="180px"></el-table-column>
      </el-table>
    </div>
    <div class="right-box">
      <div class="handle-box">
        <div class="operate-box">
          <el-button type="primary" @click="addDepartment('addRoot')">添加部门</el-button>
          <el-button type="primary">批量修改</el-button>
        </div>
      </div>
      <div class="container">
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

          <el-table-column label="操作" width="300">
            <template slot-scope="scope">
              <el-button @click="addDepartment('addSub',scope.row.departmentId)">添加下级部门</el-button>
              <el-button>编辑</el-button>
              <el-button type="danger">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <el-dialog title="添加部门" :visible.sync="departmentAddShow" width="20%">
      <el-form :model="departmentAddForm" label-width="100px" :rules="validationRules" ref="departmentAddForm">
        <el-form-item label="部门名称" prop="departmentName">
          <el-input v-model="departmentAddForm.departmentName"></el-input>
        </el-form-item>
        <el-form-item label="部门电话" prop="phone">
          <el-input v-model="departmentAddForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="所属公司" prop="companyId">
          <el-select v-model="departmentAddForm.companyId" placeholder="请选择公司">
            <el-option v-for="item in $pocket.companys" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="描述" prop="remark">
          <el-input type="textarea" v-model="departmentAddForm.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addDepartment('submit')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import c from '@c'
  import treeTable from '@c/utils/treeTable'

  export default {
    mixins: [c.mixins.list],
    name: "DepartmentList",
    props: {
      evalFunc: Function,
      evalArgs: Array,
      expandAll: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        columns: [
          {
            text: "部门名称",
            value: "departmentName"
          },
          {
            text: "电话",
            value: "phone"
          },
          {
            text: "描述",
            value: "remark"
          },
        ],
        departmentAddShow: false,
        departmentAddForm: {}
      };
    },
    computed: {
      // 格式化数据源
      formatData() {
        let tmp = c.ref.jsUtil.forTree.transChild(this.list, "departmentId");
        const func = this.evalFunc || treeTable;
        const args = this.evalArgs
          ? Array.concat([tmp, this.expandAll], this.evalArgs)
          : [tmp, this.expandAll];
        return func.apply(null, args);
      },
      validationRules() {
        return {
          departmentName: [
            {required: true, message: "必须输入部门名称", trigger: "blur"}
          ],
          companyId: [
            {required: true, message: "必须选择所属公司", trigger: "blur"}
          ],
        };
      },
    },
    methods: {
      addDepartment(status, departmentId) {
        switch (status) {
          case 'addRoot':
            this.departmentAddShow = true
            this.departmentAddForm.parentId = '00000000-0000-0000-0000-000000000000'
            break;
          case 'addSub':
            this.departmentAddShow = true
            this.departmentAddForm.parentId = departmentId
            break;
          case 'submit':
            this.submitOne()
            this.$helper.eui.actWithValidation("departmentAddForm", () => {
              this.departmentAddShow = false
              this.$api.post("department/add", this.departmentAddForm).then(res => {
                this.$refs['departmentAddForm'].resetFields();
                this.search();
              })
            })
            break;
        }
      },
      showRow(row) {
        const show = row.row.parent
          ? row.row.parent._expanded && row.row.parent._show
          : true;
        row.row._show = show;
        return show
          ? "animation:treeTableShow 1s;-webkit-animation:treeTableShow 1s;"
          : "display:none;";
      },
      toggleExpanded(trIndex) {
        const record = this.formatData[trIndex];
        record._expanded = !record._expanded;
      },
      // 图标显示
      iconShow(index, record) {
        return index === 0 && record.children && record.children.length > 0;
      },
      search() {
        this.$api.post("department/search", this.searchModel).then(v => {
          v.data.page && Object.assign(this.searchModel, v.data.page)
          this.list = v.data.list
        });
      },
    },
    mounted() {
      this.load("department/load")
    }
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


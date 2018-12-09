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
        <el-table :data="formatData" highlight-current-row>
          <table-tree-column label="部门名称" prop="departmentName" tree-key="departmentId"></table-tree-column>
          <el-table-column label="部门电话" prop="phone"></el-table-column>
          <el-table-column label="备注" prop="remark"></el-table-column>
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
  import {treeDataTranslate} from '@c/utils'
  import TableTreeColumn from '@v/general/TableTreeColumn'

  export default {
    mixins: [c.mixins.list],
    name: "DepartmentList",
    components: {
      TableTreeColumn
    },
    data() {
      return {
        departmentAddShow: false,
        departmentAddForm: {}
      };
    },
    computed: {
      formatData() {
        return treeDataTranslate(this.list, 'departmentId')
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


<template>
  <div class="combine-table">
    <div class="aside-box">
      <el-table :data="companyData" @cell-click="selectCompany" fit highlight-current-row
                cell-class-name="cell-icon">
        <el-table-column label="公司名称" prop="name" width="200px"></el-table-column>
      </el-table>
    </div>
    <div class="right-box">
      <div class="handle-box">
        <div class="operate-box">
          <el-button type="primary" @click="addSubDep">添加部门</el-button>
          <el-button type="primary">批量修改</el-button>
        </div>
        <div class="search-box">
          <el-input placeholder="查询条件"></el-input>
        </div>
      </div>
      <div class="container">
        <tree-department border :departmentList="departmentList"></tree-department>
      </div>
    </div>
    <department-add :show="addShow" @submitAdd="submitAdd" @close="close" :rowData="rowData"></department-add>
  </div>
</template>
<script>
  import TreeDepartment from "./TreeDepartment";
  import DepartmentAdd from "./DepartmentAdd";

  export default {
    name: "DepartmentIndex",
    components: {
      DepartmentAdd,
      TreeDepartment,
    },
    data() {
      return {
        //默认不显示新增界面
        addShow: false,
        //公司列表
        companyData: [],
        departmentList: [],
        // 公司行数据
        rowData: {}
      };
    },
    methods: {
      //根据选择的公司获取部门
      selectCompany(row) {
        this.$api.department.getCompanyDeps(row.id).then(res => {
          this.departmentList = res.data
        })
        this.rowData = row;
      },
      //显示新增子级部门界面
      addSubDep() {
        if (this.rowData.id) {
          this.addShow = true;
        } else {
          this.$message.warning("请选择公司");
        }
      },
      //提交新增
      submitAdd() {
        this.close();
        this.selectCompany(this.rowData);
      },
      //关闭所有Dialog
      close() {
        this.addShow = false;
      },
      // 获取公司数据
      getCompanyData() {
        this.$api.company.getCompanies().then(res => {
          this.companyData = res.data;
        });
      }
    },
    mounted() {
      this.getCompanyData();
    },
  };
</script>

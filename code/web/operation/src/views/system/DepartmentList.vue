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
              <el-button @click="editDepartment('edit',scope.row)">编辑</el-button>
              <el-button @click="modifiedDepartment(scope.row)"
                         :class="scope.row.enabled
                       ?'el-button--danger'
                       :'el-button--success'">
                {{scope.row.enabled?'禁用':'启用'}}
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 工具条 -->
        <div class="pagination">
          <el-button type="danger" icon="el-icon-delete">批量删除</el-button>
          <el-pagination background @current-change="handleIndexChange" @size-change="handleSizeChange"
                         :current-page="searchModel && searchModel.pageIndex"
                         :page-size="searchModel && searchModel.pageSize"
                         :page-sizes="[15,30,50,100]"
                         layout="total ,sizes, prev, pager, next, jumper"
                         :total="searchModel && searchModel.total">
          </el-pagination>
        </div>
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

    <el-dialog title="编辑部门" :visible.sync="departmentEditShow" width="20%">
      <el-form :model="departmentEditForm" label-width="100px" :rules="validationRules" ref="departmentEditForm">
        <el-form-item label="部门名称" prop="departmentName">
          <el-input v-model="departmentEditForm.departmentName"></el-input>
        </el-form-item>
        <el-form-item label="部门电话" prop="phone">
          <el-input v-model="departmentEditForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="所属公司" prop="companyId">
          <el-select v-model="departmentEditForm.companyId" placeholder="请选择公司">
            <el-option v-for="item in $pocket.companys" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="上级部门" prop="parentId">
          <el-select v-model="departmentEditForm.parentId" placeholder="请选择上级部门">
            <el-option
              value="00000000-0000-0000-0000-000000000000"
              label="顶级部门"
              style="color: #FF7F24;"></el-option>
            <el-option
              v-for="item in list"
              :key="item.departmentId"
              :label="item.departmentName"
              :value="item.departmentId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="描述" prop="remark">
          <el-input type="textarea" v-model="departmentEditForm.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editDepartment('submit')">确 定</el-button>
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
        props: {
          label: "departmentName",
          children: "children"
        },
        departmentAddShow: false,
        departmentAddForm: {},
        departmentEditShow: false,
        departmentEditForm: {},
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
          parentId: [
            {required: true, message: "必须选择上级部门", trigger: "blur"}
          ]
        };
      },
    },
    methods: {
      modifiedDepartment(row) {
        row.enabled = !row.enabled
        this.$api.post("department/modified", {
          departmentId: row.departmentId,
          enabled: row.enabled
        }).then(res => {
          row.enabled = res.data
        })
      },
      editDepartment(status, row) {
        switch (status) {
          case 'edit':
            this.departmentEditShow = true
            this.departmentEditForm = {
              companyId: row.companyId,
              departmentId: row.departmentId,
              departmentName: row.departmentName,
              parentId: row.parentId,
              remark: row.remark,
              phone: row.phone,
            }
            break;
          case 'submit':
            this.submitOne()
            this.$helper.eui.actWithValidation("departmentEditForm", () => {
              console.log(this.departmentEditForm)
              this.departmentEditShow = false
              this.$api.post("department/edit", this.departmentEditForm).then(() => {
                this.$helper.eui.inform(`<strong>${this.departmentEditForm.departmentName}</strong> 修改成功`, () => {
                  this.$refs['departmentEditForm'].clearValidate();
                  this.load("department/load")
                })
              })
            })
            break;
        }
      },
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
              this.$api.post("department/add", this.departmentAddForm).then(() => {
                this.$helper.eui.inform(`<strong>${this.departmentAddForm.departmentName}</strong> 添加成功`, () => {
                  this.$refs['departmentAddForm'].resetFields();
                  this.search();
                })
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

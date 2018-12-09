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

    <el-dialog title="编辑部门" :visible.sync="departmentEditShow" width="20%">
      <el-form :model="departmentEditForm" label-width="100px" :rules="validationRules" ref="departmentEditForm">
        <el-form-item label="部门名称" prop="departmentName">
          <el-input v-model="departmentEditForm.departmentName"></el-input>
        </el-form-item>
        <el-form-item label="部门电话" prop="phone">
          <el-input v-model="departmentEditForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="部门电话" prop="phone">
          <el-popover
            ref="depTreePopover"
            placement="bottom-start"
            trigger="click">
            <el-tree
              :data="formatData"
              :props="props"
              node-key="departmentId"
              ref="departmentTree"
              :default-expand-all="true"
              :highlight-current="true"
              :expand-on-click-node="false">
            </el-tree>
          </el-popover>
          <el-input v-model="departmentEditForm.parentId"
                    v-popover:depTreePopover
                    :readonly="true"
                    placeholder="点击选择上级菜单"
                    class="menu-list__input"></el-input>
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
        departmentEditForm: {}
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
      editDepartment(status, row) {
        switch (status) {
          case 'edit':
            this.departmentEditShow = true
            const temp = {
              companyId: row.companyId,
              departmentId: row.departmentId,
              departmentName: row.departmentName,
              parentId: row.parentId,
              remark: row.remark,
              phone: row.phone,
            }
            console.log(temp)
            break;
          case 'submit':
            this.submitOne()

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

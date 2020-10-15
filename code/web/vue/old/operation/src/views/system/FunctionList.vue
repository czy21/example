<template>
  <div class="main-box">
    <div class="handle-box">
      <div class="search-box">
        <el-form ref="searchForm" :inline="true" :model="searchModel" label-position="left" label-width="80px">
          <el-form-item label="权限名称" prop="functionName">
            <el-input v-model="searchModel.functionName"></el-input>
          </el-form-item>
          <el-form-item label="权限值" prop="functionCode">
            <el-input v-model="searchModel.functionCode"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="search">搜索</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="operate-box">
        <el-button type="primary" @click="addFunc('add')" :disabled="!$hasPermission('AddFunc')">添加权限</el-button>
      </div>
    </div>
    <div class="container">
      <el-table :data="list" border highlight-current-row>
        <el-table-column type="selection" prop="userId" width="55"></el-table-column>
        <el-table-column prop="functionName" label="权限名称"></el-table-column>
        <el-table-column prop="functionCode" label="权限值"></el-table-column>
        <el-table-column prop="remark" label="备注"></el-table-column>
        <el-table-column label="操作" width="250">
          <template slot-scope="scope">
            <el-button @click="editFunc('edit',scope.row)" :disabled="!$hasPermission('EditFunc')">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination class="pagination" background @current-change="handleIndexChange" @size-change="handleSizeChange"
                     :current-page="searchModel && searchModel.pageIndex"
                     :page-size="searchModel && searchModel.pageSize"
                     :page-sizes="[20,50,100]"
                     layout="total ,sizes, prev, pager, next, jumper"
                     :total="searchModel && searchModel.total">
      </el-pagination>
    </div>

    <el-dialog title="添加权限" :visible.sync="funcAddShow" width="20%">
      <el-form ref="funcAddForm" :rules="validationRules" :model="funcAddForm" label-width="80px">
        <el-form-item label="权限名称" prop="functionName">
          <el-input v-model="funcAddForm.functionName"></el-input>
        </el-form-item>
        <el-form-item label="权限值" prop="functionCode">
          <el-input v-model="funcAddForm.functionCode"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" v-model="funcAddForm.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addFunc('submit')" :disabled="isDisable">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="修改用户" :visible.sync="funcEditShow" width="20%">
      <el-form ref="funcEditForm" :rules="validationRules" :model="funcEditForm" label-width="80px">
        <el-form-item label="权限名称" prop="functionName">
          <el-input v-model="funcEditForm.functionName"></el-input>
        </el-form-item>
        <el-form-item label="权限值" prop="functionCode">
          <el-input v-model="funcEditForm.functionCode"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" v-model="funcEditForm.remark"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editFunc('submit')" :disabled="isDisable">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import c from '@c'

  export default {
    mixins: [c.mixins.list],
    name: "FunctionList",
    data() {
      return {
        funcAddShow: false,
        funcEditShow: false,
        funcAddForm: {},
        funcEditForm: {}
      }
    },
    computed: {
      validationRules() {
        return {
          functionName: [
            {required: true, message: "必须输入权限名称", trigger: "blur"}
          ],
          functionCode: [
            {required: true, message: "必须输入权限值", trigger: "blur"}
          ],
        };
      },
    },
    methods: {
      addFunc(status) {
        switch (status) {
          case 'add':
            this.funcAddShow = true
            break;
          case 'submit':
            this.submitOne()
            this.$helper.eui.actWithValidation("funcAddForm", () => {
              this.funcAddShow = false
              this.$api.post("function/add", this.funcAddForm).then(res => {
                this.$refs['funcAddForm'].resetFields();
                this.search();
              })
            })
            break;
        }
      },
      editFunc(status, row) {
        switch (status) {
          case 'edit':
            this.funcEditShow = true
            this.funcEditForm = row
            break;
          case 'submit':
            this.submitOne()
            this.$helper.eui.actWithValidation("funcEditForm", () => {
              this.funcEditShow = false
              this.$api.post("function/edit", this.funcEditForm).then(res => {
                this.$refs['funcEditForm'].clearValidate();
                this.funcEditForm = res.data
              })
            })
            break;

        }
      },
      search() {
        this.$api.post("function/search", this.searchModel).then(v => {
          v.data.page && Object.assign(this.searchModel, v.data.page)
          this.list = v.data.list
        });
      },
    },
    mounted() {
      this.load("function/load")
    }
  }
</script>


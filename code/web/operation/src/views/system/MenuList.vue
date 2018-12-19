<template>
  <div class="combine-box">
    <div class="aside-box">
      <el-tree
        :props="props"
        :data="$pocket.menuTree"
        default-expand-all
        node-key="value"
        highlight-current>
      </el-tree>
    </div>
    <div class="right-box">
      <div class="handle-box">
        <div class="operate-box">
          <el-button type="primary" @click="addMenu('add')">添加菜单(权限)</el-button>
          <el-button type="primary" @click="batchAddPermission('add')">批量添加权限</el-button>
        </div>
        <div class="search-box">
          <el-cascader expand-trigger="click"
                       :options="$pocket.menuTree"
                       style="width: 300px;"
                       clearable
                       show-all-levels
                       placeholder="请选择菜单"
                       :change-on-select="true"
                       @change="selectMenuChange">
          </el-cascader>
          <el-button type="primary" @click="search">搜索</el-button>

        </div>
      </div>
      <div class="container">
        <el-table :data="list" border highlight-current-row>
          <el-table-column prop="menuName" label="名称"></el-table-column>
          <el-table-column label="图标">
            <template slot-scope="scope">
              <i :class="scope.row.icon"></i>
            </template>
          </el-table-column>
          <el-table-column prop="url" label="菜单URL"></el-table-column>
          <el-table-column label="类型">
            <template slot-scope="scope">
              {{scope.row.isMenu?'菜单':'权限'}}
            </template>
          </el-table-column>
          <el-table-column prop="sort" label="排序"></el-table-column>
          <el-table-column prop="remark" label="菜单(权限)描述"></el-table-column>
          <el-table-column label="操作" width="260">
            <template slot-scope="scope">
              <el-button @click="editMenu('edit',scope.row)">编辑</el-button>
              <el-button @click="deleteMenu(scope.row)" type="danger">删除</el-button>
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
      <el-dialog title="添加菜单或权限" :visible.sync="menuAddShow" width="30%">
        <el-form :model="menuAddForm" label-width="120px" :rules="validationRules" ref="menuAddForm">
          <el-form-item label="菜单名称" prop="menuName">
            <el-input v-model="menuAddForm.menuName"></el-input>
          </el-form-item>
          <el-form-item label="类型" prop="isMenu">
            <el-radio-group v-model="isMenu">
              <el-radio :label="true">菜单导航</el-radio>
              <el-radio :label="false">功能请求</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="图标" prop="icon">
            <el-input v-model="menuAddForm.icon" placeholder="请输入图标的class名称" :disabled="!isMenu"></el-input>
          </el-form-item>
          <el-form-item label="链接地址" prop="url">
            <el-input v-model="menuAddForm.url"
                      :placeholder="isMenu
                      ?'输入#则为菜单节点'
                      :'请输入权限地址'"></el-input>
          </el-form-item>
          <el-form-item label="上级菜单" prop="parentId">
            <el-select v-model="menuAddForm.parentId" placeholder="请选择上级菜单">
              <el-option
                value="00000000-0000-0000-0000-000000000000"
                label="顶级菜单"
                style="color: #FF7F24;"></el-option>
              <el-option
                v-for="item in $pocket.menus"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="描述" prop="remark">
            <el-input type="textarea" v-model="menuAddForm.remark"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="addMenu('submit')">确 定</el-button>
        </div>
      </el-dialog>

      <el-dialog title="修改菜单或权限" :visible.sync="menuEditShow" width="30%">
        <el-form :model="menuEditForm" label-width="120px" :rules="validationRules" ref="menuEditForm">
          <el-form-item label="菜单名称" prop="menuName">
            <el-input v-model="menuEditForm.menuName"></el-input>
          </el-form-item>
          <el-form-item label="类型" prop="isMenu">
            <el-radio-group v-model="isMenu">
              <el-radio :label="true">菜单导航</el-radio>
              <el-radio :label="false">功能请求</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="图标" prop="icon">
            <el-input v-model="menuEditForm.icon" placeholder="请输入图标的class名称" :disabled="!isMenu"></el-input>
          </el-form-item>
          <el-form-item label="排序" prop="sort">
            <el-input v-model="menuEditForm.sort"></el-input>
          </el-form-item>
          <el-form-item label="链接地址" prop="url">
            <el-input v-model="menuEditForm.url"
                      :placeholder="isMenu
                      ?'输入#则为菜单节点'
                      :'请输入权限地址'"></el-input>
          </el-form-item>
          <el-form-item label="上级菜单" prop="parentId">
            <el-select v-model="menuEditForm.parentId" placeholder="请选择上级菜单">
              <el-option
                value="00000000-0000-0000-0000-000000000000"
                label="顶级菜单"
                style="color: #FF7F24;"></el-option>
              <el-option
                v-for="item in $pocket.menus"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="描述" prop="remark">
            <el-input type="textarea" v-model="menuEditForm.remark"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="editMenu('submit')">确 定</el-button>
        </div>
      </el-dialog>

      <el-dialog title="批量添加权限" :visible.sync="batchAddPermissionShow" width="60%">
        <div class="handle-box">
          <div class="search-box">
            <el-button type="primary" icon="el-icon-search">查询</el-button>
          </div>
        </div>
        <div class="container">
          <!-- 列表 -->
          <el-table :data="apiList" height="400" border fit highlight-current-row @select-all="selectAction">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="summary" label="权限名称"></el-table-column>
            <el-table-column prop="url" label="请求地址(API)"></el-table-column>
            <el-table-column prop="tag" label="所属控制器"></el-table-column>
          </el-table>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="batchAddPermission('submit')">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import c from '@c'

  export default {
    mixins: [c.mixins.list],
    name: "MenuList",
    data() {
      return {
        // 树形控件属性
        props: {
          label: "label",
          children: "children"
        },
        menuAddShow: false,
        menuEditShow: false,
        menuAddForm: {},
        menuEditForm: {},
        tempMenuEditForm: {},

        isMenu: true,
        batchAddPermissionShow: false,
        permissions: [],
        apiList: []
      }
    },
    computed: {
      validationRules() {
        return {
          menuName: [
            {required: true, message: "必须输入菜单名称", trigger: "blur"}
          ],
          url: [
            {required: true, message: "必须输入链接地址", trigger: "blur"}
          ],
          parentId: [
            {required: true, message: "必须选择上级菜单", trigger: "blur"}
          ]
        };
      },
    },
    methods: {
      selectAction(selection) {
        this.permissions = selection;
      },
      batchAddPermission(status) {
        switch (status) {
          case 'add':
            this.batchAddPermissionShow = true
            this.$api.get("docs").then(res => {
              this.apiList = c.ref.jsUtil.swagger.transDocsData(res.data.paths)
            })
            break;
          case 'submit':
            this.batchAddPermissionShow = false
            this.$api.post("menu/batchAddAction", {permissions: JSON.stringify(this.permissions)}).then(res => {
              this.$helper.eui.inform("批量添加成功", this.search())
            })
            break;

        }
      },
      addMenu(status) {
        switch (status) {
          case 'add':
            this.menuAddShow = true
            break;
          case 'submit':
            this.submitOne()
            this.menuAddForm.isMenu = this.isMenu
            this.$helper.eui.actWithValidation("menuAddForm", () => {
              this.menuAddShow = false
              this.$api.post("menu/add", this.menuAddForm).then(() => {
                this.$helper.eui.inform(`<strong>${this.menuAddForm.menuName}</strong> 添加成功`, () => {
                  this.$refs['menuAddForm'].resetFields();
                  this.load("menu/load")
                })
              })
            })
            break;

        }
      },
      editMenu(status, row) {
        switch (status) {
          case 'edit':
            this.menuEditShow = true
            this.menuEditForm = Object.assign({}, this.menuEditForm, row)
            break;
          case 'submit':
            this.submitOne()
            this.$helper.eui.actWithValidation("menuEditForm", () => {
              this.menuEditShow = false
              this.$api.post("menu/edit", this.menuEditForm).then(() => {
                this.$helper.eui.inform(`<strong>${this.menuEditForm.menuName}</strong> 修改成功`, () => {
                  this.$refs['menuEditForm'].clearValidate();
                  this.load("menu/load")
                })
              })
            })
            break;
        }
      },
      deleteMenu(row) {
        this.$helper.eui.confirm(`此操作不可恢复,确定删除 <strong>${row.menuName}</strong> ?`, () => {
          this.$api.delete("menu/delete", {menuId: row.menuId}).then(() => {
            this.$helper.eui.inform(`<strong>${row.menuName}</strong> 删除成功`, this.load("menu/load"))
          })
        })
      },
      selectMenuChange(data) {
        this.searchModel.menuId = data[data.length - 1];
      },
      search() {
        this.$api.post("menu/search", this.searchModel).then(v => {
          v.data.page && Object.assign(this.searchModel, v.data.page)
          this.list = v.data.list
        });
      },
    },
    watch: {
      isMenu(newVal) {
        if (!newVal) {
          this.menuAddForm.icon = ""
          this.menuEditForm.icon = ""
        }
      },
      menuEditForm(newVal) {
        this.isMenu = newVal.isMenu
      }
    },
    mounted() {
      this.load("menu/load")
    }
  };
</script>


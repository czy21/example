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
        <div class="search-box">
          <el-form ref="searchForm" :inline="true" :model="searchModel" label-position="left" label-width="80px">
            <el-form-item label="菜单" prop="menuId">
              <el-cascader expand-trigger="click"
                           :options="$pocket.menuTree"
                           style="width: 300px;"
                           clearable
                           show-all-levels
                           placeholder="请选择菜单"
                           :change-on-select="true"
                           @change="selectMenuChange">
              </el-cascader>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="search">搜索</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="operate-box">
          <el-button type="primary" @click="addMenu('add')">添加菜单</el-button>
        </div>
      </div>
      <div class="container">
        <el-tabs type="card">
          <el-tab-pane label="菜单列表"><el-table :data="list" border highlight-current-row>
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
                <el-button type="danger" @click="deleteMenu(scope.row)"
                           :disabled="!$hasPermission('menu/delete')">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
            <el-pagination class="pagination" background @current-change="handleIndexChange" @size-change="handleSizeChange"
                           :current-page="searchModel && searchModel.pageIndex"
                           :page-size="searchModel && searchModel.pageSize"
                           :page-sizes="[20,50,100]"
                           layout="total ,sizes, prev, pager, next, jumper"
                           :total="searchModel && searchModel.total">
            </el-pagination></el-tab-pane>
          <el-tab-pane label="权限列表">权限列表</el-tab-pane>
        </el-tabs>

      </div>
      <el-dialog title="添加菜单或权限" :visible.sync="menuAddShow" width="25%">
        <el-form :model="menuAddForm" label-width="120px" :rules="validationRules" ref="menuAddForm">
          <el-form-item label="菜单名称" prop="menuName">
            <el-input v-model="menuAddForm.menuName"></el-input>
          </el-form-item>
          <el-form-item label="链接地址" prop="url">
            <el-input v-model="menuAddForm.url"
                      placeholder="输入#则为菜单节点"></el-input>
          </el-form-item>
          <el-form-item label="上级菜单" prop="parentId">
            <el-select v-model="menuAddForm.parentId" placeholder="请选择上级菜单">
              <el-option value="0" label="顶级菜单" style="color: #FF7F24;"></el-option>
              <el-option v-for="item in $pocket.menus" :key="item.value" :label="item.label"
                         :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="菜单图标" prop="icon">
            <el-popover
              ref="iconListPopover"
              placement="bottom-start"
              trigger="click"
              v-model="iconAddPopoverVisible"
              popper-class="mod-menu__icon-popover">
              <div class="mod-menu__icon-list">
                <el-button
                  v-for="(item, index) in iconList"
                  :key="index"
                  :class="{ 'is-active': item === menuAddForm.icon }"
                  @click="iconActiveHandle('add',item)">
                  <svg-icon :icon-class="item"/>
                </el-button>
              </div>
            </el-popover>
            <el-input v-model="menuAddForm.icon" v-popover:iconListPopover :readonly="true" placeholder="菜单图标名称"
                      class="icon-list__input"></el-input>
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
          <el-form-item label="排序" prop="sort">
            <el-input v-model="menuEditForm.sort"></el-input>
          </el-form-item>
          <el-form-item label="链接地址" prop="url">
            <el-input v-model="menuEditForm.url"
                      placeholder="输入#则为菜单节点"></el-input>
          </el-form-item>
          <el-form-item label="上级菜单" prop="parentId">
            <el-select v-model="menuEditForm.parentId" placeholder="请选择上级菜单">
              <el-option
                value="0"
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
          <el-form-item label="菜单图标" prop="icon">
            <el-popover
              ref="iconListPopover"
              placement="bottom-start"
              trigger="click"
              v-model="iconEditPopoverVisible"
              popper-class="mod-menu__icon-popover">
              <div class="mod-menu__icon-list">
                <el-button
                  v-for="(item, index) in iconList"
                  :key="index"
                  :class="{ 'is-active': item === menuEditForm.icon }"
                  @click="iconActiveHandle('edit',item)">
                  <svg-icon :icon-class="item"/>
                </el-button>
              </div>
            </el-popover>
            <el-input v-model="menuEditForm.icon" v-popover:iconListPopover :readonly="true" placeholder="菜单图标名称"
                      class="icon-list__input"></el-input>
          </el-form-item>
          <el-form-item label="描述" prop="remark">
            <el-input type="textarea" v-model="menuEditForm.remark"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="editMenu('submit')">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import c from '@c'
  import Icon from '@v/custom/svg'

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
        iconAddPopoverVisible: false,
        iconEditPopoverVisible: false,
        batchAddPermissionShow: false,
        menuAddForm: {
          icon: ''
        },
        menuEditForm: {},
        tempMenuEditForm: {},
        permissions: [],
        apiList: [],
        iconList: [],
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
      iconActiveHandle(status, iconName) {
        switch (status) {
          case 'add':
            this.menuAddForm.icon = iconName
            this.iconAddPopoverVisible = false
            break;
          case 'edit':
            this.menuEditForm.icon = iconName
            this.iconEditPopoverVisible = false
        }
      },
      selectAction(selection) {
        this.permissions = selection;
      },
      addMenu(status) {
        switch (status) {
          case 'add':
            this.menuAddShow = true
            break;
          case 'submit':
            this.submitOne()
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
            this.menuEditForm.icon = this.isMenu ? this.menuEditForm.icon : null
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
    mounted() {
      this.iconList = Icon.getIconList();
      this.load("menu/load")
    }
  };
</script>

<style lang="scss">
  .mod-menu {
    .menu-list__input,
    .icon-list__input {
      > .el-input__inner {
        cursor: pointer;
      }
    }

    &__icon-popover {
      max-width: 370px;
    }

    &__icon-list {
      max-height: 180px;

      > .el-button {
        padding: 8px;
        margin: 8px 0 0 8px;

        > span {
          display: inline-block;
          vertical-align: middle;
          width: 18px;
          height: 18px;
          font-size: 18px;
        }
      }
    }

    .icon-list__tips {
      font-size: 18px;
      text-align: center;
      color: #e6a23c;
      cursor: pointer;
    }
  }
</style>


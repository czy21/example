<template>
  <div class="combine-table">
    <div class="aside-box">
      <el-tree
        style="width: 180px"
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
        <el-table :data="list" border fit highlight-current-row>
          <el-table-column prop="menuName" label="菜单(权限)名称"></el-table-column>
          <el-table-column prop="url" label="菜单(权限)地址"></el-table-column>
          <el-table-column label="类型">
            <template slot-scope="scope">
              {{scope.row.isMenu?'菜单':'权限'}}
            </template>
          </el-table-column>
          <el-table-column prop="remark" label="菜单(权限)描述"></el-table-column>
          <el-table-column label="操作" width="260">
            <template slot-scope="scope">
              <el-button>编辑</el-button>
              <el-button type="danger">删除</el-button>
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
        menuAddForm: {},
        isMenu: true
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
                this.$refs['menuAddForm'].resetFields();
                this.search();
              })
            })
            break;
          default:
            break;
        }
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
        (!newVal) && (this.menuAddForm.icon = "")

      }
    },
    mounted() {
      this.load("menu/load")
    }
  };
</script>


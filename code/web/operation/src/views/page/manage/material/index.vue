<template>
  <div class="combine-table">
    <div class="handle-box">
      <div class="operate-box">
        <el-select v-model="depotId"
                   clearable
                   placeholder="请选择仓库"
                   @change="selectDepotChange">
          <el-option
            v-for="item in depotOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <el-cascader expand-trigger="click" :options="categoryTree"
                     clearable
                     change-on-select
                     placeholder="请选择商品类别"
                     @change="selectCategoryChange">
        </el-cascader>
        <el-button type="primary" @click="search">搜索</el-button>
        <el-button type="primary" @click="handleAdd">添加商品</el-button>
      </div>
      <div class="search-box">

        <el-input placeholder="关键词" style="width:200px"></el-input>
        <el-button type="primary" icon="el-icon-search">查询</el-button>
      </div>
    </div>
    <div class="container">
      <el-table :data="materialList" border fit highlight-current-row>
        <el-table-column type="selection" prop="id" width="55"></el-table-column>
        <el-table-column label="商品名称" prop="name"></el-table-column>
        <el-table-column label="制造商" prop="manufacturer"></el-table-column>
        <el-table-column label="包装(KG/包)" prop="packing"></el-table-column>
        <el-table-column label="安全存量(KG)" prop="safetyStock"></el-table-column>
        <el-table-column label="型号" prop="model"></el-table-column>
        <el-table-column label="规格" prop="standard"></el-table-column>
        <el-table-column label="颜色" prop="color"></el-table-column>
        <el-table-column label="单位" prop="unit"></el-table-column>
        <el-table-column label="零售价" prop="retailPrice"></el-table-column>
        <el-table-column label="最低售价" prop="lowPrice"></el-table-column>

        <el-table-column label="操作" width="250">
          <template slot-scope="scope">
            <el-button @click="allotDepot(scope.row)">分配仓库</el-button>
            <el-button @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-button type="danger" icon="el-icon-delete">批量删除</el-button>
        <el-pagination background
                       @current-change="handleCurrentChange"
                       @size-change="handleSizeChange"
                       :current-page="pageModel.pageIndex"
                       :page-size="pageModel.pageSize"
                       :page-sizes="[15,30,50,100]"
                       :total="pageModel.totalCount"
                       layout="total ,sizes, prev, pager, next, jumper">
        </el-pagination>
      </div>
    </div>
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
            </span>
    </el-dialog>
    <material-add :show="addShow" @submitAdd="submitAdd" @close="close" :categoryTree="categoryTree"></material-add>
    <material-edit :show="editShow" @submitEdit="submitEdit" @close="close" :rowData="rowData" :categoryTree="categoryTree"></material-edit>
    <material-depot :show="materialDepotShow" @submitAssignDepot="submitAssignDepot" @close="close" :rowData="rowData"></material-depot>
  </div>
</template>

<script>
  import MaterialAdd from './MaterialAdd'
  import MaterialEdit from './MaterialEdit'
  import MaterialDepot from './MaterialDepot'
  import transJson from '@/utils/transJson'

  export default {
    inject: ['reload'],
    name: "MaterialIndex",
    components: {
      MaterialAdd,
      MaterialEdit,
      MaterialDepot,
    },
    data() {
      return {
        //默认不显示新增界面
        addShow: false,
        // 默认不显示编辑界面
        editShow: false,
        // 默认不显示删除界面
        delVisible: false,
        // 默认不显示分配仓库界面
        materialDepotShow: false,
        materialList: [],
        categoryId: '',
        depotId: '',
        materialId: 0,
        // 分页实体
        pageModel: {
          //分页的总行数
          totalCount: 0,
          //当前页值
          pageIndex: 1,
          // 当前页的行数
          pageSize: 15,
        },
        // 商品行数据
        rowData: {}
      }
    },
    computed: {
      depotOptions() {
        return this.$store.getters.pocketData.depots;
      },
      categoryTree() {
        return transJson(this.$store.getters.pocketData.categories, 'value', 'parentId', 'children');
      }
    },
    methods: {
      // 添加界面
      handleAdd() {
        this.addShow = true;
      },
      //提交新增
      submitAdd() {
        this.close();
        this.getData();
      },
      //显示编辑界面
      handleEdit(row) {
        this.editShow = true
        this.rowData = row;
      },
      //提交修改
      submitEdit() {
        this.close();
        this.getData();
      },
      //关闭所有Dialog
      close() {
        this.addShow = false;
        this.editShow = false;
        this.materialDepotShow = false
      },
      handleDelete(row) {
        this.delVisible = true;
        this.materialId = row.id;
      },
      deleteRow() {
        this.$api.material.del(this.materialId).then(res => {
          this.$notify({
            message: "删除成功",
            type: "success",
            duration: 2000
          });
          this.reload();
        });
      },
      submitAssignDepot() {
        this.close();
      },
      selectDepotChange(data) {
        this.depotId = data;
      },
      selectCategoryChange(data) {
        this.categoryId = data[data.length - 1]
      },
      allotDepot(row) {
        this.materialDepotShow = true;
        this.rowData = row
      },
      //获取当前页值
      handleCurrentChange(val) {
        this.pageModel.pageIndex = val;
        this.getData()
      },
      // 当前页显示的行数
      handleSizeChange(val) {
        this.pageModel.pageSize = val;
        this.getData()
      },
      search() {
        this.getData();
      },
      getData() {
        const pageCriteria = {
          pageIndex: this.pageModel.pageIndex,
          pageSize: this.pageModel.pageSize,
          categoryId: this.categoryId,
          depotId: this.depotId
        }
        this.$api.material.getPageMaterials(pageCriteria).then(res => {
          this.materialList = res.data.list
          this.pageModel = res.data.page;
        })
      }
    },
    mounted() {
      this.getData();
    }
  }
</script>

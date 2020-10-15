<template>
  <div class="main-box">
    <div class="handle-box">
      <div class="search-box">
        <el-form ref="searchForm" :inline="true" :model="searchModel" label-position="left" label-width="80px">
          <el-form-item label="API名称" prop="description">
            <el-input v-model="searchModel.description"></el-input>
          </el-form-item>
          <el-form-item label="方法名" prop="method">
            <el-input v-model="searchModel.method"></el-input>
          </el-form-item>
          <el-form-item label="请求IP" prop="requestIp">
            <el-input v-model="searchModel.requestIp"></el-input>
          </el-form-item>
          <el-form-item label="异常代码" prop="exceptionCode">
            <el-input v-model="searchModel.exceptionCode"></el-input>
          </el-form-item>
          <el-form-item label="异常描述" prop="exceptionDetail">
            <el-input v-model="searchModel.exceptionDetail"></el-input>
          </el-form-item>
          <el-form-item label="是否成功" prop="logType">
            <el-select v-model="searchModel.logType" placeholder="请选择">
              <el-option
                v-for="(item,index) in success"
                :key="index"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="search();clearSort()">查询</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="container">
      <!-- 列表 -->
      <el-table :data="list" border highlight-current-row @sort-change='sortChange'>
        <el-table-column prop="description" label="API名称" width="180"></el-table-column>
        <el-table-column prop="method" label="方法名" width="280"></el-table-column>
        <el-table-column prop="requestIp" label="请求IP" width="150"></el-table-column>
        <el-table-column prop="exceptionCode" label="异常代码"></el-table-column>
        <el-table-column prop="exceptionDetail" label="异常描述"></el-table-column>
        <el-table-column label="状态" width="60">
          <template slot-scope="scope">
            {{scope.row.logType?'失败':'成功'}}
          </template>
        </el-table-column>
        <el-table-column prop="operatorName" label="操作人" width="60"></el-table-column>
        <el-table-column prop="addedTime" label="执行日期" width="150" sortable="custom"
                         ref="addedTimeSort"></el-table-column>
        <el-table-column prop="spendTime" label="执行时长(ms)" width="100"></el-table-column>
      </el-table>
      <el-pagination class="pagination" background @current-change="handleIndexChange" @size-change="handleSizeChange"
                     :current-page="searchModel && searchModel.pageIndex"
                     :page-size="searchModel && searchModel.pageSize"
                     :page-sizes="[20,50,100]"
                     layout="total ,sizes, prev, pager, next, jumper"
                     :total="searchModel && searchModel.total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import c from '@c'

  export default {
    mixins: [c.mixins.list],
    data() {
      return {
        success: [
          {value: '', label: ''},
          {value: true, label: '是'},
          {value: false, label: '否'}
        ]
      }
    },
    name: "LogList",
    methods: {
      sortChange({column, prop, order}) {
        this.searchModel.addedTimeSort = order.replace("ending", "")
        this.search()
      },
      clearSort() {
        this.$refs.addedTimeSort.columnConfig.order = ''
        this.searchModel.addedTimeSort = 'desc'
      },
      search() {
        this.$api.post("log/search", this.searchModel).then(v => {
          v.data.page && Object.assign(this.searchModel, v.data.page)
          this.list = v.data.list
        });
      },
    },
    mounted() {
      this.load("log/load")
    }
  }
</script>


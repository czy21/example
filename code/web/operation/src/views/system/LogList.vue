<template>
  <div class="main-box">
    <div class="handle-box">
      <div class="search-box">
        <el-input placeholder="关键词" style="width:200px"></el-input>
        <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
      </div>
    </div>
    <div class="container">
      <!-- 列表 -->
      <el-table :data="list" border highlight-current-row>
        <el-table-column prop="description" label="API名称" width="150"></el-table-column>
        <el-table-column prop="method" label="方法名" width="200"></el-table-column>
        <el-table-column label="请求状态" width="80">
          <template slot-scope="scope">
            {{scope.row.logType?'失败':'成功'}}
          </template>
        </el-table-column>
        <el-table-column prop="requestIp" label="请求IP" width="100"></el-table-column>
        <el-table-column prop="exceptionCode" label="异常代码"></el-table-column>
        <el-table-column prop="exceptionDetail" label="异常描述"></el-table-column>
        <el-table-column prop="operatorName" label="操作人" width="100"></el-table-column>
        <el-table-column prop="addedTime" label="执行日期" width="150"></el-table-column>
        <el-table-column prop="spendTime" label="执行时长(ms)" width="100"></el-table-column>
      </el-table>
      <!-- 工具条 -->
      <div class="pagination">
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
</template>

<script>
  import c from '@c'

  export default {
    mixins: [c.mixins.list],
    name: "LogList",
    methods: {
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


export default {
  data() {
    return {
      searchModel: {},
      list: [],
    }
  },
  methods: {
    load(url) {
      this.$api.get(url, this.searchModel).then(res => {
        res.data.page && Object.assign(this.searchModel, res.data.page)
        this.list = res.data.list
      })
    },
    handleIndexChange(val) {
      this.searchModel.pageIndex = val;
    },
    handleSizeChange(val) {
      this.searchModel.pageSize = val;
    }
  }
}

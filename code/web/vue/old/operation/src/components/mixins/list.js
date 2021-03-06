export default {
  data() {
    return {
      isDisable: false,
      searchModel: {
        pageIndex: 1,
        pageSize: 20
      },
      list: [],
    }
  },
  methods: {
    submitOne() {
      this.isDisable = true
      setTimeout(() => {
        this.isDisable = false
      }, 1000)
    },
    load(url) {
      this.$api.get(url, this.searchModel).then(res => {
        res.data.page && Object.assign(this.searchModel, res.data.page)
        this.list = res.data.list
      })
    },
    handleIndexChange(val) {
      this.searchModel.pageIndex = val;
      this.search()
    },
    handleSizeChange(val) {
      this.searchModel.pageSize = val;
      this.search()
    },
  },
}

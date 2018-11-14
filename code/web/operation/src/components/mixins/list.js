export default {
  data() {
    return {
      searchModel: {
        pageIndex: 1,
        pageSize: 15
      },
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
    create(url, form) {
      return this.$api.post(url, form).then(res => {
        this.list.unshift(res.data)
        return res
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

const _ = require('lodash')


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
    handleIndexChange(val) {
      this.searchModel.pageIndex = val;
      this.verifySearch(this.search())
    },
    handleSizeChange(val) {
      this.searchModel.pageSize = val;
      this.verifySearch(this.search())
    },
    verifySearch(val) {
      if (val instanceof Promise) {
        val.then(v => {
          v.data.page && Object.assign(this.searchModel, v.data.page)
          this.list = v.data.list
        });
      }
    },
  }
}

<template>
  <div>
    <List :data="userList" :columns="columns"/>
  </div>
</template>

<script lang="ts">
import {Component, Provide, Vue} from 'vue-property-decorator';
import List, {Column} from '@c/List.vue'

@Component({
  components: {
    List
  }
})
export default class UserIndex extends Vue {
  @Provide() userList: Array<any> = []

  get columns(): Array<Column> {
    return [
      {
        prop: "name",
        label: "姓名"
      },
      {
        prop: "age",
        label: "年龄"
      }
    ]
  }

  search() {
    // this.$api.get("user/load").then((data:any) => {
    //   console.log(data)
    // })
    // this.$helper.eui.inform("hello",()=>{
    //   console.log("ss")
    // })

    this.$stub.helper.eui.inform("你是谁", () => {
      console.log("ff")
    })

  }

  mounted() {
    this.search()
  }

}
</script>
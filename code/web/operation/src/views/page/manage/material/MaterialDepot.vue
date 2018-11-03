<template>
  <el-dialog title="分配仓库" :visible.sync="dialogVisible" :before-close="close" width="40%">
    <div class="allot-transfer">
      <el-transfer :button-texts="['收回', '分配']"
                   :titles="['未分配', '已分配']"
                   :props="{key: 'value',label:'label'}"
                   :data="depotList"
                   v-model="materialDepotIds">
      </el-transfer>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitAllot">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>

  export default {
    name: "MaterialDepot",
    props: {
      show: {
        type: Boolean,
        default: false
      },
      rowData: {
        type: Object
      },
    },
    data() {
      return {
        // 分配仓库界面是否显示
        dialogVisible: this.show,
        materialDepotIds: []
      }
    },
    computed: {
      formData() {
        return this.rowData;
      },
      depotList() {
        return this.$store.getters.pocketData.depots;
      }
    },
    methods: {
      close() {
        this.$emit("close");
      },
      submitAllot() {
        const materialDepots = [];
        this.materialDepotIds.forEach((data) => {
          materialDepots.push({
            materialId: this.formData.id,
            depotId: data
          })
        })
        this.$api.material.saveMaterialDepots(this.formData.id, materialDepots).then(res => {
          this.$emit("submitAssignDepot")
        })
      },
      setMaterialDepots() {
        this.$api.material.getMaterialDepots(this.formData.id).then(res => {
          this.materialDepotIds = res.data
        })
      }
    },
    watch: {
      show(val) {
        this.dialogVisible = val;
        val && this.setMaterialDepots()
      },
    },
  }
</script>


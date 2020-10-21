<template>
  <el-form v-bind="getForm">
    <template v-for="t in getFormItems">
      <el-form-item v-bind="t">
        <el-input v-model="getForm.model[t]"></el-input>
      </el-form-item>
    </template>
  </el-form>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'vue-property-decorator';
import {Form as ElForm, FormItem as ElFormItem} from "element-ui";

@Component
export default class Form extends Vue {
  @Prop({default: {}}) private form!: ElForm
  @Prop({default: []}) private formItems!: ElFormItem[];

  get getForm() {
    return Object.assign({"label-width": "50px"}, this.form)
  }

  get getFormItems() {
    return this.formItems.map(s => Object.assign({style: "width:360px"}, s))
  }
}


</script>
<template>
  <div class="icons-container">
    <div class="icons-wrapper">
      <div v-for="item of iconsMap" :key="item">
        <div class="icon-item">
          <svg-icon :icon-class="item"/>
          <span>{{ item }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import Icon from '@v/custom/svg'

  export default {
    name: 'SvgList',
    data() {
      return {
        iconsMap: []
      }
    },
    mounted() {
      this.iconsMap = Icon.getIconList()
    },
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .icons-container {
    margin: 10px 20px 0;
    overflow: hidden;

    .icons-wrapper {
      margin: 0 auto;
    }

    .icon-item {
      margin: 20px;
      height: 110px;
      text-align: center;
      width: 110px;
      float: left;
      font-size: 30px;
      color: #24292e;
      cursor: pointer;
    }

    span {
      display: block;
      font-size: 24px;
      margin-top: 10px;
    }

    .disabled {
      pointer-events: none;
    }
  }
</style>

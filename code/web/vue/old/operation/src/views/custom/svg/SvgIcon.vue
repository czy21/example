<template>
  <svg :class="svgClass" aria-hidden="true" :style="{fontSize:iconSize}">
    <use :xlink:href="iconName"/>
  </svg>
</template>

<script>
  export default {
    name: 'SvgIcon',
    props: {
      iconClass: {
        type: String,
        required: true
      },
      className: {
        type: String,
        default: ''
      },
      iconSize: {
        type: String,
        default: '18px'
      }
    },
    computed: {
      iconName() {
        return `#${this.iconClass}`
      },
      svgClass() {
        if (this.className) {
          return 'svg-icon ' + this.className
        } else {
          return 'svg-icon'
        }
      }
    }
  }
</script>

<style scoped>
  .svg-icon {
    width: 1em;
    height: 1em;
  }
</style>

<template>
  <div class="content">
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'

  export default {
    name: "Content",
    computed: {
      ...mapGetters(["isRouterAlive"])
    },
  }
</script>

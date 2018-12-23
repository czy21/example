<template>
  <div class="content">
    <router-view></router-view>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'

  export default {
    name: "Content",
  }
</script>

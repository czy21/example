<template>
  <div class="content">
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'

  export default {
    name: "Content",
  }
</script>

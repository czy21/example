<template>
  <div class="wrapper">
    <v-header></v-header>
    <v-sidebar></v-sidebar>
    <div class="main-container" :class="{'main-collapse':isCollapse}">
      <tags-view></tags-view>
      <v-content></v-content>
    </div>
  </div>
</template>

<script>
  import {mapGetters} from "vuex";
  import vHeader from "./Header.vue";
  import vSidebar from "./Sidebar.vue";
  import vContent from "./Content.vue";
  import TagsView from "./TagsView.vue";

  export default {
    name: "Home",
    computed: {
      ...mapGetters(["sidebar"]),
      isCollapse() {
        return this.sidebar.opened;
      }
    },
    components: {
      vHeader,
      vSidebar,
      vContent,
      TagsView
    }
  };
</script>


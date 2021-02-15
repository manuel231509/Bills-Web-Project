<template>
  <v-app>
    <v-main>
      <NavBars />
      <router-view />
    </v-main>
  </v-app>
</template>
<script>
// @ is an alias to /src
import NavBars from "@/components/navigation_bar/nav_bars.vue";

export default {
  name: "App",
  components: {
    NavBars,
  },
};
</script>
<style></style>;

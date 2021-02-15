<template>
  <div>
    <v-app-bar color="blue darken-1" dense dark>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title>Bills</v-toolbar-title>

      <v-spacer></v-spacer>
    </v-app-bar>
    <v-navigation-drawer app v-model="drawer" absolute temporary>
      <v-list nav dense>
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item v-for="item in items" :key="item.title" :to="{path: item.router}">
            <v-list-item-icon>
              <v-icon right>{{ item.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  name: "NavBars",
  data: () => ({
    items: [
      { title: "HOME", icon: "mdi-home", router: '/home' },
      { title: "CREATE INVOICE", icon: "fa fa-plus-circle", router: '/create_invoice' },
      { title: "INVOICE LIST", icon: "mdi-view-list", router: '/invoice_list' },
    ],
    drawer: false,
    group: null,
  }),
  watch: {
    group() {
      this.drawer = false;
    },
  },
};
</script>

<style>
</style>
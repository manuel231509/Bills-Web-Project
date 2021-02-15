<template>
  <div>
    <br />
    <v-container
      class="text-center elevation-20"
      style="text-transform: uppercase"
    >
      <v-card width="100%">
        <v-card-title class="justify-center">
          Welcome to the invoice functionality
        </v-card-title>
        <v-card-text class="justify-center">
          We open the options, which are to create invoice and invoice list, in
          invoice list we can see the details of the invoice, as well as modify
          and delete the invoice and the detail, when eliminating the invoice or
          the item from the detail, triggers were generated for save deleted
          data with date, time and postgresql user. <br /> Thanks for your attention.
        </v-card-text>
        <v-img
          src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
          height="400px"
        ></v-img>
      </v-card>
    </v-container>
  </div>
</template>
  
<script>
export default {
  name: "Home",
  components: {},
};
</script>

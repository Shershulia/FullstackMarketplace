<template>
  <div class="home">
    <div v-if="userForAdmin.permission==='admin'">
      <select v-model="searchCategory">
        <option value="">Select category</option>
        <option v-for="category in this.categories" :key="category">{{ category }}</option>
      </select>
    </div>
    <div v-else>
      <p>You should have admin permission level to use this page
      </p>
    </div>
  </div>
</template>

<script>

import { getItems } from "@/services/ItemServiceApi";

export default {
  name: "AdminView",
  components: {
    // ListOfLittleItems,

  },
  data() {
    return {
      categories: [],
    };
  },
  created() {
    this.fetchItems();

  },
  methods: {
    fetchItems() {
      getItems().then((items) => {
        console.log("items");
        console.log(items);
        this.items = items;
      });
    },
  },
  computed:{
    userForAdmin() {
      return this.$store.getters.user;
    },
  },
};
</script>

<style scoped>
</style>

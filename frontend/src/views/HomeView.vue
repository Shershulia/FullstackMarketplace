<template>
  <div class="home">
    <!-- <ListOfLittleItems :listOfItems="items"></ListOfLittleItems> -->
    <div v-if="items.length">
      <Search :items="items"></Search>
    </div>
    <div v-else>
      <p>Not available items on marketplace
      </p>
    </div>
  </div>
</template>

<script>
// import ListOfLittleItems from "@/components/ListOfLittleItems.vue";
import Search from "@/components/Search.vue"

import { getItems } from "@/services/ItemServiceApi";

export default {
  name: "HomeView",
  components: {
    // ListOfLittleItems,
    Search
  },
  data() {
    return {
      items: [],
    };
  },
  mounted() {
    this.fetchItems();
  },
  methods: {
    fetchItems() {
      getItems().then((items) => {
        this.items = items;
      });
    },
  },
};
</script>

<style scoped>
</style>



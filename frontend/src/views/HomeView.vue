<template>
  <div class="home">
    <ListOfLittleItems :listOfItems="items"></ListOfLittleItems>
    <GoogleMap :address="items[0].location" />
    <RegisterUser />
  </div>
</template>

<script>
import ListOfLittleItems from "@/components/ListOfLittleItems.vue";
import GoogleMap from "@/components/GoogleMap.vue";
import { getItems } from "@/services/ItemServiceApi";

export default {
  name: "HomeView",
  components: {
    ListOfLittleItems,
    GoogleMap,
  },
  data() {
    return {
      items: [],
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
};
</script>

<style scoped>
</style>

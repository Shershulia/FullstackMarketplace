<template>
  <div class="home">
    <div v-if="items.length">
      <Search :items="items"></Search>
    </div>
    <div v-else>
      <p>Loading...</p>
    </div>
  </div>
</template>

<script>
import Search from "@/components/Search.vue"
import { getItems } from "@/services/ItemServiceApi";

export default {
  name: "HomeView",
  components: {
    Search
  },
  data() {
    return {
      exampleItem: {
        id: 1,
        name: "Gaming chair for beginner",
        image:
            "https://i.pinimg.com/736x/bd/c9/83/bdc9832e5f32ee6168f10536549551bc--kids-bedroom-ideas-girls-bedroom.jpg",
        location: "Falkenborgvegen 1, 7044 Trondheim",
        price: 100,
      },
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

<template>
  <div class="home">
    <div v-if="userForAdmin.permission==='admin'">

    </div>
    <div v-else>
      <p>You should have admin permission level to use this page
      </p>
    </div>
  </div>
</template>

<script>
// import ListOfLittleItems from "@/components/ListOfLittleItems.vue";
import Search from "@/components/Search.vue"

import { getItems } from "@/services/ItemServiceApi";

export default {
  name: "AdminView",
  components: {
    // ListOfLittleItems,

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
  computed:{
    userForAdmin() {
      return this.$store.getters.user;
    },
  },
};
</script>

<style scoped>
</style>

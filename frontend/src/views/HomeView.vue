<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <HelloWorld msg="Welcome to Your Vue.js App" />
    <LittleItemCard :item="exampleItem"></LittleItemCard>
    <ListOfLittleItems :listOfItems="items"></ListOfLittleItems>
    <GoogleMap :address="exampleItem.location" />
    <RegisterUser />
  </div>
</template>

<script>
import HelloWorld from "@/components/HelloWorld.vue";
import LittleItemCard from "@/components/LittleItemCard.vue";
import ListOfLittleItems from "@/components/ListOfLittleItems.vue";
import GoogleMap from "@/components/GoogleMap.vue";
import { getItems } from "@/services/ItemServiceApi";

export default {
  name: "HomeView",
  components: {
    ListOfLittleItems,
    HelloWorld,
    LittleItemCard,
    GoogleMap,
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

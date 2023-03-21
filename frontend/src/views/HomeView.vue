<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <HelloWorld msg="Welcome to Your Vue.js App" />
    <LittleItemCard
      :name="exampleItem.name"
      :image="exampleItem.image"
      :location="exampleItem.location"
      :price="exampleItem.price"
    ></LittleItemCard>
    <router-link to=/about>About us</router-link>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";
import LittleItemCard from "@/components/LittleItemCard.vue";

export default {
  name: "HomeView",
  components: {
    HelloWorld,
    LittleItemCard,
  },
  data() {
    return {
      exampleItem: {
        name: "Gaming chair for beginner ffffffffffffffffffffffffffffffffffffffffffffffff",
        image:
          "https://i.pinimg.com/736x/bd/c9/83/bdc9832e5f32ee6168f10536549551bc--kids-bedroom-ideas-girls-bedroom.jpg",
        location: "Trondheim",
        price: 100,
      },
    };
  },
};
</script>

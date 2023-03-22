<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <HelloWorld msg="Welcome to Your Vue.js App" />
    <LittleItemCard :item="exampleItem"></LittleItemCard>
    <ListOfLittleItems :listOfItems="items"></ListOfLittleItems>
    <RegisterUser />
    <GoogleMap :address="exampleItem.location" />
  </div>
</template>

<script>
import HelloWorld from "@/components/HelloWorld.vue";
import LittleItemCard from "@/components/LittleItemCard.vue";
import ListOfLittleItems from "@/components/ListOfLittleItems.vue";
import RegisterUser from "@/views/RegisterUser.vue";
export default {
  name: "HomeView",
  components: {
    ListOfLittleItems,
    HelloWorld,
    LittleItemCard,
    RegisterUser,
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
      items: [
        {
          id: 2,
          name: "Gaming chair for advanced",
          image: "https://a.d-cd.net/1YAAAgKereA-100.jpg",
          location: "Bottom of the sea",
          price: 200,
        },
        {
          id: 1,
          name: "Gaming chair for beginner",
          image:
            "https://i.pinimg.com/736x/bd/c9/83/bdc9832e5f32ee6168f10536549551bc--kids-bedroom-ideas-girls-bedroom.jpg",
          location: "Trondheim",
          price: 100,
        },
        {
          id: 3,
          name: "Mobile gaming chair",
          image: "https://i.redd.it/ksqjj3d3ikp51.jpg",
          location: "Fast Delivery",
          price: 300,
        },
      ],
    };
  },
};
</script>

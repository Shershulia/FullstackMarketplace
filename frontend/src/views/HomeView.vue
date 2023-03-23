<template>
  <div class="home">

    <Search :items="items"></Search>

  </div>
</template>

<script>

import Search from "@/components/Search.vue"

export default {
  name: "HomeView",
  components: {
    Search
  },
  data() {
    return {
      items: [
        {
          id: 1,
          name: "12",
          image: "https://a.d-cd.net/1YAAAgKereA-100.jpg",
          location: "Oslo",
          price: 200,
          category: "?"
        },
        {
          id: 2,
          name: "1",
          image:
            "https://i.pinimg.com/736x/bd/c9/83/bdc9832e5f32ee6168f10536549551bc--kids-bedroom-ideas-girls-bedroom.jpg",
          location: "Trondheim",
          price: 100,
          category: "Chair"

        },
        {
          id: 3,
          name: "Mobile gaming chair",
          image: "https://i.redd.it/ksqjj3d3ikp51.jpg",
          location: "Bergen",
          price: 300,
          category: "Chair"

        },
        {
          id: 4,
          name: "12",
          image: "https://a.d-cd.net/1YAAAgKereA-100.jpg",
          location: "Oslo",
          price: 200,
          category: "?"
        },
        {
          id: 5,
          name: "1",
          image:
              "https://i.pinimg.com/736x/bd/c9/83/bdc9832e5f32ee6168f10536549551bc--kids-bedroom-ideas-girls-bedroom.jpg",
          location: "Trondheim",
          price: 100,
          category: "Chair"

        },
        {
          id: 6,
          name: "Mobile gaming chair",
          image: "https://i.redd.it/ksqjj3d3ikp51.jpg",
          location: "Bergen",
          price: 300,
          category: "Chair"

        },
        {
          id: 7,
          name: "12",
          image: "https://a.d-cd.net/1YAAAgKereA-100.jpg",
          location: "Oslo",
          price: 200,
          category: "?"
        },
        {
          id: 8,
          name: "1",
          image:
              "https://i.pinimg.com/736x/bd/c9/83/bdc9832e5f32ee6168f10536549551bc--kids-bedroom-ideas-girls-bedroom.jpg",
          location: "Trondheim",
          price: 100,
          category: "Chair"

        },
        {
          id: 9,
          name: "Mobile gaming chair",
          image: "https://i.redd.it/ksqjj3d3ikp51.jpg",
          location: "Bergen",
          price: 300,
          category: "Chair"

        },

      ],
    };
  },
};
</script>

<style scoped>
</style>

<template>
  <div class="home">
    <LittleItemCard
      :name="exampleItem.name"
      :image="exampleItem.image"
      :location="exampleItem.location"
      :price="exampleItem.price"
    ></LittleItemCard>
  </div>
</template>

<script>
// @ is an alias to /src
import LittleItemCard from "@/components/LittleItemCard.vue";

export default {
  name: "HomeView",
  components: {
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

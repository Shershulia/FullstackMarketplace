<template>
  <ul class="listOfCards">
    <li v-for="exampleItem in listOfItems" :key="exampleItem.name">
      <LittleItemCard
        :name="exampleItem.name"
        :image="exampleItem.image"
        :location="exampleItem.location"
        :price="exampleItem.price"
      ></LittleItemCard>
    </li>
  </ul>
</template>
<script>
import LittleItemCard from "@/components/LittleItemCard.vue";
export default {
  name: "ListOfLittleItems",
  components: {
    LittleItemCard,
  },
  props: {
    listOfItems: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style>
li {
  display: inline;
}
</style>

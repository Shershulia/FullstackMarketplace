<template>
  <ul class="listOfCards">
    <li v-for="exampleItem in listOfItems" :key="exampleItem.id">
      <LittleItemCardEditableAndDeletable :item="exampleItem"></LittleItemCardEditableAndDeletable>
    </li>
  </ul>
</template>
<script>
import LittleItemCardEditableAndDeletable from "@/components/editAndDeleteItems/LittleItemCardEditableAndDeletable.vue";
export default {
  name: "ListOfLittleItems",
  components: {
    LittleItemCardEditableAndDeletable,
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

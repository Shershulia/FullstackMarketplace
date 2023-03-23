<template>
  <ul class="listOfCards">
    <li v-for="exampleItem in this.items" :key="exampleItem.id">
      <LittleItemCardEditableAndDeletable @deletedItem="deleteItemFromList" :item="exampleItem"></LittleItemCardEditableAndDeletable>
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
  data(){
    return {
      items: this.listOfItems,
    };
  },
  methods:{
    deleteItemFromList(id) {
      this.items = this.items.filter(item => item.id !== id);
    }
  }
};
</script>

<style>
li {
  display: inline;
}
</style>

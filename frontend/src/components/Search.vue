<template>
  <input
      type="text"
      placeholder="Search"
      class="searchInput"
      v-model="searchValue"
  />
  <select v-model="searchCategory">
    <option value=""></option>
    <option>Chair</option>
    <option>B</option>
    <option>C</option>
  </select>
  <ListOfLittleItems v-if="itemsList.length" :listOfItems="itemsList"></ListOfLittleItems>
  <p v-else>Nothing was found on request: {{this.searchValue}} </p>
</template>
<script>
import ListOfLittleItems from "@/components/ListOfLittleItems.vue";

  export default {
    name: "HomeView",
    props: {
      items: {
        type: Array,
        required: true,
      },
    },
    components: {
      ListOfLittleItems
    },
    data() {
      return {
        searchValue:"",
        searchCategory:"",
        searchLocation:"",
      };
    },
    computed:{
      itemsList() {
        if (this.searchValue.trim().length > 0 && this.searchCategory === "") { //Search only by text
          return this.items.filter((item) => item.name.toLowerCase().includes(this.searchValue.trim().toLowerCase()));
        } else if (this.searchValue.trim().length === 0 && this.searchCategory !== "") { //Search only by category
          return this.items.filter((item) => item.category.toLowerCase() === this.searchCategory.toLowerCase());
        } else if (this.searchValue.trim().length > 0 && this.searchCategory !== "") { //Search by category and name
          let categoriesSearch = this.items.filter((item) => item.category.toLowerCase() === this.searchCategory.toLowerCase());
          return categoriesSearch.filter((item) => item.name.toLowerCase().includes(this.searchValue.trim().toLowerCase()));
        }
        return this.items;
      }
    },
    methods: {

      }
  };
</script>
<style>
.searchInput{
  height: 100px;
  width: auto;
  border-radius: 5px;
}
</style>
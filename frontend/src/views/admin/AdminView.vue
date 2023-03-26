<template>
  <div class="home">
    <div v-if="userForAdmin.permission==='admin'">
      <p><strong>Categories in database:</strong></p>
      <select>
        <option disabled v-for="category in categories" :key="category">{{ category }}</option>
      </select>
      <input v-model="newCategory">
      <button @click="addCategory">Add category</button>
    </div>
    <div v-else>
      <p>You should have admin permission level to use this page
      </p>
    </div>
  </div>
</template>

<script>

import { getCreationCategories, getItems } from "@/services/ItemServiceApi";
import axios from "axios";

export default {
  name: "AdminView",
  components: {
    // ListOfLittleItems,

  },
  async mounted() {
    this.categories=await getCreationCategories();
    console.log(this.categories);
  },
  data() {
    return {
      categories: [],
      newCategory: "",
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
    addCategory(){
      let newCategory= this.newCategory;
      axios
        .post(
          "http://localhost:8090/item/creation-categories",
           newCategory ,
          {
            headers: {
              Authorization: "Bearer " + this.$store.getters.token,
            },
          }
        )
        .catch((error) => {
          console.error("error:");
          alert("error;could not create category");
          console.error(error);
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

<template>
  <div class="home">
    <div class="adminPanel" v-if="userForAdmin.permission==='admin'">
      <p><strong>Categories in database:</strong></p>
      <select>
        <option disabled v-for="category in categories" :key="category">{{ category }}</option>
      </select>
      <p><strong>Enter a new category</strong></p>
      <input v-model="newCategory">
      <button class="addCategory" @click="addCategory">Add category</button>
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
        ).then(()=>{
        alert("Category was added");

      })
        .catch((error) => {
          console.error("error:");
          alert("error;could not create category");
          console.error(error);
        });
      this.$router.push("/user");

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
.adminPanel{
  background-color: #f8f8f8;
  padding: 20px;
  justify-content: flex-start;
  border: 1px solid #ddd;
  border-radius: 5px;
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
input{
  width: 20%;
  display: inline-block;
  text-align: center;
  border-radius: 5px;
  border: 1px solid;
}
.addCategory{
  background-color: #4CAF50;
  color: white;
  padding: 10px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  font-size: 16px;
  margin-top: 10px;
}
</style>

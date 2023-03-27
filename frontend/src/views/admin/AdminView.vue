<template>
  <div class="home">
    <div class="adminPanel" v-if="userForAdmin.permission==='admin'">
      <p><strong>Available categories in database:</strong></p>
      <select>
        <option v-for="category in categories" :key="category">{{ category }}</option>
      </select>
      <p><strong>Enter a new category</strong></p>
      <input v-model="newCategory">
      <button class="addCategory" @click="addCategory">Add category</button>
      <hr>
      <p><strong>Modify already existed category</strong></p>
      <p><strong>Choose category to modify:</strong></p>
      <select v-model="modifiedCategory" @change="setSame">
        <option v-for="category in categories" :key="category">{{ category }}</option>
      </select>
      <input v-model="modifyTo">
      <button class="modifyCategory" @click="modifyCategory">Modify category</button>
      <hr>
      <p><strong>Choose category to delete</strong></p>
      <select v-model="deleteCategory">
        <option v-for="category in categories" >{{ category }}</option>
      </select>
      <button class="deleteCategory" @click="delCategory">Delete category</button>
    </div>
    <div v-else>
      <p>You should have admin permission level to use this page
      </p>
    </div>
  </div>
</template>

<script>

import { getCreationCategories } from "@/services/ItemServiceApi";
import axios from "axios";

export default {
  name: "AdminView",
  async mounted() {
    this.categories=await getCreationCategories();
  },
  data() {
    return {
      categories: [],
      newCategory: "",
      modifiedCategory:"",
      modifyTo:"",
      deleteCategory:"",

    };
  },
  methods: {
    setSame(){
      this.modifyTo=this.modifiedCategory;
    },
    delCategory(){
      if (this.deleteCategory!==""){
        axios
          .delete(
            `http://localhost:8090/item/creation-categories/${this.deleteCategory}`,
            {
              headers: {
                Authorization: "Bearer " + this.$store.getters.token,
              },
            }
          ).then(()=>{
          alert("Category was deleted");
        })
          .catch((error) => {
            console.error("error:");
            alert("error;could not delete category");
            console.error(error);
          });
      }else alert("could not delete category");
    },
    modifyCategory(){
      if(this.modifiedCategory!==""&&this.modifyTo!==""&&this.modifiedCategory!==this.modifyTo){
        axios.put(`http://localhost:8090/item/creation-categories/${this.modifiedCategory}`, { category: this.modifyTo }, {
          headers: { Authorization: "Bearer " + this.$store.getters.token, },
        })
          .then(( ) => {
            alert("Category was modified");
          })
          .catch(error => {
            alert(error);
            console.log(error);
          });
      }else alert("Modification is not chosen");
    },
    addCategory(){
      axios
        .post(
          "http://localhost:8090/item/creation-categories",
          { category: this.newCategory } ,
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
hr {
  width: 50%;
}
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
  background-color: #3a883d;
  color: white;
  padding: 10px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  font-size: 16px;
}
.addCategory:hover{
  background-color: #449f48;
}
.deleteCategory{
  background-color: #910000;
  color: white;
  padding: 10px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  font-size: 16px;
}
.deleteCategory:hover{
  background-color: #a60000;

}
.modifyCategory{
  background-color: #003366;
  color: white;
  padding: 10px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  font-size: 16px;
}
.modifyCategory:hover{
  background-color: #0052cc;

}
select{
  margin: 10px;
  padding: 8px;
  border-radius: 5px;
  border: 1px solid gray;
  width: 150px;
}
p{
  margin: 20px 20px 0px 0px;
}
</style>

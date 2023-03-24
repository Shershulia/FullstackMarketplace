<template>
  <div class="editItemContainer">
    <h2>Create item</h2>
    <p><strong>Name:</strong> <input v-model="item.name" /></p>
    <p><strong>Price:</strong> <input v-model="item.price" /></p>
    <p><strong>Image:</strong> <input v-model="item.image" /></p>
    <p><strong>Location:</strong> <input v-model="item.location" /></p>
    <p><strong>Description:</strong> <input v-model="item.description" /></p>
    <p><strong>Select category:</strong></p>
    <select multiple>
      <option>Electronics</option>
      <option>Clothing and Accessories</option>
      <option>Home and Garden</option>
      <option>Health and Beauty</option>
      <option>Sports and Outdoors</option>

    </select>
    <div class="saveButtonContainer">
      <button @click="updateItem" class="saveButton">Create Item</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "CreateItem",
  data() {
    return {
      id:0,
      item: {
        name: "",
        description:
          "",
        userid: this.id,
        price:
          "",
        location: "",
        image: "",
        categories:[],
        metadata:[]
      },
    };
  },
  methods: {
    updateItem() {
      console.log(this.item)
    }
  },
  computed: {
    itemBelongsTo() {
      return this.$store.getters.user.id;
    },
  }
};
</script>

<style scoped>

.editItemContainer {
  background-color: #f8f8f8;
  padding: 20px;
  justify-content: center;
  border: 1px solid #ddd;
  border-radius: 5px;
  margin-bottom: 20px;
}

.editItemContainer p  {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.editItemContainer select{
  width: 20%;
  display: inline-block;
  text-align: center;
}

.editItemContainer input {
  width: 40%;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ddd;
}

.editItemContainer strong {
  margin: 10px;
  flex: 0.2;
}

.saveButton {
  background-color: #4CAF50;
  color: white;
  padding: 10px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  font-size: 16px;
  margin-top: 10px;
}
.saveButtonContainer{
  display: flex;
  justify-content: center;
}


</style>


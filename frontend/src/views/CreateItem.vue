<template>
  <div class="editItemContainer">
    <h2>Edit item</h2>
    <p><strong>Name:</strong> <input v-model="item.name" /></p>
    <p><strong>Price:</strong> <input v-model="item.price" /></p>
    <p><strong>Image:</strong> <input v-model="item.image" /></p>
    <p><strong>Location:</strong> <input v-model="item.location" /></p>
    <p><strong>Description:</strong> <input v-model="item.description" /></p>

    <button @click="updateItem" class="saveButton">Save</button>
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
  border: 1px solid #ddd;
  border-radius: 5px;
  margin-bottom: 20px;
}

.editItemContainer p {
  display: flex;
  flex-direction: column;
  align-items: center;
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

</style>


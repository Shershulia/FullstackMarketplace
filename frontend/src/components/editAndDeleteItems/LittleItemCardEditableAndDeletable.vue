<template>
    <div class="littleCard">
      <div class="imageWithPrice">
        <router-link class="linkToDetPage" :to="{ name: 'ItemDetails', params: { id: item.id } }">
        <img class="image" :src="item.image[0]" />
        </router-link>

        <div class="buttonsLittleCard">
          <button class="delete" @click="deleteItem">Delete</button>
          <router-link class="linkToDetPage" :to="{ name: 'ItemDetails', params: { id: item.id } }">
          <button class="edit">Edit</button>
          </router-link>
        </div>
        <router-link class="linkToDetPage" :to="{ name: 'ItemDetails', params: { id: item.id } }">
        <p class="price">{{ item.price }} kr,-</p>
        </router-link>

      </div>
      <router-link class="linkToDetPage" :to="{ name: 'ItemDetails', params: { id: item.id } }">
      <p class="name">{{ item.name }}</p>
      <p class="location">Location: {{ item.location }}</p>
      </router-link>

    </div>
</template>

<script>
import { deleteItem} from "@/services/ItemServiceApi";

export default {
  name: "LittleItemCardEditableAndDeletable",
  props: {
    item: {
      type: Object,
      required: true,
    },
  },
  methods: {
    deleteItem() {
      deleteItem(this.item.id).then(() => {
        this.$emit("deletedItem",this.item.id);
        alert("Item deleted")
      });
    },
  },
};
</script>

<style>
.littleCard {
  width: 250px;
  cursor: pointer;
  border: 1px groove #39495c;
  border-radius: 10px;
  display: inline-block;
  margin: 20px;
}
.imageWithPrice {
  position: relative;
  text-align: center;
}
.image {
  width: 100%;
  height: 200px;
  border-radius: 10px;
  object-fit: cover;
}
.buttonsLittleCard {
  position: absolute;
  top: 4px;
  right: 4px;
  z-index: 1;
}
.delete,
.edit {
  margin-left: 5px;
  padding: 5px;
  border-radius: 5px;
  font-size: 12px;
  color: white;
  background-color: #39495c;
  border: none;
  cursor: pointer;
}
.edit {
  background-color: #3c763d;
}
.price {
  position: absolute;
  bottom: 4px;
  left: 0;
  margin: 0;
  padding: 10px;
  border-radius: 10px;
  color: white;
  background-color: rgb(126, 126, 126, 0.7);
}
.name {
  font-size: 18px;
  font-weight: bold;
  margin-top: 10px;
  margin-bottom: 5px;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: initial;
}

.location {
  font-size: 14px;
  margin: 0;
  color: #777;
  text-overflow: ellipsis;
  overflow: hidden;
}
.linkToDetPage{
  text-decoration: none;
}
</style>

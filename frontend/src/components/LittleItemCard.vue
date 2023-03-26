<template>
  <div class="littleCard">
    <div class="imageWithPrice">
      <p class="price">{{ item.price }} kr,-</p>
      <button v-on:click="addToCart(item)" class="addToCartButton">
        <img class="cartIcon" :src="require(`@/assets/cartIcon.png`)" />
      </button>
      <router-link :to="{ name: 'ItemDetails', params: { id: item.id } }">
        <img class="image" :src="item.image" />
      </router-link>
    </div>
    <router-link :to="{ name: 'ItemDetails', params: { id: item.id } }">
      <p class="name">{{ item.name }}</p>
      <p class="location">Location: {{ item.location }}</p>
    </router-link>

  </div>
</template>


<script>
export default {
  name: "LittleItemCard",
  props: {
    item: {
      type: Object,
      required: true,
    },
  },
  methods: {
    addToCart(item){
      console.log(item)
    }

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
.addToCartButton{
  position: absolute;
  width: 25%;
  bottom: 4px;
  right: 0;
  margin: 0;
  padding: 10px;
  border-radius: 10px;
  color: white;
  background-color: rgba(126, 126, 126, 0.7);}

.addToCartButton:hover{
  transform: rotate(360deg);
}
.cartIcon{
  width: 20px;
  height: 20px;
}
</style>

<template>
  <div class="item-details-container">
    <div class="item-image">
      <img :src="item.image" />
    </div>
    <div class="item-details">
      <h2 class="item-name">{{ item.name }}</h2>
      <p class="item-price">{{ item.price }} kr,-</p>
      <p class="item-location"><i class="fas fa-map-marker-alt"></i> {{ item.location }}</p>
      <div class="item-actions">
        <button class="add-to-cart-button">Add to cart</button>
        <button class="buy-now-button">Buy now</button>
      </div>
    </div>
  </div>
</template>

<script>
import { useRoute } from "vue-router";
export default {
  name: "ItemDetails",
  data() {
    return {
      id: 0,
      item: {
        id: this.id,
        name: "Gaming chair for beginner",
        image:
            "https://i.pinimg.com/736x/bd/c9/83/bdc9832e5f32ee6168f10536549551bc--kids-bedroom-ideas-girls-bedroom.jpg",
        location: "Trondheim",
        price: 100,
      },
    };
  },
  mounted() {
    const route = useRoute();
    this.id = route.params.id;
  },
};
</script>

<style>
.item-details-container {
  margin-left: 200px;
  margin-right: 200px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.item-image {
  width: 45%;
  margin-right: 5%;
}

.item-image img {
  display: block;
  width: 100%;
  height: auto;
  border-radius: 5px;
}

.item-details {
  width: 50%;
}

.item-name {
  font-size:36px;
  font-weight: bold;
  margin: 0 0 10px 0;
}

.item-price {
  font-size: 20px;
  font-weight: bold;
  margin: 0 0 10px 0;
}

.item-location {
  font-size: 16px;
  margin: 0 0 10px 0;
}

.item-location i {
  margin-right: 5px;
}

.item-actions {
  margin-top: 20px;
}

.add-to-cart-button,
.buy-now-button {
  background-color: #008CBA;
  color: #fff;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 18px;
  cursor: pointer;
  margin-right: 10px;
}

.add-to-cart-button:hover,
.buy-now-button:hover {
  background-color: #005F6B;
}

.buy-now-button {
  background-color: #4CAF50;
}

.buy-now-button:hover {
  background-color: #26773C;
}
</style>

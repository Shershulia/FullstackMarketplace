<template>
  <div class="littleCard">
    <div class="imageWithPrice">
      <p class="price">{{ price }} kr,-</p>
      <img class="image" :src="image" />
    </div>

    <p class="name">{{ name }}</p>
    <p class="location">Location: {{ location }}</p>
  </div>
</template>

<script>
export default {
  name: "LittleItemCard",
  props: {
    name: {
      type: String,
      required: true,
    },
    price: {
      type: Number,
      required: true,
    },
    location: {
      type: String,
      default: "Unknown",
    },
    image: {
      type: String,
      default:
        "https://www.salonlfc.com/wp-content/uploads/2018/01/image-not-found-1-scaled-1150x647.png",
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
</style>

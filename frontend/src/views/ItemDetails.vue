<template>
  <div class="item-details-container">
    <div class="item-image">
      <img :src="currentImageSrc" />
      <div class="image-navigation">
        <button :disabled="imgIndex === 0" @click="imgIndex--">Previous</button>
        <p>{{ imgIndex + 1 }} / {{ imgNum }}</p>
        <button :disabled="imgIndex === imgNum - 1" @click="imgIndex++">Next</button>
      </div>
    </div>
    <div class="item-details">
      <h2 class="item-name">{{ item.name }}</h2>
      <p class="item-price">{{ item.price }} kr,-</p>
      <div class="locationWithImage">
        <img class="gpsIcon" :src="require(`@/assets/locationLogo.png`)" />
        <p class="item-location">{{ item.location }}</p>
      </div>
      <div class="locationWithMap">
        <p class="item-description">{{ item.description }}</p>
        <GoogleMap class="map" :address="this.item.location" />
      </div>
      <div class="item-actions">
        <button class="add-to-cart-button">Add to cart</button>
        <button class="buy-now-button">Buy now</button>
      </div>
    </div>
  </div>
</template>

<script>
import { useRoute } from "vue-router";
import { getItemById } from "@/services/ItemServiceApi";
import GoogleMap from "@/components/GoogleMap.vue";

export default {
  name: "ItemDetails",
  components: {
    GoogleMap,
  },
  data() {
    return {
      item: {},
      imgIndex: 0,
      imgNum: 0,
    };
  },
  computed: {
    currentImageSrc() {
      return this.item.image[this.imgIndex];
    },
  },
  mounted() {},
  created() {
    this.fetchItem();
  },
  methods: {
    fetchItem() {
      const route = useRoute();
      let id = route.params.id;
      getItemById(id).then((response) => {
        let item = response.data;
        console.log("item");
        console.log(item);
        this.imgNum = item.image.length;
        this.item = item;
      });
    },
  },
};
</script>

<style scoped>
.item-details-container {
  margin-left: 200px;
  margin-right: 200px;
  display: flex;
  height: 100%;
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

.image-navigation {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
}

.item-details {
  width: 50%;
}

.item-name {
  font-size: 36px;
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
.add-to-cart-button,
.buy-now-button {
  background-color: #008cba;
  color: #fff;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 18px;
  cursor: pointer;
  margin-right: 10px;
}

.add-to-cart-button:hover,
.buy-now-button:hover {
  background-color: #005f6b;
}

.buy-now-button {
  background-color: #4caf50;
}

.buy-now-button:hover {
  background-color: #26773c;
}
.gpsIcon {
  max-width: 15px;
  max-height: 15px;
}
.locationWithImage {
  margin-left: 200px;
  margin-right: 200px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
}
.locationWithMap {
  display: grid;
  grid-template-columns: auto auto auto;
  grid-gap: 10px;
  padding: 10px;
}

.item-description {
  font-size: 30px;
  grid-column: 1/3;
}

.map {
  grid-column: 3/4;
}

.item-actions {
  grid-column: 1/3;
  margin-top: 20px;
}
</style>

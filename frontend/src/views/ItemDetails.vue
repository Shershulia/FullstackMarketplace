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
      <a :href="'mailto:?to=' + encodeURIComponent(this.user.email) + '&subject=' + encodeURIComponent('Marketplace - Regarding your ' + item.name + '') + '&body=' + encodeURIComponent('I have some questions regarding - ' + item.name + ': \n ')"  class="item-seller">Contact seller</a>
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
import { getItemById, getUserPubById } from "@/services/ItemServiceApi";
import GoogleMap from "@/components/GoogleMap.vue";

export default {
  name: "ItemDetails",
  components: {
    GoogleMap,
  },
  data() {
    return {
      user: {},
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
    this.fetchUserDetails();
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
    fetchUserDetails() {
        getUserPubById(useRoute().params.id).then(response => {
          let user = response.data;
          console.log("user");
          console.log(user);
          this.user = user;
        })
    },
  },
};
</script>

<style scoped>
/* For small devices */
@media screen and (max-width: 768px) {
  .item-details-container {
    flex-direction: column;
    margin-left: 10px;
    margin-right: 10px;
    padding: 10px;
  }

  .item-image {
    width: 100%;
    margin-right: 0;
    margin-bottom: 10px;
  }

  .item-details {
    width: 100%;
  }

  .item-name {
    font-size: 24px;
  }

  .item-price {
    font-size: 16px;
  }

  .locationWithImage {
    margin-left: 0;
    margin-right: 0;
    justify-content: center;
  }

  .locationWithMap {
    grid-template-columns: auto;
    padding: 0;
  }

  .item-description {
    font-size: 24px;
    grid-column: auto;
    margin-bottom: 10px;
  }

  .map {
    grid-column: auto;
  }

  .item-actions {
    grid-column: auto;
    margin-top: 10px;
  }

  .image-navigation {
    flex-wrap: wrap;
  }

  .image-navigation button {
    margin-top: 10px;
    margin-right: 5px;
  }
}

/* For medium devices */
@media screen and (min-width: 768px) and (max-width: 1024px) {
  .item-details-container {
    margin-left: 50px;
    margin-right: 50px;
  }

  .item-image {
    width: 40%;
    margin-right: 10%;
    margin-bottom: 0;
  }

  .item-details {
    width: 50%;
  }

  .item-description {
    font-size: 28px;
  }

  .item-actions {
    margin-top: 30px;
  }
}

/* For large devices */
@media screen and (min-width: 1024px) {
  .item-details-container {
    margin-left: 200px;
    margin-right: 200px;
  }

  .item-image {
    width: 45%;
    margin-right: 5%;
  }

  .item-details {
    width: 50%;
  }

  .item-description {
    font-size: 30px;
  }

  .locationWithImage {
    justify-content: space-evenly;
  }

  .locationWithMap {
    grid-template-columns: auto auto auto;
    padding: 10px;
  }
}

/* Common Styles */
.item-details-container {
  display: flex;
  height: 100%;
  flex-wrap: wrap;
  justify-content: space-between;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
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

.locationWithImage {
  display: flex;
  align-items: center;
  margin-left: 10px;
  margin-right: 10px;
}

.locationWithMap {
  display: grid;
  grid-template-columns: auto auto;
  padding: 30px;
}

.item-description {
  font-size: 24px;
  grid-column: 1 / 3;
  margin-bottom: 30px;
}

.map {
  grid-column: 2 / 4;
}

.item-actions {
  display: flex;
  justify-content: space-between;
  grid-column: 1 / 3;
  margin-top: 30px;
}

.add-to-cart-button,
.buy-now-button {
  width: 100%;
  padding: 10px 30px;
  border: none;
  border-radius: 5px;
  font-size: 20px;
  font-weight: bold;
  color: white;
  cursor: pointer;
}

.add-to-cart-button {
  background-color: #f0c040;
}

.buy-now-button {
  background-color: #f08030;
}

.gpsIcon {
  width: 25px;
  height: 25px;
  margin-right: 10px;
}

.item-location {
  font-size: 20px;
  font-weight: bold;
  margin: 0;
}

.item-location,
.item-price {
  margin: 0;
}

.item-actions {
  display: flex;
  justify-content: space-between;
  grid-column: 1 / 3;
  margin-top: 30px;
}

.add-to-cart-button,
.buy-now-button {
  width: 100%;
  padding: 10px 30px;
  border: none;
  border-radius: 5px;
  font-size: 20px;
  font-weight: bold;
  color: white;
  cursor: pointer;
}

.add-to-cart-button {
  background-color: #f0c040;
}

.buy-now-button {
  background-color: #f08030;
}

.gpsIcon {
  width: 25px;
  height: 25px;
  margin-right: 10px;
}

.item-location {
  font-size: 20px;
  font-weight: bold;
  margin: 0;
}

.item-location,
.item-price {
  margin: 0;
}

</style>

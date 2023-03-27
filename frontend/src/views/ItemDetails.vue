<template>
  <div v-if="!editMode" class="item-details-container">
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
      <div>
        <button v-if="item.userid===this.itemBelongsTo.id" class="goToLoginButton" @click="enterEditMode">Edit</button>
      </div>
      <div>
        <button v-if="this.itemBelongsTo.permission ==='admin'" class="adminButton" @click="deleteItem">Delete by admin</button>
      </div>


      <p class="item-price">{{ item.price }} kr,-</p>
      <a v-if="user.email"
        :href="'mailto:?to=' + encodeURIComponent(user.email) + '&subject=' + encodeURIComponent('Marketplace - Regarding your ' + item.name + '') + '&body=' + encodeURIComponent('I have some questions regarding - ' + item.name + ': \n ')"
        class="item-seller"
        v-tooltip="{ content: user.email, placement: 'bottom' }"
      >
        Contact seller
      </a>
      <p v-else class="item-seller">Contact seller</p>
      <div class="locationWithImage">
        <img class="gpsIcon" :src="require(`@/assets/locationLogo.png`)" />
        <p class="item-location">{{ item.location }}</p>
      </div>
      <div class="locationWithMap">
        <p class="item-description">{{ item.description }}</p>
        <GoogleMap class="map" :address="item.location" />
      </div>
      <li v-for="category in this.item.categories">
        <p>{{category}}</p>
      </li>
      <p v-if="item.userid===this.itemBelongsTo.id"> <strong> It is your item, you cannot add it to your cart </strong></p>
      <div class="item-actions">
        <button class="add-to-cart-button" @click="addToCart(); itemAdded()" v-if="item.userid!==this.itemBelongsTo.id">Add to cart</button>
        <button class="buy-now-button" @click="$router.push('/cart'); addToCart()" v-if="item.userid!==this.itemBelongsTo.id">Buy now</button>
      </div>
    </div>
    <div class="relatedItems">
      <p class="relatedPitems">Related items</p>
      <ListOfLittleItems :list-of-items="outPutIntems"></ListOfLittleItems>
    </div>
  </div>
  <div v-else class="editItemContainer">
    <h2>Edit item</h2>

    <p><strong>Name:</strong> <input v-model="item.name" /></p>
    <p><strong>Price:</strong> <input v-model="item.price" /></p>
    <p><strong title='url to image hosted on diffrent page (multiple seperated by "," with no spacing between)'>Image:</strong> <input v-model="item.image" /></p>
    <p><strong title="the adress of the item">Location:</strong> <input v-model="item.location" /></p>
    <p><strong title="short description of the item">Description:</strong> <input v-model="item.description" /></p>

    <button @click="updateItem" class="saveButton">Save</button>
  </div>
</template>

<script>
import { useRoute } from "vue-router";
import { deleteItem, getItemById, getUserPubById , getItems} from "@/services/ItemServiceApi";
import GoogleMap from "@/components/GoogleMap.vue";
import axios from "axios";
import ListOfLittleItems from "@/components/ListOfLittleItems.vue";

export default {
  name: "ItemDetails",
  components: {
    GoogleMap,
    ListOfLittleItems,
  },
  mounted() {
    this.fetchItems();
  },
  data() {
    return {
      user: {},
      item: {},
      imgIndex: 0,
      imgNum: 0,
      editMode: false,
      relatedItems:[],
      outPutIntems:[],
    };
  },
  computed: {
    currentImageSrc() {
      return this.item.image[this.imgIndex];
    },
    itemBelongsTo() {
      return this.$store.getters.user;
    },
  },
  created(){
    this.fetchItem();
  },
  watch:{
    relatedItems(){
      this.findRelatedItems();
    },
    '$route' () {
      this.fetchItem();
    }
  },
  methods: {
    fetchItem() {
      const route = useRoute();
      let id = route.params.id;

      getItemById(id).then((response) => {
        let item = response.data;
        this.imgNum = item.image.length;
        this.item = item;
        this.item.userId = item.userId;
        this.fetchUserDetails();
      });
    },
    fetchItems() {
      getItems().then((items) => {
        this.relatedItems = items;
      });
    },
    findRelatedItems() {
      this.relatedItems.forEach(related => {
        if (this.item.categories.some(r => related.categories.includes(r)) && this.item.id !== related.id && this.outPutIntems.length < 10) {
          this.outPutIntems.push(related);
        }
        if (this.outPutIntems.length < 10) {
          if (this.item.description.toLowerCase().indexOf(related.name.toLowerCase()) >= 0 && !this.outPutIntems.includes(related) && this.item.id !== related.id) {
            this.outPutIntems.push(related);
          }
        }
      })
    },
    fetchUserDetails() {
        console.log("fetchUserDetails");
        getUserPubById(this.item.userId).then(response => {
          let user = response.data;
          this.user = user;
        })

    },
    enterEditMode(){
      this.editMode=true;
    },
    deleteItem() {
      deleteItem(this.item.id).then(() => {
        this.$emit("deletedItem",this.item.id);
        alert("Item deleted");
        this.$router.push("/shopping");
      });
    },
    updateItem() {
      if(this.item.image.includes(",")){
        this.item.image = this.item.image.split(",");
      }
      axios
        .post(
          "http://localhost:8090/item/update",
          this.item,
          {
            headers: {
              Authorization: "Bearer " + this.$store.getters.token,
            },
          }
        )
        .then(() => {
          this.editMode=false;
        })
        .catch((error) => {
          console.error("error:");
          alert("error;could not update item info");
          console.error(error);
        });
    },
    addToCart() {
      this.$store.commit('addToCart', {
        name: this.item.name,
        image: this.item.image[this.imgIndex],
        productPrice: this.item.price,
      })
    },
    itemAdded() {
      document.getElementsByClassName('add-to-cart-button')[0].innerHTML = "Product added";
    }
  },
};
</script>

<style scoped>
/* For small devices */
@media screen and (max-width: 768px) {
  .item-details-container {
    flex-direction: column;
    margin: 10px 10px 10px 10px;

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
    margin-bottom: 10px;
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


  .image-navigation button {
    margin-top: 10px;
    margin-right: 5px;
  }
}

/* For medium devices */
@media screen and (min-width: 768px) and (max-width: 1024px) {
  .item-details-container {
    margin: 50px 50px 50px 50px;
  ;
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
    margin: 100px 100px 100px 100px;
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
.locationWithImage {
  justify-content: center;
}
.item-image {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.item-image img {
  display: block;
  width: 75%;
  height: 75%;
  border-radius: 5px;
  margin: auto;
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
  padding: 20px;
  font-size: 36px;
  font-weight: bold;
  margin: 0 0 10px 0;
}

.item-price {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 50px;
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
  margin-right: 10px;
}

.map {
  grid-column: 3 / 4;
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
  background-color: #e3af39;
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
  margin: 20px;
}

.buy-now-button {
  background-color: #c97900;
}
.buy-now-button:hover{
  background-color: #f69200;

}

.add-to-cart-button {
  background-color: #003366;
}
.add-to-cart-button:hover{
  background-color: #0052cc;
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
  margin-bottom: 10px;
}
.editItemContainer {
  background-color: #f8f8f8;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  margin-bottom: 20px;
}

.editItemContainer p {
  margin: 10px 0;
}

.editItemContainer input {
  width: 40%;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ddd;
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
.adminButton{
  background-color: #910000;
  color: white;
  padding: 10px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  font-size: 16px;
  transition: all 0.3s ease;
  margin-bottom: 2%;
}
.adminButton:hover{
  background-color: #a60000;
  color: white;
  border: 1px solid #333;
  padding: 15px;
}

</style>


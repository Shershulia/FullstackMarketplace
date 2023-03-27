<template>
  <div class="container">
    <div class="list-of-items">
      <ListOfLittleItems v-if="itemsList.length" :listOfItems="itemsList"></ListOfLittleItems>
      <div v-else class="nothingFound">
        <div class="onRequest">
          <p>Nothing was found</p>
          <p v-if="(this.searchValue.length>0)||(this.searchCategory!=='')">&nbsp;on request:</p>
        </div>
      <p v-if="this.searchValue.length>0">Name: {{this.searchValue}}</p>
      <p v-if="this.searchCategory!==''">Category: {{this.searchCategory}}</p>
      </div>
    </div>

    <div class="search-section">
      <input
          type="text"
          placeholder="Enter the name of item"
          class="searchInput"
          v-model="searchValue"
      />

      <select v-model="searchCategory">
        <option value="">Select category</option>
        <option v-for="category in this.categories" :key="category">{{ category }}</option>
      </select>
      <input
          type="text"
          placeholder="Search by location - Current"
          class="searchInput"
          v-model="searchLocation"
          @keyup.enter="findLocation"
      />

      <div class="popup" @click="popUpFunction">?
        <span class="popuptext" id="myPopup">Click enter to make search by location faster</span>
      </div>
    </div>
  </div>
</template>

<script>
import ListOfLittleItems from "@/components/ListOfLittleItems.vue";
import { getCreationCategories } from "@/services/ItemServiceApi";

async function getLatAndLng(location){
  const address = location;
  const apiKey = "85bd375e34d144409d549410cdec985a"; // Replace with your API key from OpenCage

  const url = `https://api.opencagedata.com/geocode/v1/json?q=${encodeURI(
      address
  )}&key=${apiKey}`;

  return fetch(url)
      .then((response) => response.json())
      .then((data) => {
        if (data.results.length > 0) {
          const { lat, lng } = data.results[0].geometry;
          let latAndLng= [];

          latAndLng[0] = lat;
          latAndLng[1] = lng;
          return latAndLng;
        }
        throw new Error("No results found");
      })
      .catch((error) => console.error(error));
}
  export default {
    name: "HomeView",
    props: {
      items: {
        type: Array,
        required: true,
      },
    },
    async mounted() {
      this.categories = await getCreationCategories();
        for (let item of this.items) {
          const latAndLng = await getLatAndLng(item.location);
          item["latitude"] = latAndLng[0];
          item["longitude"] = latAndLng[1];
      }

    },
    components: {
      ListOfLittleItems
    },
    data() {
      return {
        categories: [],
        searchValue:"",
        searchCategory:"",
        searchLocation:"",
        latitude:"",
        longitude:"",
        searchByLocation:false,
      };
    },
    computed: {
      itemsList() {
         let items = this.items;
         if (this.searchValue.trim().length > 0) {
           items = items.filter((item) => item.name.toLowerCase().includes(this.searchValue.trim().toLowerCase()));
         }
         if (this.searchCategory !== "") {
           items = items.filter((item) => item.categories.
             some((category)=> category.toLowerCase()===this.searchCategory.toLowerCase()));
         }
         if (navigator.geolocation && this.searchLocation.toLowerCase() === "current" && this.searchByLocation) {
           navigator.geolocation.getCurrentPosition((position) => {
             const userLocation = {
               latitude: position.coords.latitude,
               longitude: position.coords.longitude
             };
             items.sort((a, b) => {
               const aDistance = this.getDistance(userLocation, a);
               const bDistance = this.getDistance(userLocation, b);
               return aDistance - bDistance;
             });
           });
         }
         if (this.searchByLocation) {
           const location = {
             latitude: this.latitude,
             longitude:  this.longitude
           };
           items.sort((a, b) => {
             const aDistance = this.getDistance(location, a);
             const bDistance = this.getDistance(location, b);
             return aDistance - bDistance;
           });
         }
         return items;
       }
    },
    methods: {
      getDistance(userLocation, itemLocation) {
        const lat1 = userLocation.latitude;
        const lon1 = userLocation.longitude;
        const lat2 = itemLocation.latitude;
        const lon2 = itemLocation.longitude;
        const R = 6371; // Radius of the earth in km
        const dLat = this.deg2rad(lat2 - lat1);
        const dLon = this.deg2rad(lon2 - lon1);
        const a =
            Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(this.deg2rad(lat1)) * Math.cos(this.deg2rad(lat2)) *
            Math.sin(dLon / 2) * Math.sin(dLon / 2);
        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        const d = R * c; // Distance in km
        return d;
      },
      deg2rad(deg) {
        return deg * (Math.PI/180);
      },
      async findLocation() {
        const latAndLng = await getLatAndLng(this.searchLocation);
        this.latitude= latAndLng[0];
        this.longitude= latAndLng[1];
        this.searchByLocation=true;
      },
      popUpFunction() {
        var popup = document.getElementById("myPopup");
        popup.classList.toggle("show");
      }

    }
  };
</script>
<style scoped>
.container {
  display: flex;
  flex-direction: column-reverse;
  align-items: center;
}

.search-section {
  display: flex;
  align-items: center;
}

@media screen and (min-width: 768px) {
  .search-section {
    flex-direction: row;
  }
}
@media screen and (max-width: 767px) {
  .search-section {
    flex-direction: column;;
  }
}

.searchInput {
  margin: 10px;
  padding: 8px;
  border-radius: 5px;
  border: 1px solid gray;
  width: 200px;
}

select {
  margin: 10px;
  padding: 8px;
  border-radius: 5px;
  border: 1px solid gray;
  width: 150px;
}
.nothingFound{
  display: flex;
  flex-direction: column;
  text-align: center;
}
.onRequest{
  display: flex;
  flex-direction: row;
}
.list-of-items{
  margin-left: 10%;
  margin-right: 10%;

}

.popup {
  position: relative;
  display: inline-block;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* The actual popup */
.popup .popuptext {
  visibility: hidden;
  width: 160px;
  background-color: #555;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 8px 0;
  position: absolute;
  z-index: 1;
  top: 150%;
  left: 100%;
  margin-left: -80px;
}


/* Toggle this class - hide and show the popup */
.popup .show {
  visibility: visible;
  animation: fadeIn 1s;
}

</style>
<template>
  <input
      type="text"
      placeholder="Search"
      class="searchInput"
      v-model="searchValue"
  />
  <select v-model="searchCategory">
    <option value=""></option>
    <option>Chair</option>
    <option>B</option>
    <option>C</option>
  </select>
  <input
      type="text"
      placeholder="Search by location (or Current)"
      class="searchInput"
      v-model="searchLocation"
  />
  <ListOfLittleItems v-if="itemsList.length" :listOfItems="itemsList"></ListOfLittleItems>
  <p v-else>Nothing was found on request: {{this.searchValue}} </p>
</template>
<script>
import ListOfLittleItems from "@/components/ListOfLittleItems.vue";

async function getLatAndLng(location){
  const address = location;
  const apiKey = "85bd375e34d144409d549410cdec985a"; // Replace with your API key from OpenCage

  const url = `https://api.opencagedata.com/geocode/v1/json?q=${encodeURI(
      address
  )}&key=${apiKey}`;

  return fetch(url)
      .then((response) => response.json())
      .then((data) => {
        const { lat, lng } = data.results[0].geometry;
        let latAndLng= [];

        latAndLng[0] = lat;
        latAndLng[1] = lng;
        console.log(latAndLng);
        return latAndLng;
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
      for (const item of this.items) {
        const latAndLng = await getLatAndLng(item.location);
        console.log(item.location)
        console.log(latAndLng);
        item["latitude"] = latAndLng[0];
        item["longitude"] = latAndLng[1];
        console.log(item);
      }
    },
    components: {
      ListOfLittleItems
    },
    data() {
      return {
        searchValue:"",
        searchCategory:"",
        searchLocation:"",
      };
    },
    computed: {
      itemsList() {
        let items = this.items;
        if (this.searchValue.trim().length > 0) {
          items = items.filter((item) => item.name.toLowerCase().includes(this.searchValue.trim().toLowerCase()));
        }
        if (this.searchCategory !== "") {
          items = items.filter((item) => item.category.toLowerCase() === this.searchCategory.toLowerCase());
        }
        if (navigator.geolocation && this.searchLocation.toLowerCase()==="current") {
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

    }
  };
</script>
<style>
.searchInput{
  height: 100px;
  width: auto;
  border-radius: 5px;
}
</style>
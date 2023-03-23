<template>
  <GMapMap
    :center="this.center"
    :zoom="15"
    map-type-id="terrain"
    style="width: 200px; height: 200px"
  >
    <GMapMarker
      :position="this.center"
      :clickable="true"
      :draggable="true"
      @click="center = this.center"
    />
  </GMapMap>
</template>
<script>
export default {
  name: "GoogleMap",
  props: {
    address: {
      type: String,
      required: true,
    },
  },
  mounted() {
    const address = this.address;
    const apiKey = "85bd375e34d144409d549410cdec985a"; // Replace with your API key from OpenCage

    const url = `https://api.opencagedata.com/geocode/v1/json?q=${encodeURI(
      address
    )}&key=${apiKey}`;

    fetch(url)
      .then((response) => response.json())
      .then((data) => {
        const { lat, lng } = data.results[0].geometry;
        this.center.lat = lat;
        this.center.lng = lng;
      })
      .catch((error) => console.error(error));
  },
  data() {
    return {
      center: {
        lat: 51.093048,
        lng: 6.84212,
      },
    };
  },
};
</script>

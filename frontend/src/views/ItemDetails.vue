<template>
  <div>
    <div class="imageWithPrice">
      <p>Id :{{ this.id }}</p>
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
    };
  },
  mounted() {
    const route = useRoute();
    this.id = route.params.id;
  },
};
</script>

<style></style>

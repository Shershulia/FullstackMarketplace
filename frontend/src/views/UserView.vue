<template>
  <div class="user-info">
    <h2>User Info</h2>
    <p><strong>Username:</strong> {{ user }}</p>
    <!-- TODO: can not get from token directly and therefore not implemented in store. need an api endpoint to expose this data, and need a database with interface for this -->
    <!-- <p><strong>Email:</strong> {{ user.email }}</p> -->
    <!-- <p><strong>First Name:</strong> {{ user.first_name }}</p> -->
    <!-- <p><strong>Last Name:</strong> {{ user.last_name }}</p> -->
    <button @click="logout">Logout</button>
  </div>
</template>

<script>
export default {
  computed: {
    user() {
      console.log(this.$store.getters.username);
      return this.$store.getters.username;
    },
  },
  mounted() {
    console.log("mounted");
    // this.$store.dispatch("getUserInfo");
    //if store token is empty, redirect to login page
    // console.log("username: " + this.$store.state.username);
    // console.log("token: " + this.$store.state.token);
    if (this.$store.getters.token == null || this.$store.getters.token == "") {
      this.$router.push("/login");
    }
  },
  methods: {
    logout() {
      console.log("logout");
      this.$store.dispatch("logout");
      this.$router.push("/login"); // redirect to the login page
    },
  },
};
</script>

<style>
.user-info {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>

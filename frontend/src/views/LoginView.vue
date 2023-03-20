<template>
  <div class="login">
    <h2>Login</h2>
    <form @submit.prevent="login">
      <div>
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username" />
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" />
      </div>
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    login() {
      axios
        .post("/login", {
          username: this.username,
          password: this.password,
        })
        .then((response) => {
          const token = response.data.token;
          // save the token to local storage or a Vuex store
          axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
          // set the default authorization header for future requests
          this.$router.push("/"); // redirect to the home page
        })
        .catch((error) => {
          console.error(error);
          alert("Invalid username or password");
        });
    },
  },
};
</script>

<style>
.login {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>

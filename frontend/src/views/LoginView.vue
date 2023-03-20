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
import jwt_decode from "jwt-decode";

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
        .post("http://localhost:8090/token", {
          username: this.username,
          password: this.password,
        })
        .then((response) => {
          console.log(response);
          const token = response.data;
          localStorage.setItem("token", token); // store the token in local storage
          axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
          // set the default authorization header for future requests
          const decodedToken = jwt_decode(token);
          this.$store.dispatch("validateToken", decodedToken);
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

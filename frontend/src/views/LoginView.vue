<template>
  <div class="login">
    <h2>Login</h2>
    <form @submit.prevent="onSubmit">
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
    <button @click="registerUser">Register User</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    onSubmit() {
      this.$store
        .dispatch("login", {
          username: this.username,
          password: this.password,
        })
        .then((response) => {
          console.log(response);
          //validate token
          // this.$store.dispatch("validateToken");
          this.$router.push("/user");
        })
        .catch((error) => {
          console.error(error);
          alert("Invalid username or password");
        });
    },
    registerUser() {
      // Redirect to create user page
      this.$router.push("/register-user");
    },
  },
  mounted() {
    //if store token not empty, redirect to user page
    if (this.$store.getters.token != null) {
      this.$router.push("/user");
    }
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

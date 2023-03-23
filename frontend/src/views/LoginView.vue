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
    <button class="goToLoginButton" @click="registerUser">Register User</button>
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
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 10% auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0px 0px 10px 2px rgba(0, 0, 0, 0.1);
}

.login h2 {
  font-size: 1.8rem;
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.login form {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}

.login form div {
  margin-bottom: 20px;
}

.login form label {
  font-size: 1.2rem;
  color: #333;
  display: block;
  margin-bottom: 5px;
}

.login form input {
  padding: 5px;
  border-radius: 5px;
  border: none;
  box-shadow: 0px 0px 10px 2px rgba(0, 0, 0, 0.1);
  font-size: 1.2rem;
}

.login form button[type="submit"] {
  background-color: #003366;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px;
  font-size: 1.2rem;
  cursor: pointer;
  transition: all 0.3s ease;
  margin: 20px;
}

.login form button[type="submit"]:hover {
  background-color: #0052cc;
  color: white;
  border: 1px solid #333;
  padding: 15px;

}


</style>

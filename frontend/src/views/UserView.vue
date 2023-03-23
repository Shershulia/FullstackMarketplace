<template>
  <div class="user-info">
    <h2>User Info</h2>
    <div v-if="!editMode">
      <p><strong>Username:</strong> {{ user.username }}</p>
      <p><strong>Email:</strong> {{ user.email }}</p>
      <p><strong>Name:</strong> {{ user.name }}</p>
      <p><strong>Last Name:</strong> {{ user.lastname }}</p>
      <p><strong>Age:</strong> {{ user.age }}</p>
      <button @click="toggleEditMode">Edit</button>
    </div>
    <div v-else>
      <p><strong>Username:</strong> {{ user.username }}</p>
      <p><strong>Email:</strong> <input v-model="editUser.email" /></p>
      <p><strong>userName:</strong> <input v-model="editUser.username" /></p>
      <p><strong>Name:</strong> <input v-model="editUser.name" /></p>
      <p><strong>Last Name:</strong> <input v-model="editUser.lastname" /></p>
      <p>
        <strong>Age:</strong> <input type="number" v-model="editUser.age" />
      </p>
      <p><strong>Password:</strong> <input v-model="password" /></p>

      <button @click="toggleEditMode">Save</button>
    </div>
    <button id="logoutBtn" @click="logout">Logout</button>
  </div>
  <div class="itemsIndividual"></div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      editMode: false,
      password: "",
      items: [],
    };
  },
  computed: {
    username() {
      console.log(this.$store.getters.username);
      return this.$store.getters.username;
    },
    user() {
      return this.$store.getters.user;
    },
    editUser() {
      let euser = {
        username: this.user.username,
        password: this.user.password,
        email: this.user.email,
        name: this.user.name,
        lastname: this.user.lastname,
        age: this.user.age,
      };
      return euser;
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
    toggleEditMode() {
      this.editMode = !this.editMode;
      //update user info in store and backend
      if (!this.editMode) {
        //update user info in backend

        if (this.password == "") {
          alert("password is required");
          return;
        }

        axios
          .post(
            "http://localhost:8090/user/update",
            {
              username: this.editUser.username,
              name: this.editUser.name,
              email: this.editUser.email,
              lastname: this.editUser.lastname,
              age: this.editUser.age,
              password: this.password,
            },
            {
              headers: {
                Authorization: "Bearer " + this.$store.getters.token,
              },
            }
          )
          .then((response) => {
            console.log(response);
            //update user info in store
            this.$store.commit("setUser", this.user);
          })
          .catch((error) => {
            console.error("error:");
            alert("error;could not update user info");
            console.error(error);
          });
      }
    },
    logout() {
      this.password = "";
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

#logoutBtn {
  margin-top: 20px;
  background-color: rgb(189, 84, 84);
}
</style>

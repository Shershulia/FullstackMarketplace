<template>
  <div class="user-info">
    <h2>User Info</h2>
    <div v-if="!editMode">
      <p><strong>Username:</strong> {{ user.username }}</p>
      <p><strong>Email:</strong> {{ user.email }}</p>
      <p><strong>Name:</strong> {{ user.name }}</p>
      <p><strong>Last Name:</strong> {{ user.lastname }}</p>
      <p><strong>Age:</strong> {{ user.age }}</p>
      <p v-if="user.permission==='admin'"><strong>Permission level:</strong> {{ user.permission }}</p>
      <button @click="toggleEditMode" class="buttonEdit">Edit</button>
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

      <button @click="toggleEditMode" class="buttonEdit">Save</button>
    </div>
    <div>
      <button class="goToLoginButton" @click="logout" >Logout</button>
    </div>
    <div>
      <button @click="goToSaveItemPage" class="saveButton">Create item</button>
    </div>
    <div>
      <button v-if="user.permission==='admin'" @click="goToAdminPage" class="adminButton">Go to admin panel</button>
    </div>
  </div>
  <div class="itemsIndividual" v-if="items.length>0">
    <p>Your items:</p>
    <ListOfLittleItemsEditableAndDeletable :listOfItems="this.items"></ListOfLittleItemsEditableAndDeletable>
  </div>
</template>

<script>
import axios from "axios";
import {getItemsOfCertainUser} from "@/services/ItemServiceApi";
import ListOfLittleItemsEditableAndDeletable from "@/components/editAndDeleteItems/ListOfLittleItemsEditableAndDeletable.vue";

export default {
  components: {
    ListOfLittleItemsEditableAndDeletable,
  },
  data() {
    return {
      editMode: false,
      password: "",
      items: [],
    };
  },
  computed: {
      username() {
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
          permission:this.user.permission,
        };
        return euser;
      },
    },
  mounted() {
    if (this.$store.getters.token == null || this.$store.getters.token === "") {
      this.$router.push("/login");
    } else {
      if (this.user.id !== null) {
        getItemsOfCertainUser(this.user.id).then((items) => {
          this.items = items;
        });
      }
    }
  },
  watch: {
    user: {
      handler() {
        if (this.user.id !== null) {
          getItemsOfCertainUser(this.user.id).then((items) => {
            this.items = items;
          });
        }
      },
      deep: true
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

          //update user info in store
          this.$store.commit("setUser", this.editUser);
          this.user = this.editUser;

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
                permission: this.editUser.permission,

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
      goToSaveItemPage(){
        this.$router.push("/item/create");
      },
      goToAdminPage(){
        this.$router.push("/admin");
      },

  }
};
</script>

<style scoped>
.user-info {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.buttonEdit {
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

.buttonEdit:hover {
  background-color: #0052cc;
  color: white;
  border: 1px solid #333;
  padding: 15px;

}
.saveButton{
  background-color: #3a883d;
  color: white;
  padding: 10px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  font-size: 16px;
  transition: all 0.3s ease;
  margin-top: 10px;
}
.saveButton:hover{
  background-color: #449f48;
  color: white;
  border: 1px solid #333;
  padding: 15px;
}
.adminButton{
  background-color: #910000;
  color: white;
  padding: 10px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  font-size: 16px;
  transition: all 0.3s ease;
  margin-top: 10px;
}
.adminButton:hover{
  background-color: #a60000;
  color: white;
  border: 1px solid #333;
  padding: 15px;
}
</style>

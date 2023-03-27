<template>
  <div v-if="loggedUserId!=null" class="editItemContainer">
    <h2>Create item</h2>
    <p><strong>Name:</strong> <input v-model="item.name"
                                     :class="{ 'invalid-input': v$.$error && v$.item.name.$error }"
    /></p>
    <p class="errorMessage" v-if="v$.item.name.$error">
      {{ v$.item.name.$errors[0].$message }}
    </p>
    <p><strong>Price:</strong> <input v-model="item.price" type="number" min="0" oninput="validity.valid||(value='');"
                                      :class="{ 'invalid-input': v$.$error && v$.item.price.$error }"/></p>
    <p class="errorMessage" v-if="v$.item.price.$error">
      {{ v$.item.price.$errors[0].$message }}
    </p>

    <p><strong title='url to image hosted on diffrent page (multiple seperated by "," with no spacing between)'>Image:</strong> <input v-model="inputImage" :class="{ 'invalid-input': v$.$error && v$.inputImage.$error }" /></p>
    <p class="errorMessage" v-if="v$.inputImage.$error">
      {{ v$.inputImage.$errors[0].$message }}
    </p>


    <p><strong>Location:</strong> <input v-model="item.location" :class="{ 'invalid-input': v$.$error && v$.item.location.$error }" /></p>
    <p class="errorMessage" v-if="v$.item.location.$error">
      {{ v$.item.location.$errors[0].$message }}
    </p>

    <p><strong>Description:</strong> <input v-model="item.description" :class="{ 'invalid-input': v$.$error && v$.item.description.$error }" /></p>
    <p class="errorMessage" v-if="v$.item.description.$error">
      {{ v$.item.description.$errors[0].$message }}
    </p>


    <p><strong>Select category:</strong></p>
    <select v-model="item.categories" multiple>
      <option v-for="category in  showedCategories" :key="category">{{ category }}</option>    </select>
    <div class="saveButtonContainer">
      <button @click="updateItem" class="saveButton">Create Item</button>
    </div>
  </div>
  <div class="elseCreate" v-else>
    <h2>You should be authenticated to create a item</h2>
    <div class="buttonsCreatePage">
      <button type="submit" class="goToLoginButton" @click="goToRegisterPage"> Register</button>
      <button class="loginButtonOnCreateItem" type="button" @click="goToLoginPage">Login page</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import useValidate from "@vuelidate/core";
import { helpers, maxLength, minLength, required } from "@vuelidate/validators";
import { getCreationCategories } from "@/services/ItemServiceApi";
const onlyLinks = (value) => value.match(/[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)?/gi);


export default {
  name: "CreateItem",
  setup() {
    return { v$: useValidate() };
  },
  async mounted() {
    this.showedCategories=await getCreationCategories();
  },
  data() {
    return {
      showedCategories:[],
      inputImage: "",
      item: {
        name: "",
        description:
          "",
        userid: "",
        price:
          "",
        location: "",
        image: [],
        categories:[],
        metadata:[]
      },
    };
  },
  validations() {
    return {
      inputImage:{
        required,
        onlyLinks: helpers.withMessage("Here is only links supported", onlyLinks),
      },
      item: {
        name: {
          required,
          minLength: minLength(4)
        },
        description: {
          required,
          maxLength: maxLength(224)
        },
        price: {
          required,
        },
        location: {
          required,
        },
      },
    };
  },

  methods: {
    updateItem() {
      this.item.userid=this.$store.getters.user.id;
      //convert inputImage to array
      let imgList = []
      if (this.inputImage.includes(",")){
        imgList = this.inputImage.split(",");
      }else{
        imgList.push(this.inputImage);
      }
      this.item.image=imgList;
      console.log(this.item.image);
      this.v$.$validate();
      console.log(this.item)
      if (!this.v$.$error){
        axios
          .post(
            "http://localhost:8090/item/update",
            this.item,
            {
              headers: {
                Authorization: "Bearer " + this.$store.getters.token,
              },
            }
          )
          .then(() => {
            this.editMode=false;
          })
          .catch((error) => {
            console.error("error:");
            alert("error;could not create item");
            console.error(error);
          });
        this.$router.push("/");

      }
    },
    goToLoginPage(){
      this.$router.push("/login");

    },
    goToRegisterPage(){
      this.$router.push("/register-user");
    }
  },
  computed: {
    loggedUserId() {
      return this.$store.getters.user.id;
    },
  }
};
</script>

<style scoped>

.editItemContainer {
  background-color: #f8f8f8;
  padding: 20px;
  justify-content: center;
  border: 1px solid #ddd;
  border-radius: 5px;
  margin-bottom: 20px;
}

.editItemContainer p  {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.editItemContainer select{
  width: 20%;
  display: inline-block;
  text-align: center;
}

.editItemContainer input {
  width: 40%;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ddd;
}

.editItemContainer strong {
  margin: 10px;
  flex: 0.2;
}

.saveButton {
  background-color: #4CAF50;
  color: white;
  padding: 10px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  font-size: 16px;
  margin-top: 10px;
}
.saveButtonContainer{
  display: flex;
  justify-content: center;
}
.elseCreate{
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.buttonsCreatePage{
  display: flex;
  justify-content: center;
  flex-direction: row;
}
.buttonsCreatePage .goToLoginButton{
  margin: 10px;
  width: 40%;
}
.loginButtonOnCreateItem{
  margin: 10px;
  width: 40%;
  background-color: #003366;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px;
  font-size: 1.2rem;
  cursor: pointer;
  transition: all 0.3s ease;
}
.loginButtonOnCreateItem:hover {
  background-color: #0052cc;
  color: white;
  border: 1px solid #333;
  padding: 15px;

}
.errorMessage {
  color: red;
}
input.invalid-input {
  border: 2px solid red;
}

</style>


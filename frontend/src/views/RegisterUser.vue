<template>
  <div class="registration_form">
    <h1>Registration form</h1>
    <form @submit.prevent="login">
      <div class="oblig_inf">
        <p for="username">Username:</p>
        <input
          v-model="username"
          placeholder="Enter your username..."
          :class="{ 'invalid-input': v$.$error && v$.username.$error }"
        />
        <p for="email">Email:</p>
        <input
          v-model="email"
          type="email"
          placeholder="Enter your email..."
          :class="{ 'invalid-input': v$.$error && v$.email.$error }"
        />
        <p class="errorMessage" v-if="v$.email.$error">
          {{ v$.email.$errors[0].$message }}
        </p>
        <p for="password">Password</p>
        <div class="showPassword">
          <input
            v-model="password"
            placeholder="Enter your password..."
            :type="showPassword ? 'text' : 'password'"
            :class="{ 'invalid-input': v$.$error && v$.password.$error }"
          />
          <input type="checkbox" v-model="showPassword" id="show-password" />

          <ul class="ulErrors" v-if="v$.password.$error">
            <li
              class="liErrors"
              v-for="value in v$.password.$errors"
              :key="value"
            >
              <p class="errorMessage">{{ value.$message }}</p>
            </li>
          </ul>
        </div>
        <p for="password">Confirm password:</p>
        <input
          v-model="confirmPassword"
          type="password"
          placeholder="Confirm your password..."
          :class="{ 'invalid-input': password !== confirmPassword }"
        />
        <p class="errorMessage" v-if="this.password !== this.confirmPassword">
          Passwords should match
        </p>
      </div>
      <div class="personal_information">
        <p for="firstName">First name</p>
        <input
          name="firstName"
          v-model="firstName"
          placeholder="Enter your first name..."
          :class="{ 'invalid-input': v$.$error && v$.firstName.$error }"
        />
        <p class="errorMessage" v-if="v$.firstName.$error">
          {{ v$.firstName.$errors[0].$message }}
        </p>

        <p for="lastName">Last name</p>
        <input
          name="lastName"
          v-model="lastName"
          placeholder="Enter your last name..."
          :class="{ 'invalid-input': v$.$error && v$.lastName.$error }"
        />
        <p class="errorMessage" v-if="v$.lastName.$error">
          {{ v$.lastName.$errors[0].$message }}
        </p>
        <p for="age">Age</p>
        <input name="age" v-model="age" placeholder="Age:" type="number" />
      </div>
      <div class="buttons">
        <input type="submit" value="Register" />
        <button class="goToLoginButton" type="button" @click="goToLogin">Back to login page</button>
      </div>
    </form>
  </div>
</template>

<script>
import useValidate from "@vuelidate/core";
import { required, email, minLength, helpers } from "@vuelidate/validators";
// import { useStore } from "vuex";
import { register } from "@/services/ItemServiceApi";

const onlyLetters = (value) => value.match(/^[a-zA-Z\s]*$/);
const onedigit = (value) => value.match(/(?=.*\d)/);
const oneUpperCase = (value) => value.match(/(?=.*[A-Z])/);
const oneLowerCase = (value) => value.match(/(?=.*[a-z])/);

export default {
  name: "App",
  setup() {
    return { v$: useValidate() };
  },
  data() {
    return {
      username: "",
      email: "",
      password: "",
      confirmPassword: "",
      firstName: "",
      lastName: "",
      age: "",
      showPassword: false,
    };
  },
  validations() {
    return {
      username: {
        required,
        minLength: minLength(4),
      },
      firstName: {
        required,
        onlyLetters: helpers.withMessage("Letters only", onlyLetters),
      },
      lastName: {
        required,
        onlyLetters: helpers.withMessage("Letters only", onlyLetters),
      },
      email: { required, email },
      password: {
        required,
        onedigit: helpers.withMessage(
          "Password should contain at least one digit",
          onedigit
        ),
        oneUpperCase: helpers.withMessage(
          "Password should contain at least one upper case",
          oneUpperCase
        ),
        oneLowerCase: helpers.withMessage(
          "Password should contain at least one lower case",
          oneLowerCase
        ),
        minLength: minLength(8),
      },
    };
  },
  methods: {
    goToLogin() {
      this.$router.push("/login");
    },
    async login() {
      this.v$.$validate(); // checks all inputs
      if (!this.v$.$error && this.password === this.confirmPassword) {
        let request = {
          username: this.username,
          email: this.email,
          password: this.password,
          name: this.firstName,
          lastname: this.lastName,
          age: this.age,
          permission:"normal",
        };
        //use ItemServiceApi to send request to backend
        const response = await register(request);
      }
    },
  },
};
</script>
<style>
@media screen and (min-width: 768px) {
  .registration_form {
    margin: 50px auto;
  }
}
@media screen and (max-width: 767px) {
  .registration_form {
    margin: 50px auto 20% 20%;
  }
}
.registration_form {
  align-content: center;
  max-width: 500px;
  padding: 30px;
  background-color: #f7f7f7;
  border-radius: 5px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  display: inline-block;
}

h1 {
  text-align: center;
  margin-bottom: 30px;
}

form {
  display: flex;
  flex-wrap: wrap;
}

.oblig_inf {
  flex-basis: 100%;
  margin-bottom: 20px;
}

.oblig_inf p {
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  border-radius: 3px;
  border: none;
  background-color: #e8e8e8;
  font-size: 16px;
}

input:focus {
  outline: none;
  background-color: #f5f5f5;
}

input[type="submit"] {
  background-color: #003366;
  color: white;
  font-size: 18px;
  font-weight: bold;
  border-radius: 3px;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  transition: all 0.3s ease;

}

input[type="submit"]:hover {
  background-color: #0052cc;
  padding: 15px;
}
.buttons {
  margin-left: 15%;
  margin-top: 10%;
  display: flex;
  align-items: baseline;
  justify-content: center;
  flex-direction: column;
}
.goToLoginButton {
  background-color: #c97900;
  color: black;
  font-size: 18px;
  font-weight: bold;
  border-radius: 3px;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  transition: all 0.3s ease;
}
.goToLoginButton:hover {
  background-color: #f69200;
  padding: 15px 20px;
}
.showPassword {
  position: relative;
  display: inline;
}

.showPassword input[type="checkbox"] {
  position: absolute;
  top: 0%;
  left: 45%;
}

.ulErrors {
  list-style-type: none;
  margin: 0px;
  padding: 0;
}

.liErrors {
  margin: 0.5em 0;
}

.errorMessage {
  color: red;
}
input.invalid-input {
  border: 2px solid red;
}
</style>

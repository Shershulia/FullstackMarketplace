<template>
  <div class="registration_form">
    <h1>Registration form</h1>
    <form @submit.prevent="login">
      <div class="oblig_inf">
        <p for="username">Email:</p>
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
        <button type="button">Back to login page</button>
      </div>
    </form>
  </div>
</template>
<script>
import useValidate from "@vuelidate/core";
import { required, email, minLength, helpers } from "@vuelidate/validators";
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
      firstName: {
        required,
        onlyLetters: helpers.withMessage(
          "Here accepted only letters",
          onlyLetters
        ),
      },
      lastName: {
        required,
        onlyLetters: helpers.withMessage(
          "Here accepted only letters",
          onlyLetters
        ),
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
    async login() {
      this.v$.$validate(); // checks all inputs
      if (!this.v$.$error && this.password === this.confirmPassword) {
        let request = {
          email: this.email,
          password: this.password,
          firstName: this.firstName,
          lastName: this.lastName,
          age: this.age,
        };
        console.log(request);
      }
    },
  },
};
</script>
<style>
.registration_form {
  margin: 50px auto;
  max-width: 500px;
  padding: 30px;
  background-color: #f7f7f7;
  border-radius: 5px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
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
  background-color: #0066ff;
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
}
.buttons {
  margin-left: 15%;
  margin-top: 10%;
  display: flex;
  align-items: baseline;
  justify-content: center;
  flex-direction: column;
}
button {
  background-color: #ff9900;
  color: black;
  font-size: 18px;
  font-weight: bold;
  border-radius: 3px;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  transition: all 0.3s ease;
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

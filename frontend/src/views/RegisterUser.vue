<template>
  <div class="registration_form">
    <h1>Registration form</h1>
    <form @submit.prevent="login">
      <div class="oblig_inf">
        <p for="username">Email:</p>
        <input
          name="username"
          v-model="email"
          type="email"
          placeholder="Enter your email..."
        />
        <p for="password">Password</p>
        <div class="showPassword">
          <input
            v-model="password"
            placeholder="Enter your password..."
            :type="showPassword ? 'text' : 'password'"
          />
          <input type="checkbox" v-model="showPassword" id="show-password" />
        </div>
        <p for="password">Confirm password:</p>
        <input
          v-model="confirmPassword"
          type="password"
          placeholder="Confirm your password..."
        />
      </div>
      <div class="personal_information">
        <p for="firstName">First name</p>
        <input
          name="firstName"
          v-model="firstName"
          placeholder="Enter your first name..."
        />

        <p for="lastName">Last name</p>
        <input
          name="lastName"
          v-model="lastName"
          placeholder="Enter your last name..."
        />
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
export default {
  name: "App",
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
  methods: {
    async login() {
      let request = {
        email: this.email,
        password: this.password,
        firstName: this.firstName,
        lastName: this.lastName,
        age: this.age,
      };
      console.log(request);
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
</style>

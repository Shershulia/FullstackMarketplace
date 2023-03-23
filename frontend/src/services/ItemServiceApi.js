import axios from "axios";
import jwt_decode from "jwt-decode";

const apiClient = axios.create({
  baseURL: "http://localhost:8090",
});

export const getItemBy = (id) => {
  return apiClient.get(`/item/${id}`, {});
};

//login

export const login = (username, password) => {
  let response = apiClient.post("/token", { username, password });
  console.log(response);
  //save token to vuex store and set axios header
  this.$store.commit("setToken", response.data.token);
  this.$store.commit("setUserId", jwt_decode(response.data.token));
  axios.defaults.headers.common[
    "Authorization"
  ] = `Bearer ${response.data.token}`;
  return response;
};

//register

export const register = (username, email, password, name, lastname, age) => {
  console.log("creating user");
  console.log(username, email, password, name, lastname, age);

  let user = {
    id: 1,
    username: username,
    email: email,
    password: password,
    name: name,
    lastname: lastname,
    age: Math.floor(parseInt(age)),
  };

  apiClient
    .post("/user/register", user)
    .then((response) => {
      console.log("New user created with ID:", response.data);
    })
    .catch((error) => {
      console.error("Error creating new user:", error);
    });
};

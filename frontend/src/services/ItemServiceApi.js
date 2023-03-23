import axios from "axios";
import jwt_decode from "jwt-decode";

const apiClient = axios.create({
  baseURL: "http://localhost:8090",
  headers: {
    "Access-Control-Allow-Origin": "*",
  },
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

export const register = (user) => {
  console.log("creating user");

  console.log(user);

  axios
    .post("http://localhost:8090/user/register", user)
    .then((response) => {
      console.log("New user created with ID:", response.data);
      let id = response.data;
      if (id != null) {
        alert("New user created successfully!");
        //redirect to login page
        this.$router.push("/login");
      } else {
        alert("Error creating new user: try a diffrent username");
      }
    })
    .catch((error) => {
      alert("Server error: try again later");
      console.error("Error creating new user:", error);
    });
};

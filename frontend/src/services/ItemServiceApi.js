import axios from "axios";
import jwt_decode from "jwt-decode";

const apiClient = axios.create({
  baseURL: "http://localhost:8090",
  headers: {
    "Access-Control-Allow-Origin": "*",
  },
});

export const getItemById = (id) => {
  console.log("getItemById: " + id);
  return apiClient.get(`/item/${id}`, {});
};

export const getItemIds = () => {
  return apiClient.get(`/item-id/`, {});
};

export const getItems = () => {
  return apiClient.get(`/item`, {}).then((response) => {
    return response.data;
  });
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
      if (id != null && id != 0 && id != "") {
        alert("New user created successfully!");
        //redirect to login page
        this.$router.push("/login");
      } else {
        alert("Error creating new user: try a diffrent username");
      }
    })
    .catch((error) => {
      // alert("Server error: try again later");
      console.error("Error creating new user:", error);
    });
};

//login
export const login = (username, password) => {
  axios
    .post("http://localhost:8090/token", {
      username: username,
      password: password,
    })
    .then((response) => {
      const token = response.data;
      const decodedToken = jwt_decode(token);
      const userId = decodedToken.sub;
      console.log("token: " + token);
      console.log("decodedToken: " + decodedToken);
      console.log("userId: " + userId);

      //save token to vuex store and set axios header
      this.$store.commit("setToken", token);
      this.$store.commit("setUserId", userId);
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    });
};

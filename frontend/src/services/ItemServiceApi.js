import axios from "axios";
import jwt_decode from "jwt-decode";

export const url = "http://localhost:8090";

const apiClient = axios.create({
  baseURL: this.url,
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

export const getUserPubById = (id) => {
  console.log("getUserById: " + id);
  return apiClient.get(`/user/pub/${id}`, {})
};

//register
export const register = (user) => {
  console.log("creating user");
  console.log(user);
  axios
    .post(this.url + "/user/register", user)
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
    .post(this.url+"/token", {
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

//loginUser
export const loginUser = (username, password) => {
  new Promise((resolve, reject) => {
    axios
      .post(this.url+"/token", {
        username: username,
        password: password,
      })
      .then((response) => {
        const token = response.data;
        localStorage.setItem("token", token);
        //debug
        console.log("token-store: " + localStorage.getItem("token"));

        axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
        const decodedToken = jwt_decode(token);

        console.log(decodedToken);
        console.log(decodedToken.sub);

        this.store.commit("setUsername", decodedToken.sub);
        this.store.commit("setToken", token);

        //get user info from server
        axios
          .get(this.url+"/user/" + decodedToken.sub, {
            headers: {
              Authorization: "Bearer " + token,
            },
          })
          .then((response) => {
            console.log("response:");
            console.log(response);
            // this.$store.commit("setUserInfo", response.data);
            let user = {
              username: response.data.username,
              email: response.data.email,
              name: response.data.name,
              lastname: response.data.lastname,
              age: response.data.age,
            };
            //update store
            this.commit("setUser", user);
            return user;
          });

        resolve(response);
      })
      .catch((error) => {
        reject(error);
      });
  });
};
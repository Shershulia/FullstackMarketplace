import axios from "axios";
import jwt_decode from "jwt-decode";

let BASE_URL = "http://localhost:8090";

function getToken(username, password) {
  return new Promise((resolve, reject) => {
    axios
      .post(BASE_URL + "/token", {
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

        let data = {
          userid: decodedToken.sub,
          token: token,
        };

        resolve(data);
      })
      .catch((error) => {
        reject(error);
      });
  });
}

function getUserDetails(userid, token) {
  //get user info from server
  return new Promise((resolve, reject) => {
    axios
      .get(BASE_URL + "/user/" + userid, {
        headers: {
          Authorization: "Bearer" + token,
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
          phone: response.data.phone,
        };
        //update store
        this.commit("setUser", user);
        return user;
      });

    resolve(response);
  }).catch((error) => {
    reject(error);
  });
}

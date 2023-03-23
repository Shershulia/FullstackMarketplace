import { createStore } from "vuex";
import axios from "axios";
import jwt_decode from "jwt-decode";

export default createStore({
  state: {
    // username: null,
    user: {
      username: null,
      name: null,
      email: null,
      phone: null,
    },
    token: null,
  },
  getters: {
    isLoggedIn: (state) => !!state.username && !!state.token,
    username: (state) => state.user.username,
    user: (state) => state.user,
    token: (state) => state.token,
  },
  mutations: {
    setUsername(state, username) {
      state.user.username = username;
    },
    setUser(state, user) {
      //TODO: update server with new user info

      state.user = user;
    },
    setToken(state, token) {
      state.token = token;
    },
    clearAuthData(state) {
      state.user.username = null;
      state.user.name = null;
      state.user.email = null;
      state.user.phone = null;

      state.token = null;
    },
  },
  actions: {
    login({ commit }, { username, password }) {
      return new Promise((resolve, reject) => {
        axios
          .post("http://localhost:8090/token", {
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

            commit("setUsername", decodedToken.sub);
            commit("setToken", token);

            //get user info from server
            axios
              .get("http://localhost:8090/user/" + decodedToken.sub, {
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
    },
    validateToken({ commit, state }) {
      return new Promise((resolve, reject) => {
        axios
          .post("http://localhost:8090/verify", {
            token: state.token,
          })
          .then((response) => {
            console.log(response);
            resolve(response);
          })
          .catch((error) => {
            console.log(error);
            commit("clearAuthData");
            localStorage.removeItem("token");
            reject(error);
          });
      });
    },
    logout({ commit }) {
      commit("clearAuthData");
      localStorage.removeItem("token");
    },
    autoLogin({ commit }) {
      const token = localStorage.getItem("token");
      if (!token) {
        return;
      }
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
      const decodedToken = jwt_decode(token);
      commit("setUser", decodedToken);
      commit("setToken", token);
    },
  },
  modules: {},
});

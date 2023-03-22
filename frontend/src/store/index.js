import { createStore } from "vuex";
import axios from "axios";
import jwt_decode from "jwt-decode";

export default createStore({
  state: {
    username: null,
    token: null,
  },
  getters: {
    isLoggedIn: (state) => !!state.username && !!state.token,
    username: (state) => state.username,
    token: (state) => state.token,
  },
  mutations: {
    setUser(state, username) {
      state.username = username;
    },
    setToken(state, token) {
      state.token = token;
    },
    clearAuthData(state) {
      state.username = null;
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

            commit("setUser", decodedToken.sub);
            commit("setToken", token);
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

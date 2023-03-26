import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueGoogleMaps from "@fawmi/vue-google-maps";
import i18n from './i18n'

createApp(App)
    .use(store)
    .use(router)
    .use(i18n)
    .use(VueGoogleMaps, {
    load: {
      key: "AIzaSyBXNwArqrhesWurO7lHEYOYoG1NnVmJMGk",
    },
  })
  .mount("#app");

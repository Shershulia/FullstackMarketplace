import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import UserView from "../views/UserView.vue";
import ItemDetails from "../views/ItemDetails.vue";

const routes = [
  {
    path: "/",
    name: "home",
    props: true,
    component: HomeView,
  },
  {
    path: "/item/:id",
    name: "ItemDetails",
    props: true,
    component: ItemDetails,
  },
  {
    path: "/login",
    name: "LoginView",
    component: LoginView,
  },
  {
    path: "/user",
    name: "UserView",
    component: UserView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;

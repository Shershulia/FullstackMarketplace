import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import UserView from "../views/UserView.vue";
import ItemDetails from "../views/ItemDetails.vue";
import RegisterUser from "../views/RegisterUser.vue";
import AboutView from "@/views/AboutView";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    component: AboutView,
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
  {
    path: "/shopping",
    name: "HomeView",
    props: true,
    component: HomeView,
  },
  {
    path: "/register-user",
    name: "RegisterUser",
    component: RegisterUser,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;

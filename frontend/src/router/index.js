import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";
import UserView from "../views/UserView.vue";
import ItemDetails from "../views/ItemDetails.vue";
import RegisterUser from "../views/RegisterUser.vue";
import AboutView from "@/views/AboutView";
import CreateItem from "@/views/CreateItem.vue";
import AdminView from "@/views/admin/AdminView.vue";
import CartView from "@/views/CartView";
import CheckoutView from "@/views/CheckoutView";


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
    path: "/item/create",
    name: "CreateItem",
    component: CreateItem,
  },
  {
    path: "/login",
    name: "LoginView",
    component: LoginView,
  },
  {
    path: "/admin",
    name: "AdminView",
    component: AdminView ,
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
    path: "/",
    name: "homepage",
    props: true,
    component: HomeView,
  },
  {
    path: "/register-user",
    name: "RegisterUser",
    component: RegisterUser,
  },
  {
    path: "/cart",
    name: "Cart",
    component: CartView,
  },
  {
    path: "/checkout",
    name: "Checkout",
    component: CheckoutView,
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;

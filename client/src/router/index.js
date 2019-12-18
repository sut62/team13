import Vue from "vue";
import Router from "vue-router";
import Ambulance from "../components/Ambulance";
import ViewAmbulance from "../components/ViewAmbulance";
import Welcome from "../components/Welcome";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      component: Welcome
    },
    {
      path: "/viewAmbulance",
      component: ViewAmbulance
    },
    {
      path: "/ambulance",
      component: Ambulance
    }
  ]
});

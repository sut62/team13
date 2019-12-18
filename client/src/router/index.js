import Vue from "vue";
import Router from "vue-router";
import Ambulance from "../components/Ambulance";
import ViewAmbulance from "../components/ViewAmbulance";
import Welcome from "../components/Welcome";
import ViewAmCheck from "../components/ViewAmCheck";
import AmbulanceCheck from "../components/AmbulanceCheck";

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
      path: "/viewambulance",
      component: ViewAmbulance
    },
    {
      path: '/ambulancecheck',
      component: AmbulanceCheck
    },
    {
      path: '/viewambulancecheck',
      component: ViewAmCheck
    },
    {
      path: "/ambulance",
      component: Ambulance
    }
  ]
});

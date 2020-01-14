import Vue from "vue";
import Router from "vue-router";
import Ambulance from "../components/Ambulance";
import ViewAmbulance from "../components/ViewAmbulance";
import DriverRegis from "../components/DriverRegis";
import ViewDriver from "../components/ViewDriver";
import Welcome from "../components/Welcome";
import ViewAmCheck from "../components/ViewAmCheck";
import AmbulanceCheck from "../components/AmbulanceCheck";
import Emergency from '../components/Emergency';
import ViewEmergency from '../components/ViewEmergency';
import CheckEquipment from "../components/CheckEquipment";
import ViewCheckEquipment from "../components/ViewCheckEquipment";
import Nurse from "../components/Nurse";
import ViewNurse from "../components/ViewNurse";
import Login from "../components/Login";
Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      component: Login
    },
    {
      path: "/welcome",
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
    },
    {
      path: "/driver",
      component: DriverRegis
    },
    {
      path: '/viewdriver',
      component: ViewDriver
    },
    {
      path: '/emergency',
      component: Emergency
    },
    {
      path: '/viewEmergency',
      component: ViewEmergency
    },
    {
      path: '/checkEquipment',
      component: CheckEquipment
    },
    {
      path: '/ViewCheckEquipment',
      component: ViewCheckEquipment
    },
	{
      path: '/nurse',
      component: Nurse
    },
    {
      path: '/viewnurse',
      component: ViewNurse
    }
  ]
});

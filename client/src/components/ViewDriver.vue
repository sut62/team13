<template>
  <v-app id="landing-page">
    <v-tabs background-color="orange darken-2" style="margin-buttom:-100px">
      <v-tab @click="pushWelcome" style="color: black; margin-left:1%">HOME</v-tab>
      <v-row justify="center">
        <v-col cols="12" sm="6">
          <v-menu offset-y>
            <template v-slot:activator="{ on }">
              <v-btn flat v-on="on" color="orange black--text">
                <v-icon left>mdi-chevron-down</v-icon>
                <span>รถพยาบาล</span>
              </v-btn>
            </template>
            <v-list>
              <v-list-item v-for="link in ambulancepush" :key="link.text" router :to="link.route">
                <v-list-item-content>
                  <v-list-item-title class="black--text">{{ link.text }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-menu>
          <v-menu offset-y>
            <template v-slot:activator="{ on }">
              <v-btn flat v-on="on" color="orange black--text">
                <v-icon left>mdi-chevron-down</v-icon>
                <span>พยาบาล</span>
              </v-btn>
            </template>
            <v-list>
              <v-list-item v-for="link in nurses" :key="link.text" router :to="link.route">
                <v-list-item-content>
                  <v-list-item-title class="black--text">{{ link.text }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-menu>
          <v-menu offset-y>
            <template v-slot:activator="{ on }">
              <v-btn flat v-on="on" color="orange black--text">
                <v-icon left>mdi-chevron-down</v-icon>
                <span>เช็คสภาพรถ</span>
              </v-btn>
            </template>
            <v-list>
              <v-list-item v-for="link in checks" :key="link.text" router :to="link.route">
                <v-list-item-content>
                  <v-list-item-title class="black--text">{{ link.text }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-menu>
          <v-menu offset-y>
            <template v-slot:activator="{ on }">
              <v-btn flat v-on="on" color="orange black--text">
                <v-icon left>mdi-chevron-down</v-icon>
                <span>อุปกรณ์ในรถ</span>
              </v-btn>
            </template>
            <v-list>
              <v-list-item v-for="link in equipments" :key="link.text" router :to="link.route">
                <v-list-item-content>
                  <v-list-item-title class="black--text">{{ link.text }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-menu>
          <v-menu offset-y>
            <template v-slot:activator="{ on }">
              <v-btn flat v-on="on" color="orange black--text">
                <v-icon left>mdi-chevron-down</v-icon>
                <span>คนขับรถ</span>
              </v-btn>
            </template>
            <v-list>
              <v-list-item v-for="link in drivers" :key="link.text" router :to="link.route">
                <v-list-item-content>
                  <v-list-item-title class="black--text">{{ link.text }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-menu>
          <v-menu offset-y>
            <template v-slot:activator="{ on }">
              <v-btn flat v-on="on" color="orange black--text">
                <v-icon left>mdi-chevron-down</v-icon>
                <span>เหตุฉุกเฉิน</span>
              </v-btn>
            </template>
            <v-list>
              <v-list-item v-for="link in emergencies" :key="link.text" router :to="link.route">
                <v-list-item-content>
                  <v-list-item-title class="black--text">{{ link.text }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-col>
      </v-row>
      <v-row justify="end">
        <v-tab @click="logout" style="color: black; margin-right:5%">LOG OUT</v-tab>
      </v-row>
    </v-tabs>
    <v-container style="height: 200%; margin-right:5%">
      <v-card style="width:100%; margin:auto; background-color:#FFFFFF">
        <v-layout text-center wrap>
          <v-flex mb-4>
            <br />
            <h1 class="display-1 font-weight-bold mb-3">Driver Register Information</h1>
          </v-flex>
        </v-layout>

        <v-row justify="center">
          <v-col cols="12" sm="5">
            <v-text-field
              v-model="search"
              label="ค้นหา"
              prepend-icon="mdi mdi-file-find"
              outlined
              hide-details
            ></v-text-field>
          </v-col>
          <v-col cols="30">
            <v-data-table
              :headers="headers"
              :items="items"
              :items-per-page="5"
              class="elevation-1"
              :search="search"
            ></v-data-table>
            <v-col cols="10">
              <v-btn style="margin-left: 15px;" @click="back">Back</v-btn>
            </v-col>
          </v-col>
        </v-row>
      </v-card>
    </v-container>
  </v-app>
</template>

<script>
import http from "../http-common";
export default {
  name: "ViewDriver",
  data() {
    return {
      search: "",
      headers: [
        { text: "Driver Regis ID", value: "driverregisid", align: "left" },
        { text: "Name", value: "drivername" },
        { text: "Birthday", value: "birthday" },
        { text: "ID Card", value: "idcard" },
        { text: "Gender", value: "genderid.sex" },
        { text: "Address", value: "address" },
        { text: "Telephone", value: "telephone" },
        { text: "Province", value: "provinceid.provincename" },
        { text: "Educational", value: "educationalid.educationallevel" },
        { text: "Position Driver", value: "positionid.positionlevel" }
      ],
      items: [],
      ambulancepush: [
        { text: "เพิ่มข้อมูล", route: "/ambulance" },
        { text: "ลบข้อมูล", route: "/deleteambulance" },
        { text: "ข้อมูล", route: "/infoambulance" }
      ],
      ambulances: [
        { text: "เพิ่มข้อมูล", route: "/ambulance" },
        { text: "ลบข้อมูล", route: "/deleteambulance" },
        { text: "ข้อมูล", route: "/infoambulance" }
      ],
      nurses: [
        { text: "เพิ่มข้อมูล", route: "/nurse" },
        { text: "ลบข้อมูล", route: "/viewnurse" }
      ],
      drivers: [
        { text: "เพิ่มข้อมูล", route: "/driver" },
        { text: "ลบข้อมูล", route: "/viewdriver" }
      ],
      emergencies: [
        { text: "เพิ่มข้อมูล", route: "/emergency" },
        { text: "ลบข้อมูล", route: "/viewemergency" }
      ],
      checks: [
        { text: "เพิ่มข้อมูล", route: "/ambulancecheck" },
        { text: "ข้อมูล", route: "/viewambulancecheck" }
      ],
      equipments: [
        { text: "เพิ่มข้อมูล", route: "/checkEquipment" },
        { text: "ข้อมูล", route: "/ViewCheckEquipment" }
      ]
    };
  },
  methods: {
    /* eslint-disable no-console */
    getDriverRegis() {
      http
        .get("/driverRegis")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    back() {
      this.$router.push("/driver");
    },
    logout() {
      this.$router.push("/");
    },
    pushWelcome() {
      this.$router.push("/welcome");
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getDriverRegis();
  }
};
</script>
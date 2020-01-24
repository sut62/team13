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
    <v-container>
      <v-card style="width:90%; margin:auto; background-color:#FFFFFF">
        <v-form ref="form" v-model="valid">
          <v-layout text-center wrap>
            <v-flex xs12>
              <v-img :src="require('../assets/service_4.png')" class="my-3" contain height="200"></v-img>
            </v-flex>

            <v-container>
              <div>
                <p class="font-weight-black display-1">CHECK AMBULANCE CONDITION</p>
              </div>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="8">
                  <p>Driver</p>

                  <v-select
                    v-model="ambulanceCheck.driverRegisId"
                    :items="driverRegises"
                    item-text="drivername"
                    item-value="driverregisid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- พนักงานขับรถ --"
                    required
                    v-on:keyup.enter="validate"
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="4">
                  <p>ID Ambulance</p>

                  <v-select
                    v-model="ambulanceCheck.ambulanceId"
                    :items="ambulances"
                    item-text="ambulanceid"
                    item-value="ambulanceid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- ไอดีรถพยาบาล --"
                    required
                    v-on:keyup.enter="validate"
                  ></v-select>
                </v-col>

                <v-col cols="12" sm="4">
                  <p>Mile</p>

                  <v-select
                    v-model="ambulanceCheck.mileId"
                    :items="miles"
                    item-text="name"
                    item-value="mileid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- ระยะของรถ --"
                    required
                    v-on:keyup.enter="validate"
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>
            <v-row justify="center">
              <v-col cols="12" sm="8">
                <p>Note</p>
                <v-text-field
                  v-model="ambulanceCheck.note"
                  :rules="[(v) => !!v || 'Item is required']"
                  label="-- หมายเหตุ (ไม่เกิน100หลัก) --"
                  counter
                  required
                  v-on:keyup.enter="validate"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="8">
                  <v-checkbox
                    v-model="ambulanceCheck.battery"
                    label="เช็คแบตเตอรี่"
                    v-on:keyup.enter="validate"
                  ></v-checkbox>
                  <v-checkbox
                    v-model="ambulanceCheck.light"
                    label="เช็คล้อและยาง"
                    v-on:keyup.enter="validate"
                  ></v-checkbox>
                  <v-checkbox
                    v-model="ambulanceCheck.wheel"
                    label="เช็คช่วงล่างและระบบกันสะเทือน"
                    v-on:keyup.enter="validate"
                  ></v-checkbox>
                  <v-checkbox
                    v-model="ambulanceCheck.suspension"
                    label="เช็คระดับน้ำมันเบรค"
                    v-on:keyup.enter="validate"
                  ></v-checkbox>
                  <v-checkbox
                    v-model="ambulanceCheck.brakeFluid"
                    label="เช็คระบบไฟส่องสว่าง"
                    v-on:keyup.enter="validate"
                  ></v-checkbox>
                  <v-checkbox
                    v-model="ambulanceCheck.engineOil"
                    label="เช็คระดับน้ำมันเครื่อง"
                    v-on:keyup.enter="validate"
                  ></v-checkbox>
                  <v-checkbox
                    v-model="ambulanceCheck.radiator"
                    label="เช็คหม้อน้ำและท่อยาง"
                    v-on:keyup.enter="validate"
                  ></v-checkbox>
                  <v-checkbox
                    v-model="ambulanceCheck.toolSet"
                    label="เช็คชุดเครื่องมือประจำรถ"
                    v-on:keyup.enter="validate"
                  ></v-checkbox>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="8">
                  <p>Status</p>

                  <v-select
                    v-model="ambulanceCheck.statusId"
                    :items="statuses"
                    item-text="name"
                    item-value="statusid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- สถานะของรถ --"
                    required
                    v-on:keyup.enter="validate"
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>

            <v-row justify="center">
              <v-col cols="12">
                <v-btn :disabled="!valid" color="success" @click="validate">Submit</v-btn>
                <v-btn style="margin-left: 15px;" color="yellow darken-1" @click="view">Info.</v-btn>
                <v-btn style="margin-left: 15px;" @click="clear">Reset</v-btn>
              </v-col>
            </v-row>
          </v-layout>
        </v-form>
      </v-card>
    </v-container>
  </v-app>
</template>


<script>
import http from "../http-common";
/* eslint-disable */
export default {
  name: "ambulanceCheck",
  data() {
    return {
      ambulanceCheck: {
        driverRegisId: [],
        ambulanceId: [],
        mileId: [],
        statusId: [],
        battery: false,
        light: false,
        wheel: false,
        suspension: false,
        brakeFluid: false,
        engineOil: false,
        radiator: false,
        toolSet: false
      },
      valid: false,
      driverRegises: null,
      ambulances: null,
      miles: null,
      statuses: null,
      note: null,
      ambulancepush: [
        { text: "เพิ่ม", route: "/ambulance" },
        { text: "ลบ", route: "/deleteambulance" },
        { text: "ข้อมูลรถ", route: "/infoambulance" }
      ],
      ambulances: [
        { text: "เพิ่ม", route: "/ambulance" },
        { text: "ลบ", route: "/deleteambulance" },
        { text: "ข้อมูลรถ", route: "/infoambulance" }
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

    // ดึงข้อมูล Ambulance ใส่ combobox
    getAmbulances() {
      http
        .get("/ambulance")
        .then(response => {
          this.ambulances = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล DriverRegis ใส่ combobox
    getDriverRegises() {
      http
        .get("/driverRegis")
        .then(response => {
          this.driverRegises = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Mile ใส่ combobox
    getMiles() {
      http
        .get("/mile")
        .then(response => {
          this.miles = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Status ใส่ combobox
    getStatuses() {
      http
        .get("/status")
        .then(response => {
          this.statuses = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // function เมื่อกดปุ่ม Summit
    validate() {
      http
        .post(
          "/ambulanceCheck/" +
            this.ambulanceCheck.ambulanceId +
            "/" +
            this.ambulanceCheck.driverRegisId +
            "/" +
            this.ambulanceCheck.mileId +
            "/" +
            this.ambulanceCheck.statusId +
            "/" +
            this.ambulanceCheck.battery +
            "/" +
            this.ambulanceCheck.light +
            "/" +
            this.ambulanceCheck.wheel +
            "/" +
            this.ambulanceCheck.suspension +
            "/" +
            this.ambulanceCheck.brakeFluid +
            "/" +
            this.ambulanceCheck.engineOil +
            "/" +
            this.ambulanceCheck.radiator +
            "/" +
            this.ambulanceCheck.toolSet +
            "/" +
            this.ambulanceCheck.note,
          this.ambulanceCheck
        )
        .then(response => {
          console.log(response);
          this.$router.push("/viewambulancecheck");
          const options1 = { title: "Alert", size: "sm" };
          this.$dialogs.alert("บันทึกข้อมูลสำเร็จ", options1);
          this.$refs.form.reset();
        })
        .catch(e => {
          console.log(e);
          const options2 = { title: "Alert", size: "sm" };
          this.$dialogs.alert("บันทึกข้อมูลไม่สำเร็จ", options2);
        });
      this.submitted = true;

      if (this.$refs.form.validate()) {
        this.snackbar = true;
      }
    },
    view() {
      this.$router.push("/viewambulancecheck");
    },
    clear() {
      this.$refs.form.reset();
    },
    logout() {
      this.$router.push("/");
    },
    pushWelcome() {
      this.$router.push("/welcome");
    },
    refreshList() {}
    /* eslint-enable no-console */
  },

  mounted() {
    this.getDriverRegises();
    this.getAmbulances();
    this.getStatuses();
    this.getMiles();
  }
};
</script>

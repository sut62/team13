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
              <v-img :src="require('../assets/Emergency.png')" class="my-3" contain height="150"></v-img>
            </v-flex>

            <v-container>
              <div>
                <p class="font-weight-black display-1">EMERGENCY NOTIFICATION</p>
              </div>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="4">
                  <p>Patient's name</p>
                  <v-text-field
                    v-model="emergency.emergencyPatientName"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="-- ชื่อผู้ป่วย --"
                    required
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="4">
                  <p>Patient's phone number</p>
                  <v-text-field
                    v-model="emergency.emergencyPatientTel"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="-- เบอร์ผู้ป่วย --"
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="8">
                  <p>Patient's location / address</p>
                  <v-text-field
                    v-model="emergency.emergencyPatientAddress"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="-- ที่อยู่/ตำแหน่งผู้ป่วย --"
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="8" sm="4">
                  <p>Sickness</p>
                  <v-select
                    v-model="emergency.symptomId"
                    :items="symptoms"
                    item-text="type"
                    item-value="symptomid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- ประเภทอาการผู้ป่วย  -- "
                    required
                  ></v-select>
                </v-col>
                <v-col cols="12" sm="4">
                  <p>Additional notes</p>
                  <v-text-field
                    v-model="emergency.emergencyNote"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="-- อาการอย่างอื่นๆโปรดระบุ (ถ้าไม่มีกรอก - ) -- "
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="8" sm="4">
                  <p>Emergency Ambulance</p>
                  <v-select
                    v-model="emergency.ambulanceId"
                    :items="ambulances"
                    item-text="licenseplate"
                    item-value="ambulanceid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- ทะเบียนรถพยาบาล --"
                    required
                  ></v-select>
                </v-col>

                <v-col cols="8" sm="4">
                  <p>Ambulance Driver</p>
                  <v-select
                    v-model="emergency.driverregisId"
                    :items="driverRegiss"
                    item-text="drivername"
                    item-value="driverregisid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- ชื่อคนขับรถ --"
                    required
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="8" sm="4">
                  <p>Nurse</p>
                  <v-select
                    v-model="emergency.nurseId"
                    :items="nurses"
                    item-text="nursename"
                    item-value="nurseid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- ชื่อพยาบาลรับเรื่องฉุกเฉิน --"
                    required
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>

            <v-row justify="center">
              <v-col cols="12">
                <v-btn :disabled="!valid" color="success" @click="saveEmergencys">Submit</v-btn>
                <v-btn style="margin-left: 15px;" color="yellow darken-1" @click="viewinfo">Info.</v-btn>
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
  name: "emergency",
  data() {
    return {
      emergency: {
        emergencyPatientName: null,
        emergencyPatientTel: null,
        emergencyPatientAddress: null,
        symptomId: null,
        emergencyNote: null,
        ambulanceId: null,
        driverregisId: null,
        nurseId: null
      },
      symptoms: null,
      ambulances: null,
      driverRegiss: null,
      nurses: null,
      valid: false,
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
    getSymptoms() {
      http
        .get("/symptom")
        .then(response => {
          this.symptoms = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
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

    getDriverRegiss() {
      http
        .get("/driverRegis")
        .then(response => {
          this.driverRegiss = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getNurses() {
      http
        .get("/nurse")
        .then(response => {
          this.nurses = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    saveEmergencys() {
      http
        .post(
          "/emergency/" +
            this.emergency.emergencyPatientName +
            "/" +
            this.emergency.emergencyPatientAddress +
            "/" +
            this.emergency.emergencyPatientTel +
            "/" +
            this.emergency.symptomId +
            "/" +
            this.emergency.emergencyNote +
            "/" +
            this.emergency.ambulanceId +
            "/" +
            this.emergency.driverregisId +
            "/" +
            this.emergency.nurseId,
          this.emergency
        )
        .then(response => {
          console.log(response);
          this.$router.push("/viewEmergency");
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
    viewinfo() {
      this.$router.push("/viewEmergency");
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
  },

  mounted() {
    this.getSymptoms();
    this.getAmbulances();
    this.getDriverRegiss();
    this.getNurses();
  }
};
</script>

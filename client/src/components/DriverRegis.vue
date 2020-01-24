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
              <v-img :src="require('../assets/driver.png')" class="my-3" contain height="100"></v-img>
            </v-flex>

            <v-container>
              <div>
                <p class="font-weight-black display-1">DRIVER REGISTER</p>
              </div>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="4">
                  <p>Name</p>
                  <v-text-field
                    v-model="driverRegis.driverName"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="-- ชื่อ-นามสกุล --"
                    required
                  ></v-text-field>
                </v-col>

                <v-col cols="12" sm="4">
                  <v-menu
                    v-model="menu"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    transition="scale-transition"
                    offset-y
                    min-width="120px"
                  >
                    <template v-slot:activator="{ on }">
                      <p>Birthday</p>
                      <v-text-field
                        style="width:80%"
                        prepend-icon="mdi mdi-calendar"
                        v-model="date"
                        label="วัน-เดือน-ปีเกิด"
                        clearable
                        readonly
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker v-model="date" @input="menu = false"></v-date-picker>
                  </v-menu>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="4">
                  <p>ID Card</p>
                  <v-text-field
                    v-model="driverRegis.driverCard"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="-- เลขที่บัตรประชาชน --"
                    required
                  ></v-text-field>
                </v-col>

                <v-col cols="12" sm="4">
                  <p>Gender</p>
                  <v-select
                    v-model="driverRegis.driverGender"
                    :items="genders"
                    item-text="sex"
                    item-value="genderid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- เพศ --"
                    required
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="4">
                  <p>Address</p>
                  <v-text-field
                    v-model="driverRegis.driverAddress"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="-- ที่อยู่ --"
                    required
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="4">
                  <p>Telephone</p>
                  <v-text-field
                    v-model="driverRegis.driverTelephone"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="-- เบอร์โทรศัพท์มือถือ --"
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="4">
                  <p>Provinces</p>
                  <v-select
                    v-model="driverRegis.provinceId"
                    :items="provinces"
                    item-text="provincename"
                    item-value="provinceid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- จังหวัด --"
                    required
                  ></v-select>
                </v-col>

                <v-col cols="12" sm="4">
                  <p>Educational</p>
                  <v-select
                    v-model="driverRegis.educationalId"
                    :items="educationals"
                    item-text="educationallevel"
                    item-value="educationalid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- วุฒิการศึกษา --"
                    required
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="5">
                  <p>Position Driver</p>
                  <v-select
                    v-model="driverRegis.positionId"
                    :items="positiondrivers"
                    item-text="positionlevel"
                    item-value="positionDriverid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- ตำแหน่งพนักงานขับ --"
                    required
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>
            <v-row justify="center">
              <v-col cols="12">
                <v-btn :disabled="!valid" color="success" @click="saveDriverRegists">Submit</v-btn>
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
  name: "driverRegis",
  data() {
    return {
      date: new Date().toISOString().substr(0, 10),
      menu: false,
      driverRegis: {
        driverName: null,
        driverCard: null,
        driverGender: null,
        driverAddress: null,
        driverTelephone: null,
        provinceId: null,
        positionId: null,
        educationalId: null
      },
      educationals: null,
      positiondrivers: null,
      genders: null,
      provinces: null,
      valid: false,
      ambulancepush: [
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
    getGenders() {
      http
        .get("/gender")
        .then(response => {
          this.genders = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getPosiTions() {
      http
        .get("/positiondriver")
        .then(response => {
          this.positiondrivers = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getProvinces() {
      http
        .get("/province")
        .then(response => {
          this.provinces = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getEducatioNals() {
      http
        .get("/educational")
        .then(response => {
          this.educationals = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveDriverRegists() {
      http
        .post(
          "/driverRegis/" +
            this.date +
            "/" +
            this.driverRegis.driverName +
            "/" +
            this.driverRegis.driverCard +
            "/" +
            this.driverRegis.driverAddress +
            "/" +
            this.driverRegis.driverTelephone +
            "/" +
            this.driverRegis.driverGender +
            "/" +
            this.driverRegis.positionId +
            "/" +
            this.driverRegis.provinceId +
            "/" +
            this.driverRegis.educationalId,
          this.driverRegis
        )
        .then(response => {
          console.log(response);
          this.$router.push("/viewdriver");
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
      this.$router.push("/viewdriver");
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
    this.getGenders();
    this.getPosiTions();
    this.getProvinces();
    this.getEducatioNals();
  }
};
</script>

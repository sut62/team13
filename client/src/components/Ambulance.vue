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
      <v-card style="width:80%; margin:auto; background-color:#FFFFFF">
        <v-form ref="form" v-model="valid">
          <v-layout text-center wrap>
            <v-flex xs12>
              <v-img :src="require('../assets/ambulance.png')" class="my-3" contain height="100"></v-img>
            </v-flex>

            <v-container>
              <div>
                <p class="font-weight-black display-1">ADD AMBULANCE</p>
              </div>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="4">
                  <p>Brands</p>
                  <v-select
                    v-model="ambulance.ambulanceBrandId"
                    :items="ambulanceBrands"
                    item-text="ambrand"
                    item-value="brandid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- ยี่ห้อรถ --"
                    required
                    v-on:keyup.enter="saveAmbulances"
                  ></v-select>
                </v-col>

                <v-col cols="12" sm="4">
                  <p>Model</p>
                  <v-text-field
                    v-model="ambulance.ambulanceModel"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="-- รุ่นรถ --"
                    required
                    v-on:keyup.enter="saveAmbulances"
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="8">
                  <p>Ambulance Type</p>
                  <v-select
                    v-model="ambulance.ambulanceTypeId"
                    :items="ambulanceTypes"
                    item-text="amtype"
                    item-value="typeid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- ประเภทรถพยาบาล --"
                    required
                    v-on:keyup.enter="saveAmbulances"
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="4">
                  <p>Identification Number</p>
                  <v-text-field
                    v-model="ambulance.ambulanceNum"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="-- เลขตัวรถ 17 หลัก(ตามใบคู่มือจดทะเบียน) --"
                    counter
                    required
                    v-on:keyup.enter="saveAmbulances"
                  ></v-text-field>
                </v-col>

                <v-col cols="12" sm="4">
                  <p>Engine Number</p>
                  <v-text-field
                    v-model="ambulance.engineNum"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="-- เลขเครื่องยนตร์ 12 หลัก(ตามใบคู่มือจดทะเบียน) --"
                    counter
                    required
                    v-on:keyup.enter="saveAmbulances"
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <p>License plate</p>
              <v-row justify="center">
                <v-col cols="12" sm="4">
                  <v-text-field
                    v-model="ambulance.licensePlate"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="-- เลขทะเบียนรถ --"
                    required
                    v-on:keyup.enter="saveAmbulances"
                  ></v-text-field>
                </v-col>

                <v-col cols="12" sm="4">
                  <v-select
                    v-model="ambulance.provinceId"
                    :items="provinces"
                    item-text="provincename"
                    item-value="provinceid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- จังหวัด --"
                    required
                    v-on:keyup.enter="saveAmbulances"
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>

            <v-row justify="center">
              <v-col cols="12">
                <v-btn :disabled="!valid" color="success" @click="saveAmbulances">Submit</v-btn>
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
  name: "ambulance",
  data() {
    return {
      ambulance: {
        ambulanceBrandId: null,
        ambulanceTypeId: null,
        provinceId: null,
        ambulanceModel: null,
        ambulanceNum: null,
        licensePlate: null,
        engineNum: null
      },
      ambulanceBrands: null,
      ambulanceTypes: null,
      provinces: null,
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
    getAmbulanceBrands() {
      http
        .get("/ambulancebrand")
        .then(response => {
          this.ambulanceBrands = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getAmbulanceTypes() {
      http
        .get("/ambulancetype")
        .then(response => {
          this.ambulanceTypes = response.data;
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

    saveAmbulances() {
      http
        .post(
          "/ambulance/" +
            this.ambulance.ambulanceBrandId +
            "/" +
            this.ambulance.ambulanceModel +
            "/" +
            this.ambulance.ambulanceTypeId +
            "/" +
            this.ambulance.ambulanceNum +
            "/" +
            this.ambulance.engineNum +
            "/" +
            this.ambulance.licensePlate +
            "/" +
            this.ambulance.provinceId,
          this.ambulance
        )
        .then(response => {
          console.log(response);
          this.$router.push("/infoambulance");
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
      this.$router.push("/infoambulance");
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
    this.getAmbulanceBrands();
    this.getAmbulanceTypes();
    this.getProvinces();
  }
};
</script>


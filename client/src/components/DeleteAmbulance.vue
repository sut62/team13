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
      <v-card style="width:85%; margin:auto; background-color:#FFFFFF">
        <v-layout text-center wrap>
          <v-flex mb-4>
            <br />
            <h1 class="display-1 font-weight-bold mb-3">ลบข้อมูลรถพยาบาล</h1>
          </v-flex>
        </v-layout>

        <v-row justify="center">
          <v-col cols="12" sm="8">
            <v-text-field
              v-model="search"
              label="Search"
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
            <v-col cols="12">
              <v-btn style="margin-left: 15px;" @click="back">Back</v-btn>
            </v-col>
          </v-col>
        </v-row>

        <v-form ref="form">
          <v-row justify="center">
            <h1 class="display-1 font-weight-bold mb-3">
              เลือกรถพยาบาลที่คุต้องการลบ</h1>
          </v-row>
          <v-row justify="center">
            <v-col cols="12" sm="4">
              <v-row justify="center">
                <v-select
                  v-model="ambulance.ambulanceId"
                  :items="items"
                  item-text="ambulancenum"
                  item-value="ambulanceid"
                  :rules="[v => !!v || 'Item is required']"
                  label="--เลขตัวรถ 17 หลัก(ตามใบคู่มือจดทะเบียน)--"
                  required
                ></v-select>
              </v-row>
              <p1 v-if="ambulance.ambulanceId != null">
                ที่ต้องการลบคือ {{ambulance.ambulanceId}}
                กรุณายืนยัน username และ password เพื่อทำการลบ
                <v-text-field
                  label="Username"
                  v-model="user"
                  :rules="[(v) => !!v || 'This field is required']"
                  required
                  counter
                  clearable
                  prepend-icon="mdi-account"
                  v-on:keyup.enter="conFirmEmployee"
                />

                <v-text-field
                  label="Password"
                  v-model="pass"
                  type="password"
                  prepend-icon="mdi-lock"
                  required
                  counter
                  clearable
                  v-on:keyup.enter="conFirmEmployee"
                />

                <p2 v-if="employeeCheck != ''">
                  <v-btn style="margin-left: 15px;" color="red darken-1" @click="deleteAm">ลบข้อมูล</v-btn>
                </p2>
                <p2 v-else-if="employeeCheck == ''">
                  <v-btn
                    style="margin-left: 15px;"
                    color="yellow darken-1"
                    @click="conFirmEmployee"
                  >ยืนยัน</v-btn>
                </p2>
              </p1>
            </v-col>
          </v-row>
        </v-form>
      </v-card>
    </v-container>
  </v-app>
</template>

<script>
import http from "../http-common";

export default {
  name: "deleteAmbulance",
  data() {
    return {
      search: "",
      headers: [
        { text: "Ambulance ID", value: "ambulanceid", align: "left" },
        { text: "ยี่ห้อรถ", value: "brandid.ambrand" },
        { text: "รุ่นรถ", value: "ambulancemodel" },
        { text: "ประเภทรถพยาบาล", value: "typeid.amtype" },
        { text: " เลขตัวรถ 17 หลัก", value: "ambulancenum" },
        { text: "เลขเครื่องยนตร์ 12 หลัก", value: "enginenum" },
        { text: "เลขทะเบียนรถ", value: "licenseplate" },
        { text: "จังหวัด", value: "provinceid.provincename" }
      ],
      items: [],
      ambulance: {
        ambulanceId: null
      },
      user: "",
      pass: "",
      employee: null,
      employeeCheck: false,
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
    /* eslint-disable no-console */
    getAmbulance() {
      http
        .get("/ambulance")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    /* eslint-enable no-console */
    conFirmEmployee() {
      http
        .get("/check/" + this.user + "/" + this.pass)
        .then(response => {
          // eslint-disable-next-line no-console
          console.log(response);
          if (response.data[0] != null) {
            this.employee = response.data.username;
            this.employeeCheck = response.status;
          } else {
            // eslint-disable-next-line no-unused-vars
            const options2 = { title: "Alert", size: "sm" };
            this.$dialogs.alert(
              "Username หรือ Password อาจมีขอผิดพลาดกรุณาลองใหม่อีกครั้ง",
              options2
            );
            this.employeeCheck = false;
          }
        })
        .catch(e => {
          // eslint-disable-next-line no-console
          console.log(e);
        });
      this.submitted = true;
    },
    deleteAm() {
      http
        .post(
          "/ambulancedeleted/" + this.user + "/" + this.ambulance.ambulanceId,
          this.ambulance
        ),
        http.delete("/ambulance/" + this.ambulance.ambulanceId)
        .then(response => {
          // eslint-disable-next-line no-console
          console.log(response);
          const options1 = { title: "Alert", size: "sm" };
          this.$dialogs.alert("ลบสำเร็จ", options1);
        })
        .then(res => {
          // eslint-disable-next-line no-console
          console.log(res)
          this.$router.push("/infoambulance");
        })
        .catch(e => {
          // eslint-disable-next-line no-console
          console.log(e);
          const options2 = { title: "Alert", size: "sm" };
          this.$dialogs.alert("ลบไม่สำเร็จ", options2);
        });
      this.submitted = true;

      if (this.$refs.form.validate()) {
        this.snackbar = true;
      }
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
    back() {
      this.$router.push("/welcome");
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getAmbulance();
  }
};
</script>
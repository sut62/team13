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
            <h1 class="display-1 font-weight-bold mb-3">Nurse Information</h1>
          </v-flex>
        </v-layout>

        <v-row justify="center">
          <v-col cols="12" sm="8">
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
            <v-col cols="12">
              <v-btn style="margin-left: 15px;" @click="back">Back</v-btn>
              
            </v-col>
          </v-col>
        </v-row>
        <v-form ref="form">
          <v-row justify="center">
            <h1 class="display-1 font-weight-bold mb-3">Nurse Delete</h1>
          </v-row>
          <v-row justify="center">
            <v-col cols="12" sm="4">
              <v-row justify="center">
                <v-select
                  v-model="nurse.nurseId"
                  :items="items"
                  item-text="nursename"
                  item-value="nurseid"
                  :rules="[v => !!v || 'Item is required']"
                  label="-- nurse --"
                  required
                ></v-select>
              </v-row>
              <p1 v-if="nurse.nurseId != null">
                ID ที่ต้องการลบ : {{nurse.nurseId}}
                กรุณายืนยัน username และ password เพื่อทำการลบ
                <v-text-field
                  label="Username"
                  v-model="user"
                  :rules="[(v) => !!v || 'This field is required']"
                  required
                  counter
                  clearable
                  prepend-icon="mdi-account"
                  v-on:keyup.enter="confirmEmployee"
                />

                <v-text-field
                  label="Password"
                  v-model="pass"
                  type="password"
                  prepend-icon="mdi-lock"
                  required
                  counter
                  clearable
                  v-on:keyup.enter="confirmEmployee"
                />

                <p2 v-if="employeeCheck != ''">
                  <p>กรุณากดปุ่มลบเพื่อยื่นยันการลบข้อมูล</p>
                  <v-btn style="margin-left: 15px;" color="red darken-1" @click="deleteNurse">ลบ</v-btn>
                  <v-btn style="margin-left: 15px;" color="yellow darken-1" @click="refaresh">Refresh</v-btn>
                </p2>
                <p2 v-else-if="employeeCheck == ''">
                  <v-btn
                    style="margin-left: 15px;"
                    color="green darken-1"
                    @click="confirmEmployee"
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
  name: "viewNurse",
  data() {
    return {
      search: "",
      headers: [
        { text: "Nurse ID", value: "nurseid", align: "left" },
        { text: "Name-Surname", value: "nursename" },
        { text: "Gender", value: "genderid.sex" },
        { text: "Birthday", value: "birthday" },
        { text: "Address", value: "address" },
        { text: "Province", value: "provinceid.provincename" },
        { text: "Educational", value: "educationalid.educationallevel" },
        { text: "Tier", value: "tierid.tiername" },
        { text: "Telephone", value: "telephone" },
        { text: "E-Mail", value: "email" },
        { text: "Save Date", value: "nowdate" }
      ],
      items: [],
      nurse: {
        nurseId: null
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
    getNurse() {
      http
        .get("/nurse")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    confirmEmployee() {
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
              "Username หรือ Password อาจมีขอผิดพลาดกรุณาลองใหม่อีกครั้ง",options2
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
    deleteNurse() {
      http
        .post(
          "/nursedeleted/" + this.user + "/" + this.nurse.nurseId,
          this.nurse
        )
        .then(response => {
          http.delete("/nurse/" + this.nurse.nurseId);
          // eslint-disable-next-line no-console
          console.log(response);
          this.$router.push("viewnurse");
          const options1 = { title: "Alert", size: "sm" };
          this.$dialogs.alert("ลบข้อมูลสำเร็จ", options1);
          
        })
        .catch(e => {
          // eslint-disable-next-line no-console
          console.log(e);
          const options2 = { title: "Alert", size: "sm" };
          this.$dialogs.alert("ลบข้อมูลไม่สำเร็จ", options2);
        });
      this.submitted = true;

      if (this.$refs.form.validate()) {
        this.snackbar = true;
      }
    },
    refaresh(){
      this.$router.push("viewnurse");
      location.reload();
    },
    back() {
      this.$router.push("/nurse");
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
    this.getNurse();
  }
};
</script>
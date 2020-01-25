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
              <v-img :src="require('../assets/medical-15.png')" class="my-3" contain height="200"></v-img>
            </v-flex>

            <v-container>
              <div>
                <p class="font-weight-black display-1">เช็คอุปกรณ์การแพทย์</p>
              </div>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="4">
                  <p>Nurse</p>

                  <v-select
                    v-model="CheckEquipment.nurseId"
                    :items="Nurses"
                    item-text="nursename"
                    item-value="nurseid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- พยาบาลผู้ตรวจ --"
                    required
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="4">
                  <p>ID Ambulance</p>

                  <v-select
                    v-model="CheckEquipment.ambulanceId"
                    :items="ambulances"
                    item-text="licenseplate"
                    item-value="ambulanceid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- ไอดีรถพยาบาล --"
                    required
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>
            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="8">
                  <v-checkbox v-model="CheckEquipment.defibrillator" label="เครื่องกระตุ้นหัวใจ"></v-checkbox>
                  <v-checkbox
                    v-model="CheckEquipment.sphygmomanometer"
                    label="เครื่องวัดความดันโลหิต"
                  ></v-checkbox>
                  <v-checkbox v-model="CheckEquipment.respirator" label="เครื่องช่วยหายใจ"></v-checkbox>
                  <v-checkbox v-model="CheckEquipment.oxygenmachine" label="เครื่องออกซิเจน"></v-checkbox>
                  <v-checkbox v-model="CheckEquipment.suction" label="เครื่องดูดเสมหะ"></v-checkbox>
                  <v-checkbox v-model="CheckEquipment.motionDevice" label="อุปกรณ์เคลื่อนที่"></v-checkbox>
                  <v-checkbox
                    v-model="CheckEquipment.medicalSprayer"
                    label="เครื่องพ่นยาทางการแพทย์"
                  ></v-checkbox>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="4">
                  <p>Status</p>

                  <v-select
                    v-model="CheckEquipment.statusId"
                    :items="statuses"
                    item-text="name"
                    item-value="statusid"
                    :rules="[v => !!v || 'Item is required']"
                    label="-- สถานะของอุปกรณ์ --"
                    required
                  ></v-select>
                </v-col>
              </v-row>
            </v-container>

            <v-container>
                   <v-row justify="center">
                     <v-col cols="12" sm="4">
                        <p>Note</p>
                            <v-text-field
                                        v-model="CheckEquipment.note"
                                        :rules="[(v) => !!v || 'Item is required']"
                                        label="-- หมายเหตุ --"
                                        required
                            ></v-text-field>
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
  name: "CheckEquipment",
  data() {
    return {
      CheckEquipment: {
        ambulanceId: [],
        nurseId: [],
        statusId: [],
        defibrillator: false,
        sphygmomanometer: false,
        respirator: false,
        oxygenmachine: false,
        suction: false,
        motionDevice: false,
        medicalSprayer: false
      },
      valid: false,
      ambulances: null,
      Nurses: null,
      statuses: null,
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
    // ดึงข้อมูล Nurse ใส่ combobox
    getNurses() {
      http
        .get("/nurse")
        .then(response => {
          this.Nurses = response.data;
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
          "/checkEquipment/" +
            this.CheckEquipment.ambulanceId +
            "/" +
            this.CheckEquipment.nurseId +
            "/" +
            this.CheckEquipment.statusId +
            "/" +
            this.CheckEquipment.defibrillator +
            "/" +
            this.CheckEquipment.sphygmomanometer +
            "/" +
            this.CheckEquipment.respirator +
            "/" +
            this.CheckEquipment.oxygenmachine +
            "/" +
            this.CheckEquipment.suction +
            "/" +
            this.CheckEquipment.motionDevice +
            "/" +
            this.CheckEquipment.medicalSprayer,
          this.CheckEquipment
        )
        .then(response => {
          console.log(response);
          this.$router.push("/ViewCheckEquipment");
          alert("บันทึกข้อมูลสำเร็จ");
          this.$refs.form.reset();
        })
        .catch(e => {
          console.log(e);
          alert("บันทึกข้อมูลไม่สำเร็จ");
        });
      this.submitted = true;

      //ถ้ากรอกทั้งหมดถึงจะขึ้นปุ่มให้กด
      if (this.$refs.form.validate()) {
        this.snackbar = true;
      }
    },
    view() {
      this.$router.push("/ViewCheckEquipment");
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
    this.getAmbulances();
    this.getNurses();
    this.getStatuses();
  }
};
</script>
<template>
    <v-app id="landing-page">
        <v-card style="width:100% ; background-color:#FFFFFF" class="orange darken-2">
        <v-row >
            <v-col >
           <h1>&nbsp;&nbsp;AMBULANCE&nbsp;&nbsp;</h1>
           </v-col>
            <v-col >
                    <v-btn @click="pushAmbulance" color="#FFFFFF" style="color:#000000">&nbsp;&nbsp;รถพยาบาล&nbsp;&nbsp;</v-btn>
            </v-col>
             <v-col >
                    <v-btn @click="pushNurse" color="#FFFFFF" style="color:#000000">&nbsp;&nbsp;ข้อมูลพยาบาล&nbsp;&nbsp;</v-btn>
            </v-col>
             <v-col >
                    <v-btn @click="pushCheckAm" color="#FFFFFF" style="color:#000000">&nbsp;&nbsp;เช็คสภาพรถ&nbsp;&nbsp;</v-btn>
            </v-col>
             <v-col  >
                    <v-btn @click="pushEquipment" color="#FFFFFF" style="color:#000000">&nbsp;&nbsp;เช็คอุปกรณ์ในรถ&nbsp;&nbsp;</v-btn>
            </v-col>
             <v-col  >
                    <v-btn @click="pushDriver" color="#FFFFFF" style="color:#000000">&nbsp;&nbsp;ข้อมูลคนขับรถ&nbsp;&nbsp;</v-btn>
            </v-col>
             <v-col  >
                    <v-btn @click="pushEmergency" color="#FFFFFF" style="color:#000000">&nbsp;&nbsp;แจ้งเหตุฉุกเฉิน&nbsp;&nbsp;</v-btn>
            </v-col>
            <v-col  >
             <v-btn @click="logout" color="#FFFFFF" style="color:#000000">
            Log out
            </v-btn>
            </v-col  >
            
        </v-row>
        </v-card >
    <v-container>
        <v-card style="width:90%; margin:auto; background-color:#FFFFFF">
        <v-form
                ref="form"
                v-model="valid"
        >
            <v-layout
                    text-center
                    wrap
            >
                <v-flex xs12>
                    <v-img
                            :src="require('../assets/service_4.png')"
                            class="my-3"
                            contain
                            height="200"
                    ></v-img>
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
                            ></v-select>
                        </v-col>

                        <v-col cols="12" sm="4">
                            <p>Mile</p>

                            <v-select
                                    v-model="ambulanceCheck.mileId"
                                    :items="miles"
                                    item-text="name"
                                    item-value="id"
                                    :rules="[v => !!v || 'Item is required']"
                                    label="-- ระยะของรถ --"
                                    required
                            ></v-select>
                        </v-col>
                    </v-row>
                </v-container>
                <v-container>
                    <v-row justify="center">
                        <v-col cols="12" sm="8">
                            <v-checkbox
                                    v-model="ambulanceCheck.battery"
                                    label="เช็คแบตเตอรี่"
                            ></v-checkbox>
                            <v-checkbox
                                    v-model="ambulanceCheck.light"
                                    label="เช็คล้อและยาง"
                            ></v-checkbox>
                            <v-checkbox
                                    v-model="ambulanceCheck.wheel"
                                    label="เช็คช่วงล่างและระบบกันสะเทือน"
                            ></v-checkbox>
                            <v-checkbox
                                    v-model="ambulanceCheck.suspension"
                                    label="เช็คระดับน้ำมันเบรค"
                            ></v-checkbox>
                            <v-checkbox
                                    v-model="ambulanceCheck.brakeFluid"
                                    label="เช็คระบบไฟส่องสว่าง"
                            ></v-checkbox>
                            <v-checkbox
                                    v-model="ambulanceCheck.engineOil"
                                    label="เช็คระดับน้ำมันเครื่อง"
                            ></v-checkbox>
                            <v-checkbox
                                    v-model="ambulanceCheck.radiator"
                                    label="เช็คหม้อน้ำและท่อยาง"
                            ></v-checkbox>
                            <v-checkbox
                                    v-model="ambulanceCheck.toolSet"
                                    label="เช็คชุดเครื่องมือประจำรถ"
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
                                    item-value="id"
                                    :rules="[v => !!v || 'Item is required']"
                                    label="-- สถานะของรถ --"
                                    required
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
        driverRegises : null,
        ambulances : null,
        miles: null,
        statuses: null
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
            this.ambulanceCheck.toolSet ,
        this.ambulanceCheck
        )
        .then(response => {
          console.log(response);
          this.$router.push("/viewambulancecheck");
          alert('บันทึกข้อมูลสำเร็จ');
          this.$refs.form.reset();
        })
        .catch(e => {
          console.log(e);
          alert('บันทึกข้อมูลไม่สำเร็จ');
        });
      this.submitted = true;

          //ถ้ากรอกทั้งหมดถึงจะขึ้นปุ่มให้กด
          if (this.$refs.form.validate()) {
              this.snackbar = true
          }
    },
      view() {
          this.$router.push("/viewambulancecheck");
      },
    clear() {
      this.$refs.form.reset();
    },
      pushAmbulance() {
          this.$router.push("/ambulance");
      },
      pushCheckAm() {
          this.$router.push("/ambulancecheck");
      },
      pushNurse() {
          this.$router.push("/nurse");
      },
      pushEquipment() {
          this.$router.push("/checkEquipment");
      },
      pushDriver() {
          this.$router.push("/driver");
      },
      pushEmergency() {
          this.$router.push("/emergency");
      },
      logout() {
          this.$router.push("/");
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

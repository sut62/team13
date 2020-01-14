<template>
    <v-app id="landing-page">
        <v-tabs background-color="orange darken-2">
            <h1 style="color: black">&nbsp;&nbsp;AMBULANCE&nbsp;&nbsp;</h1>
            <v-tab @click="pushAmbulance" style="color: black">รถพยาบาล</v-tab>
            <v-tab @click="pushNurse" style="color: black">ข้อมูลพยาบาล</v-tab>
            <v-tab @click="pushCheckAm" style="color: black">เช็คสภาพรถ</v-tab>
            <v-tab @click="pushEquipment" style="color: black">เช็คอุปกรณ์ในรถ</v-tab>
            <v-tab @click="pushDriver" style="color: black">ข้อมูลคนขับรถ</v-tab>
            <v-tab @click="pushEmergency" style="color: black">แจ้งเหตุฉุกเฉิน</v-tab>
            <v-tab @click="logout" style="color: black">LOG OUT</v-tab>
        </v-tabs>
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
                            :src="require('../assets/Emergency.png')"
                            class="my-3"
                            contain
                            height="150"
                    ></v-img>
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
        emergency : {
        emergencyPatientName: null,
        emergencyPatientTel: null,
        emergencyPatientAddress: null,
        symptomId: null,
        emergencyNote : null,
        ambulanceId : null,
        driverregisId : null,
        nurseId : null
      },
        symptoms : null,
        ambulances : null,
        driverRegiss : null,
        nurses : null,
        valid: false
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
          alert('บันทึกข้อมูลสำเร็จ');
          this.$refs.form.reset();
        })
        .catch(e => {
          console.log(e);
          alert('บันทึกข้อมูลไม่สำเร็จ');
        });
      this.submitted = true;

          if (this.$refs.form.validate()) {
              this.snackbar = true
          }
    },
      viewinfo() {
          this.$router.push("/viewEmergency");
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
  },

  mounted() {
      this.getSymptoms();
      this.getAmbulances();
      this.getDriverRegiss();
      this.getNurses();
  }
};
</script>

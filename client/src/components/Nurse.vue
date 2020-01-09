<template>
    <v-app id="landing-page">
        <v-app-bar app class="orange darken-2">
            <v-toolbar-title class="headline text-uppercase">

                <v-row align="center">
                    <v-col cols="12" sm="8" md="4">
                        <span>AMBULANCE&nbsp;</span>
                        <v-btn @click="pushAmbulance" color="#FFFFFF" style="color:#000000">รถพยาบาล</v-btn>
                        <v-btn @click="pushNurse" color="#FFFFFF" style="color:#000000">ข้อมูลพยาบาล</v-btn>
                        <v-btn @click="pushCheckAm" color="#FFFFFF" style="color:#000000">เช็คสภาพรถ</v-btn>
                        <v-btn @click="pushEquipment" color="#FFFFFF" style="color:#000000">เช็คอุปกรณ์ในรถ</v-btn>
                        <v-btn @click="pushDriver" color="#FFFFFF" style="color:#000000">ข้อมูลคนขับรถ</v-btn>
                        <v-btn @click="pushEmergency" color="#FFFFFF" style="color:#000000">แจ้งเหตุฉุกเฉิน</v-btn>
                    </v-col>
                </v-row>
            </v-toolbar-title>
            <v-row align="center" justify="end">
                <v-btn @click="logout">
                    Log out
                </v-btn>
            </v-row>
        </v-app-bar>
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
                            :src="require('../assets/nurseimg.png')"
                            class="my-3"
                            contain
                            height="100"
                    ></v-img>
                </v-flex>

                <v-container>
                    <div>
                        <p class="font-weight-black display-1">Nurse</p>
                    </div>

                </v-container>


                <v-container>
                    <v-row justify="center">
                        <v-col cols="12" sm="4">
                            <p>Name</p>
                            <v-text-field
                                        v-model="nurse.nursename"
                                        :rules="[(v) => !!v || 'Item is required']"
                                        label="-- ชื่อ - นามสกุล --"
                                        required
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="4">
                            <p>Gender</p>
                            <v-select
                                v-model="nurse.genderId"
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
                            <p>Birthday</p>
                            <v-menu
                            v-model="menu"
                            :close-on-content-click="false"
                            :nudge-right="40"
                            transition="scale-transition"
                            offset-y
                            min-width="120px"
                            >
                            <template v-slot:activator="{ on }">
                              <v-text-field style= "width:80%"
                                  prepend-icon= "mdi mdi-calendar"
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
                            <p>Address</p>
                            <v-text-field
                                        v-model="nurse.address"
                                        :rules="[(v) => !!v || 'Item is required']"
                                        label="-- ที่อยู่ --"
                                        required
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="4">
                            <p>Province</p>
                            <v-select
                                    v-model="nurse.provinceId"
                                    :items="provinces"
                                    item-text="provincename"
                                    item-value="provinceid"
                                    :rules="[v => !!v || 'Item is required']"
                                    label="-- จังหวัด --"
                                    required
                            ></v-select>
                        </v-col>
                    </v-row>
                </v-container>


                <v-container>
                    <v-row justify="center">
                        <v-col cols="12" sm="4">
                            <p>Educational</p>
                            <v-select
                                v-model="nurse.educationalId"
                                    :items="educationals"
                                    item-text="educationallevel"
                                    item-value="educationalid"
                                    :rules="[v => !!v || 'Item is required']"
                                    label="-- วุฒิการศึกษา --"
                                    required
                            ></v-select>
                        </v-col>
                        
                        <v-col cols="12" sm="4">
                            <p>Tier</p>
                            <v-select
                                v-model="nurse.tierId"
                                    :items="tiers"
                                    item-text="tiername"
                                    item-value="tierid"
                                    :rules="[v => !!v || 'Item is required']"
                                    label="-- ขั้นข้าราชการ --"
                                    required
                            ></v-select>
                        </v-col>
                    </v-row>
                </v-container>

                <v-container>
                    <v-row justify="center">
                        <v-col cols="12" sm="4">
                            <p>Telephone</p>
                            <v-text-field
                                        v-model="nurse.telephone"
                                        :rules="[(v) => !!v || 'Item is required']"
                                        label="-- เบอร์โทรศัพท์ --"
                                        required
                            ></v-text-field>
                        </v-col>

                        <v-col cols="12" sm="4">
                            <p>E-Mail</p>
                            <v-text-field
                                        v-model="nurse.email"
                                        :rules="[(v) => !!v || 'Item is required']"
                                        label="-- อีเมล --"
                                        required
                            ></v-text-field>
                        </v-col>
                    </v-row>
                </v-container>

                <v-row justify="center">
                    <v-col cols="12">
                        <v-btn :disabled="!valid" color="success" @click="saveNurses">Submit</v-btn>
                        <v-btn style="margin-left: 15px;" color="yellow darken-1" @click="viewN">Info.</v-btn>
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
  name: "nurse",
  data() {
    return {
        date : new Date().toISOString().substr(0, 10),
        menu : false,
        nurse : {
        genderId: null,
        provinceId: null,
        educationalId: null,
        tierId: null,
        nursename: null,
        address: null,
        telephone: null,
        email: null
      },
        genders : null,
        provinces : null,
        educationals : null,
        tiers : null,
        valid: false
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
    
    getEducationals() {
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

    getTiers() {
      http
        .get("/tier")
        .then(response => {
            this.tiers = response.data;
            console.log(response.data);
        })
        .catch(e => {
            console.log(e);
        });

    },

      saveNurses() {
      http
        .post(
          "/nurse/" +
          this.nurse.nursename +
          "/" +
          this.nurse.genderId +
          "/" +
          this.date +
          "/" +
          this.nurse.address +
          "/" +
          this.nurse.provinceId +
          "/" +
          this.nurse.educationalId +
          "/" +
          this.nurse.tierId +
          "/" +
          this.nurse.telephone +
          "/" +
          this.nurse.email,
        this.nurse
        )
        .then(response => {
          console.log(response);
          this.$router.push("/viewnurse");
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
      viewN() {
          this.$router.push("/viewnurse");
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
      this.getGenders();
      this.getProvinces();
      this.getEducationals();
      this.getTiers();
  }
};
</script>

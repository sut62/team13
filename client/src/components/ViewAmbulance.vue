<template>
  <v-app id="landing-page">
  <v-container>
    <v-card style="width:90%; margin:auto; background-color:#FFFFFF">

    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-1 font-weight-bold mb-3">Ambulance Information</h1>
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
    <h1 class="display-1 font-weight-bold mb-3">Ambulance Delete</h1>
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
                                        label="-- ambulance --"
                                        required
                                ></v-select>
                              </v-row >
                               <p1 v-if ="ambulance.ambulanceId != null">
                                   ID ที่ต้องการลบ : {{ambulance.ambulanceId}}
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
                
                <p2 v-if ="employeeCheck != ''">
                  <v-btn style="margin-left: 15px;" color="red darken-1" @click="deleteAm">Delete</v-btn>
                </p2>
                <p2 v-else-if ="employeeCheck == ''">
                  <v-btn style="margin-left: 15px;" color="yellow darken-1" @click="conFirmEmployee">ยืนยัน</v-btn>
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
  name: "viewAmbulance",
  data() {
    return {
      search: "",
      headers: [
        { text: "Ambulance ID", value: "ambulanceid", align: "left" },
        { text: "Brands", value: "brandid.ambrand" },
        { text: "Models", value: "ambulancemodel" },
        { text: "Type", value: "typeid.amtype" },
        { text: "Identification Number", value: "ambulancenum" },
        { text: "Engine Number", value: "enginenum" },
        { text: "License plate", value: "licenseplate" },
        { text: "Province", value: "provinceid.provincename" }
      ],
      items: [],
      ambulance : {
        ambulanceId : null
      },
      user: "",
      pass: "",
      employee: null,
      employeeCheck: false,
      valid: false,
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
          }
           else {
            // eslint-disable-next-line no-unused-vars
            const options2 = {title: 'Alert', size: 'sm'}
            this.$dialogs.alert("Username หรือ Password อาจมีขอผิดพลาดกรุณาลองใหม่อีกครั้ง", options2);
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
          "/ambulancedeleted/" +
            this.user +
            "/" +
            this.ambulance.ambulanceId,
        this.ambulance
        )
        .then(response => {
          http.delete("/ambulance/" + this.ambulance.ambulanceId)
          // eslint-disable-next-line no-console
          console.log(response);
          this.$emit("refreshData");
          const options1 = {title: 'Alert', size: 'sm'}
          this.$dialogs.alert('ลบสำเร็จ',options1);
          this.$refs.form.reset();
          this.getAmbulance();
        })
        .catch(e => {
          // eslint-disable-next-line no-console
          console.log(e);
          const options2 = {title: 'Alert', size: 'sm'}
          this.$dialogs.alert('ลบไม่สำเร็จ',options2);
        });
      this.submitted = true;

          if (this.$refs.form.validate()) {
              this.snackbar = true
          }
    },
    clear() {
      this.$refs.form.reset();
    },
    back() {
      this.$router.push("/ambulance");
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getAmbulance();
  }
};
</script>
<template>
  <v-app id="landing-page">
  <v-container>
    <v-card style="width:90%; margin:auto; background-color:#FFFFFF">

    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-1 font-weight-bold mb-3">Emergency Information</h1>
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
    </v-card>
  </v-container>
  </v-app>
</template>

<script>
import http from "../http-common";
export default {
  name: "ViewEmergency",
  data() {
    return {
      search: "",
      headers: [
        { text: "ID รับเรื่องฉุกเฉิน", value: "emergencyId", align: "center" },
        { text: "วันที่รับเรื่อง", value: "emDate" },
        { text: "ชื่อผู้ป่วย", value: "patientName" },
        { text: "เบอร์โทรศัพท์", value: "patientTel" },
        { text: "ที่อยู่/ตำแหน่งของผู้ป่วย", value: "patientAddress" },
        { text: "อาการผู้ป่วย", value: "symptom.type" },
        { text: "หมายเหตุ", value: "note" },
        { text: "รถฉุกเฉิน", value: "ambulance.licenseplate" },
        { text: "คนขับรถ", value: "driverRegis.drivername" },
        { text: "พยาบาลรับเรื่อง", value: "nurse.nursename" }
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getAmbulance() {
      http
        .get("/emergency")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    back() {
      this.$router.push("/emergency");
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getAmbulance();
  }
};
</script>
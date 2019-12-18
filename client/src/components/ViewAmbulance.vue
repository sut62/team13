<template>
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
    </v-card>
  </v-container>
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
      items: []
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
    back() {
      this.$router.push("/");
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getAmbulance();
  }
};
</script>
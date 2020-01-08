<template>
  <v-container>
    <v-card style="width:90%; margin:auto; background-color:#FFFFFF">

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
    </v-card>
  </v-container>
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
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getAmbulance() {
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
    back() {
      this.$router.push("/nurse");
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getAmbulance();
  }
};
</script>
<template>
    <v-app id="landing-page">
    <v-container>
        <v-card style="width:90%; margin:auto; background-color:#FFFFFF">
            <v-layout text-center wrap>
                <v-flex mb-4>
                    <br />
                    <h1 class="display-1 font-weight-bold mb-3">CHECK AMBULANCE CONDITION INFORMATION.</h1>
                </v-flex>
            </v-layout>
        <v-row justify="center">
            <v-col cols="30">
                <v-card-title>
                    <v-text-field
                            v-model="search"
                            label="ค้นหา"
                            prepend-icon="mdi mdi-file-find"
                            outlined
                            hide-details
                    ></v-text-field>
                </v-card-title>

                <v-container>
                </v-container>
                <v-container>
                </v-container>

                <v-data-table
                        :headers="headers"
                        :items="items"
                        :items-per-page="5"
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
        name: "viewAmCheck",
        data() {
            return {
                search: '',
                headers: [
                    { text: "ID ใบเช็ครถ", value: "id"},
                    { text: "ID รถพยาบาล", value: "ambulance.ambulanceid"},
                    { text: "เลขทะเบียนรถ", value: "ambulance.licenseplate"},
                    { text: "จังหวัด", value: "ambulance.provinceid.provincename"},
                    { text: "สภาพรถ", value: "status.name"  },
                    { text: "วันที่เช็คสภาพรถ", value: "checkDate"},
                ],
                items: []
            };
        },
        methods: {
            /* eslint-disable no-console */

            // ดึงข้อมูล AmbulanceCheck ใส่ combobox
            getAmbulanceChecks() {
                http
                    .get("/ambulanceCheck")
                    .then(response => {
                        this.items = response.data;
                        console.log(this.items);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            back() {
                this.$router.push("/ambulancecheck");
            }
            /* eslint-disable no-console */
        },
        mounted() {
            this.getAmbulanceChecks();
        }
    };
</script>
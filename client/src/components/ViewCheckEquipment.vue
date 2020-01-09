<template>
    <v-app id="landing-page">
    <v-container>
        <v-layout text-center wrap>
                <br />
                <h1 class="display-1 font-weight-bold mb-3">ข้อมูลการตรวจเช็คอุปกรณ์</h1>
        </v-layout>
        <v-row justify="center" class="amber">
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
    </v-container>
    </v-app>
</template>


<script>
    import http from "../http-common";
    export default {
        name: "viewCheckEquipment",
        data() {
            return {
                search: '',
                headers: [
                    { text: "ID ใบเช็ครถ", value: "ambulance.ambulanceid"},
                    { text: "เลขทะเบียนรถ", value: "ambulance.licenseplate"},
                    { text: "สภาพอุปกรณ์", value: "status.name"  },
                    { text: "ผู้ตรวจเช็คอุปกรณ์", value: "nurse.nursename"},
                    { text: "วันที่เช็คอุปกรณ์", value: "checkDate"},
                ],
                items: []
            };
        },
        methods: {
            /* eslint-disable no-console */

            // ดึงข้อมูล Advertisement ใส่ combobox
            getcheckEquipments() {
                http
                    .get("/checkEquipment")
                    .then(response => {
                        this.items = response.data;
                        console.log(this.items);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            back() {
                this.$router.push("/checkEquipment");
            }
            /* eslint-disable no-console */
        },
        mounted() {
            this.getcheckEquipments();
        }
    };
</script>
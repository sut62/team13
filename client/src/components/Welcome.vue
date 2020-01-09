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
        <h1 class="display-2 font-weight-bold mb-3 ">&nbsp;&nbsp;Welcome !!</h1>
    </v-app>
</template>

<script>

    export default {
        name: "login",
        data() {
            return {
                user: "",
                pass: "",
                uername:"Pink"
            };
        },
        methods: {
            /* eslint-enable no-console */
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
        mounted() {}
    };
</script>
<style>
    #landing-page {
        background-image: url("../assets/d370e515ecba80b8cc994e849fd0c44d.gif");
        background-position: center;
        background-repeat: round;
    }
</style>



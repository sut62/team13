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



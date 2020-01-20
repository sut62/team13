<template>
    <v-app id="landing-page">
        <v-tabs background-color="orange darken-2" style="margin-buttom:-100px">
            <v-tab @click="pushWelcome" style="color: black; width:13%;">&nbsp;&nbsp;AMBULANCE&nbsp;&nbsp;</v-tab>
            <v-tab @click="pushAmbulance" style="color: black">รถพยาบาล</v-tab>
            <v-tab @click="pushNurse" style="color: black">ข้อมูลพยาบาล</v-tab>
            <v-tab @click="pushCheckAm" style="color: black">เช็คสภาพรถ</v-tab>
            <v-tab @click="pushEquipment" style="color: black">เช็คอุปกรณ์ในรถ</v-tab>
            <v-tab @click="pushDriver" style="color: black">ข้อมูลคนขับรถ</v-tab>
            <v-tab @click="pushEmergency" style="color: black">แจ้งเหตุฉุกเฉิน</v-tab>
            <v-tab @click="logout" style="color: black">LOG OUT</v-tab>
            
        </v-tabs>
        <v-card style="width:90%; margin:auto; background-color:#FFFFFF">
  <h1 class="font-weight">SUT HOSPITAL:</h1>
  <iframe style="border: 3px solid #000;" frameborder="0" width="100%;" height="800px" src="http://smc.sut.ac.th"></iframe>
  </v-card>
        <v-container>
        
        </v-container>
    </v-app>
</template>

<script>

    export default {
        name: "welcome",
        data() {
            return {
                user: "",
                pass: "",
                uername:""
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
            pushWelcome() {
          this.$router.push("/welcome");
            },
            refreshList() {}
            /* eslint-enable no-console */
        },
        mounted() {}
    };
</script>



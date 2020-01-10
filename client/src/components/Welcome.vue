<template>
    <v-app id="landing-page">
    
        <v-card style="width:100% ; background-color:#FFFFFF" class="orange darken-2">
        <v-row >
            <v-col >
           <h1>&nbsp;&nbsp;AMBULANCE&nbsp;&nbsp;</h1>
           </v-col>
            <v-col >
                    <v-btn @click="pushAmbulance" color="#FFFFFF" style="color:#000000">&nbsp;&nbsp;รถพยาบาล&nbsp;&nbsp;</v-btn>
            </v-col>
             <v-col >
                    <v-btn @click="pushNurse" color="#FFFFFF" style="color:#000000">&nbsp;&nbsp;ข้อมูลพยาบาล&nbsp;&nbsp;</v-btn>
            </v-col>
             <v-col >
                    <v-btn @click="pushCheckAm" color="#FFFFFF" style="color:#000000">&nbsp;&nbsp;เช็คสภาพรถ&nbsp;&nbsp;</v-btn>
            </v-col>
             <v-col  >
                    <v-btn @click="pushEquipment" color="#FFFFFF" style="color:#000000">&nbsp;&nbsp;เช็คอุปกรณ์ในรถ&nbsp;&nbsp;</v-btn>
            </v-col>
             <v-col  >
                    <v-btn @click="pushDriver" color="#FFFFFF" style="color:#000000">&nbsp;&nbsp;ข้อมูลคนขับรถ&nbsp;&nbsp;</v-btn>
            </v-col>
             <v-col  >
                    <v-btn @click="pushEmergency" color="#FFFFFF" style="color:#000000">&nbsp;&nbsp;แจ้งเหตุฉุกเฉิน&nbsp;&nbsp;</v-btn>
            </v-col>
            <v-col  >
             <v-btn @click="logout" color="#FFFFFF" style="color:#000000">
            Log out
            </v-btn>
            </v-col  >
            
        </v-row>
        </v-card >
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



<template>
  <v-app>
    <v-app-bar app class="orange darken-2">
      <v-toolbar-title class="headline text-uppercase">
        <span>AMBULANCE</span>
        <v-btn-toggle group>
          <v-btn @click="pushAmbulance" color="#FFFFFF" style="color:#000000">รถพยาบาล</v-btn>
          <v-btn @click="pushNurse" color="#FFFFFF" style="color:#000000">ข้อมูลพยาบาล</v-btn>
          <v-btn @click="pushCheckAm" color="#FFFFFF" style="color:#000000">เช็คสภาพรถ</v-btn>
          <v-btn @click="pushEquipment" color="#FFFFFF" style="color:#000000">เช็คอุปกรณ์ในรถ</v-btn>
          <v-btn @click="pushDriver" color="#FFFFFF" style="color:#000000">ข้อมูลคนขับรถ</v-btn>
          <v-btn @click="pushEmergency" color="#FFFFFF" style="color:#000000">แจ้งเหตุฉุกเฉิน</v-btn>
        </v-btn-toggle>
      </v-toolbar-title>
      <v-spacer></v-spacer>
    </v-app-bar>
    <v-content>
      <router-view />
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: "App",
  data() {
    return {};
  },
  methods: {
    login() {
      this.$router.push("/");
    },
    pushAmbulance() {
      this.$router.push("/ambulance");
    },
    pushCheckAm() {
      this.$router.push("/checkambulance");
    },
    pushNurse() {
      this.$router.push("/nurse");
    },
    pushEquipment() {
      this.$router.push("/equipment");
    },
    pushDriver() {
      this.$router.push("/driver");
    },
    pushEmergency() {
      this.$router.push("/emergency");
    }
  }
};
</script>

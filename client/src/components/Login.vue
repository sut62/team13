<template>
  <v-app id="landing-page">
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="4">
        <v-card
          tyle="width:100%;
                                margin:auto;
                                background-color:#FFFFFF"
          class="mx-auto pa-6"
          height="600"
          :elevation="25 - 1"
        >
          <v-card
            style="width:100%;
                                margin:auto;
                                background-color:#FFFFFF"
            class="mx-auto pa-6"
            height="550"
            :elevation="25 - 1"
          >
            <v-toolbar color="cyan lighten-2" dark flat height="150">
              <v-toolbar-title class="font-weight-black display-1">WELCOME</v-toolbar-title>
              <v-spacer />
              <v-img :aspect-ratio="20/9" src="../assets/giphy.gif" height="100"></v-img>
              <v-tooltip right>
                <span>Codepen</span>
              </v-tooltip>
            </v-toolbar>
            <v-container />
            <v-container />
            <v-card-text>
              <v-form>
                <v-text-field
                  label="Username"
                  v-model="user"
                  :rules="[(v) => !!v || 'This field is required']"
                  required
                  clearable
                  prepend-icon="mdi-account"
                />

                <v-text-field
                  label="Password"
                  v-model="pass"
                  type="password"
                  prepend-icon="mdi-lock"
                  required
                  counter
                  clearable
                />
              </v-form>
              <v-container />
              <v-container />
              <v-container />
            </v-card-text>
            <v-card-actions>
              <v-spacer />
              <v-btn @click="Login" x-large color="orange darken-2" :elevation="3 - 1">Login</v-btn>
            </v-card-actions>
          </v-card>
        </v-card>
      </v-col>
    </v-row>
  </v-app>
</template>

<script>
import http from "../http-common";

export default {
  name: "login",
  data() {
    return {
      user: "",
      pass: ""
    };
  },
  methods: {
    /* eslint-enable no-console */
    Login() {
      http
        .get("/check/" + this.user + "/" + this.pass)
        .then(response => {
          // eslint-disable-next-line no-console
          console.log(response);
          if (response.data[0] != null) {
            this.$router.push("/welcome");
            http.post("/login/" + this.user);
            alert("เข้าสู่ระบบสำเร็จ");
          } else {
            alert("Username หรือ Password อาจจะผิดกรุณาเข้าสู่ระบบใหม่อีกครั้ง");
          }
        })
        .catch(e => {
          // eslint-disable-next-line no-console
          console.log(e);
        });
      this.submitted = true;
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


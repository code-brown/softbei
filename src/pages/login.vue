<template>
  <v-app>
    <v-content class="content">
      <v-container fluid fill-height class="container">
        <v-row justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card>
              <v-toolbar color="primary" flat>
                <v-toolbar-title>校园智能实训系统</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-form @submit.prevent="login">
                  <v-text-field v-model="username" label="用户名" outlined required></v-text-field>
                  <v-text-field v-model="password" label="密码" type="password" outlined required></v-text-field>
                  <v-btn type="submit" color="light-blue lighten-1" block>登录</v-btn>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary">注册</v-btn>
                <v-btn text color="light-blue lighten-1" @click="redirectToTeacherPage">教师登录</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      username: '',
      password: '',
      usertype: ''
    };
  },
  methods: {
    login() {
      const username = this.username;
      const password = this.password;
      const usertype = "0";

      if (!username || !password) {
        alert("用户名和密码不能为空");
        return;
      }

      axios.post(' http://36.138.114.105:32637/api/users/login', { 
        username: username,
        password: password,
        usertype: usertype 
      })
      .then(response => {
        console.log(response.data);
        alert("登录成功");
        this.$router.push('/stumain/practice');
      })
      .catch(error => {
        console.error(error);
        if (error.response && error.response.status === 401) {
          alert("用户名或密码错误");
        } else {
          alert("登录失败，请稍后再试");
        }
      });
    },
    redirectToTeacherPage() {
      this.$router.push('/tealogin');

    }
  }
};
</script>

<style scoped lang="scss">
.v-application {
  display: flex;
  background-image: url('/src/assets/school.jpg'); /* 修改为你的图片路径 */
  background-size: cover;
}
</style>
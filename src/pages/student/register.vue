<template>
    <v-app>
      <v-content class="content">
        <v-container fluid fill-height class="container">
          <v-row justify="center">
            <v-col cols="12" sm="8" md="4">
              <v-card>
                <v-toolbar color="primary" flat>
                  <v-toolbar-title>校园智能实训系统 - 注册</v-toolbar-title>
                </v-toolbar>
                <v-card-text>
                  <v-form @submit.prevent="register">
                    <v-text-field v-model="username" label="用户名" outlined required></v-text-field>
                    <v-text-field v-model="password" label="密码" type="password" outlined required></v-text-field>
                    <v-text-field v-model="confirmPassword" label="确认密码" type="password" outlined required></v-text-field>
                    <v-combobox
  label="请选择用户类型"
  :items="['学生', '教师']"
></v-combobox>
                    <v-btn type="submit" color="light-blue lighten-1" block>注册</v-btn>
                    
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="primary" @click="redirectToLoginPage">登录</v-btn>
                
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
        confirmPassword: '',
        email: ''
      };
    },
    methods: {
      register() {
        const { username, password, confirmPassword, email } = this;
  
        if (!username || !password || !confirmPassword || !email) {
          alert("所有字段都必须填写");
          return;
        }
  
        if (password !== confirmPassword) {
          alert("密码和确认密码不匹配");
          return;
        }
  
        axios.post('http://36.138.114.105:32637/api/users/register', { 
          username: username,
          password: password,
          email: email,
          usertype: "0"
        })
        .then(response => {
          console.log(response.data);
          alert("注册成功");
          this.$router.push('/login');
        })
        .catch(error => {
          console.error(error);
          alert("注册失败，请稍后再试");
        });
      },
      redirectToLoginPage() {
        this.$router.push('/');
      },
    
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
  
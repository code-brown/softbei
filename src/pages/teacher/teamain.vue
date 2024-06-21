<template>
  <v-app>
    
    <v-app-bar app color="#1976D2" dense dark>
      <span class="font-weight-bold">校园智能实训系统-教师端</span>
      <v-spacer></v-spacer>
      
    </v-app-bar>

    <v-main>
      <v-container fluid>
        <v-row>
          
          <v-col cols="1">
            <v-navigation-drawer
              image="https://cdn.vuetifyjs.com/images/backgrounds/bg-2.jpg"
              theme="dark"
              permanent
            >
              <v-list nav class="sideNav">
                <v-list-item title="实训系统"></v-list-item>
                <v-list-item prepend-icon="mdi-email" title="发布实训" link :to="{ name: 'prcPublic' }"></v-list-item>
                <v-list-item prepend-icon="mdi-clock-start" title="实训管理" link :to="{ name: 'prcManage' }"></v-list-item>
                <v-list-item prepend-icon="mdi-account-supervisor-circle" title="学生管理" link :to="{ name: 'stuManage' }"></v-list-item>
              </v-list>
            </v-navigation-drawer>
          </v-col>

          <!-- 主内容区 -->
          <v-col>
            <router-view></router-view>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import axios from 'axios';

export default {
  name: 'TeacherDashboard',
  data() {
    return {
      teacher: '教师姓名'
    }
  },
  methods: {
    fetchTasks() {
      
    }
  },
  mounted() {
    this.fetchTasks();
  }
}
</script>

<style lang="scss" scoped>
.font-weight-bold {
  font-size: 26px;
  margin-left: 1.25rem;
}

.avatar {
  margin-right: 1.25rem;
}

.name {
  margin-right: .3525rem;
  margin-top: 0.32rem;
}

.v-list-item-title {
  font-size: 1.5rem;
  line-height: 3.5;
}

.element.style {
  font-size: x-large;
}

.v-list .v-list-item--nav:not(:only-child) {
  margin-bottom: 30px;
}
</style>

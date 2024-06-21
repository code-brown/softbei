<template>
    <v-container>
      <v-card class="report-card" elevation="5">
        <v-card-title class="text-center">学生画像</v-card-title>
        <v-divider></v-divider>
        <v-card-text>
          <v-btn color="primary" @click="fetchStudentProfile">刷新学生画像</v-btn>
          <v-data-table :headers="headers" :items="studentProfile">
            <template v-slot:item="{ item }">
              <tr>
                <td>{{ item.name }}</td>
                <td>{{ item.className }}</td>
                <td>{{ item.subjectGrade }}</td>
                <td>{{ item.homeworkCompletion }}</td>
              </tr>
            </template>
          </v-data-table>
        </v-card-text>
      </v-card>
    </v-container>
  </template>
  
  <script>
  import { defineComponent, ref } from 'vue';
  import axios from 'axios';
  
  export default defineComponent({
    setup() {
      const studentProfile = ref([]);
      const headers = [
        { text: '姓名', value: 'name' },
        { text: '班级', value: 'className' },
        { text: '学科成绩', value: 'subjectGrade' },
        { text: '作业完成情况', value: 'homeworkCompletion' }
      ];
  
      const fetchStudentProfile = async () => {
        try {
          const response = await axios.get(' http:// 36.138.114.105:32637 http:// 36.138.114.105:32637/api/studentInfo');
          studentProfile.value = response.data;
        } catch (error) {
          console.error('Error fetching student profile:', error);
        }
      };
  
      return {
        studentProfile,
        headers,
        fetchStudentProfile
      };
    },
    mounted() {
      this.fetchStudentProfile();
    }
  });
  </script>
  
  <style scoped>
  .report-card {
    margin-top: 50px;
  }
  </style>
  
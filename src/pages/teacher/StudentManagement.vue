<template>
  <v-container>
    <v-card class="report-card" elevation="5">
      <v-card-title class="text-center">学生管理</v-card-title>
      <v-divider></v-divider>
      <v-card-text class="center">
        <v-data-table :headers="headers" :items="students" class="table">
          <template v-slot:item.actions="{ item }">
            <v-icon small @click="deleteStudent(item)">mdi-delete</v-icon>
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
    const students = ref([]);
    const headers = [
      { text: '姓名', value: 'username' },
      { text: '学号', value: 'id' },
    ];

    const fetchStudents = async () => {
      try {
        const response = await axios.get('http://36.138.114.105:32637/api/users');
        students.value = response.data;
      } catch (error) {
        console.error('Error fetching students:', error);
      }
    };

    const deleteStudent = async (student) => {
      try {
        await axios.delete(`http://36.138.114.105:32637/api/users/${student.id}`);
        fetchStudents();
      } catch (error) {
        console.error('Error deleting student:', error);
      }
    };

    return {
      students,
      headers,
      fetchStudents,
      deleteStudent
    };
  },
  mounted() {
    this.fetchStudents();
  }
});
</script>

<style scoped>
.report-card {
  margin-top: 50px;
}
.table{
  margin: auto;
  align-items: center;
  max-width: fit-content
}
.center{
  
}
</style>

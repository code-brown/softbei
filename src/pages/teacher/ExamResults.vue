<template>
  <v-container>
    <v-card class="report-card" elevation="5">
      <v-card-title class="text-center">考试结果</v-card-title>
      <v-divider></v-divider>
      <v-card-text>
        <v-btn color="primary" @click="fetchExamResults">刷新考试结果</v-btn>
        <v-data-table :headers="headers" :items="examResults">
          <template v-slot:item="{ item }">
            <tr>
              <td>{{ item.examName }}</td>
              <td>{{ item.subject }}</td>
              <td>{{ item.score }}</td>
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
    const examResults = ref([]);
    const headers = [
      { text: '考试名称', value: 'examName' },
      { text: '科目', value: 'subject' },
      { text: '分数', value: 'score' }
    ];

    const fetchExamResults = async () => {
      try {
        const response = await axios.get(' http:// 36.138.114.105:32637 http:// 36.138.114.105:32637/api/studentInfo');
        examResults.value = response.data;
      } catch (error) {
        console.error('Error fetching exam results:', error);
      }
    };

    return {
      examResults,
      headers,
      fetchExamResults
    };
  },
  mounted() {
    this.fetchExamResults();
  }
});
</script>

<style scoped>
.report-card {
  margin-top: 50px;
}
</style>

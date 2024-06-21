<template>
  <v-container class="main-container">
    <v-row justify="center">
      <v-col >
        <v-card class="report-card" elevation="10">
          <v-card-title class="title">我的考试</v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <!-- 考试列表 -->
            <v-row>
              <v-col cols="12">
                <v-list>
                  <v-list-item
                    v-for="(exam, index) in filteredExams"
                    :key="index"
                    @click="selectExam(exam)"
                    class="exam-list-item"
                  >
                    <v-list-item-content>
                      <v-list-item-title>{{ exam.title }}</v-list-item-title>
                      <v-list-item-subtitle>{{ formatDateTime(exam.startTime) }}</v-list-item-subtitle>
                    </v-list-item-content>
                    <v-list-item-icon>
                      <v-icon :color="calculateDotColor(exam)">mdi-circle</v-icon>
                    </v-list-item-icon>
                  </v-list-item>
                </v-list>
              </v-col>
            </v-row>
            <v-divider class="my-4"></v-divider>
            <!-- 选定的考试详情 -->
            <v-row>
              <v-col cols="12">
                <v-card v-if="selectedExam" class="exam-details-card" color="#64b5f6">
                  <v-card-title class="title">{{ selectedExam.title }}</v-card-title>
                  <v-card-text class="exam-info">
                    <p><strong >考试时间：</strong>{{ formatDateTime(selectedExam.startTime) }}</p>
                    <p><strong >考试描述：</strong>{{ selectedExam.title }}</p>
                    <p><strong>结束时间：</strong>{{ formatDateTime(selectedExam.endTime) }}</p>
                  </v-card-text>
                  <v-card-actions class="exam-actions">
                    <v-btn @click="startExam(selectedExam)" color="primary" size="large" class="btn">开始考试</v-btn>
                  </v-card-actions>
                </v-card>
                <v-alert v-else outlined color="info">请在上方选择考试</v-alert>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      exams: [],
      selectedExam: null
    };
  },
  computed: {
    filteredExams() {
      return this.exams.filter(exam => exam.type === 2); // 仅显示类型为“考试”的实训
    }
  },
  created() {
    this.fetchExams();
  },
  methods: {
    fetchExams() {
      axios.get('http://36.138.114.105:32637/api/training-tasks')
        .then(response => {
          this.exams = response.data;
        })
        .catch(error => {
          console.error('Error fetching exams:', error);
        });
    },
    selectExam(exam) {
      this.selectedExam = exam;
    },
    startExam(exam) {
      console.log('开始考试', exam);
    },
    calculateDotColor(exam) {
      const now = new Date();
      const endDate = new Date(exam.endTime);
      const timeDifference = endDate - now;
      const twoDaysInMilliseconds = 2 * 24 * 60 * 60 * 1000;

      if (timeDifference <= 0) {
        return 'yellow';
      } else if (timeDifference < twoDaysInMilliseconds) {
        return 'pink';
      } else {
        return 'green';
      }
    },
    formatDateTime(dateTime) {
      const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
      return new Date(dateTime).toLocaleString(undefined, options);
    }
  }
};
</script>

<style scoped>
.main-container {
  margin: auto;
}

.report-card {
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
}

.title {
  text-align: center;
  font-size: 30px;
  font-weight: bold;
}

.exam-list-item {
  cursor: pointer;
  transition: background-color 0.3s;
}

.exam-list-item:hover {
  background-color: #e0f7fa;
}

.exam-details-card {
  background-color: #e3f2fd;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s ease;
}

.exam-details-card:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.exam-title {
  font-size: 24px;
  color: #333;
}

.exam-info {
  font-size: 16px;
  color: black;
}

.exam-actions {
  text-align: right;
}

.v-alert {
  text-align: center;
}
</style>

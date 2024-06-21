<template>
  <v-container class="container">
    <v-row justify="center">
      <v-col>
        <v-card class="report-card" elevation="10">
          <v-card-title class="title">学情报告</v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <!-- 学生信息 -->
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field v-model="studentInfo.name" label="学生姓名" outlined dense readonly></v-text-field>
              </v-col>
            </v-row>
            <v-divider class="my-4"></v-divider>
            <!-- 实训情况 -->
            <v-row>
              <v-col cols="12">
                <h3 class="section-title">实训情况</h3>
                <v-row>
                  <v-col cols="12" md="6">
                    <v-card class="mb-4 training-card" outlined>
                      <v-card-title class="success--text">已完成的实训</v-card-title>
                      <v-list dense>
                        <v-list-item v-if="completedTrainingTasks.length > 0" v-for="task in completedTrainingTasks" :key="task.id">
                          <v-list-item-content>
                            <v-list-item-title>{{ task.title }}</v-list-item-title>
                            <v-list-item-subtitle>{{ task.description }}</v-list-item-subtitle>
                          </v-list-item-content>
                        </v-list-item>
                        <v-list-item v-else>
                          <v-list-item-content>
                            <v-list-item-title>当前没有已完成的实训</v-list-item-title>
                          </v-list-item-content>
                        </v-list-item>
                      </v-list>
                    </v-card>
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-card class="mb-4 training-card" outlined>
                      <v-card-title class="error--text">未完成的实训</v-card-title>
                      <v-list dense>
                        <v-list-item v-if="incompleteTrainingTasks.length > 0" v-for="task in incompleteTrainingTasks" :key="task.id">
                          <v-list-item-content>
                            <v-list-item-title>{{ task.title }}</v-list-item-title>
                            <v-list-item-subtitle>{{ task.description }}</v-list-item-subtitle>
                          </v-list-item-content>
                        </v-list-item>
                        <v-list-item v-else>
                          <v-list-item-content>
                            <v-list-item-title>没有未完成的实训</v-list-item-title>
                          </v-list-item-content>
                        </v-list-item>
                      </v-list>
                    </v-card>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
            <v-divider class="my-4"></v-divider>
            <!-- 考试情况 -->
            <v-row>
              <v-col cols="12">
                <h3 class="section-title">考试情况</h3>
                <v-row>
                  <v-col cols="12" md="6">
                    <v-card class="mb-4 exam-card" outlined>
                      <v-card-title class="success--text">已完成的考试</v-card-title>
                      <v-list dense>
                        <v-list-item v-if="completedExams.length > 0" v-for="exam in completedExams" :key="exam.id">
                          <v-list-item-content>
                            <v-list-item-title>{{ exam.title }}</v-list-item-title>
                            <v-list-item-subtitle>{{ exam.description }}</v-list-item-subtitle>
                          </v-list-item-content>
                        </v-list-item>
                        <v-list-item v-else>
                          <v-list-item-content>
                            <v-list-item-title>当前没有已完成的考试</v-list-item-title>
                          </v-list-item-content>
                        </v-list-item>
                      </v-list>
                    </v-card>
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-card class="mb-4 exam-card" outlined>
                      <v-card-title class="error--text">未完成的考试</v-card-title>
                      <v-list dense>
                        <v-list-item v-if="incompleteExams.length > 0" v-for="exam in incompleteExams" :key="exam.id">
                          <v-list-item-content>
                            <v-list-item-title>{{ exam.title }}</v-list-item-title>
                            <v-list-item-subtitle>{{ exam.description }}</v-list-item-subtitle>
                          </v-list-item-content>
                        </v-list-item>
                        <v-list-item v-else>
                          <v-list-item-content>
                            <v-list-item-title>没有未完成的考试</v-list-item-title>
                          </v-list-item-content>
                        </v-list-item>
                      </v-list>
                    </v-card>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { defineComponent, reactive, onMounted } from 'vue';
import axios from 'axios';

export default defineComponent({
  setup() {
    const studentInfo = reactive({
      name: '张三' // 示例学生姓名
    });

    const completedTrainingTasks = reactive([]);
    const incompleteTrainingTasks = reactive([]);
    const completedExams = reactive([]);
    const incompleteExams = reactive([]);

    const fetchTasksAndExams = async () => {
      try {
        const response = await axios.get('http://36.138.114.105:32637/api/training-tasks');
        const tasks = response.data;

        tasks.forEach(task => {
          if (task.type === 1) { // 1代表练习
            if (task.completed) {
              completedTrainingTasks.push(task);
            } else {
              incompleteTrainingTasks.push(task);
            }
          } else if (task.type === 2) { // 2代表考试
            if (task.completed) {
              completedExams.push(task);
            } else {
              incompleteExams.push(task);
            }
          }
        });
      } catch (error) {
        console.error('Error fetching tasks and exams:', error);
      }
    };

    onMounted(() => {
      fetchTasksAndExams();
    });

    return {
      studentInfo,
      completedTrainingTasks,
      incompleteTrainingTasks,
      completedExams,
      incompleteExams
    };
  }
});
</script>

<style scoped>
.report-card {
  margin-top: 20px;
  background-color: #f3f4f6;
  padding: 20px;
  border-radius: 10px;
}

.title {
  margin: auto;
  justify-content: center;
  text-align: center;
  font-size: 30px;
  font-weight: bold;
}

.section-title {
  margin-bottom: 16px;
  font-size: 1.5rem;
  font-weight: bold;
  color: #1976D2;
}

.training-card {
  background-color: #e3f2fd;
}

.exam-card {
  background-color: #ffe0b2;
}

.container {
  margin-top: 50px;
}

.success--text {
  color: #4caf50;
}

.error--text {
  color: #f44336;
}
</style>

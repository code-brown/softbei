<template>
  <v-container class="line">
    <v-row>
      <!-- 左侧时间轴 -->
      <v-col cols="3">
        <v-timeline density="default" align-left >

        
          <v-timeline-item elevation="5" height="130px"
          :dot-color="calculateDotColor(exam)"
          size="small"
          v-for="(exam, index) in exams"
          :key="index"
          @click="selectExam(exam)"
        >
          
            <v-icon>{{ exam.icon }}</v-icon>
            <v-timeline-item-title>{{ exam.subject }}</v-timeline-item-title>
          </v-timeline-item>
        </v-timeline>
      </v-col>
      
      <!-- 右侧考试科目 -->
      <v-col cols="9">
  <v-card v-if="selectedExam" class="exam-card" color="#64b5f6">
    <v-card-title class="exam-title">{{ selectedExam.subject }}</v-card-title>
    <v-card-text class="exam-info">
      <p><strong class="title">考试时间：</strong>{{ selectedExam.date }}</p>
      <p><strong>考试科目：</strong>{{ selectedExam.subject }}</p>
      <p><strong>结束时间：</strong>{{ selectedExam.end }}</p>
    </v-card-text>
    <v-card-actions class="exam-actions">
      <v-btn @click="startExam(selectedExam) ,overlay = !overlay" >开始考试</v-btn>
    </v-card-actions>
  </v-card>
  <v-alert v-else class="no-exam-alert" outlined>请在左侧选择考试</v-alert>
</v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      exams: [
        {
          subject: '达梦数据库安装',
          date: '2024-05-20',
          icon: 'mdi-book-open-outline',
          end: '2024-05-60',
          id: 1 // 假设每个考试都有唯一的ID
        },
        {
          subject: 'web前端设计',
          date: '2023-05-21',
          icon: 'mdi-book-open-outline',
          end:"2024-05-70",
          id: 2
        },
        {
          subject: 'web前端设计',
          date: '2024-05-17',
          icon: 'mdi-book-open-outline',
          end:"2024-05-70",
          id: 3
        },
        {
          subject: 'web前端设计',
          date: '2024-05-21',
          icon: 'mdi-book-open-outline',
          end:"2024-05-70",
          id: 4
        },
        // 添加更多考试科目
      ],
      selectedExam: null
    };
  },
  methods: {
    selectExam(exam) {
      this.selectedExam = exam;
    },
    startExam(exam) {
      // 跳转至考试页面或者触发开始考试逻辑
      // 这里只是一个示例，具体实现需根据项目需求来处理
      console.log('开始考试', exam);
    },

    calculateDotColor(exam) {
      const now = new Date();
      const endDate = new Date(exam.end);
      const timeDifference = endDate - now;
      const twoDaysInMilliseconds = 2 * 24 * 60 * 60 * 1000;

      if (timeDifference <= 0) {
        return 'yellow'; 
      } else if (timeDifference < twoDaysInMilliseconds) {
        return 'pink'; 
      } else {
        return 'green'; 
      }
    }
  }
};
</script>

<style scoped lang="scss">
.line {
  margin: auto;
}
.exam-card {
  align-items: center;
  justify-items: center;
  background-color: #f0f0f0;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s ease;
}

.exam-card:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.exam-title {
  font-size: 30px;
  color: #333;
}

.exam-info {
  font-size: 16px;
  color: black;
}

.exam-actions {
  text-align: right;
}

.no-exam-alert {
  background-color: #ffebee;
  color: #b71c1c;
  border: 1px solid #ef9a9a;
  border-radius: 4px;
  font-size: 14px;
}

</style>

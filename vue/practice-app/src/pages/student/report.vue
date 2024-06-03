<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-card class="report-card" elevation="5">
          <v-card-title class="text-center">学情报告</v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <!-- 学生信息 -->
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field v-model="studentInfo.name" label="学生姓名" outlined dense></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field v-model="studentInfo.className" label="班级" outlined dense></v-text-field>
              </v-col>
            </v-row>

            <!-- 学情分析内容 -->
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-select v-model="studentInfo.subjectGrade" :items="grades" label="学科成绩" outlined dense></v-select>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field v-model="studentInfo.attendanceRate" label="出勤率" outlined dense></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-select v-model="studentInfo.homeworkCompletion" :items="homeworkStatus" label="作业完成情况" outlined dense></v-select>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-select v-model="studentInfo.learningProgress" :items="progressStatus" label="学习进步情况" outlined dense></v-select>
              </v-col>
            </v-row>

            <!-- 学科成绩图表 -->
            <v-divider></v-divider>
            <v-row class="chart-row">
              <v-col cols="12">
                <v-card>
                  <v-card-title>学科成绩分布图</v-card-title>
                  <v-card-text>
                    <v-chart :options="chartOptions" type="bar" height="300px"></v-chart>
                  </v-card-text>
                </v-card>
              </v-col>
            </v-row>

            <!-- 学生考试成绩表格 -->
            <v-divider></v-divider>
            <v-row class="table-row">
              <v-col cols="12">
                <v-data-table :items="studentInfo.examScores" dense>
                  <template v-slot:top>
                    <v-toolbar flat>
                      <v-toolbar-title>学生考试成绩</v-toolbar-title>
                    </v-toolbar>
                  </template>
                </v-data-table>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { defineComponent, reactive } from 'vue';

export default defineComponent({
  setup() {
    const studentInfo = reactive({
      name: '', // 学生姓名
      className: '', // 班级
      subjectGrade: '', // 学科成绩
      attendanceRate: '', // 出勤率
      homeworkCompletion: '', // 作业完成情况
      learningProgress: '', // 学习进步情况
      examScores: [ // 考试成绩
        { exam: '语文', score: 90 },
        { exam: '数学', score: 85 },
        { exam: '英语', score: 88 }
        // 添加更多考试科目及成绩
      ]
    });

    const grades = ['优秀', '良好', '及格', '不及格'];
    const homeworkStatus = ['及格', '不及格'];
    const progressStatus = ['进步良好', '稳步提升', '有待加强', '需要改进'];

    const chartOptions = reactive({
      title: {
        text: '学科成绩分布图',
        left: 'center'
      },
      xAxis: {
        type: 'category',
        data: ['语文', '数学', '英语', '物理', '化学', '生物']
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: [90, 85, 88, 82, 78, 85],
        type: 'bar'
      }]
    });

    return {
      studentInfo,
      grades,
      homeworkStatus,
      progressStatus,
      chartOptions
    };
  }
});
</script>

<style scoped>
.report-card {
  margin-top: 50px;
}

.chart-row {
  margin-top: 30px;
}

.table-row {
  margin-top: 30px;
}
</style>

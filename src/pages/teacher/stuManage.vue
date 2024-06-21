<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-card class="navigation-card" elevation="5">
          <v-toolbar>
            <v-toolbar-title>学生管理系统</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn text @click="currentView = 'studentManagement'">管理学生</v-btn>
            <v-btn text @click="currentView = 'examResults'">查看考试结果</v-btn>
            <v-btn text @click="currentView = 'studentProfile'">查看学生画像</v-btn>
          </v-toolbar>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12">
        <component :is="currentViewComponent"></component>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { defineComponent, ref, computed } from 'vue';
import StudentManagement from './StudentManagement.vue';
import ExamResults from './ExamResults.vue';
import StudentProfile from './StudentProfile.vue';

export default defineComponent({
  components: {
    StudentManagement,
    ExamResults,
    StudentProfile
  },
  setup() {
    const currentView = ref('studentManagement');

    const currentViewComponent = computed(() => {
      switch (currentView.value) {
        case 'studentManagement':
          return StudentManagement;
        case 'examResults':
          return ExamResults;
        case 'studentProfile':
          return StudentProfile;
        default:
          return StudentManagement;
      }
    });

    return {
      currentView,
      currentViewComponent
    };
  }
});
</script>

<style scoped>
.navigation-card {
  margin-bottom: 20px;
}
</style>

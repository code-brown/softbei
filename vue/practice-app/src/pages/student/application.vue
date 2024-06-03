<template>
  <div>
    <v-card
      v-for="(course, index) in courses"
      :key="index"
      class="card"
    >
      <v-img
        height="200px"
        :src="course.imageUrl"
        cover
      ></v-img>

      <v-card-title>
        {{ course.title }}
      </v-card-title>

      <v-card-subtitle>
        {{ course.subtitle }}
      </v-card-subtitle>

      <v-card-actions>
        <v-btn
          color="orange"
          text
          @click="explore(course)"
        >
          探索
        </v-btn>

        <v-spacer></v-spacer>

        <v-btn
          icon
          @click="toggleDescription(index)"
        >
          <v-icon>{{ showDescription[index] ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="showDescription[index]">
          <v-divider></v-divider>

          <v-card-text>
            {{ course.description }}
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      courses: [], // 从后端接口获取的课程数据
      showDescription: [] // 用于控制描述内容的显示
    };
  },
  created() {
    // 在组件创建时从后端获取课程数据
    this.fetchCourses();
  },
  methods: {
    fetchCourses() {
      // 模拟从后端获取课程数据的过程
      // 实际情况中，您需要替换成真实的后端接口请求
      setTimeout(() => {
        this.courses = [
          {
            title: "课程标题1",
            subtitle: "课程副标题1",
            description: "课程描述内容1。这是您可以添加关于课程的更多详细信息的地方。例如，课程内容、教学方法、课程费用等等。",
            imageUrl: "../../assets/dameng.jpg"
          },
          {
            title: "课程标题2",
            subtitle: "课程副标题2",
            description: "课程描述内容2。这是您可以添加关于课程的更多详细信息的地方。例如，课程内容、教学方法、课程费用等等。",
            imageUrl: "../../assets/dameng.jpg"
          }
          // 可以根据需求添加更多课程数据
        ];

        // 初始化描述内容显示状态数组
        this.showDescription = new Array(this.courses.length).fill(false);
      }, 1000);
    },
    explore(course) {
      // 在这里添加探索按钮点击后的逻辑，可以使用传入的课程对象
      console.log("Exploring:", course);
    },
    toggleDescription(index) {
  // 直接通过修改数组来切换描述内容的显示状态
  this.showDescription[index] = !this.showDescription[index];
}

  }
};
</script>

<style scoped lang="scss">
.card {
  width: 80%;
  margin: 20px auto;
  overflow: hidden;
}
</style>

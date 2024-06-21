<template>
  <v-container fluid>
    <!-- 顶部标题 -->
    <v-row>
      <v-col>
        <v-card class="pa-4">
          <v-card-title class="headline">实训管理面板</v-card-title>
        </v-card>
      </v-col>
    </v-row>

    <!-- 信息展示区域 -->
    <v-row>
      <!-- 信息卡片 -->
      <v-col>
        <v-card class="pa-4">
          <v-card-title class="headline">信息</v-card-title>
          <v-card-text>
            <v-list dense>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>总任务数</v-list-item-title>
                  <v-list-item-subtitle>{{ info.totalTasks }}</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>完成任务数</v-list-item-title>
                  <v-list-item-subtitle>{{ info.completedTasks }}</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>进行中任务数</v-list-item-title>
                  <v-list-item-subtitle>{{ info.totalTasks - info.completedTasks }}</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <!-- 数据表格 -->
    <v-row>
      <v-col>
        <v-card class="pa-4">
          <v-card-title class="headline">数据表格</v-card-title>
          <v-card-text>
            <v-data-table
              :headers="headers"
              :items="tasks"
              class="elevation-1"
              :items-per-page="5"
              :search="search"
            >
              <template v-slot:top>
                <v-toolbar flat>
                  <v-toolbar-title>任务列表</v-toolbar-title>
                  <v-divider class="mx-4" inset vertical></v-divider>
                  <v-spacer></v-spacer>
                  <v-text-field
                    v-model="search"
                    label="搜索"
                    class="mx-4"
                    append-icon="mdi-magnify"
                    single-line
                    hide-details
                  ></v-text-field>
                </v-toolbar>
              </template>
              <template v-slot:item.type="{ item }">
                <span>{{ item.type === 1 ? '练习' : item.type === 2 ? '考试' : '未知类型' }}</span>
              </template>
              <template v-slot:item.status="{ item }">
                <v-chip :color="item.status === '已完成' ? 'green' : 'blue'" dark>
                  {{ item.status }}
                </v-chip>
              </template>
              <template v-slot:item.actions="{ item }">
                <v-icon small @click="deleteTask(item.id)">mdi-delete</v-icon>
              </template>
            </v-data-table>
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
      info: {
        totalTasks: 0,
        completedTasks: 0,
        inProgressTasks: 0,
      },
      headers: [
        { text: 'ID', value: 'id' },
        { text: '任务名称', value: 'title' },
        { text: '描述', value: 'description' },
        { text: '类型', value: 'type' },
        { text: '开始时间', value: 'startTime' },
        { text: '结束时间', value: 'endTime' },
        { text: '操作', value: 'actions', sortable: false },
      ],
      tasks: [],
      search: '',
    };
  },
  created() {
    this.fetchTasks();
  },
  methods: {
    fetchTasks() {
      axios.get("http://36.138.114.105:32637/api/training-tasks")
        .then(response => {
          this.tasks = response.data;
          this.info.totalTasks = this.tasks.length;
          this.info.completedTasks = this.tasks.filter(task => task.status === '已完成').length;
          this.info.inProgressTasks = this.tasks.filter(task => task.status === '进行中').length;
        })
        .catch(error => {
          console.error('There was an error fetching the tasks!', error);
        });
    },
    deleteTask(id) {
      axios.delete(`http://36.138.114.105:32637/api/training-tasks/${id}`)
        .then(response => {
          this.fetchTasks();
          this.$toast.success('Task deleted successfully!');
        })
        .catch(error => {
          console.error('There was an error deleting the task!', error);
        });
    },
  }
};
</script>

<style scoped>
.headline {
  font-family: 'Arial', sans-serif;
  font-size: 24px;
  font-weight: bold;
  color: #3f51b5;
  text-align: center;
  text-transform: uppercase;
  margin-bottom: 20px;
  border-bottom: 2px solid #3f51b5;
  padding-bottom: 10px;
}
</style>

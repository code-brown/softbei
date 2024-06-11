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
      <v-col cols="12" md="4">
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
                  <v-list-item-subtitle>{{ info.inProgressTasks }}</v-list-item-subtitle>
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
              <template v-slot:item.status="{ item }">
                <v-chip
                  :color="item.status === '已完成' ? 'green' : 'blue'"
                  dark
                >
                  {{ item.status }}
                </v-chip>
              </template>
            </v-data-table>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  data() {
    return {
      info: {
        totalTasks: 100,
        completedTasks: 75,
        inProgressTasks: 25,
      },
      headers: [
        { text: 'ID', value: 'id' },
        { text: '任务名称', value: 'title' },
        { text: '描述', value: 'description' },
        { text: '状态', value: 'status' },
      ],
      tasks: [
        { id: 1, title: '任务一', description: '任务描述一', status: '进行中' },
        { id: 2, title: '任务二', description: '任务描述二', status: '已完成' },
        { id: 3, title: '任务三', description: '任务描述三', status: '进行中' },
        { id: 4, title: '任务四', description: '任务描述四', status: '已完成' },
        { id: 5, title: '任务五', description: '任务描述五', status: '已完成' },
        { id: 6, title: '任务六', description: '任务描述六', status: '进行中' },
      ],
      search: ''
    };
  }
};
</script>

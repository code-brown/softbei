<template>
  <v-app>
    <!-- Top Navigation Bar -->
    <v-app-bar app color="#64B5F6" dense>
      <span class="font-weight-bold">校园智能实训系统</span>
      <v-spacer></v-spacer>
      <span class="name">{{ student }}</span>
      <v-avatar color="surface-variant" class="avatar"></v-avatar>
    </v-app-bar>

    <!-- 主内容区域 -->
    <router-view ></router-view>

    <!-- 底部导航栏 -->
    <v-container class="footer">
  <v-bottom-navigation :elevation="9" grow="true" density="comfortable" height="65">
    <router-link to="/stumain/practice">
      <v-btn>
        <v-icon>mdi-history</v-icon>
        <span>我的实训</span>
      </v-btn>
    </router-link>
    <router-link to="/stumain/application">
      <v-btn>
        <v-icon>mdi-heart</v-icon>
        <span>申请课程</span>
      </v-btn>
    </router-link>
    <router-link to="/stumain/exam">
      <v-btn>
        <v-icon>mdi-pencil</v-icon>
        <span>我的考试</span>
      </v-btn>
    </router-link>
    <router-link to="/stumain/report">
      <v-btn>
        <v-icon>mdi-map-marker</v-icon>
        <span>学情报告</span>
      </v-btn>
    </router-link>
  </v-bottom-navigation>
</v-container>

  </v-app>
</template>

<script lang="ts">

</script>

<style lang="scss" scoped>
.font-weight-bold {
  font-size: 26px;
  margin-left: 1.25rem;
}

.avatar {
  margin-right: 1.25rem;
}

.name {
  margin-right: .3525rem;
  margin-top: 0.32rem;
}


a {
  all: unset;
}


</style>

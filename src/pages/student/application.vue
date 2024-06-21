<template>
  <v-container class="card">
    <v-row class="my-5" justify="center">
      <v-col cols="3" md="8" lg="12" v-for="card in filteredCards" :key="card.id">
        <v-card max-width="90%">
          <v-img
            class="white--text align-end"
            height="200"
            src="../../assets/dameng.jpg"
            gradient="to top right, rgba(0,0,0,.1), rgba(0,0,0,.5)"
          >
            <v-card-title>{{ card.title }}</v-card-title>
          </v-img>
          <v-card-text>
            <div>{{ card.description }}</div>
          </v-card-text>
          <v-card-actions>
            <v-btn color="orange" @click="applyTraining(card)">申请实训</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import axios from 'axios';

export default {
  name: 'App',
  data() {
    return {
      student: '学生姓名',
      defaultCards: [
        {
          id: 1,
          title: '达梦数据库安装',
          description: '本课程将引导你完成达梦数据库的安装、配置和基础使用。',
          image: '../../assets/dameng.jpg',
          type: 1 // 假设类型为练习
        },
        {
          id: 2,
          title: 'Web前端结合达梦数据库',
          description: '本课程将指导你如何使用Web前端技术与达梦数据库进行交互。',
          image: 'https://i0.hdslb.com/bfs/archive/0d790389ba86fdcb6a29190d6cb6c27ea2c2508e.jpg@672w_378h_1c_!web-search-common-cover.png',
          type: 1 // 假设类型为练习
        }
      ],
      cards: []
    }
  },
  computed: {
    filteredCards() {
      return this.cards.filter(card => card.type === 1); // 仅显示类型为“练习”的实训
    }
  },
  created() {
    this.fetchCards();
  },
  methods: {
    fetchCards() {
      axios.get('http://36.138.114.105:32637/api/training-tasks')
        .then(response => {
          this.cards = [...this.defaultCards, ...response.data];
        })
        .catch(error => {
          console.error('Error fetching cards:', error);
        });
    },
    applyTraining(card) {
      // 将申请的实训保存到本地存储中（或者发送到后端）
      let appliedTrainings = JSON.parse(localStorage.getItem('appliedTrainings')) || [];
      appliedTrainings.push(card);
      localStorage.setItem('appliedTrainings', JSON.stringify(appliedTrainings));
      alert(`成功申请实训: ${card.title}`);
    }
  }
}
</script>

<style scoped lang="scss">
.card {
  margin-top: 3.25rem;
}
a {
  all: unset;
}
.v-btn--stacked.v-btn--density-comfortable {
  height: calc(var(--v-btn-height) + -16px);
  margin: 0px 40px;
}
</style>

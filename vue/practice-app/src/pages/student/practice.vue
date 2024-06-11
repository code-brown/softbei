<template>
    <v-container class="card">
      <v-row class="my-5" justify="center">
        <v-col cols="12" md="6" lg="4" v-for="card in cards" :key="card.id">
          <v-card max-width="400">
            <v-img
              class="white--text align-end"
              height="200"
              :src="card.image"
              gradient="to top right, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            >
              <v-card-title>{{ card.title }}</v-card-title>
            </v-img>
            <v-card-text>
              <div>{{ card.description }}</div>
            </v-card-text>
          
            <v-card-actions>
              <v-btn color="orange">进入实训</v-btn>
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
          Image:'../../assets/dameng.jpg'
        },
        {
          id: 2,
          title: 'Web前端结合达梦数据库',
          description: '本课程将指导你如何使用Web前端技术与达梦数据库进行交互。',
          image: 'https://i0.hdslb.com/bfs/archive/0d790389ba86fdcb6a29190d6cb6c27ea2c2508e.jpg@672w_378h_1c_!web-search-common-cover.png'
        }
      ],
      cards: []
    }
  },
  created() {
    this.fetchCards();
  },
  methods: {
    fetchCards() {
      axios.get('/api/cards')
        .then(response => {
          this.cards = [...this.defaultCards, ...response.data];
        })
        .catch(error => {
          console.error('Error fetching cards:', error);
        });
    },
    goTo(path) {
      this.$router.push(path);
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
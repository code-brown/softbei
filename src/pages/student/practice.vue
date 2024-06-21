<template>
  <div>
    <h1>我的实训</h1>
    <v-container class="card">
      <v-row class="my-5" justify="center">
        <v-col cols="12" md="6" lg="4" v-for="training in appliedTrainings" :key="training.id">
          <v-card max-width="400">
            <v-img
              class="white--text align-end"
              height="200"
              src="../../assets/dameng.jpg"
              gradient="to top right, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            >
              <v-card-title>{{ training.title }}</v-card-title>
            </v-img>
            <v-card-text>
              <div>{{ training.description }}</div>
            </v-card-text>
            <v-btn width="100%">进入实训</v-btn>
            
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script lang="ts">
export default {
  name: 'MyTrainings',
  data() {
    return {
      appliedTrainings: []
    }
  },
  created() {
    this.fetchAppliedTrainings();
  },
  methods: {
    fetchAppliedTrainings() {
      this.appliedTrainings = JSON.parse(localStorage.getItem('appliedTrainings')) || [];
    }
  }
}
</script>

<style scoped lang="scss">
.card {
  margin-top: 3.25rem;
}
</style>

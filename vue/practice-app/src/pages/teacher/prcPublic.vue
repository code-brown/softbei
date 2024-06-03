<template>
  <h1>发布实训任务</h1>
  <br><br>
  <form @submit.prevent="submit">
    <v-text-field
      v-model="title"
      :error-messages="errors.title"
      label="实训名称"
      required
    ></v-text-field>

    <v-text-field
      v-model="description"
      :error-messages="errors.description"
      label="任务描述"
      required
    ></v-text-field>

    <v-radio-group v-model="type">
      <v-radio label="练习" value="1"></v-radio>
      <v-radio label="考试" value="2"></v-radio>
    </v-radio-group>

    <v-text-field
      v-model="startTime"
      :error-messages="errors.startTime"
      label="开始时间"
      type="datetime-local"
      required
    ></v-text-field>

    <v-text-field
      v-model="endTime"
      :error-messages="errors.endTime"
      label="结束时间"
      type="datetime-local"
      required
    ></v-text-field>

    <v-select
      v-model="subjects"
      :items="subjectOptions"
      :error-messages="errors.subjects"
      label="科目选择"
      chips
      required
    ></v-select>

    <v-file-input
      v-model="coverImage"
      :error-messages="errors.coverImage"
      label="封面上传"
      accept="image/*"
      required
    ></v-file-input>

    <v-btn
      color="primary"
      class="me-4"
      type="submit"
      size="large"
    >
      提交
    </v-btn>

    <v-btn @click="handleReset"
      color="error"
      size="large">
      清除
    </v-btn>
  </form>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      title: '',
      description: '',
      type: null,
      startTime: '',
      endTime: '',
      subjects: [],
      coverImage: null,
      subjectOptions: ['科目1', '科目2', '科目3', '科目4', '科目5'],
      errors: {
        title: '',
        description: '',
        startTime: '',
        endTime: '',
        subjects: '',
        coverImage: ''
      }
    };
  },
  methods: {
    validate() {
      this.errors = {
        title: '',
        description: '',
        startTime: '',
        endTime: '',
        subjects: '',
        coverImage: ''
      };

      if (!this.title) {
        this.errors.title = '实训名称不能为空';
      }
      if (!this.description) {
        this.errors.description = '任务描述不能为空';
      }
      if (!this.startTime) {
        this.errors.startTime = '开始时间不能为空';
      }
      if (!this.endTime) {
        this.errors.endTime = '结束时间不能为空';
      }
      if (!this.subjects.length) {
        this.errors.subjects = '请选择至少一个科目';
      }
      if (!this.coverImage) {
        this.errors.coverImage = '封面不能为空';
      }
    },
    async submit() {
      this.validate();
      if (this.errors.title || this.errors.description || this.errors.startTime || this.errors.endTime || this.errors.subjects || this.errors.coverImage) {
        return;
      }

      const formData = new FormData();
      formData.append('title', this.title);
      formData.append('description', this.description);
      formData.append('type', this.type);
      formData.append('startTime', this.startTime);
      formData.append('endTime', this.endTime);
      formData.append('subjects', JSON.stringify(this.subjects));
      formData.append('coverImage', this.coverImage);

      try {
        await axios.post('http://localhost:8080/api/training-tasks', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
        this.handleReset();
        alert('实训任务已成功提交');
      } catch (error) {
        console.error('提交失败', error);
        alert('提交失败，请重试');
      }
    },
    handleReset() {
      this.title = '';
      this.description = '';
      this.type = null;
      this.startTime = '';
      this.endTime = '';
      this.subjects = [];
      this.coverImage = null;
      this.errors = {
        title: '',
        description: '',
        startTime: '',
        endTime: '',
        subjects: '',
        coverImage: ''
      };
    }
  }
};
</script>

<style scoped>
.me-4 {
  margin-right: 16px;
}
</style>

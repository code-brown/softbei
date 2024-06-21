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

    <v-file-input
      v-model="coverImage"
      :error-messages="errors.coverImage"
      label="封面上传"
      accept="image/*"
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
      coverImage: null,
      errors: {
        title: '',
        description: '',
        startTime: '',
        endTime: '',
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
        coverImage: ''
      };

      if (!this.title) {
        this.errors.title = '实训名称不能为空';
      }
      if (!this.startTime) {
        this.errors.startTime = '开始时间不能为空';
      }
      if (!this.endTime) {
        this.errors.endTime = '结束时间不能为空';
      }
    },
    async submit() {
      this.validate();
      if (this.errors.title || this.errors.startTime || this.errors.endTime) {
        return;
      }

      const formData = new FormData();
      formData.append('title', this.title);
      formData.append('description', this.description || '');
      formData.append('type', this.type);
      formData.append('startTime', this.startTime); // 提交正确的日期时间格式
      formData.append('endTime', this.endTime); // 提交正确的日期时间格式

      if (this.coverImage) {
        formData.append('coverImage', this.coverImage);
      }

      for (let pair of formData.entries()) {
        console.log(`${pair[0]}: ${pair[1]}`); // 调试输出
      }

      try {
        const response = await axios.post('http://36.138.114.105:32637/api/training-tasks', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });

        if (this.type === 2) {
          this.$router.push({ name: 'ExamPage', params: { title: this.title, description: this.description, startTime: this.startTime, endTime: this.endTime } }); // 跳转至考试页面
        } else {
          this.handleReset();
          alert('实训任务已成功提交');
        }
      } catch (error) {
        console.error('提交失败', error);
        if (error.response && error.response.data) {
          console.error('错误详情:', error.response.data); // 打印详细错误信息
        }
        alert('提交失败，请重试');
      }
    },
    handleReset() {
      this.title = '';
      this.description = '';
      this.type = null;
      this.startTime = '';
      this.endTime = '';
      this.coverImage = null;
      this.errors = {
        title: '',
        description: '',
        startTime: '',
        endTime: '',
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

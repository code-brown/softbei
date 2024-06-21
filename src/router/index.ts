import { createRouter, createWebHistory } from 'vue-router';
import login from '../pages/login.vue';
import tealogin from '../pages/teacher/tealogin.vue';
import stumain from '../pages/student/stumain.vue';
import teamain from '../pages/teacher/teamain.vue';
import stuManage from '../pages/teacher/stuManage.vue';
import PrcManage from '../pages/teacher/prcManage.vue';
import prcPublic from '../pages/teacher/prcPublic.vue';
import Application from '../pages/student/application.vue';
import Examination from '../pages/student/examination.vue';
import Report from '../pages/student/report.vue';
import Practice from '../pages/student/practice.vue';
import register from '../pages/student/register.vue';



const routes = [
  {
    path: '/register',
    name:'register',
    component: register
  },
  {
    path: '/',
    name: 'login',
    component: login
  },
  {
    path:'/tealogin',
    name: 'tealogin',
    component: tealogin
  },
  {
    path:'/stumain',
    name:'stumain',
    component: stumain,
    children:[
      {
        path:'application',
        name:'application',
        component: Application
      },
      {
        path:'exam',
        name:'exam',
        component: Examination 
      },
      {
        path:'report',
        name:'report',
        component: Report
      },
      {
        path:'practice',
        name:'practice',
        component:Practice
      }
    ]
  },
  {
    path: '/teamain',
    name: 'teamain',
    component: teamain,
    children: [
      {
        path: 'prcPublic',
        name: 'prcPublic',
        component: prcPublic
      },
      {
        path: 'prcManage',
        name: 'prcManage',
        component: PrcManage
      },
      {
        path: 'stuManage',
        name: 'stuManage',
        component: stuManage
      },
    ]
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;

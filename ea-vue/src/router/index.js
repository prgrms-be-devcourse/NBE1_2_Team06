import {createRouter, createWebHashHistory, createWebHistory} from 'vue-router';
import HospitalsView from '@/views/HospitalsView.vue';
import EmergencyRoomDetailView from '@/views/EmergencyRoomDetailView.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HospitalsView,
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue'),
  },
  {
    path: '/hospital/:id', // 병원 ID를 URL 파라미터로 받음
    name: 'EmergencyRoomDetail',
    component: EmergencyRoomDetailView,
    props: true, // URL 파라미터를 컴포넌트에 props로 전달
  },
  { path: '/EmergencyDirections',
    name: 'EmergencyDirections',
    component: () => import('../views/EmergencyDirections.vue')
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

import Vue from 'vue';
import VueRouter from 'vue-router';
import AppHouse from '@/views/AppHouse';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'main',
    component: AppHouse,
    redirect: '/house',
  },
  {
    path: '/house',
    name: 'house',
    component: AppHouse,
  },
  {
    path: '/board',
    name: 'board',
    component: () => import(/* webpackChunkName: "board" */ '@/views/AppBoard'),
    redirect: '/board/list',
    children: [
      {
        path: 'list',
        name: 'boardlist',
        component: () => import(/* webpackChunkName: "board" */ '@/components/board/BoardList'),
      },
      {
        path: 'write',
        name: 'boardwrite',
        component: () => import(/* webpackChunkName: "board" */ '@/components/board/BoardWrite'),
      },
      {
        path: 'view/:category/:category_no',
        name: 'boardview',
        component: () => import(/* webpackChunkName: "board" */ '@/components/board/BoardView'),
      },
      {
        path: 'update',
        name: 'boardupdate',
        component: () => import(/* webpackChunkName: "board" */ '@/components/board/BoardUpdate'),
      },
      {
        path: 'delete/:category/:category_no',
        name: 'boarddelete',
        component: () => import(/* webpackChunkName: "board" */ '@/components/board/BoardDelete'),
      },
    ],
  },
  {
    path: '/news',
    name: 'news',
    component: () => import(/* webpackChunkName: "news" */ '@/views/AppNews'),
    redirect: '/news/list',
    children: [
      {
        path: 'list',
        name: 'newslist',
        component: () => import(/* webpackChunkName: "board" */ '@/components/news/NewsList'),
      },
    ],
  },
  {
    path: '/register',
    name: 'register',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../components/Register.vue'),
  },
  {
    path: '/login',
    name: 'login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../components/Login.vue'),
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;

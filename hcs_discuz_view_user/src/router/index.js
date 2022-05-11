import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/home/Home.vue'
import TopicListByUpdate from '../views/home/TopicListByUpdate.vue'
import TopicDetail from '../views/topic/TopicDetail.vue'
import Category from '../views/category/Category.vue'
import Profile from '../views/profile/Profile.vue'
import WriteTopic from '../views/write/WriteTopic.vue'
import Discover from '../views/discover/Discover.vue'
import Announcement from '../views/announcement/Announcement.vue'
import Search from '../views/search/Search.vue'

const routes = [
  {
    path: '/',
    name: '/',
    component: Home
  },
  {
    path: '/new',
    name: '/new',
    component: TopicListByUpdate
  },
  {
    path: '/category',
    name: '/category',
    component: Category
  },
  {
    path: '/discover',
    name: '/discover',
    component: Discover
  },
  {
    path: '/announcement',
    name: '/announcement',
    component: Announcement
  },

  {
    path: '/search/:topicTitle',
    name: '/search',
    component: Search
  },

  {
    path: '/topicdetail',
    name: '/topicdetail',
    component: TopicDetail
  },
  {
    path: '/profile',
    name: '/profile',
    component: Profile
  },
  {
    path: '/writetopic',
    name: '/writetopic',
    component: WriteTopic
  },
  {
    path: '/navbar',
    name: 'NavBar',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../components/navbar/NavBar.vue')
  }
]

const router = createRouter({
  // history: createWebHistory(process.env.BASE_URL),
  history: createWebHashHistory(),
  routes
})

export default router

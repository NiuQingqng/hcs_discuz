import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/home/Home.vue'
import Login from '../views/auth/Login.vue'

import UserList from '../views/user/UserList.vue'
import UserInfo from '../views/user/UserInfo.vue'
import UserEdit from '../views/user/UserEdit.vue'
import UserAdd from '../views/user/UserAdd.vue'

import TopicList from '../views/content/topic/TopicList.vue'
import TopicInfo from '../views/content/topic/TopicInfo.vue'
import WriteTopic from '../views/content/topic/WriteTopic.vue'

import CommentList from '../views/content/comment/CommentList.vue'
import AdviseList from '../views/advise/AdviseList.vue'

import CategoryList from '../views/category/CategoryList.vue'

import WebsiteInfo from '../views/websiteinfo/WebsiteInfo.vue'

import MessageBoard from '../views/messageboard/MessageBoard.vue'

const routes = [
  {
    path: '/',
    name: '/',
    component: Home
  },
  {
    path: '/login',
    name: '/login',
    component: Login
  },

  {
    path: '/userlist',
    name: '/userlist',
    component: UserList
  },
  {
    path: '/userinfo/:userId',
    name: '/userinfo',
    component: UserInfo
  },
  {
    path: '/useredit/:userId',
    name: '/useredit',
    component: UserEdit
  },
  {
    path: '/useradd',
    name: '/useradd',
    component: UserAdd
  },

  {
    path: '/topiclist',
    name: '/topiclist',
    component: TopicList
  },
  {
    path: '/topicinfo/:topicId',
    name: '/topicinfo',
    component: TopicInfo
  },
  {
    path: '/writetopic',
    name: '/writetopic',
    component: WriteTopic
  },

  {
    path: '/commentlist',
    name: '/commentlist',
    component: CommentList
  },
  {
    path: '/categorylist',
    name: '/categorylist',
    component: CategoryList
  },
  {
    path: '/adviselist',
    name: '/adviselist',
    component: AdviseList
  },
  {
    path: '/websiteinfo',
    name: '/websiteinfo',
    component: WebsiteInfo
  },
  {
    path: '/messageboard',
    name: '/messageboard',
    component: MessageBoard
  },
  {
    path: '/about',
    name: '/about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/about/About.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

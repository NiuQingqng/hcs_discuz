import axios from "axios";
// <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
//import router from '../router';
import Cookies from 'js-cookie'

import urlConfig from '../assets/js/config'

export function request(config) {
  // 1.创建axios的实例
  const instance = axios.create({
    method: 'POST',
    baseURL: urlConfig.host,
    changeOrigin: true,
    timeout: 5000,
    headers:{
      'token':Cookies.get('token'),
    }
  })

  // 2.axios的拦截器
  // 2.1.请求拦截的作用
  instance.interceptors.request.use(config => {
    return config
  })

  // 2.2.响应拦截
  instance.interceptors.response.use(res => {
    //返回参数
    return res
  }, err => {
    console.log(err);
  })

  // 3.发送真正的网络请求
  return instance(config)
}
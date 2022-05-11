import axios from "axios";
//import router from '../router';
import Cookies from 'js-cookie'

import urlConfig from '../assets/js/config'

export function request(config) {
  // 1.创建axios的实例
  const instance = axios.create({
    method: 'POST',
    baseURL: urlConfig.host,
    // baseURL: 'http://39.105.73.65:8081',
    changeOrigin: true,
    timeout: 5000,
    headers:{
      'token':Cookies.get('token'),
      //'token':sessionStorage.getItem('token'),
    }
    //withCredentials: true, // 运行跨域带上cookies
  })

  // 2.axios的拦截器
  // 2.1.请求拦截的作用
  instance.interceptors.request.use(config => {
    return config
  })

  // 2.2.响应拦截
  instance.interceptors.response.use(res => {
    //检测未登录，跳转到登录页
    if (res.data.code === 300){
      alert("请先登录！")
      //router.replace("/login")
      window.location.href = urlConfig.loginPage
      return
    }
    //返回参数
    return res
  }, err => {
    console.log(err);
  })

  // 3.发送真正的网络请求
  return instance(config)
}
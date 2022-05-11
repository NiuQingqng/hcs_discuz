import {request} from "./request"
//使用统一axios.create会出错
// export function uploadFile(form) {
//   return axios.post(
//     'http://127.0.0.1:8088/file/upload', 
//     form, 
//     )
// }  错误理解
// 报错原因
// 传递对象（json）和formData数据需使用data而不能使用params
// 使用data时不能像params使用
// data:{
//   form
// }格式data:form
// 需使用
// Content-Type请求头可加可不加，axios会自动判断
// headers:{
//   'Content-Type': 'multipart/form-data'
// }
export function uploadFile(form) {
  return request({
    url: 'file/upload',
    data: form,
  })
}

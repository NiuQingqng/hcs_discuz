import {request} from "./request"

export function getCategoryList() {
  return request({
    url: 'category/findall',
  })
}

export function addCategory(category) {
  return request({
    url: 'category/addcategory',
    data: category
    //传递对象（json）数据需使用data而不能使用params
    //使用data时不能像params使用
    // data:{
    //   user
    // }格式
  })
}


export function updateCategory(category) {
  return request({
    url: 'category/updatecategory',
    data: category
  })
}
  
export function delCategory(categoryId) {
  return request({
    url: 'category/delcategory',
    params: {
      categoryId
    }
  })
}
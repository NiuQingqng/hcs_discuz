import {request} from "./request"

export function getUserList(pageNum, pageSize) {
  return request({
    url: 'user/findall',
    params: {
      pageNum,
      pageSize
    }
  })
}

export function getUserInfo(userId) {
  return request({
    url: 'user/findone',
    params: {
      userId
    }
  })
}

export function getUserInfoByCondition(user,pageNum, pageSize) {
  return request({
    url: 'user/findonebycondition',
    data: user,
    params:{
      pageNum,
      pageSize
    }
  })
}

export function addUser(user) {
  return request({
    url: 'user/adduser',
    data: user
    //传递对象（json）数据需使用data而不能使用params
    //使用data时不能像params使用
    // data:{
    //   user
    // }格式
  })
}


export function updateUser(user) {
  return request({
    url: 'user/updateuser',
    data: user
    //传递对象（json）数据需使用data而不能使用params
    //使用data时不能像params使用
    // data:{
    //   user
    // }格式
  })
}

export function delUser(userId) {
  return request({
    url: 'user/deluser',
    params: {
      userId
    }
  })
}
import {request} from "./request"
export function UserLogin(username,password) {
  return request({
    url: 'user/login',
    params: {
      username,
      password
    }
  })
}

export function UserFind() {
  return request({
    url: 'user/check',
  })
}

export function UserLogout() {
  return request({
    url: 'user/logout',
  })
}
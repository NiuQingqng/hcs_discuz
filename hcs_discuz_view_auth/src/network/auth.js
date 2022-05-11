import {request} from "./request"
export function userLogin(username,password,imgcodeId,code) {
  return request({
    url: 'user/login',
    params: {
      username,
      password,
      imgcodeId,
      code
    }
  })
}

export function userFind() {
  return request({
    url: 'user/check',
  })
}

export function userLogout() {
  return request({
    url: 'user/logout',
  })
}

export function userRegister(user, code) {
  return request({
    url: 'user/register',
    data: user,
    params: {
      code,
    }
  })
}

// 重置密码
export function resetPwd(email, code, newpwd) {
  return request({
    url: 'user/resetpwd',
    params: {
      email,
      code,
      newpwd
    }
  })
}

//注册邮件验证码
export function regSendCode(toEmail) {
  return request({
    url: 'code/regsendemail',
    params: {
      toEmail,
    }
  })
}

// 给已存在的邮箱发送邮件验证码
export function existSendCode(toEmail) {
  return request({
    url: 'code/existsendemail',
    params: {
      toEmail,
    }
  })
}
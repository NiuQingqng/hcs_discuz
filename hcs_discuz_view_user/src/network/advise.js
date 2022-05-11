import {request} from "./request"

export function addAdvise(advise) {
  return request({
    url: 'advise/add',
    data: advise
  })
}

export function addMessage(content) {
  return request({
    url: 'advise/addmessage',
    params: {
      content
    }
  })
}

export function getMessageList() {
  return request({
    url: 'advise/findallmessage',
  })
}

export function deleteMessage(adviseId) {
  return request({
    url: 'advise/delete',
    params: {
      adviseId
    }
  })
}
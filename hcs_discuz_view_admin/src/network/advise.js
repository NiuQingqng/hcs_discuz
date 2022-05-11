import {request} from "./request"

export function addAdvise(advise) {
  return request({
    url: 'advise/add',
    data: advise
  })
}

export function getAdviseList() {
  return request({
    url: 'advise/findall',
  })
}

export function handleAdvise(adviseId) {
  return request({
    url: 'advise/handle',
    params: {
      adviseId
    }
  })
}

export function getAdviseListByCondition(category) {
  return request({
    url: 'advise/findbycondition',
    params: {
      category
    }
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
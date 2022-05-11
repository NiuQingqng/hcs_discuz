import {request} from "./request"

export function getTopicList(pageNum, pageSize) {
  return request({
    url: 'topic/findall',
    params: {
      pageNum,
      pageSize
    }
  })
}

export function getTopicListByHot(pageNum, pageSize) {
  return request({
    url: 'topic/findallbyhot',
    params: {
      pageNum,
      pageSize
    }
  })
}

export function getTopicInfoByCondition(Topic, pageNum, pageSize) {
  return request({
    url: 'topic/findonebycondition',
    data: Topic,
    params: {
      pageNum,
      pageSize
    }
  })
}

export function getTopicInfo(topicId) {
  return request({
    url: 'topic/findone',
    params: {
      topicId
    }
  })
}

export function addTopic(topic) {
  return request({
    url: 'topic/addtopic',
    data: topic
  })
}

export function updateTopic(topic) {
  return request({
    url: 'topic/updatetopic',
    data: topic
  })
}
  
export function delTopic(topicId) {
  return request({
    url: 'topic/deltopic',
    params: {
      topicId
    }
  })
}
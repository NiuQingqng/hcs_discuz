import {request} from "./request"

export function getNotificationInfoByCondition(notification, pageNum, pageSize) {
  return request({
    url: 'notification/findby',
    data: notification,
    params: {
      pageNum,
      pageSize
    }
  })
}

export function handleNotification(toUserId) {
  return request({
    url: 'notification/handle',
    params: {
      toUserId,
    }
  })
}
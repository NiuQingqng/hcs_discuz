import {request} from "./request"

export function addThumb(thumb) {
  return request({
    url: 'thumb/add',
    data: thumb
  })
}
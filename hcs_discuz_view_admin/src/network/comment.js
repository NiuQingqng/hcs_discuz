import {request} from "./request"

export function getCommentList(pageNum, pageSize) {
  return request({
    url: 'comment/findall',
    params: {
      pageNum,
      pageSize
    }
  })
}

export function getCommentListByCondition(comment, pageNum, pageSize) {
  return request({
    url: 'comment/findbycondition',
    data: comment,
    params: {
      pageNum,
      pageSize
    }
  })
}

export function getCommentInfo(commentId) {
  return request({
    url: 'comment/findone',
    params: {
      commentId
    }
  })
}

export function updateComment(comment) {
  return request({
    url: 'comment/updatecomment',
    data: comment
  })
}

export function addComment(comment) {
  return request({
    url: 'comment/addcomment',
    data: comment
    //传递对象（json）数据需使用data而不能使用params
    //使用data时不能像params使用
    // data:{
    //   user
    // }格式
  })
}

export function delComment(commentId) {
  return request({
    url: 'comment/delcomment',
    params: {
      commentId
    }
  })
}
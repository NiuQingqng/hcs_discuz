package com.niuniu.hcs_discuz.model;


import java.util.Date;

public class Thumb {

  private Integer thumbId;
  private Integer fromUserId;
  private Integer topicId;
  private Integer commentId;
  private Date createTime;
  private Integer status;

  public Integer getThumbId() {
    return thumbId;
  }

  public void setThumbId(Integer thumbId) {
    this.thumbId = thumbId;
  }

  public Integer getFromUserId() {
    return fromUserId;
  }

  public void setFromUserId(Integer fromUserId) {
    this.fromUserId = fromUserId;
  }

  public Integer getTopicId() {
    return topicId;
  }

  public void setTopicId(Integer topicId) {
    this.topicId = topicId;
  }

  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

}

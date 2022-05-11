package com.niuniu.hcs_discuz.model;

import java.util.Date;

public class Comment {

  private Integer commentId;
  private Integer userId;
  private Integer topicId;
  private Integer rootId;
  private String content;
  private Date createTime;
  private Integer status;
  private Integer thumbCount;
  private Integer commentCount;

  private User user;

  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getTopicId() {
    return topicId;
  }

  public void setTopicId(Integer topicId) {
    this.topicId = topicId;
  }

  public Integer getRootId() {
    return rootId;
  }

  public void setRootId(Integer rootId) {
    this.rootId = rootId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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

  public Integer getThumbCount() {
    return thumbCount;
  }

  public void setThumbCount(Integer thumbCount) {
    this.thumbCount = thumbCount;
  }

  public Integer getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(Integer commentCount) {
    this.commentCount = commentCount;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Comment{" +
            "commentId=" + commentId +
            ", userId=" + userId +
            ", topicId=" + topicId +
            ", rootId=" + rootId +
            ", content='" + content + '\'' +
            ", createTime=" + createTime +
            ", status=" + status +
            ", thumbCount=" + thumbCount +
            ", commentCount=" + commentCount +
            ", user=" + user +
            '}';
  }
}

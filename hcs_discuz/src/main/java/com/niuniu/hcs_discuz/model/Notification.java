package com.niuniu.hcs_discuz.model;

import java.util.Date;

public class Notification {

  private Integer notificationId;
  private Integer toUserId;
  private Integer fromUserId;
  private String subject;
  private String action;
  private Integer status;
  private Date createTime;
  private Date handleTime;
  private Integer topicId;
  private Integer commentId;

  private User user;

  private Topic topic;

  public Notification() {
  }

  public Notification(Integer toUserId, Integer fromUserId, String subject, Integer topicId) {
    this.toUserId = toUserId;
    this.fromUserId = fromUserId;
    this.subject = subject;
    this.topicId = topicId;
  }

  public Integer getNotificationId() {
    return notificationId;
  }

  public void setNotificationId(Integer notificationId) {
    this.notificationId = notificationId;
  }

  public Integer getToUserId() {
    return toUserId;
  }

  public void setToUserId(Integer toUserId) {
    this.toUserId = toUserId;
  }

  public Integer getFromUserId() {
    return fromUserId;
  }

  public void setFromUserId(Integer fromUserId) {
    this.fromUserId = fromUserId;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getHandleTime() {
    return handleTime;
  }

  public void setHandleTime(Date handleTime) {
    this.handleTime = handleTime;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }
}

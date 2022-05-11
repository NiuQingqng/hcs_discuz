package com.niuniu.hcs_discuz.model;

import java.util.Date;

public class Advise {

  private Integer adviseId;
  private String describe;
  private String content;
  private Integer userId;
  private Integer topicId;
  private Integer commentId;
  private Integer result;
  private String resultStr;
  private Integer adviseUserId;
  private Integer adminId;
  private Date createTime;
  private Date handleTime;
  private Integer category;

  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Integer getAdviseId() {
    return adviseId;
  }

  public void setAdviseId(Integer adviseId) {
    this.adviseId = adviseId;
  }

  public String getDescribe() {
    return describe;
  }

  public void setDescribe(String describe) {
    this.describe = describe;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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

  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  public Integer getResult() {
    return result;
  }

  public void setResult(Integer result) {
    this.result = result;
  }

  public String getResultStr() {
    return resultStr;
  }

  public void setResultStr(String resultStr) {
    this.resultStr = resultStr;
  }

  public Integer getAdviseUserId() {
    return adviseUserId;
  }

  public void setAdviseUserId(Integer adviseUserId) {
    this.adviseUserId = adviseUserId;
  }

  public Integer getAdminId() {
    return adminId;
  }

  public void setAdminId(Integer adminId) {
    this.adminId = adminId;
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

  public Integer getCategory() {
    return category;
  }

  public void setCategory(Integer category) {
    this.category = category;
  }

}

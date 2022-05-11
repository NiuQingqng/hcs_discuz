package com.niuniu.hcs_discuz.model;

import java.util.Date;

public class Image {

  private Integer imageId;
  private Integer topicId;
  private Integer commentId;
  private Integer adviseId;
  private String path;
  private Date uploadTime;
  private Integer uploadUserId;
  private Integer status;

  public Integer getImageId() {
    return imageId;
  }

  public void setImageId(Integer imageId) {
    this.imageId = imageId;
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

  public Integer getAdviseId() {
    return adviseId;
  }

  public void setAdviseId(Integer adviseId) {
    this.adviseId = adviseId;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Date getUploadTime() {
    return uploadTime;
  }

  public void setUploadTime(Date uploadTime) {
    this.uploadTime = uploadTime;
  }

  public Integer getUploadUserId() {
    return uploadUserId;
  }

  public void setUploadUserId(Integer uploadUserId) {
    this.uploadUserId = uploadUserId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}

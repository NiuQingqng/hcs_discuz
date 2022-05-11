package com.niuniu.hcs_discuz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niuniu.hcs_discuz.constant.NotificationCst;
import com.niuniu.hcs_discuz.dao.ICommentDao;
import com.niuniu.hcs_discuz.model.Comment;
import com.niuniu.hcs_discuz.model.Notification;
import com.niuniu.hcs_discuz.model.Topic;
import com.niuniu.hcs_discuz.model.User;
import com.niuniu.hcs_discuz.service.ICommentService;
import com.niuniu.hcs_discuz.service.INotificationService;
import com.niuniu.hcs_discuz.service.ITopicService;
import com.niuniu.hcs_discuz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/14
 */
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    ICommentDao commentDao;

    @Autowired
    ITopicService topicService;

    @Autowired
    private IUserService userService;

    @Autowired
    INotificationService notificationService;

    @Override
    public PageInfo<Comment> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> commentList = commentDao.findAll();
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        return pageInfo;
    }

    @Override
    public Comment findOneCommentById(Integer commentId) {
        return commentDao.findOneCommentById(commentId);
    }

    @Override
    public PageInfo<Comment> findCommentByCondition(Comment comment, Integer pageNum, Integer pageSize) {
        if (null != comment.getContent() && !"".equals(comment.getContent()))
            comment.setContent("%" + comment.getContent() + "%");
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> commentList = commentDao.findCommentByCondition(comment);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        return pageInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addComment(Comment comment) {
        //TopicId和RootId只能有一个
        //对帖子的回复，帖子回复数加一
        if (null != comment.getTopicId()) {
            Topic topic = topicService.findOneTopicById(comment.getTopicId());
            topic.setViewCount(topic.getViewCount() - 1); //因为在查询一个时增加了一次浏览次数，所以在此减去
            topic.setCommentCount(topic.getCommentCount() + 1); //评论数加1
            topicService.updateCount(topic);
            //回复帖子添加通知
            Notification notification = new Notification(topic.getUserId(), comment.getUserId(), NotificationCst.COMMENT_TOPIC, topic.getTopicId());
//            notification.setToUserId(topic.getUserId());
//            notification.setFromUserId(comment.getUserId());
//            notification.setSubject(NotificationCst.COMMENT_TOPIC);
//            notification.setTopicId(topic.getTopicId());
            notificationService.addNotification(notification);
        }
        //对回复的评论，回复评论数加一
        else if (null != comment.getRootId()) {
            Comment rootComment = this.findOneCommentById(comment.getRootId());
            rootComment.setCommentCount(rootComment.getCommentCount() + 1); //评论数加1
            this.updateCount(rootComment);
            //todo 评论回复添加通知
        }
        //用户发表的评论数加1
        User user = userService.findOneUserById(comment.getUserId());
        user.setCommentCount(user.getCommentCount() + 1);
        userService.updateCount(user);
        return commentDao.addComment(comment) > 0 ? true : false;
    }

    @Override
    public Boolean updateComment(Comment comment) {
        return commentDao.updateComment(comment) > 0 ? true : false;
    }

    @Override
    public Boolean updateCount(Comment comment) {
        return commentDao.updateCount(comment) > 0 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean delComment(Integer commentId) {
        Comment comment = this.findOneCommentById(commentId);
        //TopicId和RootId只能有一个
        //对帖子的回复删除，帖子回复数减一
        if (null != comment.getTopicId()) {
            Topic topic = topicService.findOneTopicById(comment.getTopicId());
            topic.setViewCount(topic.getViewCount() - 1); //因为在查询一个时增加了一次浏览次数，所以在此减去
            topic.setCommentCount(topic.getCommentCount() - 1); //评论数减1
            topicService.updateCount(topic);
        }
        //对回复的评论的删除，回复评论数减一
        else if (null != comment.getRootId()) {
            Comment rootComment = this.findOneCommentById(comment.getRootId());
            rootComment.setCommentCount(rootComment.getCommentCount() - 1); //评论数减1
            this.updateCount(rootComment);
        }
        //用户发表的评论数减1
        User user = userService.findOneUserById(comment.getUserId());
        user.setCommentCount(user.getCommentCount() - 1);
        userService.updateCount(user);
        return commentDao.delComment(commentId) > 0 ? true : false;
    }

}

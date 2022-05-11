package com.niuniu.hcs_discuz.service.impl;

import com.niuniu.hcs_discuz.constant.NotificationCst;
import com.niuniu.hcs_discuz.dao.IThumbDao;
import com.niuniu.hcs_discuz.model.*;
import com.niuniu.hcs_discuz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/14
 */
@Service
public class ThumbServiceImpl implements IThumbService {

    @Autowired
    IThumbDao thumbDao;

    @Autowired
    ITopicService topicService;

    @Autowired
    ICommentService commentService;

    @Autowired
    INotificationService notificationService;

    @Override
    public Thumb findThumbById(Integer thumbId) {
        return thumbDao.findThumbById(thumbId);
    }

    @Override
    public List<Thumb> findThumbByCondition(Thumb thumb) {
        return thumbDao.findThumbByCondition(thumb);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addThumb(Thumb thumb) {
        if (null != thumb.getCommentId()) {
            Comment comment = commentService.findOneCommentById(thumb.getCommentId());
            comment.setThumbCount(comment.getThumbCount() + 1); //赞加1
            commentService.updateCount(comment);
            //todo 赞了评论添加通知
        } else if (null != thumb.getTopicId()) {
            Topic topic = topicService.findOneTopicById(thumb.getTopicId());
            topic.setViewCount(topic.getViewCount() - 1); //因为在查询一个时增加了一次浏览次数，所以在此减去
            topic.setThumbCount(topic.getThumbCount() + 1); //赞加1
            topicService.updateCount(topic);
            //todo 赞了帖子添加通知
            Notification notification = new Notification(topic.getUserId(), thumb.getFromUserId(), NotificationCst.THUMB_TOPIC, topic.getTopicId());
//            notification.setToUserId(topic.getUserId());
//            notification.setFromUserId(thumb.getFromUserId());
//            notification.setSubject(NotificationCst.THUMB_TOPIC);
//            notification.setTopicId(topic.getTopicId());
            notificationService.addNotification(notification);
        }
        return thumbDao.addThumb(thumb) > 0 ? true : false;
    }

    @Override
    public Integer delThumb(Integer thumbId) {
        return thumbDao.delThumb(thumbId);
    }

}

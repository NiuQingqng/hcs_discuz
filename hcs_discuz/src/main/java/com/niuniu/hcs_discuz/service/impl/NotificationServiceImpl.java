package com.niuniu.hcs_discuz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niuniu.hcs_discuz.dao.INotificationDao;
import com.niuniu.hcs_discuz.model.Notification;
import com.niuniu.hcs_discuz.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/4/13
 */
@Service
public class NotificationServiceImpl implements INotificationService {

    @Autowired
    INotificationDao notificationDao;

    @Override
    public List<Notification> findAll() {
        return notificationDao.findAll();
    }

    @Override
    public Notification findOneNotificationById(Integer notificationId) {
        return notificationDao.findOneNotificationById(notificationId);
    }

    @Override
    public PageInfo<Notification> findNotificationByCondition(Notification notification, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notification> notificationList = notificationDao.findNotificationByCondition(notification);
        PageInfo<Notification> pageInfo = new PageInfo<>(notificationList);
        return pageInfo;
    }

    @Override
    public Boolean addNotification(Notification notification) {
        if (notification.getToUserId().equals(notification.getFromUserId())) {
            return true;
        }
        return notificationDao.addNotification(notification) > 0 ? true : false;
    }

    @Override
    public Boolean handleNotification(Integer toUserId) {
        return notificationDao.handleNotification(toUserId) > 0 ? true : false;
    }

}

package com.niuniu.hcs_discuz.service;

import com.github.pagehelper.PageInfo;
import com.niuniu.hcs_discuz.model.Notification;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/4/13
 */
public interface INotificationService {

    List<Notification> findAll();

    Notification findOneNotificationById(Integer notificationId);

    PageInfo<Notification> findNotificationByCondition(Notification notification, Integer pageNum, Integer pageSize);

    /**
     * 新增通知
     *
     * @author
     * @date
     */
    Boolean addNotification(Notification notification);

    /**
     * 编辑分类信息
     *
     * @author
     * @date
     */
    Boolean handleNotification(Integer toUserId);

}

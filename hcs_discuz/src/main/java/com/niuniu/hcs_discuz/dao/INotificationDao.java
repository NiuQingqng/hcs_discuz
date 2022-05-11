package com.niuniu.hcs_discuz.dao;

import com.niuniu.hcs_discuz.model.Notification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/14
 */
@Repository
public interface INotificationDao {

    List<Notification> findAll();

    Notification findOneNotificationById(Integer notificationId);

    List<Notification> findNotificationByCondition(Notification notification);

    /**
     * 新增通知
     *
     * @author
     * @date
     */
    Integer addNotification(Notification notification);

    /**
     * 编辑分类信息
     *
     * @author
     * @date
     */
    Integer handleNotification(Integer toUserId);

}

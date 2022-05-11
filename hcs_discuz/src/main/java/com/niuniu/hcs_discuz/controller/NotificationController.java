package com.niuniu.hcs_discuz.controller;

import com.github.pagehelper.PageInfo;
import com.niuniu.hcs_discuz.model.Notification;
import com.niuniu.hcs_discuz.model.Topic;
import com.niuniu.hcs_discuz.service.INotificationService;
import com.niuniu.hcs_discuz.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/4/13
 */
@RestController
@RequestMapping("notification")
public class NotificationController {

    @Autowired
    INotificationService notificationService;

//    @RequestMapping("findall")
//    public List<Notification> findAll() {
//        return notificationService.findAll();
//    }
//
//    @RequestMapping("findone")
//    public Notification findOneNotificationById(Integer notificationId) {
//        return notificationService.findOneNotificationById(notificationId);
//    }

    @RequestMapping("findby")
    public JsonResult findNotificationByCondition(@RequestBody Notification notification, Integer pageNum, Integer pageSize) {
        JsonResult result = new JsonResult();
        result.setSuccess();
        PageInfo<Notification> notifications = notificationService.findNotificationByCondition(notification, pageNum, pageSize);
        result.addDatas("notificationListWithPage", notifications);
        return result;
    }

//    @RequestMapping("add")
//    public Integer addNotification(Notification notification) {
//        return notificationService.addNotification(notification);
//    }

    @RequestMapping("handle")
    public JsonResult handleNotification(Integer toUserId) {
        JsonResult result = new JsonResult();
        if (notificationService.handleNotification(toUserId)) {
            result.setSuccess();
        } else {
            result.setSysErr();
        }
        return result;
    }

}

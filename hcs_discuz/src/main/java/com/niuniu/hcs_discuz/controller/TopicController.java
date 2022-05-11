package com.niuniu.hcs_discuz.controller;

import com.github.pagehelper.PageInfo;
import com.niuniu.hcs_discuz.model.Topic;
import com.niuniu.hcs_discuz.service.ITopicService;
import com.niuniu.hcs_discuz.utils.JsonResult;
import com.niuniu.hcs_discuz.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author niuniu
 * @date 2022/3/2
 */
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private ITopicService topicService;

    @Autowired
    TokenUtil tokenUtil;

    //查询列表
    @RequestMapping("/findall")
    public JsonResult findAll(Integer pageNum, Integer pageSize) {
        JsonResult result = new JsonResult();
        result.setSuccess();
        PageInfo<Topic> topics = topicService.findAll(pageNum, pageSize);
        result.addDatas("topicListWithPage", topics);
        return result;
    }

    //查询列表
    @RequestMapping("/findallbyhot")
    public JsonResult findAllByHot(Integer pageNum, Integer pageSize) {
        JsonResult result = new JsonResult();
        result.setSuccess();
        PageInfo<Topic> topics = topicService.findAllByHot(pageNum, pageSize);
        result.addDatas("topicListWithPage", topics);
        return result;
    }

    //根据条件查询列表
    @RequestMapping("/findonebycondition")
    public JsonResult findTopicByCondition(@RequestBody Topic topic, Integer pageNum, Integer pageSize) {
        JsonResult result = new JsonResult();
        result.setSuccess();
        PageInfo<Topic> topics = topicService.findTopicByCondition(topic, pageNum, pageSize);
        result.addDatas("topicListWithPage", topics);
        return result;
    }

    //查询单条信息
    @RequestMapping("/findone")
    public JsonResult findOneTopicById(Integer topicId) {
        JsonResult result = new JsonResult();
        Topic topic = topicService.findOneTopicById(topicId);
        if (topic != null) {
            result.setSuccess();
            result.addDatas("topicInfo", topic);
        } else {
            result.setFail("未找到此条帖子！");
        }
        return result;
    }

    //删除信息
    @RequestMapping("/deltopic")
    public JsonResult delTopic(Integer topicId) {
        JsonResult result = new JsonResult();
        if (topicService.delTopic(topicId)) {
            result.setSuccess();
        } else {
            result.setSysErr();
        }
        return result;
    }

    //修改
    @RequestMapping("/updatetopic")
    public JsonResult updateTopic(@RequestBody Topic topic) {
        JsonResult result = new JsonResult();
        if (topicService.updateTopic(topic)) {
            result.setSuccess();
        } else {
            result.setSysErr();
        }
        return result;
    }

    //新增信息
    @RequestMapping("/addtopic")
    public JsonResult addTopic(@RequestBody Topic topic, @RequestHeader(value = "token") String token) {
        JsonResult result = new JsonResult();
        topic.setUserId(tokenUtil.getCacheUser(token).getUserId());
        if (topicService.addTopic(topic)) {
            result.setSuccess();
        } else {
            result.setSysErr();
        }
        return result;
    }

}

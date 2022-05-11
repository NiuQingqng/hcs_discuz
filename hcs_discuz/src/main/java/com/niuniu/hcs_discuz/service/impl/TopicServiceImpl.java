package com.niuniu.hcs_discuz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niuniu.hcs_discuz.constant.CommonCst;
import com.niuniu.hcs_discuz.dao.ITopicDao;
import com.niuniu.hcs_discuz.model.Topic;
import com.niuniu.hcs_discuz.model.User;
import com.niuniu.hcs_discuz.service.ICategoryService;
import com.niuniu.hcs_discuz.service.ITopicService;
import com.niuniu.hcs_discuz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/2
 */
@Service
public class TopicServiceImpl implements ITopicService {

    @Autowired
    private ITopicDao topicDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICategoryService categoryService;

    @Override
    public PageInfo<Topic> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Topic> topicList = topicDao.findAll();
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        return pageInfo;
    }

    @Override
    public PageInfo<Topic> findAllByHot(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Topic> topicList = topicDao.findAllByHot();
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        return pageInfo;
    }

    @Override
    public Topic findOneTopicById(Integer topicId) {
        Topic topic = topicDao.findOneTopicById(topicId);
        if (null == topic) {
            return null;
        }
        //浏览数加一
        topic.setViewCount(topic.getViewCount() + 1);
        this.updateCount(topic);
        return topic;
    }

    @Override
    public PageInfo<Topic> findTopicByCondition(Topic topic, Integer pageNum, Integer pageSize) {
        if (null != topic.getTitle() && !"".equals(topic.getTitle()))
            topic.setTitle("%" + topic.getTitle() + "%");
        PageHelper.startPage(pageNum, pageSize);
        List<Topic> topicList = topicDao.findTopicByCondition(topic);
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        return pageInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addTopic(Topic topic) {
        //用户帖子数量加一
        User user = userService.findOneUserById(topic.getUserId());
        user.setTopicCount(user.getTopicCount() + 1);
        userService.updateCount(user);
        //分类下帖子数量加一
        categoryService.updateCount(topic.getCategoryId(), CommonCst.COUNT_INCREASE);
        return topicDao.addTopic(topic) > 0 ? true : false;
    }

    @Override
    public Boolean updateTopic(Topic topic) {
        return topicDao.updateTopic(topic) > 0 ? true : false;
    }

    @Override
    public Boolean updateCount(Topic topic) {
        return topicDao.updateCount(topic) > 0 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean delTopic(Integer topicId) {
        Topic topic = this.findOneTopicById(topicId);
        //用户帖子数量减一
        User user = userService.findOneUserById(topic.getUserId());
        user.setTopicCount(user.getTopicCount() - 1);
        userService.updateCount(user);
        //分类下帖子数量减一
        categoryService.updateCount(topic.getCategoryId(), CommonCst.COUNT_DECREASE);
        return topicDao.delTopic(topicId) > 0 ? true : false;
    }

}

package com.niuniu.hcs_discuz.service;

import com.github.pagehelper.PageInfo;
import com.niuniu.hcs_discuz.model.Topic;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/2
 */
public interface ITopicService {

    PageInfo<Topic> findAll(Integer pageNum, Integer pageSize);

    PageInfo<Topic> findAllByHot(Integer pageNum, Integer pageSize);

    Topic findOneTopicById(Integer topicId);

    /**
     * 使用条件查询帖子
     *
     * @author
     * @date
     */
    PageInfo<Topic> findTopicByCondition(Topic topic, Integer pageNum, Integer pageSize);

    /**
     * 添加帖子
     *
     * @author
     * @date
     */
    Boolean addTopic(Topic topic);

    /**
     * 编辑帖子
     *
     * @author
     * @date
     */
    Boolean updateTopic(Topic topic);

    /**
     * 被浏览、收到点赞、评论时更新数量
     *
     * @author
     * @date
     */
    Boolean updateCount(Topic topic);

    /**
     * 假删除帖子
     *
     * @author
     * @date
     */
    Boolean delTopic(Integer topicId);

}

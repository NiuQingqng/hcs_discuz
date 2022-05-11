package com.niuniu.hcs_discuz.dao;

import com.github.pagehelper.PageInfo;
import com.niuniu.hcs_discuz.model.Topic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/1
 */
@Repository
public interface ITopicDao {

    List<Topic> findAll();

    List<Topic> findAllByHot();

    Topic findOneTopicById(Integer topicId);

    /**
     * 使用条件查询帖子
     *
     * @author
     * @date
     */
    List<Topic> findTopicByCondition(Topic topic);

    /**
     * 添加帖子
     *
     * @author
     * @date
     */
    Integer addTopic(Topic topic);

    /**
     * 编辑帖子
     *
     * @author
     * @date
     */
    Integer updateTopic(Topic topic);

    /**
     * 被浏览、收到点赞、评论时更新数量
     *
     * @author
     * @date
     */
    Integer updateCount(Topic topic);

    /**
     * 假删除帖子
     *
     * @author
     * @date
     */
    Integer delTopic(Integer topicId);

}

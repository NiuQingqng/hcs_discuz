package com.niuniu.hcs_discuz.dao;

import com.niuniu.hcs_discuz.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/1
 */
@Repository
public interface ICommentDao {

    List<Comment> findAll();

    Comment findOneCommentById(Integer commentId);

    /**
     * 使用条件查询评论
     *
     * @author
     * @date
     */
    List<Comment> findCommentByCondition(Comment comment);

    /**
     * 添加评论
     *
     * @author
     * @date
     */
    Integer addComment(Comment comment);

    /**
     * 编辑评论
     *
     * @author
     * @date
     */
    Integer updateComment(Comment comment);

    /**
     * 被浏览、收到点赞、评论时更新数量
     *
     * @author
     * @date
     */
    Integer updateCount(Comment comment);

    /**
     * 假删除评论
     *
     * @author
     * @date
     */
    Integer delComment(Integer commentId);

}

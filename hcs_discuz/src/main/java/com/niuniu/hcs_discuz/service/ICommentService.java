package com.niuniu.hcs_discuz.service;

import com.github.pagehelper.PageInfo;
import com.niuniu.hcs_discuz.model.Comment;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/14
 */
public interface ICommentService {
    PageInfo<Comment> findAll(Integer pageNum, Integer pageSize);

    Comment findOneCommentById(Integer commentId);

    /**
     * 使用条件查询评论
     *
     * @author
     * @date
     */
    PageInfo<Comment> findCommentByCondition(Comment comment, Integer pageNum, Integer pageSize);

    /**
     * 添加评论
     *
     * @author
     * @date
     */
    Boolean addComment(Comment comment);

    /**
     * 编辑评论
     *
     * @author
     * @date
     */
    Boolean updateComment(Comment comment);

    /**
     * 被浏览、收到点赞、评论时更新数量
     *
     * @author
     * @date
     */
    Boolean updateCount(Comment comment);

    /**
     * 假删除评论
     *
     * @author
     * @date
     */
    Boolean delComment(Integer commentId);
}

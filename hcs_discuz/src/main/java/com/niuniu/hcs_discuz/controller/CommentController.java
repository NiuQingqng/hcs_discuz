package com.niuniu.hcs_discuz.controller;

import com.github.pagehelper.PageInfo;
import com.niuniu.hcs_discuz.model.Category;
import com.niuniu.hcs_discuz.model.Comment;
import com.niuniu.hcs_discuz.model.User;
import com.niuniu.hcs_discuz.service.ICommentService;
import com.niuniu.hcs_discuz.utils.JsonResult;
import com.niuniu.hcs_discuz.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/14
 */
@ResponseBody
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    ICommentService commentService;

    @Autowired
    TokenUtil tokenUtil;

    //查询评论列表
    @RequestMapping("/findall")
    public JsonResult findAll(Integer pageNum, Integer pageSize) {
        JsonResult result = new JsonResult();
        result.setSuccess();
        PageInfo<Comment> comments = commentService.findAll(pageNum, pageSize);
        result.addDatas("commentListWithPage", comments);
        return result;
    }

    //查询单条评论信息
    @RequestMapping("/findone")
    public JsonResult findOneCommentById(Integer commentId) {
        JsonResult result = new JsonResult();
        Comment comment = commentService.findOneCommentById(commentId);
        if (comment != null) {
            result.setSuccess();
            result.addDatas("commentInfo", comment);
        } else {
            result.setFail("未找到此条评论！");
        }
        return result;
    }

    //删除用户
    @RequestMapping("/delcomment")
    public JsonResult delComment(Integer commentId) {
        JsonResult result = new JsonResult();
        if (commentService.delComment(commentId)) {
            result.setSuccess();
        } else {
            result.setSysErr();
        }
        return result;
    }

    //修改
    @RequestMapping("/updatecomment")
    public JsonResult updateComment(@RequestBody Comment comment) {
        JsonResult result = new JsonResult();
        if (commentService.updateComment(comment)) {
            result.setSuccess();
        } else {
            result.setSysErr();
        }
        return result;
    }

    //新增信息
    @RequestMapping("/addcomment")
    public JsonResult addComment(@RequestBody Comment comment, @RequestHeader(value = "token") String token) {
        JsonResult result = new JsonResult();
        comment.setUserId(tokenUtil.getCacheUser(token).getUserId());
        if (commentService.addComment(comment)) {
            result.setSuccess();
        } else {
            result.setSysErr();
        }
        return result;
    }

    @RequestMapping("/findbycondition")
    public JsonResult findCommentByCondition(@RequestBody Comment comment, Integer pageNum, Integer pageSize) {
        JsonResult result = new JsonResult();
        result.setSuccess();
        PageInfo<Comment> comments = commentService.findCommentByCondition(comment, pageNum, pageSize);
        result.addDatas("commentListWithPage", comments);
        return result;
    }

    //更新点赞数量等信息
//    @RequestMapping("/updatecount")
//    public Integer updateCount(Comment comment) {
//        return commentService.updateCount(comment);
//    }

}

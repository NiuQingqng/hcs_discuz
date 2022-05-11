package com.niuniu.hcs_discuz.service;

import com.github.pagehelper.PageInfo;
import com.niuniu.hcs_discuz.model.User;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/1
 */
public interface IUserService {
    PageInfo<User> findAll(Integer pageNum, Integer pageSize);

    User findOneUserById(Integer userId);

    /**
     * 使用条件查询用户
     *
     * @author
     * @date
     */
    PageInfo<User> findUserByCondition(User user, Integer pageNum, Integer pageSize);

    /**
     * 添加用户
     *
     * @author
     * @date
     */
    Boolean addUser(User user);

    /**
     * 修改用户资料
     *
     * @author
     * @date
     */
    Boolean updateUser(User user);

    /**
     * 验证登录，并在登陆时更新登陆时间和ip
     *
     * @author
     * @date
     */
    User loginUser(User user);

    /**
     * 发帖、评论、收到点赞时更新数量
     *
     * @author
     * @date
     */
    Boolean updateCount(User user);

    /**
     * 假删除用户或者禁用或者启用用户
     *
     * @author
     * @date
     */
    Boolean delOrDisableUser(Integer userId, Boolean isToDel);

    // 重置密码
    Boolean resetPassword(String email, String newpwd);

}

package com.niuniu.hcs_discuz.dao;

import com.niuniu.hcs_discuz.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/1
 */
@Repository
public interface IUserDao {

    List<User> findAll();

    User findOneUserById(Integer userId);

    /**
     * 根据用户名和密码查询用户,用来验证登录
     *
     * @author
     * @date
     */
    User findOneUserByUnameAndPwd(User user);

    /**
     * 使用条件查询用户
     *
     * @author
     * @date
     */
    List<User> findUserByCondition(User user);

    /**
     * 添加用户
     *
     * @author
     * @date
     */
    Integer addUser(User user);

    /**
     * 修改用户资料
     *
     * @author
     * @date
     */
    Integer updateUser(User user);

    /**
     * 登陆时更新登陆时间和ip
     *
     * @author
     * @date
     */
    Integer updateUserLoginInfo(User user);

    /**
     * 发帖、评论、收到点赞时更新数量
     *
     * @author
     * @date
     */
    Integer updateCount(User user);

}

package com.niuniu.hcs_discuz.constant;

/**
 * @author niuniu
 * @date 2022/3/16
 */
public interface UserCst {

    Integer UN_LOGIN = 300; //未登录错误码

    String LOGIN_SUCCESS = "登陆成功！";
    String PWD_ERR = "用户名或密码错误！";
    String NULL_ERR = "用户名或密码不可为空！";
    String EMAIL_NULL_ERR = "邮箱或邮箱验证码不可为空！";
    String EMAIL_CODE_ERR = "邮箱验证码不正确！";
    String IMG_CODE_NULL_ERR = "验证码不能为空！";
    String IMG_CODE_ERR = "验证码不正确！";
    String NEWPWD_NULL_ERR = "密码不能为空！";
    String USERNAME_IS_EXIST = "用户名已被占用！";

    Integer USER_STATUS_ENABLE = 1;
    Integer USER_STATUS_DISABLE = 2;
    Integer USER_STATUS_DEL = 3;

    //用户身份
    Integer USER_ROLE_USER = 1;
    Integer USER_ROLE_TEACHER = 2;
    Integer USER_ROLE_ADMIN = 3;

}

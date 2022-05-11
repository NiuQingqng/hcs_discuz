package com.niuniu.hcs_discuz.controller;

import com.niuniu.hcs_discuz.model.User;
import com.niuniu.hcs_discuz.service.IUserService;
import com.niuniu.hcs_discuz.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 获取图片验证码和发送邮件验证码
 *
 * @author niuniu
 * @date 2022/3/16
 */
@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    ImgCodeUtil imgCodeUtil;

    @Autowired
    RedisCache redisCache;

    @Autowired
    IUserService userService;

    // 图片验证码
    @RequestMapping("/findimg")
    public void findImg(HttpServletResponse response, String imgcodeid) throws IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
        response.setHeader("imgID", "");
        //创建验证码
        BufferedImage codeImg = imgCodeUtil.createImgCode(imgcodeid);
        //响应图片
        ImageIO.write(codeImg, "png", response.getOutputStream());
    }

    @RequestMapping("/test")
    public String codeTest(HttpServletResponse response, String imgcodeid) throws IOException {
        return "111";
    }

    // 注册验证码
    @RequestMapping("/regsendemail")
    public JsonResult regSendEmail(String toEmail) {
        JsonResult result = new JsonResult();
        // 判断邮箱是否已经存在
        User condition = new User();
        condition.setEmail(toEmail);
        if (userService.findUserByCondition(condition, 0, 10).getList().size() > 0) {
            result.setFail("邮箱已经被注册！");
            return result;
        }
        return this.sendEmail(toEmail);
    }

    // 已存在邮箱验证码
    @RequestMapping("/existsendemail")
    public JsonResult existSendEmail(String toEmail) {
        JsonResult result = new JsonResult();
        // 判断邮箱是否已经存在
        User condition = new User();
        condition.setEmail(toEmail);
        if (userService.findUserByCondition(condition, 0, 10).getList().size() > 0) {
            return this.sendEmail(toEmail);
        }
        result.setFail("此邮箱未注册！");
        return result;
    }

    // 发送验证码
    @RequestMapping("/sendemail")
    public JsonResult sendEmail(String toEmail) {
        JsonResult result = new JsonResult();
        // 邮箱判空
        if (CommonUtil.strIsNull(toEmail)) {
            result.setFail("邮箱不可为空！");
            return result;
        }
        String title = "HAUE Center Square";
        String content = "你的验证码是：";
        String tail = "，10分钟内有效。";
        String msgCode;
        //生成随机验证码（100000-999999之间）
        msgCode = (int) (Math.random() * 900000 + 100000) + "";
        System.out.println(msgCode);
        redisCache.set(toEmail, msgCode, 60 * 10); //存储验证码到Redis,并设置过期时间
        if (msgCode.equals(redisCache.get(toEmail))) {
            MailSender mailSender = new MailSender(title, content + msgCode + tail, toEmail);
            mailSender.send();     //发送动作
            result.setSuccess();
            return result;
        }
        result.setFail();
        return result;
    }

}

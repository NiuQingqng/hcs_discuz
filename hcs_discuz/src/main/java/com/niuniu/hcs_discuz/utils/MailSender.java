package com.niuniu.hcs_discuz.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * 邮件发送器
 *
 * @author niuniu
 * @date 2022/2/27
 */
public class MailSender {

    private String hostName = "smtp.163.com";
    private String name = "HAUE Center Square";
    private String fromEmail = "你的发送邮件的邮箱";
    private String password = "你的邮箱密码";
    private String title;
    private String content;
    private String toEmail;

    public MailSender(String title, String content, String toEmail) {
        this.title = title;
        this.content = content;
        this.toEmail = toEmail;
    }

    public void send() {
        sendEmailThread sendEmailThread = new sendEmailThread();
        sendEmailThread.start();
    }

    class sendEmailThread extends Thread {

        @Override
        public void run() {
            HtmlEmail email = new HtmlEmail();//创建一个HtmlEmail实例对象
            email.setHostName(hostName);//邮箱的SMTP服务器
            email.setCharset("utf-8");//设置发送的字符类型
            email.setAuthentication(fromEmail, password);//设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
            email.setSubject(title);//设置发送主题
            try {
                email.addTo(toEmail);//设置收件人
                email.setFrom(fromEmail, name);//发送人的邮箱为自己的，用户名可以随便填
                email.setMsg(content);//设置发送内容
                email.send();//进行发送
                System.out.println("已发送！");
            } catch (EmailException e) {
                e.printStackTrace();
            }
        }
    }

}

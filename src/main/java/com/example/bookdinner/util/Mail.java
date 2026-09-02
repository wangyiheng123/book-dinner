package com.example.bookdinner.util;

import com.example.bookdinner.controller.exception.SendMessageException;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/*
发送邮件验证码的工具类
 */
public class Mail {
    private static final String codeBefore = "【食全食美】您正在注册，验证码为";

    private static final String codeAfter = ",请不要将验证码泄露给其他人。";

    private static final String userNameBefore = "【食全食美】您已注册成功，您的用户名为";

    private static final String passwordBefore = ",您的密码为";

    private static final String passwordAfter = ",请您不要将密码泄露给他人，以免造成不良后果。";
    /*
    发送邮件验证码信息
     */
    public static void sendMail(String email,String code) throws SendMessageException{
        String sendMessage = codeBefore + code + codeAfter;
        Properties prop = new Properties();
        prop.put("mail.transport.protocol","smtp");
        prop.put("mail.smtp.host","smtp.qq.com");
        Session session = Session.getInstance(prop);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("11111@qq.com"));
            message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(email));
            message.setSubject("验证码通知");
            message.setSentDate(new Date());
            message.setText(sendMessage);
            message.saveChanges();
            Transport transport = session.getTransport();
            transport.connect("11111@qq.com","iiu");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            throw new SendMessageException("发送验证码失败！");
        }
    }

    /*
    发送密码信息
     */
    public static void sendPassword(String email,String password) throws SendMessageException{
        String sendMessage = userNameBefore + email + passwordBefore + password + passwordAfter;
        Properties prop = new Properties();
        prop.put("mail.transport.protocol","smtp");
        prop.put("mail.smtp.host","smtp.qq.com");
        Session session = Session.getInstance(prop);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("1296433453@qq.com"));
            message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(email));
            message.setSubject("用户名与密码");
            message.setSentDate(new Date());
            message.setText(sendMessage);
            message.saveChanges();
            Transport transport = session.getTransport();
            transport.connect("1296433453@qq.com","iczdujckylyegfgi");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            throw new SendMessageException("发送密码失败！");
        }
    }
}

package com.hit.narration.service.impl;

import com.hit.narration.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    private final JavaMailSender mailSender;

    private final String from = "mahdimaleki16@gmail.com";
    private final String personal = "Narration Support";

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendCreationAccountEmail(UserDTO user) throws UnsupportedEncodingException, MessagingException {
        log.debug("Sending creation email to '{}'", user.getEmail());
        String subject = "New Account Created – [Narration]";
        String content = "<p>Hello,</p>"
                + "<p>Dear " + user.getFirstName() + " " + user.getLastName() + ",</p>"
                + "<p>, A new account has been created for you in Narration.</p>"
                + "<br>"
                + "<p>Your User name is:" + user.getEmail() + ",</p>"
                + "<br>"
                + "<p>To start using the Narration, log in below link: </p>"
                + "<p><a href=\"" + "link" + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
        sendEmail(user.getEmail(), subject, content, false, true);
        log.debug("Sent creation email to '{}'", user.getEmail());
    }

    @Async
    public void sendActivationEmail(UserDTO user, String link) throws UnsupportedEncodingException, MessagingException {
        log.debug("Sending activation email to '{}'", user.getEmail());
        String subject = "New Account Created – [Narration]";
        String content = "<p>Hello,</p>"
                + "<p>Dear " + user.getFirstName() + " " + user.getLastName() + ",</p>"
                + "<br>"
                + "<p>We need to verify your email address.</p>"
                + "<br>"
                + "<p>Click the link below to active your account:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendResetPasswordMail(UserDTO user, String link) throws UnsupportedEncodingException, MessagingException {
        log.debug("Sending reset password email to User '{}'", user.getEmail());
        String subject = "Here's the link to reset your password";
        String content = "<p>Hello,</p>"
                + "<p>Dear " + user.getFirstName() + " " + user.getLastName() + ",</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        sendEmail(user.getEmail(), subject, content, false, true);
        log.debug("Sent reset password email to User '{}'", user.getEmail());
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) throws MessagingException, UnsupportedEncodingException {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart, isHtml, to, subject, content);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(to);
        helper.setFrom(from, personal);
        helper.setSubject(subject);
        helper.setText(content, isHtml);
        mailSender.send(message);
    }
}

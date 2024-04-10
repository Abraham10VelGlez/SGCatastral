/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.services;

/**
 *
 * @author UAEM
 */
import java.util.Map;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Component("emailService")
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    
    public void sendEmailFromTemplate(String toEmailAddresses, String fromEmailAddress, String subject, Map model, String template) throws Exception {
        sendEmailTemplate(toEmailAddresses, fromEmailAddress, subject, null, null, model, template);
    }
    
    private void sendEmailTemplate(final String toEmailAddresses, final String fromEmailAddress, final String subject, final String attachmentPath, final String attachmentName, final Map model, final String template) throws Exception {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setTo(toEmailAddresses);
                message.setFrom(new InternetAddress(fromEmailAddress));
                message.setSubject(subject);
                String body = VelocityEngineUtils.mergeTemplateIntoString(EmailService.this.velocityEngine, template, "UTF-8", model);
                message.setText(body, true);
                if (!StringUtils.isBlank(attachmentPath)) {
                    FileSystemResource file = new FileSystemResource(attachmentPath);
                    message.addAttachment(attachmentName, file);
                }
            }
        };
        
        this.mailSender.send(preparator);
        
    }
    
}

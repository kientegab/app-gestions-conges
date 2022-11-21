/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.config.ApplicationProperties;
import bf.mfptps.appgestionsconges.entities.Agent;
import bf.mfptps.appgestionsconges.entities.Notification;
import bf.mfptps.appgestionsconges.repositories.AgentRepository;
import bf.mfptps.appgestionsconges.repositories.NotificationAgentRepository;
import java.nio.charset.StandardCharsets;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 *
 * @author Fatogoma HEBIE <fat19ebie@gmail.com>
 */
@Slf4j
@Service
public class EmailServiceImpl {
    
    public final JavaMailSenderImpl javaMailSender;
    public final SpringTemplateEngine templateEngine;
    public final NotificationAgentRepository notificationAgentRepository;
    public final ApplicationProperties applicationProperties;
    public final AgentRepository agentRepository;
	
    public EmailServiceImpl(NotificationAgentRepository notificationAgentRepository
			, JavaMailSenderImpl javaMailSender
			, SpringTemplateEngine templateEngine
			, ApplicationProperties applicationProperties
                        , AgentRepository agentRepository) {
		this.notificationAgentRepository = notificationAgentRepository;
		this.javaMailSender = javaMailSender;
		this.templateEngine = templateEngine;
		this.applicationProperties = applicationProperties;
                this.agentRepository = agentRepository;
	}
	
	@Async
	public void sendEmail(Notification notification, Agent agent) {
		Context context = new Context();
		context.setVariable("notification", notification);
		String content = templateEngine.process("notification", context);
		String to = agent.getEmail();
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
        	MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
        	message.setTo(to);
			message.setFrom(applicationProperties.getMail().getFrom());
			message.setSubject(notification.getObjet());
			message.setText(content, true);
			javaMailSender.send(mimeMessage);
			log.debug("Sent e-mail to User '{}'", to);
		} catch (Exception e) {
			log.warn("E-mail could not be sent to user '{}'", to, e);
		} 
    }
}

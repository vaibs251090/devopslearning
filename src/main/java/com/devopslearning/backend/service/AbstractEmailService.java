package com.devopslearning.backend.service;

import com.devopslearning.web.domain.frontend.FeedbackPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.to.address}")
    private String defaultToAddress;
    protected SimpleMailMessage prepareSimpleMailMessageFromFeedbackPojo(FeedbackPojo feedbackPojo){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(defaultToAddress);
        message.setFrom(feedbackPojo.getEmail());
        message.setSubject("[Devops Buddy]: Feedback received by - " +feedbackPojo.getFirstName()+" "+feedbackPojo.getLastName()+ " !!");
        message.setText(feedbackPojo.getFeedback());
        return message;
    }

    @Override
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo) {
        sendGenericEmailMessage(prepareSimpleMailMessageFromFeedbackPojo(feedbackPojo));
    }


}

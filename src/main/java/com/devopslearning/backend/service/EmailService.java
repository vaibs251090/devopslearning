package com.devopslearning.backend.service;

import com.devopslearning.web.domain.frontend.FeedbackPojo;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    public void sendFeedbackEmail(FeedbackPojo feedbackPojo);

    public void sendGenericEmailMessage(SimpleMailMessage message);
}

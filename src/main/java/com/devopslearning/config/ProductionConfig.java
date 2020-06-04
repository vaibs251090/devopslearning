package com.devopslearning.config;

import com.devopslearning.backend.service.EmailService;
import com.devopslearning.backend.service.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("prod")
@PropertySource("file:${user.home}/devops-learning/.devopslearning/application-prod.properties")
//@PropertySource("file:///${user.home}/devops-learning/.devopslearning/application-prod.properties")
public class ProductionConfig {

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }

}

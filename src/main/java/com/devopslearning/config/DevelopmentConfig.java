package com.devopslearning.config;

import com.devopslearning.backend.service.EmailService;
import com.devopslearning.backend.service.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("dev")
@PropertySource("file:${user.home}/devops-learning/.devopslearning/application-dev.properties")
//@PropertySource("file:///${user.home}/devops-learning/.devopslearning/application-dev.properties")
public class DevelopmentConfig {

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }

}

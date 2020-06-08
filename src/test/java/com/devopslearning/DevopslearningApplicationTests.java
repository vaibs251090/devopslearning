package com.devopslearning;

import com.devopslearning.web.i18n.I18NService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;


@SpringBootTest
@WebAppConfiguration
class DevopslearningApplicationTests {

    @Autowired
    private I18NService i18NService;

    @Test
    public void testMessgaeI18n() throws Exception{
        String expectedResult =  "Bootstrap starter template";
        String messageId = "index.main.callout";
        String actual_message = i18NService.getMessage(messageId);
        //Assertions.assertEquals(expectedResult, actual_message, "The expected message do not matches with actual messg");
        Assertions.assertEquals(expectedResult, actual_message, "The expected message do not matches with actual messg");
    }

}

package com.adrdev.starters.commons.logging.config;

import com.adrdev.starters.commons.logging.LoggingAspect;
import com.adrdev.starters.commons.logging.utils.LoggingService;
import com.adrdev.starters.commons.logging.utils.QueryTypeUtil;
import com.adrdev.starters.commons.logging.utils.RequestIdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAutoConfiguration {

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect(new RequestIdUtil(), new LoggingService(), new QueryTypeUtil());
    }
}

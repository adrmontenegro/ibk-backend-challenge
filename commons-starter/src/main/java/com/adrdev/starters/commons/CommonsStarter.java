package com.adrdev.starters.commons;

import com.adrdev.starters.commons.logging.config.LoggingAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@AutoConfigureAfter(LoggingAutoConfiguration.class)
@ComponentScan("com.adrdev.starters.commons")
public class CommonsStarter {

}

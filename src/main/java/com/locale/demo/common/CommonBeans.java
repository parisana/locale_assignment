package com.locale.demo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Parisana
 */
@Configuration
public class CommonBeans {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor(){
        final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}

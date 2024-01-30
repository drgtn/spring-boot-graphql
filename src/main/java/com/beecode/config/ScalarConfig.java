package com.beecode.config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class ScalarConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.NonNegativeInt);
    }

    @Bean
    public RuntimeWiringConfigurer date() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.Date);
    }

    @Bean
    public RuntimeWiringConfigurer dateTime() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.DateTime);
    }
}

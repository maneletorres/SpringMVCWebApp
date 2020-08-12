package com.maneletorres.springmvc.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.maneletorres.springmvc")
public class JpaIntegrationConfig {
}
